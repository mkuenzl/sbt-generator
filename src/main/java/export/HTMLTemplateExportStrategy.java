package main.java.export;

import main.java.projekt.Projekt;
import main.java.templates.IHtmlTemplateStrategy;

public class HtmlTemplateExportStrategy extends ATemplateExportStrategy
{
    public HtmlTemplateExportStrategy(final IHtmlTemplateStrategy strategy)
    {
        super(strategy);
    }

    @Override
    String format(final Projekt projekt)
    {
        strategy.buildHtmlTable(projekt.getData());
        return strategy.buildHtmlTemplate();
    }
}
