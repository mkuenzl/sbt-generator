package sbt.automization.core.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.core.data.DataTable;
import sbt.automization.DatatableProvider;

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
		List<DataTable> dataTables = new ArrayList<>();
		DatatableProvider datatableProvider = new DatatableProvider();
		for (int i = 0 ; i < 100 ; i++)
		{
			dataTables.add(datatableProvider.getTestDataTable());
		}

		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 20);
	}

	@Test
	public void partitioningLessThenExpectedTest()
	{
		List<DataTable> dataTables = new ArrayList<>();
		DatatableProvider datatableProvider = new DatatableProvider();
		for (int i = 0 ; i < 3 ; i++)
		{
			dataTables.add(datatableProvider.getTestDataTable());
		}

		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 1);
	}

	@Test
	public void partitioningIrregularAmountTest()
	{
		List<DataTable> dataTables = new ArrayList<>();
		DatatableProvider datatableProvider = new DatatableProvider();
		for (int i = 0 ; i < 17 ; i++)
		{
			dataTables.add(datatableProvider.getTestDataTable());
		}

		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 4);
	}

	@Test
	public void partitioningNullTest()
	{
		List<DataTable> dataTables = new ArrayList<>();

		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 0);
	}
}
