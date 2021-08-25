package sbt.automization.export;

import sbt.automization.data.DataTable;
import sbt.automization.data.Examination;
import sbt.automization.templates.HtmlTemplate;
import sbt.automization.html.Html;
import sbt.automization.html.HtmlBody;
import sbt.automization.html.HtmlDiv;

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

	public HtmlTemplateExport(final HtmlTemplate strategy)
	{
		super(strategy);
	}

	public HtmlTemplateExport()
	{
		super();
	}

	@Override
	String format(final Examination examination)
	{
		tableExportStrategy.constructTemplate(examination);

		return format(tableExportStrategy.getTemplate());
	}

	/**
	 * Method constructs a complete HTML file with
	 * <html>
	 * <head>
	 * <css>
	 *
	 * </css>
	 * </head>
	 * <body>
	 *          <div>
	 *              <table>
	 *                  from strategy
	 *              </table>
	 *          </div>
	 *      </body>
	 * </html>
	 *
	 * @param tables expects a list of explorations sites
	 * @return a HTML file containing the strategy table
	 */
	@Override
	String format(List<DataTable> tables)
	{
		tableExportStrategy.constructTemplate(tables);

		return format(tableExportStrategy.getTemplate());
	}

	@Override
	String format(String htmlCode)
	{
		HtmlDiv div = new HtmlDiv.Builder()
				.appendAttribute("class", "WordSection1")
				.appendContent(htmlCode)
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

	@Override
	public String getPath()
	{
		if (Examination.exportPath == null)
			return System.getProperty("user.dir").concat(File.separator).concat(tableExportStrategy.getExportFileName()).concat(".html");

		return Examination.exportPath.concat(File.separator).concat(tableExportStrategy.getExportFileName()).concat(".html");
	}

	@Override
	public String getPath(String path)
	{
		return path.concat(tableExportStrategy.getExportFileName()).concat(".html");
	}

	/**
	 * Method loads the resource/sbt-table-stylesheet.txt which contains the actual header and css.
	 *
	 * @return as String the read text file from the resource folder
	 */
	private String constructAndGetHtmlHead()
	{
		StringBuilder stringBuilder = new StringBuilder();

		try (InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("/sbt-table-stylesheet.txt")) ;
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
