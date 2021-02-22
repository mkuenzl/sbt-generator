package sbt.automization.export;


import sbt.automization.TableEngine;
import sbt.automization.templates.IHtmlTemplateStrategy;

public class HtmlTemplateExportStrategy extends ATemplateExportStrategy
{
    public HtmlTemplateExportStrategy(final IHtmlTemplateStrategy strategy)
    {
        super(strategy);
    }

    @Override
    String format(final TableEngine tableEngine)
    {
        strategy.buildHtmlTable(tableEngine.getData());
        return strategy.buildHtmlTemplate();
    }
}
