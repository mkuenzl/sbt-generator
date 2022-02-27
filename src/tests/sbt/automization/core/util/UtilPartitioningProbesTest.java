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
public class UtilPartitioningProbesTest
{
	@Test
	public void partitioningByProbeTest()
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
	public void partitioningByProbeLessThenExpectedTest()
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
	public void partitioningByProbeIrregularAmountTest()
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
	public void partitioningByProbeNullTest()
	{
		List<DataTable> dataTables = new ArrayList<>();

		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateBasedOnSize(dataTables, 5);

		Assert.assertTrue(partitionBasedOnSize.size() == 0);
	}
	
	@Test
	public void partitioningBySampleTest()
	{
		List<DataTable> dataTables = new ArrayList<>();
		DatatableProvider datatableProvider = new DatatableProvider();
		for (int i = 0 ; i < 100 ; i++)
		{
			dataTables.add(datatableProvider.getTestDataTable(6));
		}
		
		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateProbesBySizeOfSamples(dataTables, 12);
		
		Assert.assertTrue(partitionBasedOnSize.size() == 50);
	}
	
	@Test
	public void partitioningBySampleLessThenExpectedTest()
	{
		List<DataTable> dataTables = new ArrayList<>();
		DatatableProvider datatableProvider = new DatatableProvider();
		for (int i = 0 ; i < 3 ; i++)
		{
			dataTables.add(datatableProvider.getTestDataTable(2));
		}
		
		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateProbesBySizeOfSamples(dataTables, 12);
		
		Assert.assertTrue(partitionBasedOnSize.size() == 1);
	}
	
	@Test
	public void partitioningBySampleOverlappingAmountsTest()
	{
		List<DataTable> dataTables = new ArrayList<>();
		DatatableProvider datatableProvider = new DatatableProvider();
		
		dataTables.add(datatableProvider.getTestDataTable(4));
		dataTables.add(datatableProvider.getTestDataTable(4));
		dataTables.add(datatableProvider.getTestDataTable(9));
		dataTables.add(datatableProvider.getTestDataTable(3));
		dataTables.add(datatableProvider.getTestDataTable(9));
		dataTables.add(datatableProvider.getTestDataTable(3));
		dataTables.add(datatableProvider.getTestDataTable(3));
		dataTables.add(datatableProvider.getTestDataTable(3));
		dataTables.add(datatableProvider.getTestDataTable(3));
		dataTables.add(datatableProvider.getTestDataTable(4));
		dataTables.add(datatableProvider.getTestDataTable(6));
		
		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateProbesBySizeOfSamples(dataTables, 12);
		
		Assert.assertTrue(partitionBasedOnSize.size() == 5);
	}
	
	@Test
	public void partitioningBySampleNullTest()
	{
		List<DataTable> dataTables = new ArrayList<>();
		
		Collection<List<DataTable>> partitionBasedOnSize = ListSeparator.separateProbesBySizeOfSamples(dataTables, 12);
		
		Assert.assertTrue(partitionBasedOnSize.size() == 0);
	}
}
