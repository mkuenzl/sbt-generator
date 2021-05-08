package sbt.automization.templates;


import sbt.automization.data.ExplorationSite;

import java.util.List;

public interface IHtmlTemplate
{
    String constructAndGetTemplate();

    void constructTable(List<ExplorationSite> sites);

    void constructTable(ExplorationSite site);

    String getExportFileName();
}
