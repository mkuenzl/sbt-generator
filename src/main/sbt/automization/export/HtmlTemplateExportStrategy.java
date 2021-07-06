package sbt.automization.export;


import sbt.automization.data.TableInformation;
import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.IHtmlTemplate;

import java.util.List;

public final class HtmlTemplateExportStrategy extends ATemplateExportStrategy
{
    public HtmlTemplateExportStrategy(final IHtmlTemplate strategy)
    {
        super(strategy);
    }

    @Override
    String format(final TableInformation tableInformation)
    {
        strategy.constructTable(tableInformation.getExplorationSites());
        return strategy.constructAndGetTemplate();
    }

    @Override
    String format(List<ExplorationSite> explorationSites)
    {
        strategy.constructTable(explorationSites);
        return strategy.constructAndGetTemplate();
    }
}
