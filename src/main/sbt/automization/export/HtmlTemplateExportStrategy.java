package sbt.automization.export;


import sbt.automization.TableEngine;
import sbt.automization.data.Erkundungsstelle;
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
