package main.java.export;

import main.java.projekt.Projekt;
import main.java.templates.ATemplateStrategy;
import main.java.templates.ITemplateStrategy;


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

    public HTMLTemplateExportStrategy(ITemplateStrategy strategy) {
        super(strategy);
    }


    @Override
    String format(Projekt projekt) {

        StringBuilder strb = new StringBuilder();

        strb.append(this.strategy.getHtmlHead());

        strb.append("<body ")
                .append(this.htmlBodyParameter)
                .append(">")
                .append("<div>");

        strb.append(this.strategy.buildHtmlTable(projekt));

        strb.append(this.htmlCloser);

        return strb.toString();
    }

    @Override
    String format(ITemplateStrategy strategy) {
        return null;
    }
}
