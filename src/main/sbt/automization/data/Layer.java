package sbt.automization.data;

import java.io.Serializable;
import java.util.Map;

/**
 * Class which contains all information about a layer.
 */
public final class Layer implements Comparable<Layer>, Cloneable, Serializable
{
	private Map<String, String> informationMap;

	/**
	 * Constructs a new Layer based on a map.
	 *
	 * @param informationMap a map parsed from an excel template
	 */
	public Layer(Map<String, String> informationMap)
	{
		this.informationMap = informationMap;
	}

	public Layer()
	{

	}

	/**
	 * Used to add information about a layer. Each information is put into a map.
	 *
	 * @param key   a String that represents the name of the information. Should contain SCHICHT_ or CHEMIE_
	 * @param value a String value of the information
	 */
	public void addInformation(String key, String value)
	{
		this.informationMap.put(key, value);
	}

	/**
	 * Compare this layer to another layer based on their SCHICHT_NR. Necessary for sorting layers in an exploration site.
	 *
	 * @param layer a layer to compare
	 * @return Positive - layers are in wrong order. Negative - layers are in correct order
	 */
	@Override
	public int compareTo(final Layer layer)
	{
		int s1 = Integer.parseInt(this.getInformation("SCHICHT_NR"));
		int s2 = Integer.parseInt(layer.getInformation("SCHICHT_NR"));
		return s1 - s2;
	}

	/**
	 * Used to receive information from a layer.
	 *
	 * @param key a String which contains SCHICHT_ or CHEMIE_. Represents a column in the excel template.
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

	public Map<String, String> getInformationMap()
	{
		return informationMap;
	}

	/**
	 * Used to create multiple objects of the same layer. Necessary for PN Template.
	 *
	 * @return a new object with the same information
	 * @throws CloneNotSupportedException only if there was a problem creating another map
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		Layer cloned = (Layer) super.clone();
		return cloned;
	}
}
