package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UtilEmptyLineTest
{
    @Test
    public void explorationSiteNoEmptyValueTest()
    {
        String identifier = ReferenceKey.SITE_ID.getIdentifier();

        ExplorationSite explorationSiteOne = new ExplorationSite(new HashMap<>(){{
            put(identifier, "FB1");
        }});
        ExplorationSite explorationSiteTwo = new ExplorationSite(new HashMap<>(){{
            put(identifier, "FB2");
        }});
        ExplorationSite explorationSiteThree = new ExplorationSite(new HashMap<>(){{
            put(identifier, "FB3");
        }});

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", ReferenceKey.SITE_ID);

        Assert.assertTrue(exists);
    }

    @Test
    public void explorationSiteSomeEmptyValuesTest()
    {
        String identifier = ReferenceKey.SITE_ID.getIdentifier();

        ExplorationSite explorationSiteOne = new ExplorationSite(new HashMap<>(){{
            put(identifier, "FB1");
        }});
        ExplorationSite explorationSiteTwo = new ExplorationSite();
        ExplorationSite explorationSiteThree = new ExplorationSite(new HashMap<>(){{
            put(identifier, "FB3");
        }});

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", ReferenceKey.SITE_ID);

        Assert.assertTrue(exists);
    }

    @Test
    public void explorationSiteAllEmptyValuesTest()
    {
        String identifier = ReferenceKey.SITE_ID.getIdentifier();

        ExplorationSite explorationSiteOne = new ExplorationSite(new HashMap<>(){{
        }});
        ExplorationSite explorationSiteTwo = new ExplorationSite(new HashMap<>(){{
        }});
        ExplorationSite explorationSiteThree = new ExplorationSite(new HashMap<>(){{
        }});

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", ReferenceKey.SITE_ID);

        Assert.assertFalse(exists);
    }

    @Test
    public void explorationSiteAnEmptyListProvidedTest()
    {
        List<ExplorationSite> explorationSites = Arrays.asList();

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", ReferenceKey.SITE_ID);

        Assert.assertFalse(exists);
    }

    @Test
    public void layerNoEmptyValueTest()
    {
        String identifier = ReferenceKey.LAYER_RUK.getIdentifier();

        LayerSample emptyLayerSample = new LayerSample(new HashMap<>(){{
            put(identifier, "");
        }});
        LayerSample nonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(identifier, "value");
        }});


        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(emptyLayerSample);
        explorationSiteOne.addLayer(nonEmptyLayerSample);
        explorationSiteOne.addLayer(nonEmptyLayerSample);

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(nonEmptyLayerSample);
        explorationSiteTwo.addLayer(nonEmptyLayerSample);

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(nonEmptyLayerSample);
        explorationSiteThree.addLayer(emptyLayerSample);

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", ReferenceKey.LAYER_RUK);

        Assert.assertTrue(exists);
    }

    @Test
    public void layerSomeEmptyValuesTest()
    {
        String identifier = ReferenceKey.LAYER_RUK.getIdentifier();

        LayerSample emptyLayerSample = new LayerSample(new HashMap<>(){{
            put(identifier, "");
        }});
        LayerSample nonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(identifier, "value");
        }});


        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(emptyLayerSample);
        explorationSiteOne.addLayer(nonEmptyLayerSample);
        explorationSiteOne.addLayer(nonEmptyLayerSample);

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(emptyLayerSample);
        explorationSiteTwo.addLayer(emptyLayerSample);

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(nonEmptyLayerSample);
        explorationSiteThree.addLayer(emptyLayerSample);

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", ReferenceKey.LAYER_RUK);

        Assert.assertTrue(exists);
    }

    @Test
    public void layerAllEmptyValuesTest()
    {
        String identifier = ReferenceKey.LAYER_RUK.getIdentifier();

        LayerSample emptyLayerSample = new LayerSample(new HashMap<>(){{
            put(identifier, "");
        }});
        LayerSample nonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(identifier, "value");
        }});


        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(emptyLayerSample);
        explorationSiteOne.addLayer(emptyLayerSample);
        explorationSiteOne.addLayer(emptyLayerSample);

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(emptyLayerSample);
        explorationSiteTwo.addLayer(emptyLayerSample);

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(emptyLayerSample);
        explorationSiteThree.addLayer(emptyLayerSample);

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", ReferenceKey.LAYER_RUK);

        Assert.assertFalse(exists);
    }

    @Test
    public void layerAnEmptyListProvidedTest()
    {
        String identifier = ReferenceKey.LAYER_RUK.getIdentifier();

        LayerSample emptyLayerSample = new LayerSample(new HashMap<>(){{
            put(identifier, "");
        }});
        LayerSample nonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(identifier, "value");
        }});


        ExplorationSite explorationSiteOne = new ExplorationSite();

        ExplorationSite explorationSiteTwo = new ExplorationSite();

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(emptyLayerSample);
        explorationSiteThree.addLayer(emptyLayerSample);

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", ReferenceKey.LAYER_RUK);

        Assert.assertFalse(exists);
    }

    @Test
    public void layerFromOutcropNoEmptyValueTest()
    {
        String valueIdentifier = ReferenceKey.LAYER_RUK.getIdentifier();
        String outcropIdentifier = ReferenceKey.LAYER_OUTCROP.getIdentifier();
        String outcropValue = "GOB";

        LayerSample outcropEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, outcropValue);
            put(valueIdentifier, "");
        }});
        LayerSample outcropNonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, outcropValue);
            put(valueIdentifier, "value");
        }});
        LayerSample wrongOutcropEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, "nonExistent");
            put(valueIdentifier, "");
        }});
        LayerSample wrongOutcropNonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, "nonExistent");
            put(valueIdentifier, "value");
        }});


        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(outcropEmptyLayerSample);
        explorationSiteOne.addLayer(outcropNonEmptyLayerSample);
        explorationSiteOne.addLayer(outcropNonEmptyLayerSample);

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(outcropEmptyLayerSample);
        explorationSiteTwo.addLayer(outcropEmptyLayerSample);

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(outcropNonEmptyLayerSample);
        explorationSiteThree.addLayer(outcropEmptyLayerSample);

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, outcropValue, ReferenceKey.LAYER_RUK);

        Assert.assertTrue(exists);
    }

    @Test
    public void layerFromOutcropSomeEmptyValuesTest()
    {
        String valueIdentifier = ReferenceKey.LAYER_RUK.getIdentifier();
        String outcropIdentifier = ReferenceKey.LAYER_OUTCROP.getIdentifier();
        String outcropValue = "GOB";

        LayerSample outcropEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, outcropValue);
            put(valueIdentifier, "");
        }});
        LayerSample outcropNonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, outcropValue);
            put(valueIdentifier, "value");
        }});
        LayerSample wrongOutcropEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, "nonExistent");
            put(valueIdentifier, "");
        }});
        LayerSample wrongOutcropNonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, "nonExistent");
            put(valueIdentifier, "value");
        }});


        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(outcropEmptyLayerSample);
        explorationSiteOne.addLayer(outcropNonEmptyLayerSample);
        explorationSiteOne.addLayer(outcropNonEmptyLayerSample);

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(wrongOutcropEmptyLayerSample);
        explorationSiteTwo.addLayer(wrongOutcropNonEmptyLayerSample);

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(wrongOutcropEmptyLayerSample);
        explorationSiteThree.addLayer(wrongOutcropEmptyLayerSample);

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, outcropValue, ReferenceKey.LAYER_RUK);

        Assert.assertTrue(exists);
    }

    @Test
    public void layerFromOutcropAllEmptyValuesTest()
    {
        String valueIdentifier = ReferenceKey.LAYER_RUK.getIdentifier();
        String outcropIdentifier = ReferenceKey.LAYER_OUTCROP.getIdentifier();
        String outcropValue = "GOB";

        LayerSample outcropEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, outcropValue);
            put(valueIdentifier, "");
        }});
        LayerSample outcropNonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, outcropValue);
            put(valueIdentifier, "value");
        }});
        LayerSample wrongOutcropEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, "nonExistent");
            put(valueIdentifier, "");
        }});
        LayerSample wrongOutcropNonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, "nonExistent");
            put(valueIdentifier, "value");
        }});


        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(wrongOutcropEmptyLayerSample);
        explorationSiteOne.addLayer(wrongOutcropEmptyLayerSample);
        explorationSiteOne.addLayer(wrongOutcropEmptyLayerSample);

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(wrongOutcropEmptyLayerSample);
        explorationSiteTwo.addLayer(wrongOutcropNonEmptyLayerSample);

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(wrongOutcropEmptyLayerSample);
        explorationSiteThree.addLayer(wrongOutcropEmptyLayerSample);

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, outcropValue, ReferenceKey.LAYER_RUK);

        Assert.assertFalse(exists);
    }

    @Test
    public void layerFromOutcropAnEmptyListProvidedTest()
    {
        String valueIdentifier = ReferenceKey.LAYER_RUK.getIdentifier();
        String outcropIdentifier = ReferenceKey.LAYER_OUTCROP.getIdentifier();
        String outcropValue = "GOB";

        LayerSample outcropEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, outcropValue);
            put(valueIdentifier, "");
        }});
        LayerSample outcropNonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, outcropValue);
            put(valueIdentifier, "value");
        }});
        LayerSample wrongOutcropEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, "nonExistent");
            put(valueIdentifier, "");
        }});
        LayerSample wrongOutcropNonEmptyLayerSample = new LayerSample(new HashMap<>(){{
            put(outcropIdentifier, "nonExistent");
            put(valueIdentifier, "value");
        }});


        ExplorationSite explorationSiteOne = new ExplorationSite();

        ExplorationSite explorationSiteTwo = new ExplorationSite();

        ExplorationSite explorationSiteThree = new ExplorationSite();

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, outcropValue, ReferenceKey.LAYER_RUK);

        Assert.assertFalse(exists);
    }

}
