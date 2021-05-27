package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.ErkundungsstellenTestFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtilTest
{
	@Test
	public void exportCSVTemplate() throws IOException
	{
		Util.exportFile("/sbt-excel-template.xlsx");
	}

	@Test
	public void partitioningTest()
	{
		List<ExplorationSite> explorationSites = new ArrayList<>();
		ErkundungsstellenTestFactory erkundungsstellenTestFactory = new ErkundungsstellenTestFactory();
		for (int i = 0 ; i < 100 ; i++)
		{
			explorationSites.add(erkundungsstellenTestFactory.getTestErkundungsstelle1());
		}

		Collection<List<ExplorationSite>> partitionBasedOnSize = Util.separateBasedOnSize(explorationSites, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 20);
	}

	@Test
	public void partitioningLessThenExpectedTest()
	{
		List<ExplorationSite> explorationSites = new ArrayList<>();
		ErkundungsstellenTestFactory erkundungsstellenTestFactory = new ErkundungsstellenTestFactory();
		for (int i = 0 ; i < 3 ; i++)
		{
			explorationSites.add(erkundungsstellenTestFactory.getTestErkundungsstelle1());
		}

		Collection<List<ExplorationSite>> partitionBasedOnSize = Util.separateBasedOnSize(explorationSites, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 1);
	}

	@Test
	public void partitioningIrregularAmountTest()
	{
		List<ExplorationSite> explorationSites = new ArrayList<>();
		ErkundungsstellenTestFactory erkundungsstellenTestFactory = new ErkundungsstellenTestFactory();
		for (int i = 0 ; i < 17 ; i++)
		{
			explorationSites.add(erkundungsstellenTestFactory.getTestErkundungsstelle1());
		}

		Collection<List<ExplorationSite>> partitionBasedOnSize = Util.separateBasedOnSize(explorationSites, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 4);
	}

	@Test
	public void partitioningNullTest()
	{
		List<ExplorationSite> explorationSites = new ArrayList<>();

		Collection<List<ExplorationSite>> partitionBasedOnSize = Util.separateBasedOnSize(explorationSites, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 0);
	}
}
