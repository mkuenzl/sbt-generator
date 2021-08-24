package sbt.automization.format.datatable;

import sbt.automization.data.DataTable;
import sbt.automization.data.Parameter;
import sbt.automization.data.Sample;
import sbt.automization.data.key.*;
import sbt.automization.gui.ErrorPopup;
import sbt.automization.util.HeapConstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to provide static methods for layer manipulation.
 */
public final class SampleFormatter
{
	private DataTable dataTable;
	private String outcrop;

	public SampleFormatter()
	{}

	public SampleFormatter(DataTable dataTable)
	{
		this.dataTable = dataTable;
	}

	public SampleFormatter(DataTable dataTable, String outcrop)
	{
		this.dataTable = dataTable;
		this.outcrop = outcrop;
	}

	/**
	 * Method used to combine layers of an outcrop from an exploration site based on a specific tag
	 *
	 * @param tag       a String specifying an information tag
	 * @return an updated list of layers with all possible layers combined
	 */
	public List<Sample> combineSamplesByTag(final Key tag)
	{
		if (outcrop != null)
		{
			List<Sample> samplesWithOutcrop = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);
			return combineSamples(samplesWithOutcrop, tag);
		} else
		{
			List<Sample> samples = dataTable.getSamples();
			return combineSamples(samples, tag);
		}
	}

	/**
	 * Method used to combine consecutive identical layers in a list.
	 *
	 * @param samples a List of Layers
	 * @param tag     a String representing the information to compare
	 * @return an updated list of layers with all possible layers combined
	 */
	public List<Sample> combineSamples(final List<Sample> samples, final Key tag)
	{
		List<Sample> updatedSamples = new ArrayList<>();

		for (int i = 0 ; i < samples.size() ; i++)
		{
			Sample sample = samples.get(i);
			Sample finalSample = sample;

			int next = i + 1;
			while (next < samples.size())
			{ // Checks how many consecutive layers have the same value as tag
				Sample nextSample = samples.get(next);
				Sample combinedSample = combineSamples(sample, nextSample, tag);

				if (combinedSample != null)
				{
					finalSample = combinedSample;
				} else
				{
					break;
				}
				// Sets i on the position of the last consecutive element
				i = next;
				next++;
			}

			updatedSamples.add(finalSample);
		}
		return updatedSamples;
	}

	/**
	 * Method used for combination of the layers based on a specified tag.
	 *
	 * @param firstSample  a Layer Object
	 * @param secondSample a Layer Object
	 * @param tag          a String representing a column of the excel template
	 * @return a Layer Object with the tag, the depth start from the first layer and end from the second layer.
	 */
	public Sample combineSamples(final Sample firstSample, final Sample secondSample, final Key tag)
	{
		if (firstSample == null) return secondSample;
		if (secondSample == null) return firstSample;
		if (tag == null) return null;

		if (tag instanceof SampleKey)
		{
			if (! firstSample.get(tag).equals(secondSample.get(tag)))
				return null;
			else
			{
				Sample sample = new Sample(new HashMap<>()
				{{
					put(tag.getKey(), firstSample.get(tag));
					put(SampleKey.DEPTH_START.getKey(),
							firstSample.get(SampleKey.DEPTH_START));
					put(SampleKey.DEPTH_END.getKey(),
							secondSample.get(SampleKey.DEPTH_END));
				}});

				return sample;
			}
		}

		if (tag instanceof ChemistryKey)
		{
			final String parameterValueOfFirstSample = firstSample.getParameterValueBy(SampleKey.CHEMISTRY_ID, tag);
			String parameterValueOfSecondSample = secondSample.getParameterValueBy(SampleKey.CHEMISTRY_ID, tag);

			if (! parameterValueOfFirstSample.equals(parameterValueOfSecondSample))
				return null;
			else
			{
				Parameter parameter = new Parameter(new HashMap<>()
				{{
					put(tag.getKey(), parameterValueOfFirstSample);
					put(ChemistryKey.ID.getKey(), firstSample.get(SampleKey.CHEMISTRY_ID));
				}});
				Sample sample = new Sample(new HashMap<>()
				{{
					put(SampleKey.CHEMISTRY_ID.getKey(), firstSample.get(SampleKey.CHEMISTRY_ID));
					put(SampleKey.DEPTH_START.getKey(),
							firstSample.get(SampleKey.DEPTH_START));
					put(SampleKey.DEPTH_END.getKey(),
							secondSample.get(SampleKey.DEPTH_END));
				}});

				sample.addParameter(parameter);

				return sample;
			}
		}

		if (tag instanceof RuKKey)
		{
			final String parameterValueOfFirstSample = firstSample.getParameterValueBy(SampleKey.RUK_ID, tag);
			String parameterValueOfSecondSample = secondSample.getParameterValueBy(SampleKey.RUK_ID, tag);

			if (! parameterValueOfFirstSample.equals(parameterValueOfSecondSample))
				return null;
			else
			{
				Parameter parameter = new Parameter(new HashMap<>()
				{{
					put(tag.getKey(), parameterValueOfFirstSample);
					put(RuKKey.ID.getKey(), firstSample.get(SampleKey.RUK_ID));
				}});
				Sample sample = new Sample(new HashMap<>()
				{{
					put(SampleKey.RUK_ID.getKey(), firstSample.get(SampleKey.RUK_ID));
					put(SampleKey.DEPTH_START.getKey(),
							firstSample.get(SampleKey.DEPTH_START));
					put(SampleKey.DEPTH_END.getKey(),
							secondSample.get(SampleKey.DEPTH_END));
				}});

				sample.addParameter(parameter);

				return sample;
			}
		}
		return null;
	}

	private Sample checkChemistryTag()
	{
		return null;
	}

	public List<Sample> createHeapSamples()
	{
		List<Sample> splitHeapSamples = new ArrayList<>();

		List<Sample> heapSamples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);

		for (Sample heapSample : heapSamples)
		{
			int[] sampleVolumes = HeapConstruction.calculateVolumes(heapSample);
			List<Sample> split = splitHeap(heapSample, sampleVolumes);
			splitHeapSamples.addAll(split);
		}
		return splitHeapSamples;
	}

	private List<Sample> splitHeap(Sample sample, int[] volumes)
	{
		List<Sample> clonedSamples = new ArrayList<>();

		for (int volume : volumes)
		{
			try
			{
				Sample clonedSample = (Sample) sample.clone();
				clonedSample.add(SampleKey.VOLUME, String.valueOf(volume));
				clonedSamples.add(clonedSample);
			} catch (CloneNotSupportedException e)
			{
				e.printStackTrace();
			}
		}
		return clonedSamples;
	}
}