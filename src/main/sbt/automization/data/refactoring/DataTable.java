package sbt.automization.data.refactoring;

import sbt.automization.data.refactoring.references.Reference;

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
}
