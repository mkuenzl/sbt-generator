package sbt.automization.export;

import sbt.automization.data.TableInformation;
import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.IHtmlTable;
import sbt.automization.util.html.Html;
import sbt.automization.util.html.HtmlBody;
import sbt.automization.util.html.HtmlDiv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Class for the HTML export strategy
 */
public final class HtmlTemplateExport extends ATemplateExport
{
    static final String HTML_BODY_STYLE_ATTRIBUTE = "'tab-interval:35.4pt;word-wrap:break-word'";
    /**
     * Necessary for the import into Microsoft Word.
     */
    static final String HTML_ATTRIBUTE_XMLNSO = "\"urn:schemas-microsoft-com:office:office\"";
    static final String HTML_ATTRIBUTE_XMLNS = "\"http://www.w3.org/TR/REC-html40\"";

    public HtmlTemplateExport(final IHtmlTable strategy)
    {
        super(strategy);
    }

    @Override
    String format(final TableInformation tableInformation)
    {
        return format(tableInformation.getExplorationSites());
    }

    /**
     * Method creates a path based on the location of the csv and the table strategy file name
     * @return
     */
    @Override
    public String getPath() {
        if (TableInformation.exportPath == null)
            return System.getProperty("user.dir").concat(File.separator).concat(tableStrategy.getExportFileName()).concat(".html");

        return TableInformation.exportPath.concat(File.separator).concat(tableStrategy.getExportFileName()).concat(".html");
    }

    /**
     * Method constructs a complete HTML file with
     * <html>
     *      <head>
     *          <css>
     *
     *          </css>
     *      </head>
     *      <body>
     *          <div>
     *              <table>
     *                  from strategy
     *              </table>
     *          </div>
     *      </body>
     * </html>
     *
     * @param explorationSites expects a list of explorations sites
     * @return a HTML file containing the strategy table
     */
    @Override
    String format(List<ExplorationSite> explorationSites)
    {
        tableStrategy.constructTable(explorationSites);

        HtmlDiv div = new HtmlDiv.Builder()
                .appendAttribute("class", "WordSection1")
                .appendContent(tableStrategy.getTable())
                .build();


        HtmlBody body = new HtmlBody.Builder()
                .appendAttribute("lang", "DE")
                .appendAttribute("style", HTML_BODY_STYLE_ATTRIBUTE)
                .appendContent(div.appendTag())
                .build();

        Html template = new Html.Builder()
                .appendAttribute("xmlns:o", HTML_ATTRIBUTE_XMLNSO)
                .appendAttribute("xmlns", HTML_ATTRIBUTE_XMLNS)
                .appendContent(constructAndGetHtmlHead())
                .appendContent(body.appendTag())
                .build();

        return template.appendTag();
    }

    /**
     * Method loads the resource/sbt-table-stylesheet.txt which contains the actual header and css.
     * @return as String the read text file from the resource folder
     */
    private String constructAndGetHtmlHead()
    {
        StringBuilder stringBuilder = new StringBuilder();

        try (InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("/sbt-table-stylesheet.txt"));
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
        {
            String line;
            String ls = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        } catch (IOException e)
        {
            e.printStackTrace();
            stringBuilder.append("<head></head>");
        }

        return stringBuilder.toString();
    }
}