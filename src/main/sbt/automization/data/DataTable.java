package sbt.automization.data;

import sbt.automization.data.references.Reference;

import java.util.List;
import java.util.Map;

public interface DataTable
{
	void add(String key, String value);

	void add(Reference key, String value);

	String get(Reference key);

	String get(String key);

	String getAsString(Reference key);

	String getAsString(String key);

	Integer getAsInteger(Reference key);

	Integer getAsInteger(String key);

	Double getAsDouble(Reference key);

	Double getAsDouble(String key);

	Map<String, String> getTable();

	void setTable(Map<String, String> table);

	boolean containsReference(Reference key);

	boolean containsReference(String key);

	boolean contains(String value);

	boolean isEmpty();

	boolean isRelatedBy(Reference source, DataTable target);

	boolean containsValueFor(Reference reference);

	boolean hasSampleWith(final Reference reference, final String value);

	List<Sample> getSamplesBy(final Reference reference, final String[] values);

	List<Sample> getSamplesBy(final Reference reference, final String value);

	String getParameterValueBy(Reference parameterID, Reference valueID);

	Parameter getParameterBy(final Reference reference);
}
