package sbt.automization.format;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.data.Layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class LayerFormatUtil
{
	private LayerFormatUtil() {}

	public static List<Layer> combineLayers(final ExplorationSite explorationSite, final String outcrop, final String tag)
	{
		List<Layer> layersWithOutcrop = explorationSite.getLayersWithOutcrop(outcrop);

		return combineLayers(layersWithOutcrop, tag);
	}

	public static List<Layer> combineLayers(final List<Layer> layers, final String tag)
	{
		List<Layer> updatedLayers = new ArrayList<>();

		for (int i = 0 ; i < layers.size() ; i++)
		{
			int next = i + 1;
			if (next < layers.size())
			{
				Layer firstLayer = layers.get(i);
				Layer secondLayer = layers.get(next);

				Layer combinedLayer = combineLayers(firstLayer, secondLayer, tag);

				if (combinedLayer != null)
				{
					updatedLayers.add(combinedLayer);
				} else
				{
					updatedLayers.add(firstLayer);
				}
			}

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

		if (firstLayer.getInformation(tag) != secondLayer.getInformation(tag))
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