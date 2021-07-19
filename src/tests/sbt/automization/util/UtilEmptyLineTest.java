package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UtilEmptyLineTest
{
    @Test
    public void explorationSiteNoEmptyValueTest()
    {
        String identifier = InformationTag.SITE_ID.getIdentifier();

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

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", InformationTag.SITE_ID);

        Assert.assertTrue(exists);
    }

    @Test
    public void explorationSiteSomeEmptyValuesTest()
    {
        String identifier = InformationTag.SITE_ID.getIdentifier();

        ExplorationSite explorationSiteOne = new ExplorationSite(new HashMap<>(){{
            put(identifier, "FB1");
        }});
        ExplorationSite explorationSiteTwo = new ExplorationSite();
        ExplorationSite explorationSiteThree = new ExplorationSite(new HashMap<>(){{
            put(identifier, "FB3");
        }});

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", InformationTag.SITE_ID);

        Assert.assertTrue(exists);
    }

    @Test
    public void explorationSiteAllEmptyValuesTest()
    {
        String identifier = InformationTag.SITE_ID.getIdentifier();

        ExplorationSite explorationSiteOne = new ExplorationSite(new HashMap<>(){{
        }});
        ExplorationSite explorationSiteTwo = new ExplorationSite(new HashMap<>(){{
        }});
        ExplorationSite explorationSiteThree = new ExplorationSite(new HashMap<>(){{
        }});

        List<ExplorationSite> explorationSites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree);

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", InformationTag.SITE_ID);

        Assert.assertFalse(exists);
    }

    @Test
    public void explorationSiteAnEmptyListProvidedTest()
    {
        List<ExplorationSite> explorationSites = Arrays.asList();

        boolean exists = Util.thereExistsAnExplorationSiteWithData(explorationSites, "", InformationTag.SITE_ID);

        Assert.assertFalse(exists);
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
