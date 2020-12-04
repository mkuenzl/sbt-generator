package main.java.templates;

import main.java.projekt.AErkundungsstelle;

import java.util.List;

public interface IHtmlTemplateStrategy
{
    String buildHtmlTemplate();

    void buildHtmlTable(List<AErkundungsstelle> data);
}
