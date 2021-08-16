package sbt.automization.data;

import java.io.Serializable;
import java.util.*;


/**
 * Class which contains all information about an exploration site.
 */
public final class DataTableOld implements Comparable<DataTableOld>, Serializable
{
	private final List<LayerSample> layerSampleList = new ArrayList<>();
	private Map<String, String> informationMap;

	/**
	 * Constructs a new ExplorationSite based on a map. From this map each key containing ERK_ is accepted as information
	 * about this exploration site.
	 *
	 * @param explorationSiteInformation a map parsed from an excel template
	 */
	public DataTableOld(Map<String, String> explorationSiteInformation)
	{

		this.informationMap = new HashMap<>();

		for (String key : explorationSiteInformation.keySet())
		{
			if (key.contains("ERK_")) informationMap.put(key, explorationSiteInformation.get(key));
		}
	}

	/**
	 * Constructor
	 */
	public DataTableOld()
	{ }

	/**
	 * Is used to prepare the order of exploration sites shown in templates
	 *
	 * @param dataTable expects a explorationSite with a data map, containing a valid ERK_NUMMER
	 * @return a compare to key for Collections.sort
	 */
	@Override
	public int compareTo(final DataTableOld dataTable)
	{
		String thisOrder = this.getInformation(ReferenceKey.SITE_NUMBER);
		String otherOrder = dataTable.getInformation(ReferenceKey.SITE_NUMBER);

		if (thisOrder == null || otherOrder == null || "-".equals(thisOrder) || "-".equals(otherOrder))
		{
			//something went wrong order will not change
			return 0;
		}

		int thisOrderValue = Integer.parseInt(thisOrder);
		int otherOrderValue = Integer.parseInt(otherOrder);

		// value < 0 means order is smaller value > 0 means order is greater
		return thisOrderValue > otherOrderValue ? 1 : - 1;

	}

	/**
	 * Used to receive information from a layer.
	 *
	 * @param tag an Enum which contains EX_SITE. Represents a column in the excel template.
	 * @return the value based on provided tag
	 */
	public String getInformation(ReferenceKey tag)
	{
		return getInformation(tag.getIdentifier());
	}

	/**
	 * Used to receive information from an exploration site.
	 *
	 * @param key a String which contains ERK_. Represents a column in the excel template.
	 * @return the value based on provided key
	 */
	public String getInformation(String key)
	{
		if (informationMap == null)
		{
			return "Missing Values";
		}
		String s = informationMap.get(key);
		if (s == null || s.equals("")) return "-";
		return s;
	}

	/**
	 * Calculates the overall thickness of all layers.
	 * @return thickness as Double value
	 */
	public Double getThickness()
	{
		double thickness = 0;

		for (LayerSample layerSample : layerSampleList)
		{
			String layerThickness = layerSample.getInformation(ReferenceKey.LAYER_THICKNESS).replace(",", ".");
			thickness += Double.parseDouble(layerThickness);
		}

		return thickness;
	}

	/**
	 * Calculates thickness of all layers for a specified outcrop.
	 * @param outcrop represents a type of layers like GOB, TOB, TMHB...
	 * @return thickness as Double value
	 */
	public Double getOutcropThickness(final String outcrop)
	{
		double thickness = 0;

		for (LayerSample layerSample : getLayersWithOutcrop(outcrop))
		{
			String layerThickness = layerSample.getInformation(ReferenceKey.LAYER_THICKNESS).replace(",", ".");
			thickness += Double.parseDouble(layerThickness);
		}

		return thickness;
	}

	/**
	 * Used to get specific layers based on outcrop
	 *
	 * @param outcrop expects a valid outcrop identifier
	 * @return a list of layers associated with the specified outcrop
	 */
	public List<LayerSample> getLayersWithOutcrop(final String outcrop)
	{
		List<LayerSample> layerSamples = new ArrayList<>();
		for (LayerSample layerSample : layerSampleList)
		{
			if (outcrop.equals(layerSample.getInformation(ReferenceKey.LAYER_OUTCROP)))
			{
				layerSamples.add(layerSample);
			}
		}
		return layerSamples;
	}

	/**
	 * Sorts all layers based on their SCHICHT_NR
	 */
	public void sortLayers()
	{
		Collections.sort(layerSampleList);
	}

	public void addLayer(LayerSample layerSample)
	{
		layerSampleList.add(layerSample);
	}

	public List<LayerSample> getLayers()
	{
		return layerSampleList;
	}

	public Map<String, String> getInformationMap()
	{
		return informationMap;
	}

	public DataTableOld setInformationMap(Map<String, String> informationMap)
	{
		this.informationMap = informationMap;
		return this;
	}
}
