package sbt.automization.data.refactoring;

import sbt.automization.data.refactoring.references.Probe;
import sbt.automization.data.refactoring.references.Sample;

import java.util.*;

public final class DataTableFactory
{
	static List<DataTable> probes = new ArrayList<>();
	static List<DataTable> samples = new ArrayList<>();
	static List<DataTable> parameters = new ArrayList<>();

	private DataTableFactory() {}

	public static List<DataTable> getProbes(List<Map<String, String>> csvTable)
	{
		initialize(csvTable);
		return probes;
	}

	public static List<DataTable> getSamples(List<Map<String, String>> csvTable)
	{
		initialize(csvTable);
		return samples;
	}

	public static List<DataTable> getParameters(List<Map<String, String>> csvTable)
	{
		initialize(csvTable);
		return parameters;
	}

	public static void initialize(List<Map<String, String>> csvTable)
	{
		createProbes(csvTable);

		createSamples(csvTable);

		createParameters(csvTable);

		addParametersToProbes();
		addParametersToSamples();

		addSamplesToProbes();
	}

	public static void createProbes(List<Map<String, String>> csvTable)
	{
		probes = new ArrayList<>();
		createListOfProbes(csvTable);
	}

	public static void createSamples(List<Map<String, String>> csvTable)
	{
		samples = new ArrayList<>();
		createListOfSamples(csvTable);
	}

	public static void createParameters(List<Map<String, String>> csvTable)
	{
		parameters = new ArrayList<>();
		createListOfParameters(csvTable);
	}

	private static void addParametersToSamples()
	{
		for (DataTable sample : samples)
		{
			sbt.automization.data.refactoring.Sample table = (sbt.automization.data.refactoring.Sample) sample;

			for (DataTable parameter : parameters)
			{
				if (table.isRelatedBy(Sample.CHEMISTRY_ID, parameter)) {
					table.addParameter((Parameter) parameter);
				}
				if (table.isRelatedBy(Sample.RUK_ID, parameter)) {
					table.addParameter((Parameter) parameter);
				}
			}
		}
	}

	private static void addParametersToProbes()
	{
		for (DataTable probe : probes)
		{
			sbt.automization.data.refactoring.Probe table = (sbt.automization.data.refactoring.Probe) probe;

			for (DataTable parameter : parameters)
			{
				if (table.isRelatedBy(Probe.LP_ID, parameter)) {
					table.addParameter((Parameter) parameter);
				}
			}
		}
	}

	private static void addSamplesToProbes()
	{
		for (DataTable probe : probes)
		{
			sbt.automization.data.refactoring.Probe table = (sbt.automization.data.refactoring.Probe) probe;

			for (DataTable sample : samples)
			{
				if (table.isRelatedBy(Probe.ID, sample)) {
					table.addSample((sbt.automization.data.refactoring.Sample) sample);
				}
			}
		}
	}

	public static void createListOfProbes(List<Map<String, String>> csvTable)
	{
		// PROBE.
		for (Map<String, String> csvRow : csvTable)
		{
			DataTable probe = createProbeFrom(csvRow);
			if (! probe.isEmpty() && ! isTableInList(probes, probe))
			{
				probes.add(probe);
			}
		}
	}

	public static void createListOfSamples(List<Map<String, String>> csvTable)
	{
		// SAMPLE.
		for (Map<String, String> csvRow : csvTable)
		{
			DataTable sample = createSampleFrom(csvRow);
			if (! sample.isEmpty() && ! isTableInList(samples, sample))
			{
				samples.add(sample);
			}
		}
	}

	public static void createListOfParameters(List<Map<String, String>> csvTable)
	{
		// PARAMETER.
		for (Map<String, String> csvRow : csvTable)
		{
			List<DataTable> parameter = createParameterFrom(csvRow);
			for (DataTable dataTable : parameter)
			{
				if (! dataTable.isEmpty() && ! isTableInList(parameters, dataTable))
				{
					parameters.add(dataTable);
				}
			}
		}
	}

	private static List<DataTable> createParameterFrom(Map<String, String> csvRow)
	{
		List<DataTable> parameter = new ArrayList<>();

		parameter.add(createParameterFrom(csvRow, "PARAMETER.LP."));
		parameter.add(createParameterFrom(csvRow,  "PARAMETER.CHEMISTRY."));
		parameter.add(createParameterFrom(csvRow, "PARAMETER.RUK."));

		return parameter;
	}

	public static sbt.automization.data.refactoring.Probe createProbeFrom(Map<String, String> csvRow)
	{
		// PROBE.
		Map<String, String> informationMap = createMapBasedOnIdentifier(csvRow, "PROBE.");

		sbt.automization.data.refactoring.Probe probe = new sbt.automization.data.refactoring.Probe(informationMap);

		return probe;
	}

	public static sbt.automization.data.refactoring.Sample createSampleFrom(Map<String, String> csvRow)
	{
		// SAMPLE.
		Map<String, String> informationMap = createMapBasedOnIdentifier(csvRow, "SAMPLE.");

		sbt.automization.data.refactoring.Sample sample = new sbt.automization.data.refactoring.Sample(informationMap);

		return sample;
	}


	public static Map<String, String> createMapBasedOnIdentifier(Map<String, String> source, String identifier)
	{
		Map<String, String> identifierMap = new HashMap<>();

		if (source == null) return identifierMap;

		for (String key : source.keySet())
		{
			if (key.startsWith(identifier))
			{
				identifierMap.put(key, source.get(key));
			}
		}
		return identifierMap;
	}

	public static Parameter createParameterFrom(Map<String, String> csvRow, String identifier)
	{
		// PARAMETER.LP.
		Map<String, String> informationMap = createMapBasedOnIdentifier(csvRow, identifier);

		Parameter parameter = new Parameter(informationMap);

		return parameter;
	}

	private static boolean isTableInList(List<DataTable> compareToList, DataTable target)
	{
		Map<String, String> targetTable = target.getTable();

		for (DataTable dataTable : compareToList)
		{
			Map<String, String> compareToTable = dataTable.getTable();

			if (targetTable.equals(compareToTable))
			{
				return true;
			}
		}

		return false;
	}

}
