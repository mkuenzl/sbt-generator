package sbt.automization.data.refactoring;

import sbt.automization.data.refactoring.references.Reference;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
	{ this.informationMap = new HashMap<>();}

	@Override
	public void add(String key, String value)
	{
		this.informationMap.put(key, value);
	}

	@Override
	public void add(Reference key, String value)
	{
		this.informationMap.put(key.getKey(), value);
	}

	@Override
	public String get(Reference key)
	{
		return this.informationMap.get(key.getKey());
	}

	@Override
	public String get(String key)
	{
		return this.informationMap.get(key);
	}

	@Override
	public String getAsString(Reference key)
	{
		String value = get(key);
		return value;
	}

	@Override
	public String getAsString(String key)
	{
		String value = get(key);
		return value;
	}

	@Override
	public Integer getAsInteger(Reference key)
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
	public Double getAsDouble(Reference key)
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
	public boolean containsReference(Reference key)
	{
		return informationMap.containsKey(key.getKey());
	}

	@Override
	public boolean containsReference(String key)
	{
		return informationMap.containsKey(key);
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
	public Object clone() throws CloneNotSupportedException
	{
		DataTable cloned = (DataTable) super.clone();
		return cloned;
	}

	public boolean isEmpty()
	{
		for (String value : informationMap.values())
		{
			if (!"".equals(value)) return false;
		}
		return true;
	}

	public boolean isRelatedBy(Reference source, DataTable target)
	{
		String sourceValue = get(source);

		if ("".equals(sourceValue)) return false;

		return target.contains(sourceValue);
	}

	public boolean contains(String value)
	{
		if ("".equals(value)) return false;

		return informationMap.containsValue(value);
	}

	public boolean containsValueFor(Reference reference)
	{
		String value = informationMap.get(reference.getKey());

		return !"".equals(value);
	}
}
