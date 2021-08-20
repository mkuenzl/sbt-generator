package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.format.text.TextFormatter;

public class TextFormatSoilGroupTest
{
    @Test
    public void bodengruppeAuffuellungWithoutClipTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("[OH]");
        Assert.assertFalse(schichtBodenGruppe.equals("Oberboden OH"));
    }

    @Test
    public void bodengruppeAuffuellungWithClipTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("[OH]");
        Assert.assertTrue(schichtBodenGruppe.equals("Oberboden [OH]"));
    }

    @Test
    public void bodengruppeWithoutClipTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("OH");
        Assert.assertTrue(schichtBodenGruppe.equals("Oberboden OH"));
    }

    @Test
    public void bodengruppeWithClipTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("OH");
        Assert.assertFalse(schichtBodenGruppe.equals("Oberboden [OH]"));
    }

    @Test
    public void bodengruppeNullTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup(null);
        Assert.assertTrue(schichtBodenGruppe.equals("-"));
    }

    @Test
    public void invalidBodengruppeTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("x");
        Assert.assertTrue(schichtBodenGruppe.equals("x"));
    }

    @Test
    public void invalidBodengruppeAuffuelungTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("[x]");
        Assert.assertTrue(schichtBodenGruppe.equals(" [x]"));
    }

    @Test
    public void bodengruppeCaseStarTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("ST*");
        Assert.assertTrue(schichtBodenGruppe.equals("Sand-Ton-Gemisch ST*"));
    }

    @Test
    public void bodengruppeCaseTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("ST");
        Assert.assertTrue(schichtBodenGruppe.equals("Sand-Ton-Gemisch ST"));
    }

    @Test
    public void bodengruppenTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("ST - ST*");
        Assert.assertTrue(schichtBodenGruppe.equals("ST - ST*"));
    }

    @Test
    public void bodengruppenAufuellungTest(){
        String schichtBodenGruppe = TextFormatter.formatSoilGroup("[ST - ST*]");
        Assert.assertTrue(schichtBodenGruppe.equals("[ST - ST*]"));
    }
}
