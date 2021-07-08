package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.export.HtmlTemplateExport;

import java.io.IOException;

import static sbt.automization.templates.TemplateBuildingTest.openExportFile;


/* View results
 * HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport();
 * htmlTemplateExportStrategy.exportAsShowcase(formattedCode);
 * openExportFile(htmlTemplateExportStrategy.getPathToShowcase());
 */

public class TextFormatLPTest
{
    @Test
    public void formatEmptyEv85Test() throws IOException {
        String formattedCode = TextFormatUtil.formatLP("< 80", "-");

        Assert.assertEquals(formattedCode, "<p class=Normal >\n" +
                "< 80\n" +
                "</p>");
    }

    @Test
    public void formatEmptyTest() throws IOException {
        String formattedCode = TextFormatUtil.formatLP("-", "-");

        Assert.assertEquals(formattedCode, "<p class=Normal >\n" +
                "-\n" +
                "</p>");
    }

}
