package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;

public class UtilHeapSampleSizeTest
{
    @Test
    public void calculateSamplesTest()
    {
        int[] heapSampleSizes = Util.calculateHeapSampleSizes(1050, 12);

        int[] compareToSizes = {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 170};

        Assert.assertArrayEquals(compareToSizes, heapSampleSizes);
    }
}
