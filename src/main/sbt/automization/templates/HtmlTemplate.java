package sbt.automization.templates;


import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;

import java.util.List;

public interface HtmlTemplate
{
	void constructTable(List<ExplorationSite> explorationSites);

	void constructTable(ExplorationSite explorationSite);

	String getExportFileName();

	String getTemplate();

	void constructTemplate(List<DataTable> dataTables);

	void constructTemplate(DataTable dataTable);
}
