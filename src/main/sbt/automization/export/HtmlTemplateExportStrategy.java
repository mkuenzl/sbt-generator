package sbt.automization.export;


import sbt.automization.TableEngine;
import sbt.automization.data.Erkundungsstelle;
import sbt.automization.templates.IHtmlTemplateStrategy;

import java.util.List;

public class HtmlTemplateExportStrategy extends ATemplateExportStrategy
{
    public HtmlTemplateExportStrategy(final IHtmlTemplateStrategy strategy)
    {
        super(strategy);
    }

    @Override
    String format(final TableEngine tableEngine)
    {
        strategy.buildHtmlTable(tableEngine.getErkundungsstellen());
        return strategy.buildHtmlTemplate();
    }

    @Override
    String format(List<Erkundungsstelle> erkundungsstellen)
    {
        strategy.buildHtmlTable(erkundungsstellen);
        return strategy.buildHtmlTemplate();
    }
}
