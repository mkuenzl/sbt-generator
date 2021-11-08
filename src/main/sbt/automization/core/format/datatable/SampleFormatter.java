package sbt.automization.core.format.datatable;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.*;
import sbt.automization.core.util.HeapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class to provide static methods for layer manipulation.
 */
public final class SampleFormatter
{
	private DataTable dataTable;
	private String outcrop;
	
	public SampleFormatter()
	{
	}
	
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
	 * @param tag a String specifying an information tag
	 * @return an updated list of layers with all possible layers combined
	 */
	public List<Sample> combineSamplesByTag(final Key... tag)
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
	public List<Sample> combineSamples(final List<Sample> samples, final Key... tag)
	{
		List<Sample> updatedSamples = new ArrayList<>();
		
		for (int i = 0; i < samples.size(); i++)
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
	
	public Sample combineSamples(final Sample firstSample, final Sample secondSample, final Key... keys)
	{
		if (firstSample == null) return secondSample;
		if (secondSample == null) return firstSample;
		if (keys == null) return null;
		
		if (samplesAreEqualByTags(firstSample, secondSample, keys))
		{
			Sample sample = new Sample(firstSample);
			Map<String, String> sampleTable = sample.getTable();
			sampleTable.put(SampleKey.DEPTH_END.getKey(), secondSample.get(SampleKey.DEPTH_END));
			return sample;
		}
		
		return null;
	}
	
	private boolean samplesAreEqualByTags(final Sample firstSample, final Sample secondSample, final Key... keys)
	{
		boolean isEquals = true;
		
		for (Key key : keys)
		{
			if (key instanceof SampleKey)
			{
				if (!firstSample.get(key).equals(secondSample.get(key)))
				{
					isEquals = false;
					break;
				}
			}
			
			if (key instanceof ChemistryKey)
			{
				final String parameterValueOfFirstSample = firstSample.getParameterValueBy(SampleKey.CHEMISTRY_ID, key);
				String parameterValueOfSecondSample = secondSample.getParameterValueBy(SampleKey.CHEMISTRY_ID, key);
				
				if (!parameterValueOfFirstSample.equals(parameterValueOfSecondSample))
				{
					isEquals = false;
					break;
				}
			}
			
			if (key instanceof RuKKey)
			{
				final String parameterValueOfFirstSample = firstSample.getParameterValueBy(SampleKey.RUK_ID, key);
				String parameterValueOfSecondSample = secondSample.getParameterValueBy(SampleKey.RUK_ID, key);
				
				if (!parameterValueOfFirstSample.equals(parameterValueOfSecondSample))
				{
					isEquals = false;
					break;
				}
			}
			
			if (key instanceof LpKey)
			{
				final String parameterValueOfFirstSample = firstSample.getParameterValueBy(SampleKey.LP_ID, key);
				String parameterValueOfSecondSample = secondSample.getParameterValueBy(SampleKey.LP_ID, key);
				
				if (!parameterValueOfFirstSample.equals(parameterValueOfSecondSample))
				{
					isEquals = false;
					break;
				}
			}
			
		}
		
		return isEquals;
	}
	
	public List<Sample> createHeapSamples()
	{
		List<Sample> splitHeapSamples = new ArrayList<>();
		
		List<Sample> heapSamples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);
		
		for (Sample heapSample : heapSamples)
		{
			int[] sampleVolumes = HeapUtil.calculateVolumes(heapSample);
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