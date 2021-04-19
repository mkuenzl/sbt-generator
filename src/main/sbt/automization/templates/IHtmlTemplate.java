package sbt.automization.templates;


import sbt.automization.data.ExplorationSite;

import java.util.List;

public interface IHtmlTemplate
{
    String buildHtmlTemplate();

    void buildHtmlTable(List<ExplorationSite> sites);

    void buildHtmlTable(ExplorationSite site);

    String getExportFileName();
}
