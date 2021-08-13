package sbt.automization.templates;


import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Probe;

import java.util.List;

public interface HtmlTableTemplate
{
    void constructTable(List<ExplorationSite> explorationSites);

    void constructTable(ExplorationSite explorationSite);

    String getExportFileName();

    String getTable();

    void constructTemplate(List<DataTable> tables);
}
