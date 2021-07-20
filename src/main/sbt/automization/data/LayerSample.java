package sbt.automization.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class which contains all information about a layer.
 */
public final class LayerSample implements ISample, Comparable<LayerSample>, Cloneable, Serializable
{
	private Map<String, String> informationMap = new HashMap<>();

	/**
	 * Constructs a new Layer based on a map.
	 *
	 * @param informationMap a map parsed from an excel template
	 */
	public LayerSample(Map<String, String> informationMap)
	{
		this.informationMap = informationMap;
	}

	/**
	 * Constructor
	 */
	public LayerSample()
	{ }

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
	 * @param layerSample a layer to compare
	 * @return Positive - layers are in wrong order. Negative - layers are in correct order
	 */
	@Override
	public int compareTo(final LayerSample layerSample)
	{
		int s1 = Integer.parseInt(this.getInformation(InformationTag.LAYER_NUMBER));
		int s2 = Integer.parseInt(layerSample.getInformation(InformationTag.LAYER_NUMBER));
		return s1 - s2;
	}

	/**
	 * Used to receive information from a layer.
	 *
	 * @param tag an Enum which contains LAYER or CHEMISTRY. Represents a column in the excel template.
	 * @return the value based on provided tag
	 */
	public String getInformation(InformationTag tag)
	{
		return getInformation(tag.getIdentifier());
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
		LayerSample cloned = (LayerSample) super.clone();
		return cloned;
	}

	/**
	 * Method used for testing purposes in reality every layer should be unique.
	 *
	 * @param obj an Object to compare to
	 * @return a boolean if equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		final LayerSample other = (LayerSample) obj;
		if (! Objects.equals(this.informationMap, other.informationMap)) {
			return false;
		}

		return true;
	}
}
