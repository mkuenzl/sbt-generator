package sbt.automization.data;

import sbt.automization.export.ATemplateExport;

import java.util.List;
import java.util.Map;

/**
 * A class which encapsulates multiple exploration sites for easy access.
 * To be refactored or removed.
 */
public final class TableInformation
{
	public static String exportPath;
	private final List<ExplorationSite> explorationSites;

	/**
	 * Constructor of the TableInformation class. Uses the parsed excel data to create all available ExplorationSites.
	 *
	 * @param parsedSiteInformation represents the parsed excel template information
	 * @param fileExportPath        an export path to the location of the excel template
	 */
	public TableInformation(List<Map<String, String>> parsedSiteInformation, String fileExportPath)
	{
		explorationSites = TableFactory.createExplorationSites(parsedSiteInformation);
		exportPath = fileExportPath;
	}

	/**
	 * Used to export exploration sites in different formats.
	 * Implemented as of now is only the Html export format.
	 *
	 * @param template expects a template export strategy
	 */
	public void export(ATemplateExport template) throws Exception {
		//template.export(this);
	}

	/**
	 * Exploration site getter.
	 *
	 * @return the list of parsed exploration sites
	 */
	public List<ExplorationSite> getExplorationSites()
	{
		return explorationSites;
	}
}
