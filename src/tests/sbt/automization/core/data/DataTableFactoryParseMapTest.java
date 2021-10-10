package sbt.automization.core.data;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DataTableFactoryParseMapTest
{
	@Test
	public void parseMapWithIdentifierTest()
	{
		Map<String, String> source = new HashMap<>()
		{{
			put("PROBE.ID", "ID");
			put("PROBE.SAMPLE", "SAMPLE");
			put("SAMPLE.ID", "ID");
			put("PROBE.LP.ID", "LP.ID");
			put("PARAMETER.CHEMISTRY.ID", "CHEMISTRY.ID");
		}};

		Map<String, String> target = DataTableFactory.createMapBasedOnIdentifier(source, "PROBE.");

		Map<String, String> expected = new HashMap<>()
		{{
			put("PROBE.ID", "ID");
			put("PROBE.SAMPLE", "SAMPLE");
			put("PROBE.LP.ID", "LP.ID");
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void parseEmptyMapWithIdentifierTest()
	{
		Map<String, String> source = new HashMap<>();

		Map<String, String> target = DataTableFactory.createMapBasedOnIdentifier(source, "PROBE.");

		Map<String, String> expected = new HashMap<>();

		Assert.assertEquals(expected, target);
	}

	@Test
	public void parseMapWithoutIdentifierTest()
	{
		Map<String, String> source = new HashMap<>()
		{{
			put("PROBE.ID", "ID");
			put("PROBE.SAMPLE", "SAMPLE");
			put("SAMPLE.ID", "ID");
			put("PROBE.LP.ID", "LP.ID");
			put("PARAMETER.CHEMISTRY.ID", "CHEMISTRY.ID");
		}};

		Map<String, String> target = DataTableFactory.createMapBasedOnIdentifier(source, "TEST.");

		Map<String, String> expected = new HashMap<>();

		Assert.assertEquals(expected, target);
	}

	@Test
	@Ignore
	public void parseNullWithIdentifierTest()
	{
		Map<String, String> source = null;

		Map<String, String> target = DataTableFactory.createMapBasedOnIdentifier(source, "TEST.");

		Map<String, String> expected = new HashMap<>();

		Assert.assertEquals(expected, target);
	}
}
