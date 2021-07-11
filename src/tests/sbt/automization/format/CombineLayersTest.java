package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.InformationTag;
import sbt.automization.data.Layer;

import java.util.HashMap;

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
	public void combineLayersFromList()
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

	}
}
