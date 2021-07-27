package sbt.automization.data.refactoring;

import sbt.automization.export.ATemplateExport;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A class which encapsulates multiple data tables for easy access.
 * To be refactored or removed.
 */
public final class Project
{
	public final String exportPath;
	private final Collection<DataTable> tables;


	public Project(List<Map<String, String>> parsedCsv, String exportPath)
	{
		this.tables = DataTableFactory.createListOfProbes(parsedCsv);
		this.exportPath = exportPath;
	}

	/**
	 * Constructor of the TableInformation class. Uses the parsed excel data to create all available ExplorationSites.
	 *
	 * @param probes     represents the parsed excel template information
	 * @param exportPath an export path to the location of the excel template
	 */
	public Project(Collection<DataTable> probes, String exportPath)
	{
		this.tables = probes;
		this.exportPath = exportPath;
	}

	/**
	 * Constructor of the TableInformation class. Uses the parsed excel data to create all available ExplorationSites.
	 *
	 * @param probes represents the parsed excel template information
	 */
	public Project(Collection<DataTable> probes)
	{
		this.tables = probes;
		this.exportPath = "";
	}

	/**
	 * Used to export data tables in different formats.
	 * Implemented as of now is only the Html export format.
	 *
	 * @param template expects a template export strategy
	 */
	public void export(ATemplateExport template) throws Exception
	{
		//template.export(this);
	}

	/**
	 * Table getter.
	 *
	 * @return the list of parsed data tables
	 */
	public Collection<DataTable> getTables()
	{
		return tables;
	}

	public String getExportPath()
	{
		return exportPath;
	}
}

