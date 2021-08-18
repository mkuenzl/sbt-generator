package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;

public class UtilHeapSampleSizeTest
{
	@Test
	public void calculateSamplesFirstTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(1050.0, 12);

		int[] compareToSizes = {85, 85, 85, 85, 85, 85, 85, 85, 85, 85, 85, 115};

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesSecondTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(470.0, 9);

		int[] compareToSizes = {50, 50, 50, 50, 50, 50, 50, 50, 70};

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesThirdTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(30.0, 2);

		int[] compareToSizes = {15, 15};

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesFourthTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(550.0, 10);

		int[] compareToSizes = {55, 55, 55, 55, 55, 55, 55, 55, 55, 55};

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesUnevenTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(551.0, 10);

		int[] compareToSizes = {55, 55, 55, 55, 55, 55, 55, 55, 55, 56};

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesCountZeroTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(551.0, 0);

		int[] compareToSizes = null;

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesVolumeZeroTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(0, 10);

		int[] compareToSizes = null;

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesCountGreaterVolumeTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(5, 10);

		int[] compareToSizes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 5};

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesVolumeDoubleTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(555.55, 10);

		int[] compareToSizes = {55, 55, 55, 55, 55, 55, 55, 55, 55, 61};

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}

	@Test
	public void calculateSamplesVolumeRoundingDoubleTest()
	{
		int[] heapSampleSizes = HeapConstruction.calculateHeapSampleSizes(555.42, 10);

		int[] compareToSizes = {55, 55, 55, 55, 55, 55, 55, 55, 55, 60};

		Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
	}
}
