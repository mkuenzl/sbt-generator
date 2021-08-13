package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.appendix.AppendixTemplate;
import sbt.automization.templates.report.ReportTemplate;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class AttemptTemplate extends AppendixTemplate
{

	private static AttemptTemplate instance;

	private AttemptTemplate() {}

	public static AttemptTemplate getInstance()
	{
		if (instance == null)
		{
			synchronized (AttemptTemplate.class)
			{
				if (instance == null)
				{
					instance = new AttemptTemplate();
				}
			}
		}
		return instance;
	}

	@Override
	HtmlTable constructAndGetTableObject()
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();

		return table;
	}


	@Override
	String constructAndGetTableHeader()
	{
		return null;
	}


	@Override
	public void constructTable(List<ExplorationSite> sites)
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		HtmlCell htmlCell = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("TextFormatUtil.formatOutcropLayers(sites.get(0))")
				.build();

		HtmlRow htmlRow = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(htmlCell.appendTag())
				.build();

		table.appendContent(htmlRow.appendTag());

		setTable(table.appendTag());
	}

	@Override
	public void constructTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "example";
	}
}
