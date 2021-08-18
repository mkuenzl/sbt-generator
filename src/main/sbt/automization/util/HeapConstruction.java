package sbt.automization.util;

public final class HeapConstruction
{
	private HeapConstruction(){}

	public static int[] calculateHeapSampleSizes(double volume, int count)
	{
		if (count <= 0 || volume <= 0) return null;

		double x = volume / count;

		double modulo = x % 5;

		x -= modulo;

		double sumOfModulo = modulo * count;

		int[] sampleSizes = new int[count];

		for (int i = 0 ; i < count - 1 ; i++)
		{
			sampleSizes[i] = (int) x;
		}

		sampleSizes[count - 1] = (int) (x + Math.round(sumOfModulo));

		return sampleSizes;
	}
}
