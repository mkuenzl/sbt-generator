package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.HtmlTemplate;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.styles.ReportStyle;
import sbt.automization.util.Util;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.Collection;
import java.util.List;

/**
 * Abstract class for all report tables inherits from AHtmlTable
 */
public abstract class Report implements HtmlTemplate
{
	protected final Outcrop outcrop;
	protected final StringBuilder template;
	protected HtmlTable table;

	public Report()
	{
		this.template = new StringBuilder();
		this.outcrop = null;
	}

	public Report(Outcrop outcrop)
	{
		this.template = new StringBuilder();
		this.outcrop = outcrop;
	}

	public String getTemplate()
	{
		return template.toString();
	}

	void addPageBreak()
	{
		this.template.append("<br>");
	}

	void addTable()
	{
		String tableString = table.appendTag();
		this.template.append(tableString);
	}

	/**
	 * Method used to retrieve all exploration sites containing an outcrop and dividing them into
	 * A3 paper sized portions.
	 *
	 * @param tables a List of ExplorationSites
	 * @return a Collection of Lists
	 */
	Collection<List<DataTable>> splitIntoPortionPerPage(List<DataTable> tables)
	{
		List<DataTable> probesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(tables, outcrop.toString());

		Collection<List<DataTable>> portions = Util.separateBasedOnSize(probesWhichIncludeOutcrop, 17);

		return portions;
	}

	abstract void constructTechnicalFeatures(List<DataTable> dataTables);

	void addTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = dataTables.size() + 1;

		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Technische Merkmale"})
		});

		addToTable(row.appendTag());
	}

	abstract void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables);

	void addEnvironmentTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = dataTables.size() + 1;

		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Umwelttechnische Merkmale"})
		});

		addToTable(row.appendTag());
	}

	void createTable()
	{
		this.table = new HtmlTable.Builder()
				.appendAttribute("class", ReportStyle.TABLE.getStyleClass())
				.appendAttribute("border", "1")
				.appendAttribute("style", ReportStyle.TABLE.getStyle())
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();
	}

	void addToTable(String content)
	{
		table.appendContent(content);
	}
}
