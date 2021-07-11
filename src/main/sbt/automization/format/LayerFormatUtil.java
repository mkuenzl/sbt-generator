package sbt.automization.format;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.data.Layer;

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
	public static List<Layer> combineLayers(final ExplorationSite explorationSite, final String outcrop, final String tag)
	{
		List<Layer> layersWithOutcrop = explorationSite.getLayersWithOutcrop(outcrop);

		return combineLayers(layersWithOutcrop, tag);
	}

	/**
	 * Method used to combine consecutive identical layers in a list.
	 *
	 * @param layers a List of Layers
	 * @param tag a String representing the information to compare
	 * @return an updated list of layers with all possible layers combined
	 */
	public static List<Layer> combineLayers(final List<Layer> layers, final String tag)
	{
		List<Layer> updatedLayers = new ArrayList<>();

		for (int i = 0 ; i < layers.size() ; i++)
		{
			Layer layer = layers.get(i);
			Layer finalLayer = layer;

			int next = i + 1;
			while (next < layers.size())
			{ // Checks how many consecutive layers have the same value as tag
				Layer nextLayer = layers.get(next);
				Layer combinedLayer = combineLayers(layer, nextLayer, tag);

				if (combinedLayer != null)
				{
					finalLayer = combinedLayer;
				} else
				{
					break;
				}
				// Sets i on the position of the last consecutive element
				i = next;
				next++;
			}

			updatedLayers.add(finalLayer);
		}
		return updatedLayers;
	}

	/**
	 * Method used for combination of the layers based on a specified tag.
	 *
	 * @param firstLayer  a Layer Object
	 * @param secondLayer a Layer Object
	 * @param tag         a String representing a column of the excel template
	 * @return a Layer Object with the tag, the depth start from the first layer and end from the second layer.
	 */
	public static Layer combineLayers(final Layer firstLayer, final Layer secondLayer, final String tag)
	{
		if (firstLayer == null) return secondLayer;
		if (secondLayer == null) return firstLayer;
		if (tag == null) return null;

		if (!firstLayer.getInformation(tag).equals(secondLayer.getInformation(tag)))
			return null;
		else
		{
			Layer layer = new Layer(new HashMap<>()
			{{
				put(tag, firstLayer.getInformation(tag));
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(),
						firstLayer.getInformation(InformationTag.LAYER_DEPTH_START));
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(),
						secondLayer.getInformation(InformationTag.LAYER_DEPTH_END));
			}});

			return layer;
		}
	}
}