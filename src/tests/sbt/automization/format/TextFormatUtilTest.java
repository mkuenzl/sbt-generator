package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;

public class TextFormatUtilTest
{
    @Test
    public void bodengruppeAuffuellungWithoutClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe("[OH]");
        Assert.assertFalse(schichtBodenGruppe.equals("Oberboden OH"));
    }

    @Test
    public void bodengruppeAuffuellungWithClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe("[OH]");
        Assert.assertTrue(schichtBodenGruppe.equals("Oberboden [OH]"));
    }

    @Test
    public void bodengruppeWithoutClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe("OH");
        Assert.assertTrue(schichtBodenGruppe.equals("Oberboden OH"));
    }

    @Test
    public void bodengruppeWithClipTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe("OH");
        Assert.assertFalse(schichtBodenGruppe.equals("Oberboden [OH]"));
    }

    @Test
    public void bodengruppeNullTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe(null);
        Assert.assertTrue(schichtBodenGruppe.equals("-"));
    }

    @Test
    public void invalidBodengruppeTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe("x");
        Assert.assertTrue(schichtBodenGruppe.equals("Invalid Bodengruppe x"));
    }

    @Test
    public void invalidBodengruppeAuffuelungTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe("[x]");
        Assert.assertTrue(schichtBodenGruppe.equals("Invalid Bodengruppe [x]"));
    }

    @Test
    public void bodengruppeCaseStarTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe("ST*");
        Assert.assertTrue(schichtBodenGruppe.equals("Sand-Ton-Gemisch ST*"));
    }

    @Test
    public void bodengruppeCaseTest(){
        String schichtBodenGruppe = TextFormatUtil.formatSchichtBodenGruppe("ST");
        Assert.assertTrue(schichtBodenGruppe.equals("Sand-Ton-Gemisch ST"));
    }
}
