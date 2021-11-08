package sbt.automization.core.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.data.key.Key;
import sbt.automization.core.format.datatable.SampleFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CombineSamplesTest
{
	@Test
	public void combineTwoLayersBasedOnTag()
	{
		Key key = SampleKey.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = new SampleFormatter().combineSamples(sampleOne, sampleTwo, key);

		Sample compareToSample = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Assert.assertEquals(combinedSample, compareToSample);
	}

	@Test
	public void combineTwoLayersBasedOnTagWithNumber()
	{
		Key key = SampleKey.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "C1");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "C1");
			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = new SampleFormatter().combineSamples(sampleOne, sampleTwo, key);

		Sample compareToSample = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "C1");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Assert.assertEquals(combinedSample, compareToSample);
	}

	@Test
	public void combineTwoLayersBasedOnFalseTag()
	{
		Key key = SampleKey.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xyz");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "abc");
			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = new SampleFormatter().combineSamples(sampleOne, sampleTwo, key);

		Assert.assertNull(combinedSample);
	}

	@Test
	public void combineLayerAndNullBasedOnTag()
	{
		Key key = SampleKey.COLOR;

		Sample sample = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xyz");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample combinedSample = new SampleFormatter().combineSamples(sample, null, key);

		Assert.assertEquals(combinedSample, sample);
	}

	@Test
	public void combineNullAndLayerBasedOnTag()
	{
		Key key = SampleKey.COLOR;

		Sample sample = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "abc");
			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = new SampleFormatter().combineSamples(null, sample, key);

		Assert.assertEquals(combinedSample, sample);
	}

	@Test
	public void combineTwoLayersBasedOnNull()
	{
		Key key = SampleKey.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = new SampleFormatter().combineSamples(sampleOne, sampleTwo, null);

		Assert.assertNull(combinedSample);
	}

	@Test
	public void combineLayersFromListOfNone()
	{
		Key key = SampleKey.COLOR;

		List<Sample> samples = new ArrayList<>();

		List<Sample> compareToSamples = new ArrayList<>();

		List<Sample> combinedSamples = new SampleFormatter().combineSamples(samples, key);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineLayersFromListOfOne()
	{
		Key key = SampleKey.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		List<Sample> samples = new ArrayList<>()
		{{
			add(sampleOne);
		}};

		List<Sample> compareToSamples = new ArrayList<>()
		{{
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "xy");
				put(SampleKey.DEPTH_START.getKey(), "5");
				put(SampleKey.DEPTH_END.getKey(), "15");
			}}));
		}};

		List<Sample> combinedSamples = new SampleFormatter().combineSamples(samples, key);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineAllLayersFromList()
	{
		Key key = SampleKey.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample sampleThree = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "35");
			put(SampleKey.DEPTH_END.getKey(), "45");
		}});

		Sample sampleFour = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "45");
			put(SampleKey.DEPTH_END.getKey(), "50");
		}});

		Sample sampleFive = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "50");
			put(SampleKey.DEPTH_END.getKey(), "65");
		}});

		Sample sampleSix = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "65");
			put(SampleKey.DEPTH_END.getKey(), "100");
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
				put(key.getKey(), "xy");
				put(SampleKey.DEPTH_START.getKey(), "5");
				put(SampleKey.DEPTH_END.getKey(), "100");
			}}));
		}};

		List<Sample> combinedSamples = new SampleFormatter().combineSamples(samples, key);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineSomeLayersFromList()
	{
		Key key = SampleKey.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample sampleThree = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xz");
			put(SampleKey.DEPTH_START.getKey(), "35");
			put(SampleKey.DEPTH_END.getKey(), "45");
		}});

		Sample sampleFour = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "45");
			put(SampleKey.DEPTH_END.getKey(), "50");
		}});

		Sample sampleFive = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "50");
			put(SampleKey.DEPTH_END.getKey(), "65");
		}});

		Sample sampleSix = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xz");
			put(SampleKey.DEPTH_START.getKey(), "65");
			put(SampleKey.DEPTH_END.getKey(), "100");
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
				put(key.getKey(), "xy");
				put(SampleKey.DEPTH_START.getKey(), "5");
				put(SampleKey.DEPTH_END.getKey(), "35");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "xz");
				put(SampleKey.DEPTH_START.getKey(), "35");
				put(SampleKey.DEPTH_END.getKey(), "45");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "xy");
				put(SampleKey.DEPTH_START.getKey(), "45");
				put(SampleKey.DEPTH_END.getKey(), "65");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "xz");
				put(SampleKey.DEPTH_START.getKey(), "65");
				put(SampleKey.DEPTH_END.getKey(), "100");
			}}));
		}};

		List<Sample> combinedSamples = new SampleFormatter().combineSamples(samples, key);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineNoneLayersFromList()
	{
		Key key = SampleKey.COLOR;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xy");
			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "abc");
			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample sampleThree = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xz");
			put(SampleKey.DEPTH_START.getKey(), "35");
			put(SampleKey.DEPTH_END.getKey(), "45");
		}});

		Sample sampleFour = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xf");
			put(SampleKey.DEPTH_START.getKey(), "45");
			put(SampleKey.DEPTH_END.getKey(), "50");
		}});

		Sample sampleFive = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xl");
			put(SampleKey.DEPTH_START.getKey(), "50");
			put(SampleKey.DEPTH_END.getKey(), "65");
		}});

		Sample sampleSix = new Sample(new HashMap<>()
		{{
			put(key.getKey(), "xxl");
			put(SampleKey.DEPTH_START.getKey(), "65");
			put(SampleKey.DEPTH_END.getKey(), "100");
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
				put(key.getKey(), "xy");
				put(SampleKey.DEPTH_START.getKey(), "5");
				put(SampleKey.DEPTH_END.getKey(), "15");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "abc");
				put(SampleKey.DEPTH_START.getKey(), "25");
				put(SampleKey.DEPTH_END.getKey(), "35");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "xz");
				put(SampleKey.DEPTH_START.getKey(), "35");
				put(SampleKey.DEPTH_END.getKey(), "45");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "xf");
				put(SampleKey.DEPTH_START.getKey(), "45");
				put(SampleKey.DEPTH_END.getKey(), "50");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "xl");
				put(SampleKey.DEPTH_START.getKey(), "50");
				put(SampleKey.DEPTH_END.getKey(), "65");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put(key.getKey(), "xxl");
				put(SampleKey.DEPTH_START.getKey(), "65");
				put(SampleKey.DEPTH_END.getKey(), "100");
			}}));
		}};

		List<Sample> combinedSamples = new SampleFormatter().combineSamples(samples, key);

		Assert.assertEquals(compareToSamples, combinedSamples);
	}

	@Test
	public void combineTwoLayersBasedOnMultipleTag()
	{
		Key color = SampleKey.COLOR;
		Key type = SampleKey.TYPE;
		Key rounding = SampleKey.ROUNDING_GRADATION;

		Sample sampleOne = new Sample(new HashMap<>()
		{{
			put(color.getKey(), "grey");
			put(type.getKey(), "concrete");
			put(rounding.getKey(), "round");

			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "15");
		}});

		Sample sampleTwo = new Sample(new HashMap<>()
		{{
			put(color.getKey(), "grey");
			put(type.getKey(), "concrete");
			put(rounding.getKey(), "round");

			put(SampleKey.DEPTH_START.getKey(), "25");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Sample combinedSample = new SampleFormatter().combineSamples(sampleOne, sampleTwo, color, type, rounding);

		Sample compareToSample = new Sample(new HashMap<>()
		{{
			put(color.getKey(), "grey");
			put(type.getKey(), "concrete");
			put(rounding.getKey(), "round");

			put(SampleKey.DEPTH_START.getKey(), "5");
			put(SampleKey.DEPTH_END.getKey(), "35");
		}});

		Assert.assertEquals(combinedSample, compareToSample);
	}
}
