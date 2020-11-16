package main.java.export;

import main.java.projekt.Projekt;
import main.java.templates.PN_TemplateStrategy;
import main.java.templates.RUK_TemplateStrategy;

import java.io.File;
import java.io.IOException;

public class HTMLTemplateExportStrategy extends ATemplateExportStrategy{

    private final String htmlBodyParameter = "lang=DE style='tab-interval:35.4pt;word-wrap:break-word'";

    final String htmlCloser =
            "\n" +
            "<p class=MsoNormal><o:p>&nbsp;</o:p></p>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>";


    @Override
    String format(Projekt projekt) {
        StringBuilder strb = new StringBuilder();
        try {
            /*strb.append(readHTMLHead(System.getProperty("user.dir")
                    .concat(File.separator).concat("WordTemplates")
                    .concat(File.separator).concat("PN98")
                    .concat(File.separator).concat("PN_Template_Head")));*/

            strb.append(readHTMLHead(System.getProperty("user.dir")
                    .concat(File.separator).concat("WordTemplates")
                    .concat(File.separator).concat("RUK")
                    .concat(File.separator).concat("RUK_Template_Head")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        strb.append("<body ").append(htmlBodyParameter).append(">");
        strb.append("<div ").append(">");

        //strb.append(PN_TemplateStrategy.getInstance().buildHtmlTable(projekt));

        strb.append(RUK_TemplateStrategy.getInstance().buildHtmlTable(projekt));

        strb.append(htmlCloser);

        return strb.toString();
    }
}
