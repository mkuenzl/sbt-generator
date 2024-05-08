package sbt.automization.core.data;

import sbt.automization.core.data.key.Key;

import java.util.List;
import java.util.Map;

public interface DataTable
{
	void put(Key key, String value);
	String get(Key key);
	Integer getAsInteger(Key key);
	Double getAsDouble(Key key);
	Map<String, String> getTable();
	void setTable(Map<String, String> table);
	boolean containsReference(Key key);
	
	boolean contains(String value);
	
	boolean isEmpty();
	
	boolean isRelatedBy(Key sourceKey, Key targetKey, DataTable target);
	
	boolean containsValueFor(Key key);
	
	boolean hasSampleWith(final Key key, final String value);
	
	List<Sample> getSamples();
	
	List<Sample> getSamplesBy(final Key key, final String[] values);
	
	List<Sample> getSamplesBy(final Key key, final String value);
	
	String getParameterValueBy(Key parameterID, Key valueID);
}
