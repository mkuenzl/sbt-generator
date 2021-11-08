package sbt.automization.core.export;

import sbt.automization.core.Project;
import sbt.automization.core.data.DataTable;
import sbt.automization.core.html.Html;
import sbt.automization.core.html.HtmlBody;
import sbt.automization.core.html.HtmlDiv;
import sbt.automization.core.templates.HtmlTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class WordExport extends TemplateExport
{
	static final String HTML_BODY_STYLE_ATTRIBUTE = "'tab-interval:35.4pt;word-wrap:break-word'";
	/**
	 * Necessary for the import into Microsoft Word.
	 */
	static final String HTML_ATTRIBUTE_XMLNSO = "\"urn:schemas-microsoft-com:office:office\"";
	static final String HTML_ATTRIBUTE_XMLNS = "\"http://www.w3.org/TR/REC-html40\"";
	
	public WordExport(final HtmlTemplate strategy)
	{
		super(strategy);
	}
	
	public WordExport()
	{
		super();
	}
	
	@Override
	public String getPath()
	{
		return null;
	}
	
	@Override
	String format(Project project)
	{
		return null;
	}
	
	@Override
	String format(List<DataTable> tables)
	{
		tableExportStrategy.constructTemplate(tables);
		
		return format(tableExportStrategy.getTemplate());
	}
	
	@Override
	public String getPath(String path)
	{
		return path.concat(tableExportStrategy.getExportFileName()).concat(".docx");
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
	
	/**
	 * Method loads the resource/sbt-table-stylesheet.txt which contains the actual header and css.
	 *
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
