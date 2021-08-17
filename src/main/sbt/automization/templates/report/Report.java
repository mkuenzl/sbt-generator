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
	private Outcrop outcrop;

	public static final String HTML_BASIC_TABLE_STYLE = new StringBuilder()
			.append("'")
			.append("border-collapse:collapse")
			.append(";")
			.append("mso-table-layout-alt:fixed")
			.append(";")
			.append("border:none")
			.append(";")
			.append("mso-border-alt:solid windowtext .5pt")
			.append(";")
			.append("mso-padding-alt:0cm 5.4pt 0cm 5.4pt")
			.append("'")
			.toString();

	protected int linesPerPage;
	protected int lines;
	protected final StringBuilder template;
	protected HtmlTable table;

	public Report()
	{
		linesPerPage = 0;
		lines = 0;
		template = new StringBuilder();
	}

	public String getTemplate()
	{
		return template.toString();
	}

	public void addToTemplate(final String table)
	{
		this.template.append(table);
	}

	public abstract String constructAndGetTableHeader();

	protected void setOutcrop(Outcrop outcrop){
		this.outcrop = outcrop;
	}

	/**
	 * Method used to retrieve all exploration sites containing an outcrop and dividing them into
	 * A3 paper sized portions.
	 *
	 * @param tables a List of ExplorationSites
	 * @return a Collection of Lists
	 */
	public Collection<List<DataTable>> splitGroupOf(List<DataTable> tables)
	{
		List<DataTable> probesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(tables, outcrop.toString());

		Collection<List<DataTable>> dividedExplorationSites = Util.separateBasedOnSize(probesWhichIncludeOutcrop, 17);

		return dividedExplorationSites;
	}

	abstract void buildTechnicalFeatures(List<DataTable> dataTables);

	void constructAndGetTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = dataTables.size() + 1;

		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Technische Merkmale"})
		});

		table.appendContent(row.appendTag());
	}

	abstract void buildEnvironmentTechnicalFeatures(List<DataTable> dataTables);

	void constructAndGetEnvironmentTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = dataTables.size() + 1;

		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Umwelttechnische Merkmale"})
		});

		table.appendContent(row.appendTag());
	}

	public HtmlTable constructAndGetTableObject()
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		return table;
	}
}
