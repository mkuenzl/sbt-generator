package main.java.export;

import main.java.projekt.Projekt;
import main.java.templates.PN_TemplateStrategy;

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
            strb.append(readHTMLHead("/Users/moritzkunzl/Documents/GitHub/sbt-generator/PN_Template_Head"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        strb.append("<body ").append(htmlBodyParameter).append(">");
        strb.append("<div ").append(">");
        strb.append(new PN_TemplateStrategy().buildHtmlTable(projekt));
        strb.append(htmlCloser);

        return strb.toString();
    }
}
