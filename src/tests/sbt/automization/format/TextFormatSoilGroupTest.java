package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;

public class TextFormatSoilGroupTest
{
    @Test
    public void bodengruppeAuffuellungWithoutClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("[OH]");
        Assert.assertFalse(schichtBodenGruppe.equals("Oberboden OH"));
    }

    @Test
    public void bodengruppeAuffuellungWithClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("[OH]");
        Assert.assertTrue(schichtBodenGruppe.equals("Oberboden [OH]"));
    }

    @Test
    public void bodengruppeWithoutClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("OH");
        Assert.assertTrue(schichtBodenGruppe.equals("Oberboden OH"));
    }

    @Test
    public void bodengruppeWithClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("OH");
        Assert.assertFalse(schichtBodenGruppe.equals("Oberboden [OH]"));
    }

    @Test
    public void bodengruppeNullTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup(null);
        Assert.assertTrue(schichtBodenGruppe.equals("-"));
    }

    @Test
    public void invalidBodengruppeTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("x");
        Assert.assertTrue(schichtBodenGruppe.equals("Invalid Bodengruppe x"));
    }

    @Test
    public void invalidBodengruppeAuffuelungTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("[x]");
        Assert.assertTrue(schichtBodenGruppe.equals("Invalid Bodengruppe [x]"));
    }

    @Test
    public void bodengruppeCaseStarTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("ST*");
        Assert.assertTrue(schichtBodenGruppe.equals("Sand-Ton-Gemisch ST*"));
    }

    @Test
    public void bodengruppeCaseTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("ST");
        Assert.assertTrue(schichtBodenGruppe.equals("Sand-Ton-Gemisch ST"));
    }

    @Test
    public void bodengruppenTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("ST - ST*");
        Assert.assertTrue(schichtBodenGruppe.equals("ST - ST*"));
    }

    @Test
    public void bodengruppenAufuellungTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSoilGroup("[ST - ST*]");
        Assert.assertTrue(schichtBodenGruppe.equals("[ST - ST*]"));
    }
}
