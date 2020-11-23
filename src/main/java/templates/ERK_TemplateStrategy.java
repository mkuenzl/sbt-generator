package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.Projekt;
import main.java.wordblocks.WordObjectRow;

import java.util.List;

public final class ERK_TemplateStrategy extends  ATemplateStrategy
{
    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
        return null;
    }

    @Override
    public void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList)
    {

    }

    @Override
    public List<WordObjectRow> buildRows(final AErkundungsstelle erkundungsstelle)
    {
        return null;
    }

    @Override
    public String getHtmlHead()
    {
        return null;
    }
}
