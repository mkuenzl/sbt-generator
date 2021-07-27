package sbt.automization.data.refactoring;

import java.util.*;

public final class DataTableFactory
{
	private DataTableFactory() {}

	public static Collection<DataTable> createListOfProbes(List<Map<String, String>> parsedCSVRowList)
	{
		// PROBE.
		Collection<DataTable> probes = new ArrayList<>();

		for (Map<String, String> csvMap : parsedCSVRowList)
		{
			DataTable probe = createProbeFrom(csvMap);
			probes.add(probe);
		}

		return probes;
	}

	public static DataTable createProbeFrom(Map<String, String> csvMap)
	{
		// PROBE.
		Map<String, String> informationMap = parseMapBasedOnIdentifier(csvMap, "PROBE.");

		Probe probe = new Probe(informationMap);

		return probe;
	}

	public static Map<String, String> parseMapBasedOnIdentifier(Map<String, String> source, String identifier)
	{
		Map<String, String> identifierMap = new HashMap<>();

		for (String key : source.keySet())
		{
			if (key.contains(identifier))
			{
				identifierMap.put(key, source.get(key));
			}
		}
		return identifierMap;
	}

	public static List<DataTable> createListOfSamplesFrom(List<Map<String, String>> parsedCSVRowList)
	{
		// SAMPLE. with same PROBE.ID

		return null;
	}

	public static DataTable createSampleFrom(Map<String, String> csvMap)
	{
		// SAMPLE.
		Map<String, String> informationMap = parseMapBasedOnIdentifier(csvMap, "SAMPLE.");

		Sample sample = new Sample(informationMap);

		return sample;
	}

	public static List<DataTable> createListOfParameterFrom(List<Map<String, String>> parsedCSVRowList)
	{
		// PARAMETER.RUK. / PARAMETER.LP. / PARAMETER.CHEMISTRY.

		return null;
	}

	public static DataTable createLpParameterFrom(Map<String, String> csvMap)
	{
		// PARAMETER.LP.
		Map<String, String> informationMap = parseMapBasedOnIdentifier(csvMap, "PARAMETER.LP.");

		Parameter parameter = new Parameter(informationMap);

		return parameter;
	}

	public static DataTable createChemistryParameterFrom(Map<String, String> csvMap)
	{
		// PARAMETER.CHEMISTRY.
		Map<String, String> informationMap = parseMapBasedOnIdentifier(csvMap, "PARAMETER.CHEMISTRY.");

		Parameter parameter = new Parameter(informationMap);

		return parameter;
	}

	public static DataTable createRuKParameterFrom(Map<String, String> csvMap)
	{
		// PARAMETER.RUK.
		Map<String, String> informationMap = parseMapBasedOnIdentifier(csvMap, "PARAMETER.RUK.");

		Parameter parameter = new Parameter(informationMap);

		return parameter;
	}

}
