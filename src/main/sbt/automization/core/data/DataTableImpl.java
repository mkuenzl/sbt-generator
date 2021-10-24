package sbt.automization.core.data;

import sbt.automization.core.data.key.Key;

import java.io.Serializable;
import java.util.*;

public abstract class DataTableImpl implements DataTable, Comparable<DataTable>, Cloneable, Serializable
{
	protected Map<String, String> informationMap;
	
	/**
	 * Constructs a new DataTable based on a map.
	 *
	 * @param informationMap a map parsed from an excel template
	 */
	public DataTableImpl(Map<String, String> informationMap)
	{
		this.informationMap = informationMap;
	}
	
	/**
	 * Constructor
	 */
	public DataTableImpl()
	{
		this.informationMap = new HashMap<>();
	}
	
	@Override
	public void add(String key, String value)
	{
		this.informationMap.put(key, value);
	}
	
	@Override
	public void add(Key key, String value)
	{
		this.informationMap.put(key.getKey(), value);
	}
	
	@Override
	public String get(Key key)
	{
		return this.informationMap.get(key.getKey());
	}
	
	@Override
	public String get(String key)
	{
		return this.informationMap.get(key);
	}
	
	@Override
	public String getAsString(Key key)
	{
		return get(key);
	}
	
	@Override
	public String getAsString(String key)
	{
		return get(key);
	}
	
	@Override
	public Integer getAsInteger(Key key)
	{
		String value = get(key);
		if (isNumeric(value))
		{
			return Integer.parseInt(value);
		}
		return null;
	}
	
	@Override
	public Integer getAsInteger(String key)
	{
		String value = get(key);
		if (isNumeric(value))
		{
			return Integer.parseInt(value);
		}
		return null;
	}
	
	@Override
	public Double getAsDouble(Key key)
	{
		String value = get(key);
		if (isNumeric(value))
		{
			return Double.parseDouble(value);
		}
		return null;
	}
	
	@Override
	public Double getAsDouble(String key)
	{
		String value = get(key);
		if (isNumeric(value))
		{
			return Double.parseDouble(value);
		}
		return null;
	}
	
	@Override
	public Map<String, String> getTable()
	{
		return informationMap;
	}
	
	@Override
	public void setTable(Map<String, String> table)
	{
		this.informationMap = table;
	}
	
	@Override
	public boolean containsReference(Key key)
	{
		return informationMap.containsKey(key.getKey());
	}
	
	@Override
	public boolean containsReference(String key)
	{
		return informationMap.containsKey(key);
	}
	
	public boolean contains(String value)
	{
		if ("".equals(value)) return false;
		
		return informationMap.containsValue(value);
	}
	
	public boolean isEmpty()
	{
		for (String value : informationMap.values())
		{
			if (!"".equals(value)) return false;
		}
		return true;
	}
	
	public boolean isRelatedBy(Key sourceKey, Key targetKey, DataTable target)
	{
		String sourceValue = get(sourceKey);
		String targetValue = target.get(targetKey);
		
		if ("".equals(sourceValue) || "".equals(targetValue)) return false;
		
		return sourceValue.equals(targetValue);
	}
	
	public boolean containsValueFor(Key key)
	{
		String value = informationMap.get(key.getKey());
		
		if (value == null) return false;
		
		return !"".equals(value);
	}
	
	public boolean hasSampleWith(final Key key, final String value)
	{
		return false;
	}
	
	public List<Sample> getSamples()
	{
		return new ArrayList<>();
	}
	
	public List<Sample> getSamplesBy(final Key key, final String[] values)
	{
		return new ArrayList<>();
	}
	
	public List<Sample> getSamplesBy(final Key key, final String value)
	{
		return new ArrayList<>();
	}
	
	public String getParameterValueBy(Key parameterID, Key valueID)
	{
		return "";
	}
	
	public Parameter getParameterBy(final Key key)
	{
		return new Parameter();
	}
	
	private boolean isNumeric(String str)
	{
		if (str == null)
		{
			return false;
		}
		try
		{
			double d = Double.parseDouble(str);
		} catch (NumberFormatException exception)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Method used for testing purposes in reality every layer should be unique.
	 *
	 * @param obj an Object to compare to
	 * @return a boolean if equal or not
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		
		if (obj.getClass() != this.getClass())
		{
			return false;
		}
		
		final DataTable otherTable = (DataTable) obj;
		
		return Objects.equals(this.getTable(), otherTable.getTable());
	}
	
	/**
	 * Used to create multiple objects of the same table. Necessary for PN Template.
	 *
	 * @return a new object with the same information
	 * @throws CloneNotSupportedException only if there was a problem creating another map
	 */
	@Override
	public DataTable clone() throws CloneNotSupportedException
	{
		DataTable cloned = (DataTable) super.clone();
		
		Map<String, String> clonedMap = new HashMap<>(this.informationMap);
		
		cloned.setTable(clonedMap);
		
		return cloned;
	}
}
