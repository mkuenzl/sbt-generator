package sbt.automization.templates;


import sbt.automization.projekt.AErkundungsstelle;

import java.util.List;

public interface IHtmlTemplateStrategy
{
    String buildHtmlTemplate();

    void buildHtmlTable(List<AErkundungsstelle> data);

    void buildHtmlTable(AErkundungsstelle data);

    String getExportFileName();
}
