package sbt.automization.data;

import java.util.*;

/**
 * Class to create data objects from parsed csv information
 */
public final class TableFactory
{
	private TableFactory(){}

	/**
	 * Will create different exploration sites based on their ID values. Each map should contain an ERK_ID key. Maps
	 * with the same ID key are fused together into ExplorationSite objects.
	 *
	 * @param parsedExplorationSiteInformation expects a list of maps which contain data of exploration sites and layers
	 *                                         based on the parsed excel (database) template
	 * @return a list of exploration sites based on the provided information.
	 */
	public static List<DataTableOld> createExplorationSites(List<Map<String, String>> parsedExplorationSiteInformation)
	{
		List<DataTableOld> dataTables = new ArrayList<>();

		Set<String> createdIds = new HashSet<>();

		for (Map<String, String> explorationSiteInformation : parsedExplorationSiteInformation)
		{
			String explorationSiteId = String.valueOf(explorationSiteInformation.get("ERK_ID"));

			if (! createdIds.contains(explorationSiteId))
			{
				createdIds.add(String.valueOf(explorationSiteId));
				DataTableOld dataTable = new DataTableOld(explorationSiteInformation);

				LayerSample layerSample = createLayer(explorationSiteInformation);
				dataTable.addLayer(layerSample);
				dataTables.add(dataTable);
			} else
			{
				for (DataTableOld dataTable : dataTables)
				{
					if (dataTable.getInformation("ERK_ID").equals(explorationSiteId))
					{
						LayerSample layerSample = createLayer(explorationSiteInformation);
						dataTable.addLayer(layerSample);
					}
				}
			}
		}

		// sorts layers per exploration site based on their layer number
		for (DataTableOld dataTable : dataTables)
		{
			dataTable.sortLayers();
		}

		// sorts exploration sites based on their number
		Collections.sort(dataTables);

		return dataTables;
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
	private static DataTableOld createExplorationSite(Map<String, String> dataRow)
	{
		DataTableOld dataTable = new DataTableOld();

		return dataTable;
	}


}
