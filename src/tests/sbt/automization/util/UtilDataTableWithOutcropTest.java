package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.RefSample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UtilDataTableWithOutcropTest
{
	@Test
	public void emptyList()
	{
		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(Arrays.asList(), "UG");

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void explorationSiteWithoutLayer()
	{
		DataTable dataTable = new Probe();

		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(Arrays.asList(dataTable), "UG");

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void explorationSiteWithoutOutcrop()
	{
		Probe dataTable = new Probe();
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(Arrays.asList(dataTable), "UG");

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void explorationSiteWithOutcrop()
	{
		Probe dataTable = new Probe();
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(Arrays.asList(dataTable), "TOB");

		Assert.assertEquals(1, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void explorationSiteWithRandomString()
	{
		Probe dataTable = new Probe();
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(Arrays.asList(dataTable), "DUBBABU");

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void multipleExplorationSitesWithoutOutcropTestOne()
	{
		Probe dataTableOne = new Probe();
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableFour = new Probe();
		dataTableFour.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sites = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree, dataTableFour);
		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(sites, "TOB");

		Assert.assertEquals(2, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void multipleExplorationSitesWithoutOutcropTestTwo()
	{
		Probe dataTableOne = new Probe();
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableFour = new Probe();
		dataTableFour.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sites = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree, dataTableFour);
		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(sites, "GOB");

		Assert.assertEquals(3, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void multipleExplorationSitesWithoutOutcropTestThree()
	{
		Probe dataTableOne = new Probe();
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableFour = new Probe();
		dataTableFour.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sites = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree, dataTableFour);
		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(sites, "FUGE");

		Assert.assertEquals(4, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void multipleExplorationSitesWithOutcrop()
	{
		Probe dataTableOne = new Probe();
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "GOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "TOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableFour = new Probe();
		dataTableFour.addSample(new Sample(new HashMap<>()
		{{
			put(RefSample.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sites = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree, dataTableFour);
		List<DataTable> sitesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(sites, "UG");

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}
}
