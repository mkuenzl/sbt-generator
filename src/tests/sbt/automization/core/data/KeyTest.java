package sbt.automization.core.data;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.core.data.key.*;
import sbt.automization.core.parser.ExcelParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyTest
{
	
	static List<String> header = new ArrayList<>();
	
	@BeforeClass
	public static void initializeHeader()
	{
		File inputFile = new File("tests-resources/input/excel/excel-template-test.xlsx");
		
		ExcelParser parser = new ExcelParser();
		parser.setSheetName("Daten");
		header = parser.retrieveHeader(inputFile);
	}
	
	@Test
	public void sampleReferenceExistInHeaderTest()
	{
		List<String> missingReferences = new ArrayList<>();
		
		SampleKey[] sampleKeys = SampleKey.values();
		for (SampleKey reference : sampleKeys)
		{
			String key = reference.getKey();
			if (!header.contains(key))
			{
				missingReferences.add(key);
			}
		}
		
		Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
	}
	
	@Test
	public void probeReferenceExistInHeaderTest()
	{
		List<String> missingReferences = new ArrayList<>();
		
		ProbeKey[] probeKeys = ProbeKey.values();
		for (ProbeKey reference : probeKeys)
		{
			String key = reference.getKey();
			if (!header.contains(key))
			{
				missingReferences.add(key);
			}
		}
		
		Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
	}
	
	@Test
	public void parameterChemistryReferenceExistInHeaderTest()
	{
		List<String> missingReferences = new ArrayList<>();
		
		ChemistryKey[] chemistryKey = ChemistryKey.values();
		for (ChemistryKey reference : chemistryKey)
		{
			String key = reference.getKey();
			if (!header.contains(key))
			{
				missingReferences.add(key);
			}
		}
		
		Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
	}
	
	@Test
	public void parameterRUKReferenceExistInHeaderTest()
	{
		List<String> missingReferences = new ArrayList<>();
		
		RuKKey[] ruKKey = RuKKey.values();
		for (RuKKey reference : ruKKey)
		{
			String key = reference.getKey();
			if (!header.contains(key))
			{
				missingReferences.add(key);
			}
		}
		
		Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
	}
	
	@Test
	public void parameterLPReferenceExistInHeaderTest()
	{
		List<String> missingReferences = new ArrayList<>();
		
		LpKey[] loadPlate = LpKey.values();
		for (LpKey reference : loadPlate)
		{
			String key = reference.getKey();
			if (!header.contains(key))
			{
				missingReferences.add(key);
			}
		}
		
		Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
	}
	
	@Test
	public void missingHeaderInReferenceTest()
	{
		List<String> missingReferences = new ArrayList<>();
		
		List<String> keyList =
				Stream.of(Arrays.asList(LpKey.values()),
                                Arrays.asList(RuKKey.values()),
                                Arrays.asList(ChemistryKey.values()),
                                Arrays.asList(SampleKey.values()),
                                Arrays.asList(ProbeKey.values()))
                        .flatMap(Collection::stream)
                        .map(Key::getKey)
                        .collect(Collectors.toList());
		
		for (String reference : header)
		{
			if (!keyList.contains(reference))
			{
				missingReferences.add(reference);
			}
		}
		
		Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
	}
}
