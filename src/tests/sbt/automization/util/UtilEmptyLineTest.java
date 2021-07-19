package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UtilEmptyLineTest
{
    @Test
    public void explorationSiteNoEmptyValueTest()
    {
        ExplorationSite explorationSiteOne = new ExplorationSite(new HashMap<>(){{
            put("ERK_ID", "FB1");
        }});
        ExplorationSite explorationSiteTwo = new ExplorationSite(new HashMap<>(){{
            put("ERK_ID", "FB2");
        }});
        ExplorationSite explorationSiteThree = new ExplorationSite(new HashMap<>(){{
            put("ERK_ID", "FB3");
        }});

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteLayerWithData(explorationSites, "", InformationTag.SITE_ID);

        Assert.assertTrue(exists);
    }

    @Test
    public void explorationSiteSomeEmptyValuesTest()
    {

    }

    @Test
    public void explorationSiteAllEmptyValuesTest()
    {

    }

    @Test
    public void explorationSiteAnEmptyListProvidedTest()
    {

    }

    @Test
    public void layerNoEmptyValueTest()
    {

    }

    @Test
    public void layerSomeEmptyValuesTest()
    {

    }

    @Test
    public void layerAllEmptyValuesTest()
    {

    }

    @Test
    public void layerAnEmptyListProvidedTest()
    {

    }

    @Test
    public void layerFromOutcropNoEmptyValueTest()
    {

    }

    @Test
    public void layerFromOutcropSomeEmptyValuesTest()
    {

    }

    @Test
    public void layerFromOutcropAllEmptyValuesTest()
    {

    }

    @Test
    public void layerFromOutcropAnEmptyListProvidedTest()
    {

    }

}
