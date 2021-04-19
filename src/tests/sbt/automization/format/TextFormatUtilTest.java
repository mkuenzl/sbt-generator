package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;

public class TextFormatUtilTest
{
    @Test
    public void bodengruppeAuffuellungWithoutClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("[OH]");
        Assert.assertFalse(schichtBodenGruppe.equals("Oberboden OH"));
    }

    @Test
    public void bodengruppeAuffuellungWithClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("[OH]");
        Assert.assertTrue(schichtBodenGruppe.equals("Oberboden [OH]"));
    }

    @Test
    public void bodengruppeWithoutClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("OH");
        Assert.assertTrue(schichtBodenGruppe.equals("Oberboden OH"));
    }

    @Test
    public void bodengruppeWithClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("OH");
        Assert.assertFalse(schichtBodenGruppe.equals("Oberboden [OH]"));
    }

    @Test
    public void bodengruppeNullTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup(null);
        Assert.assertTrue(schichtBodenGruppe.equals("-"));
    }

    @Test
    public void invalidBodengruppeTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("x");
        Assert.assertTrue(schichtBodenGruppe.equals("Invalid Bodengruppe x"));
    }

    @Test
    public void invalidBodengruppeAuffuelungTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("[x]");
        Assert.assertTrue(schichtBodenGruppe.equals("Invalid Bodengruppe [x]"));
    }

    @Test
    public void bodengruppeCaseStarTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("ST*");
        Assert.assertTrue(schichtBodenGruppe.equals("Sand-Ton-Gemisch ST*"));
    }

    @Test
    public void bodengruppeCaseTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("ST");
        Assert.assertTrue(schichtBodenGruppe.equals("Sand-Ton-Gemisch ST"));
    }

    @Test
    public void bodengruppenTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("ST - ST*");
        Assert.assertTrue(schichtBodenGruppe.equals("ST - ST*"));
    }

    @Test
    public void bodengruppenAufuellungTest(){
        String schichtBodenGruppe = TextFormatUtil.formatLayerSoilGroup("[ST - ST*]");
        Assert.assertTrue(schichtBodenGruppe.equals("[ST - ST*]"));
    }
}
