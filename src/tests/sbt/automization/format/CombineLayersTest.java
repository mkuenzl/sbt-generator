package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.InformationTag;
import sbt.automization.data.Layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CombineLayersTest
{
	@Test
	public void combineTwoLayersBasedOnTag()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		Layer layerTwo = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Layer combinedLayer = LayerFormatUtil.combineLayers(layerOne, layerTwo, tag);

		Layer compareToLayer = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Assert.assertEquals(combinedLayer, compareToLayer);
	}

	@Test
	public void combineTwoLayersBasedOnTagWithNumber()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "C1");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		Layer layerTwo = new Layer(new HashMap<>(){{
			put(tag, "C1");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Layer combinedLayer = LayerFormatUtil.combineLayers(layerOne, layerTwo, tag);

		Layer compareToLayer = new Layer(new HashMap<>(){{
			put(tag, "C1");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Assert.assertEquals(combinedLayer, compareToLayer);
	}

	@Test
	public void combineTwoLayersBasedOnFalseTag()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "xyz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		Layer layerTwo = new Layer(new HashMap<>(){{
			put(tag, "abc");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Layer combinedLayer = LayerFormatUtil.combineLayers(layerOne, layerTwo, tag);

		Assert.assertNull(combinedLayer);
	}

	@Test
	public void combineLayerAndNullBasedOnTag()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "xyz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		Layer combinedLayer = LayerFormatUtil.combineLayers(layerOne, null, tag);

		Assert.assertEquals(combinedLayer, layerOne);
	}

	@Test
	public void combineNullAndLayerBasedOnTag()
	{
		String tag = "information";

		Layer layerTwo = new Layer(new HashMap<>(){{
			put(tag, "abc");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Layer combinedLayer = LayerFormatUtil.combineLayers(null, layerTwo, tag);

		Assert.assertEquals(combinedLayer, layerTwo);
	}

	@Test
	public void combineTwoLayersBasedOnNull()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		Layer layerTwo = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Layer combinedLayer = LayerFormatUtil.combineLayers(layerOne, layerTwo, null);

		Assert.assertNull(combinedLayer);
	}

	@Test
	public void combineLayersFromListOfNone()
	{
		String tag = "information";

		List<Layer> layers = new ArrayList<>();

		List<Layer> compareToLayers = new ArrayList<>();

		List<Layer> combinedLayers = LayerFormatUtil.combineLayers(layers, tag);

		Assert.assertEquals(compareToLayers, combinedLayers);
	}

	@Test
	public void combineLayersFromListOfOne()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		List<Layer> layers = new ArrayList<>()
		{{
			add(layerOne);
		}};

		List<Layer> compareToLayers = new ArrayList<>()
		{{
			add(new Layer(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
			}}));
		}};

		List<Layer> combinedLayers = LayerFormatUtil.combineLayers(layers, tag);

		Assert.assertEquals(compareToLayers, combinedLayers);
	}

	@Test
	public void combineAllLayersFromList()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		Layer layerTwo = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Layer layerThree = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
		}});

		Layer layerFour = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "50");
		}});

		Layer layerFive = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "50");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
		}});

		Layer layerSix = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
		}});

		List<Layer> layers = new ArrayList<>()
		{{
			add(layerOne);
			add(layerTwo);
			add(layerThree);
			add(layerFour);
			add(layerFive);
			add(layerSix);
		}};

		List<Layer> compareToLayers = new ArrayList<>()
		{{
			add(new Layer(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
			}}));
		}};

		List<Layer> combinedLayers = LayerFormatUtil.combineLayers(layers, tag);

		Assert.assertEquals(compareToLayers, combinedLayers);
	}

	@Test
	public void combineSomeLayersFromList()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		Layer layerTwo = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Layer layerThree = new Layer(new HashMap<>(){{
			put(tag, "xz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
		}});

		Layer layerFour = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "50");
		}});

		Layer layerFive = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "50");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
		}});

		Layer layerSix = new Layer(new HashMap<>(){{
			put(tag, "xz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
		}});

		List<Layer> layers = new ArrayList<>()
		{{
			add(layerOne);
			add(layerTwo);
			add(layerThree);
			add(layerFour);
			add(layerFive);
			add(layerSix);
		}};

		List<Layer> compareToLayers = new ArrayList<>()
		{{
			add(new Layer(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
			}}));
			add(new Layer(new HashMap<>(){{
				put(tag, "xz");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
			}}));
			add(new Layer(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
			}}));
			add(new Layer(new HashMap<>(){{
				put(tag, "xz");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
			}}));
		}};

		List<Layer> combinedLayers = LayerFormatUtil.combineLayers(layers, tag);

		Assert.assertEquals(compareToLayers, combinedLayers);
	}

	@Test
	public void combineNoneLayersFromList()
	{
		String tag = "information";

		Layer layerOne = new Layer(new HashMap<>(){{
			put(tag, "xy");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
		}});

		Layer layerTwo = new Layer(new HashMap<>(){{
			put(tag, "abc");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
		}});

		Layer layerThree = new Layer(new HashMap<>(){{
			put(tag, "xz");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
		}});

		Layer layerFour = new Layer(new HashMap<>(){{
			put(tag, "xf");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "50");
		}});

		Layer layerFive = new Layer(new HashMap<>(){{
			put(tag, "xl");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "50");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
		}});

		Layer layerSix = new Layer(new HashMap<>(){{
			put(tag, "xxl");
			put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
			put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
		}});

		List<Layer> layers = new ArrayList<>()
		{{
			add(layerOne);
			add(layerTwo);
			add(layerThree);
			add(layerFour);
			add(layerFive);
			add(layerSix);
		}};

		List<Layer> compareToLayers = new ArrayList<>()
		{{
			add(new Layer(new HashMap<>(){{
				put(tag, "xy");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "5");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "15");
			}}));
			add(new Layer(new HashMap<>(){{
				put(tag, "abc");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "25");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "35");
			}}));
			add(new Layer(new HashMap<>(){{
				put(tag, "xz");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "35");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "45");
			}}));
			add(new Layer(new HashMap<>(){{
				put(tag, "xf");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "45");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "50");
			}}));
			add(new Layer(new HashMap<>(){{
				put(tag, "xl");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "50");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "65");
			}}));
			add(new Layer(new HashMap<>(){{
				put(tag, "xxl");
				put(InformationTag.LAYER_DEPTH_START.getIdentifier(), "65");
				put(InformationTag.LAYER_DEPTH_END.getIdentifier(), "100");
			}}));
		}};

		List<Layer> combinedLayers = LayerFormatUtil.combineLayers(layers, tag);

		Assert.assertEquals(compareToLayers, combinedLayers);
	}
}
