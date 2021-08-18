package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.Sample;
import sbt.automization.data.references.RefSample;
import sbt.automization.data.references.Reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CombineSamplesTest
{
	@Test
	public void combineTwoLayersBasedOnTag()
	{
		Reference reference = RefSample.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "25");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = CombineSampleUtil.combineSamples(sampleOne, sampleTwo, reference);

		Sample compareToSample = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Assert.assertEquals(combinedSample, compareToSample);
	}

	@Test
	public void combineTwoLayersBasedOnTagWithNumber()
	{
		Reference reference = RefSample.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "C1");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "C1");
			put(RefSample.DEPTH_START.getKey(), "25");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = CombineSampleUtil.combineSamples(sampleOne, sampleTwo, reference);

		Sample compareToSample = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "C1");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Assert.assertEquals(combinedSample, compareToSample);
	}

	@Test
	public void combineTwoLayersBasedOnFalseTag()
	{
		Reference reference = RefSample.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xyz");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "abc");
			put(RefSample.DEPTH_START.getKey(), "25");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = CombineSampleUtil.combineSamples(sampleOne, sampleTwo, reference);

		Assert.assertNull(combinedSample);
	}

	@Test
	public void combineLayerAndNullBasedOnTag()
	{
		Reference reference = RefSample.COLOR;

		Sample sample = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xyz");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		Sample combinedSample = CombineSampleUtil.combineSamples(sample, null, reference);

		Assert.assertEquals(combinedSample, sample);
	}

	@Test
	public void combineNullAndLayerBasedOnTag()
	{
		Reference reference = RefSample.COLOR;

		Sample sample = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "abc");
			put(RefSample.DEPTH_START.getKey(), "25");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = CombineSampleUtil.combineSamples(null, sample, reference);

		Assert.assertEquals(combinedSample, sample);
	}

	@Test
	public void combineTwoLayersBasedOnNull()
	{
		Reference reference = RefSample.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "25");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = CombineSampleUtil.combineSamples(sampleOne, sampleTwo, null);

		Assert.assertNull(combinedSample);
	}

	@Test
	public void combineLayersFromListOfNone()
	{
		Reference reference = RefSample.COLOR;

		List<Sample> samples = new ArrayList<>();

		List<Sample> compareToSamples = new ArrayList<>();

		List<Sample> combinedSamples = CombineSampleUtil.combineSamples(samples, reference);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineLayersFromListOfOne()
	{
		Reference reference = RefSample.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		List<Sample> samples = new ArrayList<>()
		{{
			add(sampleOne);
		}};

		List<Sample> compareToSamples = new ArrayList<>()
		{{
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xy");
				put(RefSample.DEPTH_START.getKey(), "5");
				put(RefSample.DEPTH_END.getKey(), "15");
			}}));
		}};

		List<Sample> combinedSamples = CombineSampleUtil.combineSamples(samples, reference);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineAllLayersFromList()
	{
		Reference reference = RefSample.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "25");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Sample sampleThree = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "35");
			put(RefSample.DEPTH_END.getKey(), "45");
		}});

		Sample sampleFour = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "45");
			put(RefSample.DEPTH_END.getKey(), "50");
		}});

		Sample sampleFive = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "50");
			put(RefSample.DEPTH_END.getKey(), "65");
		}});

		Sample sampleSix = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "65");
			put(RefSample.DEPTH_END.getKey(), "100");
		}});

		List<Sample> samples = new ArrayList<>()
		{{
			add(sampleOne);
			add(sampleTwo);
			add(sampleThree);
			add(sampleFour);
			add(sampleFive);
			add(sampleSix);
		}};

		List<Sample> compareToSamples = new ArrayList<>()
		{{
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xy");
				put(RefSample.DEPTH_START.getKey(), "5");
				put(RefSample.DEPTH_END.getKey(), "100");
			}}));
		}};

		List<Sample> combinedSamples = CombineSampleUtil.combineSamples(samples, reference);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineSomeLayersFromList()
	{
		Reference reference = RefSample.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "25");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Sample sampleThree = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xz");
			put(RefSample.DEPTH_START.getKey(), "35");
			put(RefSample.DEPTH_END.getKey(), "45");
		}});

		Sample sampleFour = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "45");
			put(RefSample.DEPTH_END.getKey(), "50");
		}});

		Sample sampleFive = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "50");
			put(RefSample.DEPTH_END.getKey(), "65");
		}});

		Sample sampleSix = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xz");
			put(RefSample.DEPTH_START.getKey(), "65");
			put(RefSample.DEPTH_END.getKey(), "100");
		}});

		List<Sample> samples = new ArrayList<>()
		{{
			add(sampleOne);
			add(sampleTwo);
			add(sampleThree);
			add(sampleFour);
			add(sampleFive);
			add(sampleSix);
		}};

		List<Sample> compareToSamples = new ArrayList<>()
		{{
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xy");
				put(RefSample.DEPTH_START.getKey(), "5");
				put(RefSample.DEPTH_END.getKey(), "35");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xz");
				put(RefSample.DEPTH_START.getKey(), "35");
				put(RefSample.DEPTH_END.getKey(), "45");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xy");
				put(RefSample.DEPTH_START.getKey(), "45");
				put(RefSample.DEPTH_END.getKey(), "65");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xz");
				put(RefSample.DEPTH_START.getKey(), "65");
				put(RefSample.DEPTH_END.getKey(), "100");
			}}));
		}};

		List<Sample> combinedSamples = CombineSampleUtil.combineSamples(samples, reference);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineNoneLayersFromList()
	{
		Reference reference = RefSample.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xy");
			put(RefSample.DEPTH_START.getKey(), "5");
			put(RefSample.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "abc");
			put(RefSample.DEPTH_START.getKey(), "25");
			put(RefSample.DEPTH_END.getKey(), "35");
		}});

		Sample sampleThree = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xz");
			put(RefSample.DEPTH_START.getKey(), "35");
			put(RefSample.DEPTH_END.getKey(), "45");
		}});

		Sample sampleFour = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xf");
			put(RefSample.DEPTH_START.getKey(), "45");
			put(RefSample.DEPTH_END.getKey(), "50");
		}});

		Sample sampleFive = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xl");
			put(RefSample.DEPTH_START.getKey(), "50");
			put(RefSample.DEPTH_END.getKey(), "65");
		}});

		Sample sampleSix = new Sample(new HashMap<>()
		{{
			put(reference.getKey(), "xxl");
			put(RefSample.DEPTH_START.getKey(), "65");
			put(RefSample.DEPTH_END.getKey(), "100");
		}});

		List<Sample> samples = new ArrayList<>()
		{{
			add(sampleOne);
			add(sampleTwo);
			add(sampleThree);
			add(sampleFour);
			add(sampleFive);
			add(sampleSix);
		}};

		List<Sample> compareToSamples = new ArrayList<>()
		{{
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xy");
				put(RefSample.DEPTH_START.getKey(), "5");
				put(RefSample.DEPTH_END.getKey(), "15");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "abc");
				put(RefSample.DEPTH_START.getKey(), "25");
				put(RefSample.DEPTH_END.getKey(), "35");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xz");
				put(RefSample.DEPTH_START.getKey(), "35");
				put(RefSample.DEPTH_END.getKey(), "45");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xf");
				put(RefSample.DEPTH_START.getKey(), "45");
				put(RefSample.DEPTH_END.getKey(), "50");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xl");
				put(RefSample.DEPTH_START.getKey(), "50");
				put(RefSample.DEPTH_END.getKey(), "65");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(reference.getKey(), "xxl");
				put(RefSample.DEPTH_START.getKey(), "65");
				put(RefSample.DEPTH_END.getKey(), "100");
			}}));
		}};

		List<Sample> combinedSamples = CombineSampleUtil.combineSamples(samples, reference);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}
}
