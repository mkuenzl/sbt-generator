package sbt.automization.export;


import sbt.automization.TableEngine;
import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.IHtmlTemplate;

import java.util.List;

public class HtmlTemplateExportStrategy extends ATemplateExportStrategy
{
    public HtmlTemplateExportStrategy(final IHtmlTemplate strategy)
    {
        super(strategy);
    }

    @Override
    String format(final TableEngine tableEngine)
    {
        strategy.buildHtmlTable(tableEngine.getExplorationSites());
        return strategy.buildHtmlTemplate();
    }

    @Override
    String format(List<ExplorationSite> explorationSites)
    {
        strategy.buildHtmlTable(explorationSites);
        return strategy.buildHtmlTemplate();
    }
}
