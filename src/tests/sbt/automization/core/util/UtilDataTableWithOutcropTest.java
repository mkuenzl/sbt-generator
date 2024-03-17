package sbt.automization.core.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UtilDataTableWithOutcropTest
{
	@Test
	public void emptyList()
	{
		List<DataTable> sitesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(Arrays.asList(), Outcrop.UG);

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void explorationSiteWithoutLayer()
	{
		DataTable dataTable = new Probe();

		List<DataTable> sitesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(Arrays.asList(dataTable), Outcrop.UG);

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void explorationSiteWithoutOutcrop()
	{
		Probe dataTable = new Probe();
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sitesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(Arrays.asList(dataTable), Outcrop.UG);

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void explorationSiteWithOutcrop()
	{
		Probe dataTable = new Probe();
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTable.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sitesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(Arrays.asList(dataTable), Outcrop.TOB);

		Assert.assertEquals(1, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void multipleExplorationSitesWithoutOutcropTestOne()
	{
		Probe dataTableOne = new Probe();
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableFour = new Probe();
		dataTableFour.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sites = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree, dataTableFour);
		List<DataTable> sitesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(sites, Outcrop.TOB);

		Assert.assertEquals(2, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void multipleExplorationSitesWithoutOutcropTestTwo()
	{
		Probe dataTableOne = new Probe();
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableFour = new Probe();
		dataTableFour.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sites = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree, dataTableFour);
		List<DataTable> sitesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(sites, Outcrop.GOB);

		Assert.assertEquals(3, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void multipleExplorationSitesWithoutOutcropTestThree()
	{
		Probe dataTableOne = new Probe();
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableFour = new Probe();
		dataTableFour.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sites = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree, dataTableFour);
		List<DataTable> sitesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(sites, Outcrop.GAP);

		Assert.assertEquals(4, sitesWhichIncludeOutcrop.size());
	}

	@Test
	public void multipleExplorationSitesWithOutcrop()
	{
		Probe dataTableOne = new Probe();
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableOne.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTableTwo.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), Outcrop.GOB.toString());
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "TOB");
		}}));
		dataTableThree.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		Probe dataTableFour = new Probe();
		dataTableFour.addSample(new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "FUGE");
		}}));

		List<DataTable> sites = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree, dataTableFour);
		List<DataTable> sitesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(sites, Outcrop.UG);

		Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
	}
}
