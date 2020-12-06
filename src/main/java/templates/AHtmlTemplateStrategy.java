package main.java.templates;

import main.java.util.html.Html;
import main.java.util.html.HtmlBody;
import main.java.util.html.HtmlDiv;

import java.io.*;

abstract class AHtmlTemplateStrategy implements IHtmlTemplateStrategy
{
    static final String HTML_BODY_STYLE_ATTRIBUTE = "'tab-interval:35.4pt;word-wrap:break-word'";
    static final String HTML_ATTRIBUTE_XMLNSO = "\"urn:schemas-microsoft-com:office:office\"";
    static final String HTML_ATTRIBUTE_XMLNS = "\"http://www.w3.org/TR/REC-html40\"";

    private String htmlTable;

    public String buildHtmlTemplate(){

        //<html> content </html>
        Html template = new Html();
        template.appendAttribute("xmlns:o",HTML_ATTRIBUTE_XMLNSO);
        template.appendAttribute("xmlns", HTML_ATTRIBUTE_XMLNS);

        HtmlBody body = new HtmlBody();
        body.appendAttribute("lang", "DE");
        body.appendAttribute("style", HTML_BODY_STYLE_ATTRIBUTE);

        HtmlDiv div = new HtmlDiv();
        div.appendContent(htmlTable);
        body.appendContent(div.appendTag());
        template.appendContent(getHtmlHead());
        template.appendContent(body.appendTag());

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
