package sbt.automization.templates.report;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.HtmlTableTemplate;
import sbt.automization.util.Util;
import sbt.automization.util.html.HtmlTable;

import java.util.Collection;
import java.util.List;

/**
 * Abstract class for all report tables inherits from AHtmlTable
 */
public abstract class ReportTemplate implements HtmlTableTemplate
{
	String layerKind;

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

	private String table;

	public String getTempWlate()
	{
		return table;
	}

	public void setTable(final String table)
	{
		this.table = table;
	}

	public abstract String constructAndGetTableHeader();

	@Override
	public void constructTemplate(List<DataTable> tables)
	{

	}

	/**
	 * Method used to retrieve all exploration sites containing an outcrop and dividing them into
	 * A3 paper sized portions.
	 *
	 * @param sites a List of ExplorationSites
	 * @return a Collection of Lists
	 */
	public Collection<List<ExplorationSite>> divideExplorationSites(List<ExplorationSite> sites)
	{
		List<ExplorationSite> explorationSitesWhichIncludeOutcrop = Util.getExplorationSitesWhichIncludeOutcrop(sites, layerKind);

		Collection<List<ExplorationSite>> dividedExplorationSites = Util.separateBasedOnSize(explorationSitesWhichIncludeOutcrop, 17);

		return dividedExplorationSites;
	}

	abstract String buildTechnicalFeatures(List<ExplorationSite> explorationSites);

	abstract String buildEnvironmentTechnicalFeatures(List<ExplorationSite> explorationSites);

	HtmlTable constructAndGetTableObject()
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
