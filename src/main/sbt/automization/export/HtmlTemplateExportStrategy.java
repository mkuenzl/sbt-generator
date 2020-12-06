package sbt.automization.export;


import sbt.automization.projekt.Projekt;
import sbt.automization.templates.IHtmlTemplateStrategy;

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
