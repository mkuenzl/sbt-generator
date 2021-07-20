package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.data.LayerSample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UtilExplorationSiteWithOutcropTest
{
    @Test
    public void emptyList()
    {
        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(Arrays.asList(), "UG");

        Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
    }

    @Test
    public void explorationSiteWithoutLayer()
    {
        ExplorationSite explorationSite = new ExplorationSite();

        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(Arrays.asList(explorationSite), "UG");

        Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
    }

    @Test
    public void explorationSiteWithoutOutcrop()
    {
        ExplorationSite explorationSite = new ExplorationSite();
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(Arrays.asList(explorationSite), "UG");

        Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
    }

    @Test
    public void explorationSiteWithOutcrop(){
        ExplorationSite explorationSite = new ExplorationSite();
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(Arrays.asList(explorationSite), "TOB");

        Assert.assertEquals(1, sitesWhichIncludeOutcrop.size());
    }

    @Test
    public void explorationSiteWithRandomString(){
        ExplorationSite explorationSite = new ExplorationSite();
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSite.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(Arrays.asList(explorationSite), "DUBBABU");

        Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
    }

    @Test
    public void multipleExplorationSitesWithoutOutcropTestOne(){
        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteOne.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteFour = new ExplorationSite();
        explorationSiteFour.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        List<ExplorationSite> sites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree, explorationSiteFour);
        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(sites, "TOB");

        Assert.assertEquals(2, sitesWhichIncludeOutcrop.size());
    }

    @Test
    public void multipleExplorationSitesWithoutOutcropTestTwo(){
        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteOne.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteFour = new ExplorationSite();
        explorationSiteFour.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        List<ExplorationSite> sites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree, explorationSiteFour);
        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(sites, "GOB");

        Assert.assertEquals(3, sitesWhichIncludeOutcrop.size());
    }

    @Test
    public void multipleExplorationSitesWithoutOutcropTestThree(){
        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteOne.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteFour = new ExplorationSite();
        explorationSiteFour.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        List<ExplorationSite> sites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree, explorationSiteFour);
        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(sites, "FUGE");

        Assert.assertEquals(4, sitesWhichIncludeOutcrop.size());
    }

    @Test
    public void multipleExplorationSitesWithOutcrop(){
        ExplorationSite explorationSiteOne = new ExplorationSite();
        explorationSiteOne.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteOne.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteTwo = new ExplorationSite();
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSiteTwo.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteThree = new ExplorationSite();
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "GOB");
        }}));
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "TOB");
        }}));
        explorationSiteThree.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        ExplorationSite explorationSiteFour = new ExplorationSite();
        explorationSiteFour.addLayer(new LayerSample(new HashMap<>(){{
            put(InformationTag.LAYER_OUTCROP.getIdentifier(), "FUGE");
        }}));

        List<ExplorationSite> sites = Arrays.asList(explorationSiteOne, explorationSiteTwo, explorationSiteThree, explorationSiteFour);
        List<ExplorationSite> sitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(sites, "UG");

        Assert.assertEquals(0, sitesWhichIncludeOutcrop.size());
    }
}
