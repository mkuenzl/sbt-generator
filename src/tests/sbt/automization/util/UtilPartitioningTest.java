package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.DataTableOld;
import sbt.automization.templates.ExplorationSiteFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO remove getTestErkundungsstelle1() implement use of serialization
 */
public class UtilPartitioningTest
{
	@Test
	public void partitioningTest()
	{
		List<DataTableOld> dataTables = new ArrayList<>();
		ExplorationSiteFactory explorationSiteFactory = new ExplorationSiteFactory();
		for (int i = 0 ; i < 100 ; i++)
		{
			dataTables.add(explorationSiteFactory.getTestErkundungsstelle1());
		}

		Collection<List<DataTableOld>> partitionBasedOnSize = Util.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 20);
	}

	@Test
	public void partitioningLessThenExpectedTest()
	{
		List<DataTableOld> dataTables = new ArrayList<>();
		ExplorationSiteFactory explorationSiteFactory = new ExplorationSiteFactory();
		for (int i = 0 ; i < 3 ; i++)
		{
			dataTables.add(explorationSiteFactory.getTestErkundungsstelle1());
		}

		Collection<List<DataTableOld>> partitionBasedOnSize = Util.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 1);
	}

	@Test
	public void partitioningIrregularAmountTest()
	{
		List<DataTableOld> dataTables = new ArrayList<>();
		ExplorationSiteFactory explorationSiteFactory = new ExplorationSiteFactory();
		for (int i = 0 ; i < 17 ; i++)
		{
			dataTables.add(explorationSiteFactory.getTestErkundungsstelle1());
		}

		Collection<List<DataTableOld>> partitionBasedOnSize = Util.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 4);
	}

	@Test
	public void partitioningNullTest()
	{
		List<DataTableOld> dataTables = new ArrayList<>();

		Collection<List<DataTableOld>> partitionBasedOnSize = Util.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 0);
	}
}
