package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.InformationTag;
import sbt.automization.data.LayerSample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CombineLayersTest
{
	@Test
	public void combineTwoLayersBasedOnTag()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		LayerSample layerSampleTwo = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		LayerSample combinedLayerSample = LayerFormatUtil.combineLayers(layerSampleOne, layerSampleTwo, tag);

		LayerSample compareToLayerSample = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Assert.assertEquals(combinedLayerSample, compareToLayerSample);
	}

	@Test
	public void combineTwoLayersBasedOnTagWithNumber()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "C1");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		LayerSample layerSampleTwo = new LayerSample(new HashMap<>(){{
			put(tag, "C1");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		LayerSample combinedLayerSample = LayerFormatUtil.combineLayers(layerSampleOne, layerSampleTwo, tag);

		LayerSample compareToLayerSample = new LayerSample(new HashMap<>(){{
			put(tag, "C1");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Assert.assertEquals(combinedLayerSample, compareToLayerSample);
	}

	@Test
	public void combineTwoLayersBasedOnFalseTag()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "xyz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		LayerSample layerSampleTwo = new LayerSample(new HashMap<>(){{
			put(tag, "abc");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		LayerSample combinedLayerSample = LayerFormatUtil.combineLayers(layerSampleOne, layerSampleTwo, tag);

		Assert.assertNull(combinedLayerSample);
	}

	@Test
	public void combineLayerAndNullBasedOnTag()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "xyz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		LayerSample combinedLayerSample = LayerFormatUtil.combineLayers(layerSampleOne, null, tag);

		Assert.assertEquals(combinedLayerSample, layerSampleOne);
	}

	@Test
	public void combineNullAndLayerBasedOnTag()
	{
		String tag = "information";

		LayerSample layerSampleTwo = new LayerSample(new HashMap<>(){{
			put(tag, "abc");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		LayerSample combinedLayerSample = LayerFormatUtil.combineLayers(null, layerSampleTwo, tag);

		Assert.assertEquals(combinedLayerSample, layerSampleTwo);
	}

	@Test
	public void combineTwoLayersBasedOnNull()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		LayerSample layerSampleTwo = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		LayerSample combinedLayerSample = LayerFormatUtil.combineLayers(layerSampleOne, layerSampleTwo, null);

		Assert.assertNull(combinedLayerSample);
	}

	@Test
	public void combineLayersFromListOfNone()
	{
		String tag = "information";

		List<LayerSample> layerSamples = new ArrayList<>();

		List<LayerSample> compareToLayerSamples = new ArrayList<>();

		List<LayerSample> combinedLayerSamples = LayerFormatUtil.combineLayers(layerSamples, tag);

		Assert.assertEquals(compareToLayerSamples, combinedLayerSamples);
	}

	@Test
	public void combineLayersFromListOfOne()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		List<LayerSample> layerSamples = new ArrayList<>()
		{{
			add(layerSampleOne);
		}};

		List<LayerSample> compareToLayerSamples = new ArrayList<>()
		{{
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
			}}));
		}};

		List<LayerSample> combinedLayerSamples = LayerFormatUtil.combineLayers(layerSamples, tag);

		Assert.assertEquals(compareToLayerSamples, combinedLayerSamples);
	}

	@Test
	public void combineAllLayersFromList()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		LayerSample layerSampleTwo = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		LayerSample layerSampleThree = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
		}});

		LayerSample layerSampleFour = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "50");
		}});

		LayerSample layerSampleFive = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "50");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
		}});

		LayerSample layerSampleSix = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
		}});

		List<LayerSample> layerSamples = new ArrayList<>()
		{{
			add(layerSampleOne);
			add(layerSampleTwo);
			add(layerSampleThree);
			add(layerSampleFour);
			add(layerSampleFive);
			add(layerSampleSix);
		}};

		List<LayerSample> compareToLayerSamples = new ArrayList<>()
		{{
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
			}}));
		}};

		List<LayerSample> combinedLayerSamples = LayerFormatUtil.combineLayers(layerSamples, tag);

		Assert.assertEquals(compareToLayerSamples, combinedLayerSamples);
	}

	@Test
	public void combineSomeLayersFromList()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		LayerSample layerSampleTwo = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		LayerSample layerSampleThree = new LayerSample(new HashMap<>(){{
			put(tag, "xz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
		}});

		LayerSample layerSampleFour = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "50");
		}});

		LayerSample layerSampleFive = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "50");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
		}});

		LayerSample layerSampleSix = new LayerSample(new HashMap<>(){{
			put(tag, "xz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
		}});

		List<LayerSample> layerSamples = new ArrayList<>()
		{{
			add(layerSampleOne);
			add(layerSampleTwo);
			add(layerSampleThree);
			add(layerSampleFour);
			add(layerSampleFive);
			add(layerSampleSix);
		}};

		List<LayerSample> compareToLayerSamples = new ArrayList<>()
		{{
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
			}}));
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xz");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
			}}));
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
			}}));
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xz");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
			}}));
		}};

		List<LayerSample> combinedLayerSamples = LayerFormatUtil.combineLayers(layerSamples, tag);

		Assert.assertEquals(compareToLayerSamples, combinedLayerSamples);
	}

	@Test
	public void combineNoneLayersFromList()
	{
		String tag = "information";

		LayerSample layerSampleOne = new LayerSample(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		LayerSample layerSampleTwo = new LayerSample(new HashMap<>(){{
			put(tag, "abc");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		LayerSample layerSampleThree = new LayerSample(new HashMap<>(){{
			put(tag, "xz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
		}});

		LayerSample layerSampleFour = new LayerSample(new HashMap<>(){{
			put(tag, "xf");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "50");
		}});

		LayerSample layerSampleFive = new LayerSample(new HashMap<>(){{
			put(tag, "xl");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "50");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
		}});

		LayerSample layerSampleSix = new LayerSample(new HashMap<>(){{
			put(tag, "xxl");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
		}});

		List<LayerSample> layerSamples = new ArrayList<>()
		{{
			add(layerSampleOne);
			add(layerSampleTwo);
			add(layerSampleThree);
			add(layerSampleFour);
			add(layerSampleFive);
			add(layerSampleSix);
		}};

		List<LayerSample> compareToLayerSamples = new ArrayList<>()
		{{
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
			}}));
			add(new LayerSample(new HashMap<>(){{
				put(tag, "abc");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
			}}));
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xz");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
			}}));
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xf");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "50");
			}}));
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xl");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "50");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
			}}));
			add(new LayerSample(new HashMap<>(){{
				put(tag, "xxl");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
			}}));
		}};

		List<LayerSample> combinedLayerSamples = LayerFormatUtil.combineLayers(layerSamples, tag);

		Assert.assertEquals(compareToLayerSamples, combinedLayerSamples);
	}
}
