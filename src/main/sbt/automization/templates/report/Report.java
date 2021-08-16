package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.HtmlTemplate;
import sbt.automization.templates.Outcrop;
import sbt.automization.util.Util;
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
	public Collection<List<DataTable>> divideExplorationSites(List<DataTable> tables)
	{
		List<DataTable> probesWhichIncludeOutcrop = Util.getProbesWhichIncludeOutcrop(tables, outcrop.toString());

		Collection<List<DataTable>> dividedExplorationSites = Util.separateBasedOnSize(probesWhichIncludeOutcrop, 17);

		return dividedExplorationSites;
	}

	abstract String buildTechnicalFeatures(List<DataTable> dataTables);

	abstract String buildEnvironmentTechnicalFeatures(List<DataTable> dataTables);

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
