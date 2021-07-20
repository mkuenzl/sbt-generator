package sbt.automization.format;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.data.LayerSample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to provide static methods for layer manipulation.
 */
public final class LayerFormatUtil
{
	private LayerFormatUtil() {}

	/**
	 * Method used to combine layers of an outcrop from an exploration site based on a specific tag
	 *
	 * @param explorationSite a ExplorationSite Object
	 * @param outcrop a String defining an outcrop
	 * @param tag a String specifying an information tag
	 * @return an updated list of layers with all possible layers combined
	 */
	public static List<LayerSample> combineLayers(final ExplorationSite explorationSite, final String outcrop, final String tag)
	{
		List<LayerSample> layersWithOutcrop = explorationSite.getLayersWithOutcrop(outcrop);

		return combineLayers(layersWithOutcrop, tag);
	}

	/**
	 * Method used to combine consecutive identical layers in a list.
	 *
	 * @param layerSamples a List of Layers
	 * @param tag a String representing the information to compare
	 * @return an updated list of layers with all possible layers combined
	 */
	public static List<LayerSample> combineLayers(final List<LayerSample> layerSamples, final String tag)
	{
		List<LayerSample> updatedLayerSamples = new ArrayList<>();

		for (int i = 0; i < layerSamples.size() ; i++)
		{
			LayerSample layerSample = layerSamples.get(i);
			LayerSample finalLayerSample = layerSample;

			int next = i + 1;
			while (next < layerSamples.size())
			{ // Checks how many consecutive layers have the same value as tag
				LayerSample nextLayerSample = layerSamples.get(next);
				LayerSample combinedLayerSample = combineLayers(layerSample, nextLayerSample, tag);

				if (combinedLayerSample != null)
				{
					finalLayerSample = combinedLayerSample;
				} else
				{
					break;
				}
				// Sets i on the position of the last consecutive element
				i = next;
				next++;
			}

			updatedLayerSamples.add(finalLayerSample);
		}
		return updatedLayerSamples;
	}

	/**
	 * Method used for combination of the layers based on a specified tag.
	 *
	 * @param firstLayerSample  a Layer Object
	 * @param secondLayerSample a Layer Object
	 * @param tag         a String representing a column of the excel template
	 * @return a Layer Object with the tag, the depth start from the first layer and end from the second layer.
	 */
	public static LayerSample combineLayers(final LayerSample firstLayerSample, final LayerSample secondLayerSample, final String tag)
	{
		if (firstLayerSample == null) return secondLayerSample;
		if (secondLayerSample == null) return firstLayerSample;
		if (tag == null) return null;

		if (!firstLayerSample.getInformation(tag).equals(secondLayerSample.getInformation(tag)))
			return null;
		else
		{
			LayerSample layerSample = new LayerSample(new HashMap<>()
			{{
				put(tag, firstLayerSample.getInformation(tag));
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(),
						firstLayerSample.getInformation(InformationTag.LAYER_DEPTH_START));
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(),
						secondLayerSample.getInformation(InformationTag.LAYER_DEPTH_END));
			}});

			return layerSample;
		}
	}
}