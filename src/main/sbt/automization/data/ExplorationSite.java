package sbt.automization.data;

import java.io.Serializable;
import java.util.*;


/**
 * Class which contains all information about an exploration site.
 */
public final class ExplorationSite implements Comparable<ExplorationSite>, Serializable
{
	private final List<Layer> layerList = new ArrayList<>();
	private Map<String, String> informationMap;

	/**
	 * Constructs a new ExplorationSite based on a map. From this map each key containing ERK_ is accepted as information
	 * about this exploration site.
	 *
	 * @param explorationSiteInformation a map parsed from an excel template
	 */
	public ExplorationSite(Map<String, String> explorationSiteInformation)
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
	public ExplorationSite()
	{ }

	/**
	 * Is used to prepare the order of exploration sites shown in templates
	 *
	 * @param explorationSite expects a explorationSite with a data map, containing a valid ERK_NUMMER
	 * @return a compare to key for Collections.sort
	 */
	@Override
	public int compareTo(final ExplorationSite explorationSite)
	{
		String thisOrder = this.getInformation("ERK_NUMMER");
		String otherOrder = explorationSite.getInformation("ERK_NUMMER");

		if (thisOrder == null || otherOrder == null)
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
	public String getInformation(InformationTag tag)
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

		for (Layer layer : layerList)
		{
			String layerThickness = layer.getInformation("SCHICHT_DICKE").replace(",", ".");
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

		for (Layer layer : getLayersWithOutcrop(outcrop))
		{
			String layerThickness = layer.getInformation("SCHICHT_DICKE").replace(",", ".");
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
	public List<Layer> getLayersWithOutcrop(final String outcrop)
	{
		List<Layer> layers = new ArrayList<>();
		for (Layer layer : layerList)
		{
			if (outcrop.equals(layer.getInformation("SCHICHT_AUFSCHLUSS")))
			{
				layers.add(layer);
			}
		}
		return layers;
	}

	/**
	 * Sorts all layers based on their SCHICHT_NR
	 */
	public void sortLayers()
	{
		Collections.sort(layerList);
	}

	public void addLayer(Layer layer)
	{
		layerList.add(layer);
	}

	public List<Layer> getLayers()
	{
		return layerList;
	}

	public Map<String, String> getInformationMap()
	{
		return informationMap;
	}

	public ExplorationSite setInformationMap(Map<String, String> informationMap)
	{
		this.informationMap = informationMap;
		return this;
	}
}
