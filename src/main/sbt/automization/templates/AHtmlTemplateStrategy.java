package sbt.automization.templates;

import sbt.automization.util.html.HtmlBody;
import sbt.automization.util.html.HtmlDiv;

import java.io.*;
import sbt.automization.util.html.Html;

abstract class AHtmlTemplateStrategy implements IHtmlTemplateStrategy
{
    static final String HTML_BODY_STYLE_ATTRIBUTE = "'tab-interval:35.4pt;word-wrap:break-word'";
    static final String HTML_ATTRIBUTE_XMLNSO = "\"urn:schemas-microsoft-com:office:office\"";
    static final String HTML_ATTRIBUTE_XMLNS = "\"http://www.w3.org/TR/REC-html40\"";

    private String htmlTable;

    public String buildHtmlTemplate(){

        HtmlDiv div = new HtmlDiv.Builder()
                .appendContent(htmlTable)
                .build();


        HtmlBody body = new HtmlBody.Builder()
                .appendAttribute("lang", "DE")
                .appendAttribute("style", HTML_BODY_STYLE_ATTRIBUTE)
                .appendContent(div.appendTag())
                .build();

        Html template = new Html.Builder()
                .appendAttribute("xmlns:o",HTML_ATTRIBUTE_XMLNSO)
                .appendAttribute("xmlns", HTML_ATTRIBUTE_XMLNS)
                .appendContent(getHtmlHead())
                .appendContent(body.appendTag())
                .build();

        return template.appendTag();
    }

    String getHtmlHead(){
        try
        {
            // "/css.txt" sollte ausreichen als Pfad für die JAR
            BufferedReader reader =
                    new BufferedReader(new FileReader(System.getProperty("user.dir")
                            .concat(File.separator)
                            .concat("resources")
                            .concat(File.separator)
                            .concat("css.txt")));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return "<head></head>";
    }

    void setHtmlTable(final String htmlTable)
    {
        this.htmlTable = htmlTable;
    }

    abstract String setHtmlTableHeader();

}
