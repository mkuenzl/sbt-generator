package sbt.automization.data;

import sbt.automization.data.key.Key;

import java.util.List;
import java.util.Map;

public interface DataTable
{
	void add(String key, String value);

	void add(Key key, String value);

	String get(Key key);

	String get(String key);

	String getAsString(Key key);

	String getAsString(String key);

	Integer getAsInteger(Key key);

	Integer getAsInteger(String key);

	Double getAsDouble(Key key);

	Double getAsDouble(String key);

	Map<String, String> getTable();

	void setTable(Map<String, String> table);

	boolean containsReference(Key key);

	boolean containsReference(String key);

	boolean contains(String value);

	boolean isEmpty();

	boolean isRelatedBy(Key source, DataTable target);

	boolean containsValueFor(Key key);

	boolean hasSampleWith(final Key key, final String value);

	List<Sample> getSamplesBy(final Key key, final String[] values);

	List<Sample> getSamplesBy(final Key key, final String value);

	String getParameterValueBy(Key parameterID, Key valueID);

	Parameter getParameterBy(final Key key);
}
