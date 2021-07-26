package sbt.automization.data.refactoring;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.LayerSample;

import java.util.*;

public final class DataTableFactory
{
	private DataTableFactory() {}

	public static Project getProject()
	{
		return null;
	}

	public static List<Examination> getProbeList()
	{
		return null;
	}

	public static Examination getProbe()
	{
		return null;
	}

	public static Probe getSample()
	{
		return null;
	}

	public static Parameter getParameter()
	{
		return null;
	}

	/**
	 * Will create different exploration sites based on their ID values. Each map should contain an ERK_ID key. Maps
	 * with the same ID key are fused together into ExplorationSite objects.
	 *
	 * @param parsedExplorationSiteInformation expects a list of maps which contain data of exploration sites and layers
	 *                                         based on the parsed excel (database) template
	 * @return a list of exploration sites based on the provided information.
	 */
	public static List<ExplorationSite> createExplorationSites(List<Map<String, String>> parsedExplorationSiteInformation)
	{
		List<ExplorationSite> explorationSites = new ArrayList<>();

		Set<String> createdIds = new HashSet<>();

		for (Map<String, String> explorationSiteInformation : parsedExplorationSiteInformation)
		{
			String explorationSiteId = String.valueOf(explorationSiteInformation.get("ERK_ID"));

			if (! createdIds.contains(explorationSiteId))
			{
				createdIds.add(String.valueOf(explorationSiteId));
				ExplorationSite explorationSite = new ExplorationSite(explorationSiteInformation);

				LayerSample layerSample = createLayer(explorationSiteInformation);
				explorationSite.addLayer(layerSample);
				explorationSites.add(explorationSite);
			} else
			{
				for (ExplorationSite explorationSite : explorationSites)
				{
					if (explorationSite.getInformation("ERK_ID").equals(explorationSiteId))
					{
						LayerSample layerSample = createLayer(explorationSiteInformation);
						explorationSite.addLayer(layerSample);
					}
				}
			}
		}

		// sorts layers per exploration site based on their layer number
		for (ExplorationSite explorationSite : explorationSites)
		{
			explorationSite.sortLayers();
		}

		// sorts exploration sites based on their number
		Collections.sort(explorationSites);

		return explorationSites;
	}

	/**
	 * Expects a map which contains data about layers and chemistry and is provided by the function createExplorationSites.
	 * Uses the information of the keys SCHICHT_ ... and CHEMIE_ ...
	 * to construct a new layer object.
	 *
	 * @param layerInformationMap a map of Strings containing information about layers.
	 * @return a new layer object based on the layer information of the map.
	 */
	public static LayerSample createLayer(Map<String, String> layerInformationMap)
	{
		//EntrySets
		Map<String, String> tmpMap = new HashMap<>();

		for (String key : layerInformationMap.keySet())
		{
			if (key.contains("SCHICHT_") || key.contains("CHEMIE_"))
			{
				tmpMap.put(key, layerInformationMap.get(key));
			}
		}
		return new LayerSample(tmpMap);
	}

	@Deprecated
	private static ExplorationSite createExplorationSite(Map<String, String> dataRow)
	{
		ExplorationSite explorationSite = new ExplorationSite();

		return explorationSite;
	}

}
