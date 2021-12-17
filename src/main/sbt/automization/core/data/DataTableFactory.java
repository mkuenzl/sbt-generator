package sbt.automization.core.data;


import sbt.automization.core.data.key.*;
import sbt.automization.view.popup.ErrorPopup;

import javax.xml.crypto.Data;
import java.util.*;

public final class DataTableFactory
{
	static List<Probe> probes = new ArrayList<>();
	static List<Sample> samples = new ArrayList<>();
	static List<Parameter> parameters = new ArrayList<>();
	
	private DataTableFactory()
	{
	}
	
	public static List<DataTable> getTables()
	{
		List<DataTable> tables = new ArrayList<>();
		tables.addAll(probes);
		tables.addAll(samples);
		tables.addAll(parameters);
		
		return tables;
	}
	
	public static List<Probe> getProbes()
	{
		return probes;
	}
	
	public static void initialize(List<Map<String, String>> csvTable)
	{
		createProbes(csvTable);
		
		createSamples(csvTable);
		
		createParameters(csvTable);
		
		addParametersToSamples();
		
		addSamplesToProbes();
	}
	
	public static void createProbes(List<Map<String, String>> csvTable)
	{
		probes = new ArrayList<>();
		createListOfProbes(csvTable);
	}
	
	public static void createListOfProbes(List<Map<String, String>> csvTable)
	{
		// PROBE.
		for (Map<String, String> csvRow : csvTable)
		{
			Probe probe = createProbeFrom(csvRow);
			if (!probe.isEmpty() && !isTableInList(probe))
			{
				probes.add(probe);
			}
		}
	}
	
	public static Probe createProbeFrom(Map<String, String> csvRow)
	{
		// PROBE.
		Map<String, String> informationMap = createMapBasedOnIdentifier(csvRow, "PROBE.");
		
		Probe probe = new Probe(informationMap);
		
		return probe;
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
	
	private static boolean compare(DataTable target, DataTable table)
	{
		Map<String, String> targetTable = target.getTable();
		Map<String, String> compareTable = table.getTable();
		
		return targetTable.equals(compareTable);
	}
	
	private static boolean isTableInList(DataTable target)
	{
		if (target instanceof Probe)
		{
			for (Probe probe : probes)
			{
				if (compare(target, probe))
				{
					return true;
				}
			}
		}
		
		if (target instanceof Sample)
		{
			for (Sample sample : samples)
			{
				if (compare(target, sample))
				{
					return true;
				}
			}
		}
		
		if (target instanceof Parameter)
		{
			for (Parameter parameter : parameters)
			{
				if (compare(target, parameter))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public static void createSamples(List<Map<String, String>> csvTable)
	{
		samples = new ArrayList<>();
		createListOfSamples(csvTable);
	}
	
	public static void createListOfSamples(List<Map<String, String>> csvTable)
	{
		// SAMPLE.
		for (Map<String, String> csvRow : csvTable)
		{
			Sample sample = createSampleFrom(csvRow);
			if (!sample.isEmpty() && !isTableInList(sample))
			{
				samples.add(sample);
			}
		}
	}
	
	public static Sample createSampleFrom(Map<String, String> csvRow)
	{
		// SAMPLE.
		Map<String, String> informationMap = createMapBasedOnIdentifier(csvRow, "SAMPLE.");
		
		Sample sample = new Sample(informationMap);
		
		return sample;
	}
	
	public static void createParameters(List<Map<String, String>> csvTable)
	{
		parameters = new ArrayList<>();
		createListOfParameters(csvTable);
	}
	
	public static void createListOfParameters(List<Map<String, String>> csvTable)
	{
		// PARAMETER.
		for (Map<String, String> csvRow : csvTable)
		{
			List<Parameter> parameterList = createParameterFrom(csvRow);
			for (Parameter parameter : parameterList)
			{
				if (!parameter.isEmpty() && !isTableInList(parameter))
				{
					parameters.add(parameter);
				}
			}
		}
	}
	
	private static List<Parameter> createParameterFrom(Map<String, String> csvRow)
	{
		List<Parameter> parameter = new ArrayList<>();
		
		parameter.add(createParameterFrom(csvRow, "PARAMETER.LP."));
		parameter.add(createParameterFrom(csvRow, "PARAMETER.CHEMISTRY."));
		parameter.add(createParameterFrom(csvRow, "PARAMETER.RUK."));
		
		return parameter;
	}
	
	public static Parameter createParameterFrom(Map<String, String> csvRow, String identifier)
	{
		// PARAMETER.LP.
		Map<String, String> informationMap = createMapBasedOnIdentifier(csvRow, identifier);
		
		Parameter parameter = new Parameter(informationMap);
		
		return parameter;
	}
	
	private static void addParametersToSamples()
	{
		for (Sample sample : samples)
		{
			for (Parameter parameter : parameters)
			{
				if (sample.isRelatedBy(SampleKey.CHEMISTRY_ID, ChemistryKey.ID, parameter))
				{
					sample.addParameter(parameter);
					parameter.addSample(sample);
				}
				if (sample.isRelatedBy(SampleKey.RUK_ID, RuKKey.ID, parameter))
				{
					sample.addParameter(parameter);
					parameter.addSample(sample);
				}
				if (sample.isRelatedBy(SampleKey.LP_ID, LpKey.ID, parameter))
				{
					sample.addParameter(parameter);
					parameter.addSample(sample);
				}
			}
		}
	}
	
	private static void addSamplesToProbes()
	{
		for (Probe probe : probes)
		{
			for (Sample sample : samples)
			{
				if (probe.isRelatedBy(ProbeKey.ID, SampleKey.PROBE_ID, sample))
				{
					probe.addSample(sample);
					sample.setProbe(probe);
				}
			}
			sortSamples(probe);
		}
	}
	
	private static void sortSamples(Probe table)
	{
		try
		{
			table.getSamples().sort(Comparator.comparing(a -> Integer.parseInt(a.get(SampleKey.NUMBER))));
		} catch (Exception e)
		{
			ErrorPopup.showMessage("Es fehlt eine Nummerierung der Samples. Demnach kann eine Sorierung nicht durchgeführt werden.");
		}
	}
	
	public static List<Sample> getSamples()
	{
		return samples;
	}
	
	public static List<Parameter> getParameters()
	{
		return parameters;
	}
	
}
