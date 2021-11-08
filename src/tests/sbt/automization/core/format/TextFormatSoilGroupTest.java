package sbt.automization.core.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.core.format.text.SoilGroupTextFormatter;

public class TextFormatSoilGroupTest
{
    @Test
    public void soilGroupFillUpWithoutClipTest(){
        String soilGroup = new SoilGroupTextFormatter().format("[OH]");
        Assert.assertFalse(soilGroup.equals("Oberboden OH"));
    }

    @Test
    public void soilGroupFillUpWithClipTest(){
        String soilGroup = new SoilGroupTextFormatter().format("[OH]");
        Assert.assertTrue(soilGroup.equals("Oberboden [OH]"));
    }

    @Test
    public void soilGroupWithoutClipTest(){
        String soilGroup = new SoilGroupTextFormatter().format("OH");
        Assert.assertTrue(soilGroup.equals("Oberboden OH"));
    }

    @Test
    public void soilGroupWithClipTest(){
        String soilGroup = new SoilGroupTextFormatter().format("OH");
        Assert.assertFalse(soilGroup.equals("Oberboden [OH]"));
    }

    @Test
    public void soilGroupNullTest(){
        String soilGroup = new SoilGroupTextFormatter().format(null);
        Assert.assertTrue(soilGroup.equals("-"));
    }

    @Test
    public void invalidSoilGroupTest(){
        String soilGroup = new SoilGroupTextFormatter().format("x");
        Assert.assertTrue(soilGroup.equals("x"));
    }

    @Test
    public void invalidSoilGroupFillUpTest(){
        String soilGroup = new SoilGroupTextFormatter().format("[x]");
        Assert.assertTrue(soilGroup.equals(" [x]"));
    }

    @Test
    public void soilGroupCaseStarTest(){
        String soilGroup = new SoilGroupTextFormatter().format("ST*");
        Assert.assertTrue(soilGroup.equals("Sand-Ton-Gemisch ST*"));
    }

    @Test
    public void soilGroupCaseTest(){
        String soilGroup = new SoilGroupTextFormatter().format("ST");
        Assert.assertTrue(soilGroup.equals("Sand-Ton-Gemisch ST"));
    }

    @Test
    public void soilGroupTest(){
        String soilGroup = new SoilGroupTextFormatter().format("ST - ST*");
        Assert.assertTrue(soilGroup.equals("ST - ST*"));
    }

    @Test
    public void soilGroupFillUpTest(){
        String soilGroup = new SoilGroupTextFormatter().format("[ST - ST*]");
        Assert.assertTrue(soilGroup.equals("[ST - ST*]"));
    }
}
