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
                .appendAttribute("class", "WordSection1")
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

        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            // "/css.txt" sollte ausreichen als Pfad für die JAR
            inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("/css.txt"));
            bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return "<head></head>";
            }
        }
        return stringBuilder.toString();
    }

    void setHtmlTable(final String htmlTable)
    {
        this.htmlTable = htmlTable;
    }

    String getHtmlTable()
    {
        return htmlTable;
    }

    abstract String setHtmlTableHeader();

}
