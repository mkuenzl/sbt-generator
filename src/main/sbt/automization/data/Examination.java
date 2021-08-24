package sbt.automization.data;

import sbt.automization.export.ATemplateExport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A class which encapsulates multiple data tables for easy access.
 * To be refactored or removed.
 */
public final class Examination extends ArrayList<DataTable>
{
	public static String exportPath = "";

	public Examination(List<Map<String, String>> parsedCsv, String exportPath)
	{
		this.addAll(DataTableFactory.getProbes(parsedCsv));
		this.exportPath = exportPath;
	}

	/**
	 * Constructor of the TableInformation class. Uses the parsed excel data to create all available ExplorationSites.
	 *
	 * @param probes     represents the parsed excel template information
	 * @param exportPath an export path to the location of the excel template
	 */
	public Examination(Collection<DataTable> probes, String exportPath)
	{
		this.addAll(probes);
		this.exportPath = exportPath;
	}

	/**
	 * Constructor of the TableInformation class. Uses the parsed excel data to create all available ExplorationSites.
	 *
	 * @param probes represents the parsed excel template information
	 */
	public Examination(Collection<DataTable> probes)
	{
		this.addAll(probes);
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
		template.export(this);
	}

	public String getExportPath()
	{
		return exportPath;
	}
}

