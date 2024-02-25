package sbt.automization.core.format;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.core.format.text.LoadPlateTextFormatterExclusiveEv85Value;


/* View results
 * HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport();
 * htmlTemplateExportStrategy.exportAsShowcase(formattedCode);
 * openExportFile(htmlTemplateExportStrategy.getPathToShowcase());
 */

public class TextFormatLPTest
{
    @Test
    public void formatEmptyEv85Test() {
        String formattedCode = new LoadPlateTextFormatterExclusiveEv85Value().format("< 80", "-");

        Assert.assertEquals("< 80", formattedCode);
    }

    @Test
    public void formatEmptyTest(){
        String formattedCode = new LoadPlateTextFormatterExclusiveEv85Value().format("-", "-");

        Assert.assertEquals(formattedCode, "-");
    }

    @Test
    public void formatEv2Ev85Test() {
        String formattedCode = new LoadPlateTextFormatterExclusiveEv85Value().format("< 80", "31");

        Assert.assertEquals("<p class=Normal >" +
                "\n< 80" +
                "\n<p class=Normal6 >" +
                "\n[50 - 60]" +
                "\n</p>" +
                "\n</p>", formattedCode);
    }
}
