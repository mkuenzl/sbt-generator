package sbt.automization.templates;


import sbt.automization.engine.Erkundungsstelle;

import java.util.List;

public interface IHtmlTemplateStrategy
{
    String buildHtmlTemplate();

    void buildHtmlTable(List<Erkundungsstelle> data);

    void buildHtmlTable(Erkundungsstelle data);

    String getExportFileName();
}
