package sbt.automization.format;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;
import sbt.automization.data.refactoring.Parameter;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.RefChemistry;
import sbt.automization.data.refactoring.references.RefRuK;
import sbt.automization.data.refactoring.references.RefSample;
import sbt.automization.data.refactoring.references.Reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to provide static methods for layer manipulation.
 */
public final class CombineSampleUtil
{
	private CombineSampleUtil() {}

	/**
	 * Method used to combine layers of an outcrop from an exploration site based on a specific tag
	 *
	 * @param dataTable a ExplorationSite Object
	 * @param outcrop a String defining an outcrop
	 * @param tag a String specifying an information tag
	 * @return an updated list of layers with all possible layers combined
	 */
	public static List<Sample> combineSamplesOfOutcrop(final DataTable dataTable, final String outcrop, final Reference tag)
	{
		List<Sample> datatablesWithOutcrop = dataTable.getSamplesBy(RefSample.OUTCROP, outcrop);

		return combineSamples(datatablesWithOutcrop, tag);
	}

	/**
	 * Method used to combine consecutive identical layers in a list.
	 *
	 * @param samples a List of Layers
	 * @param tag a String representing the information to compare
	 * @return an updated list of layers with all possible layers combined
	 */
	public static List<Sample> combineSamples(final List<Sample> samples, final Reference tag)
	{
		List<Sample> updatedSamples = new ArrayList<>();

		for (int i = 0; i < samples.size() ; i++)
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
	 * @param tag         a String representing a column of the excel template
	 * @return a Layer Object with the tag, the depth start from the first layer and end from the second layer.
	 */
	public static Sample combineSamples(final Sample firstSample, final Sample secondSample, final Reference tag)
	{
		if (firstSample == null) return secondSample;
		if (secondSample == null) return firstSample;
		if (tag == null) return null;

		if (tag instanceof RefSample)
		{
			if (!firstSample.get(tag).equals(secondSample.get(tag)))
				return null;
			else
			{
				Sample sample = new Sample(new HashMap<>()
				{{
					put(tag.getKey(), firstSample.get(tag));
					put(RefSample.DEPTH_START.getKey(),
							firstSample.get(RefSample.DEPTH_START));
					put(RefSample.DEPTH_END.getKey(),
							secondSample.get(RefSample.DEPTH_END));
				}});

				return sample;
			}
		}

		if (tag instanceof RefChemistry)
		{
			final String parameterValueOfFirstSample = firstSample.getParameterValueBy(RefSample.CHEMISTRY_ID, tag);
			String parameterValueOfSecondSample = secondSample.getParameterValueBy(RefSample.CHEMISTRY_ID, tag);

			if (! parameterValueOfFirstSample.equals(parameterValueOfSecondSample))
				return null;
			else
			{
				Parameter parameter = new Parameter(new HashMap<>()
				{{
					put(tag.getKey(), parameterValueOfFirstSample);
					put(RefChemistry.ID.getKey(), firstSample.get(RefSample.CHEMISTRY_ID));
				}});
				Sample sample = new Sample(new HashMap<>()
				{{
					put(RefSample.DEPTH_START.getKey(),
							firstSample.get(RefSample.DEPTH_START));
					put(RefSample.DEPTH_END.getKey(),
							secondSample.get(RefSample.DEPTH_END));
				}});

				sample.addParameter(parameter);

				return sample;
			}
		}

		if (tag instanceof RefRuK)
		{
			final String parameterValueOfFirstSample = firstSample.getParameterValueBy(RefSample.RUK_ID, tag);
			String parameterValueOfSecondSample = secondSample.getParameterValueBy(RefSample.RUK_ID, tag);

			if (! parameterValueOfFirstSample.equals(parameterValueOfSecondSample))
				return null;
			else
			{
				Parameter parameter = new Parameter(new HashMap<>()
				{{
					put(tag.getKey(), parameterValueOfFirstSample);
					put(RefRuK.ID.getKey(), firstSample.get(RefSample.RUK_ID));
				}});
				Sample sample = new Sample(new HashMap<>()
				{{
					put(RefSample.DEPTH_START.getKey(),
							firstSample.get(RefSample.DEPTH_START));
					put(RefSample.DEPTH_END.getKey(),
							secondSample.get(RefSample.DEPTH_END));
				}});

				sample.addParameter(parameter);

				return sample;
			}
		}
		return null;
	}
}