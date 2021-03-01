package sbt.automization.templates;


import sbt.automization.data.Erkundungsstelle;

import java.util.List;

public interface IHtmlTemplate
{
    String buildHtmlTemplate();

    void buildHtmlTable(List<Erkundungsstelle> data);

    void buildHtmlTable(Erkundungsstelle data);

    String getExportFileName();
}