package sbt.automization.util;

import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.gui.ErrorPopup;

public final class HeapConstruction
{
	private HeapConstruction() {}

	public static int[] calculateVolumes(Sample sample)
	{
		int[] sampleVolumes = new int[0];

		try
		{
			Double volume = sample.getAsDouble(SampleKey.VOLUME);
			Integer count = sample.getAsInteger(SampleKey.SAMPLE_AMOUNT);
			sampleVolumes = calculateVolumes(volume, count);
		} catch (NullPointerException exception)
		{
			String message = "Probe " + sample.get(SampleKey.PROBE_ID) + " fehlt in Bereich " + sample.get(SampleKey.ID) + " das Volumen oder die Anzahl an Proben.";
			ErrorPopup.showMessage(message);
		}

		return sampleVolumes;
	}

	public static int[] calculateVolumes(double volume, int count)
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
