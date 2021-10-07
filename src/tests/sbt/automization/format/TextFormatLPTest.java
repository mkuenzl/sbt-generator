package sbt.automization.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.format.text.LoadPlateTextFormatter;

import java.io.IOException;


/* View results
 * HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport();
 * htmlTemplateExportStrategy.exportAsShowcase(formattedCode);
 * openExportFile(htmlTemplateExportStrategy.getPathToShowcase());
 */

public class TextFormatLPTest
{
    @Test
    public void formatEmptyEv85Test() throws IOException {
        String formattedCode = new LoadPlateTextFormatter().format("< 80", "-");

        Assert.assertEquals("< 80", formattedCode);
    }

    @Test
    public void formatEmptyTest() throws IOException {
        String formattedCode = new LoadPlateTextFormatter().format("-", "-");

        Assert.assertEquals(formattedCode, "-");
    }

    @Test
    public void formatEv2Ev85Test() throws IOException {
        String formattedCode = new LoadPlateTextFormatter().format("< 80", "31");

        Assert.assertEquals("<p class=Normal >" +
                "\n< 80" +
                "\n<p class=Normal6 >" +
                "\n[50 - 60]" +
                "\n</p>" +
                "\n</p>", formattedCode);
    }
}
