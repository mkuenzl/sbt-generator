package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.util.Util;
import sbt.automization.util.html.HtmlTable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class for all report tables inherits from AHtmlTable
 */
public abstract class AReportTable extends AHtmlTable
{
	String layerKind;

	/**
	 * Method used to retrieve all exploration sites containing an outcrop and dividing them into
	 * A3 paper sized portions.
	 *
	 * @param sites a List of ExplorationSites
	 * @return a Collection of Lists
	 */
	public Collection<List<ExplorationSite>> divideExplorationSites(List<ExplorationSite> sites)
	{
		List<ExplorationSite> templateExplorationSites = sites.stream()
				.filter(e -> e.getLayersWithOutcrop(layerKind).size() > 0)
				.collect(Collectors.toList());

		Collection<List<ExplorationSite>> dividedExplorationSites = Util.separateBasedOnSize(templateExplorationSites, 17);

		return dividedExplorationSites;
	}

	abstract String buildTechnicalFeatures(List<ExplorationSite> explorationSites);

	abstract String buildEnvironmentTechnicalFeatures(List<ExplorationSite> explorationSites);

	@Override
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
