package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.Projekt;
import main.java.wordblocks.WordObjectRow;

import java.util.List;

public interface ITemplateStrategy {
    String buildHtmlTable(final Projekt projekt);

    void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList);

    List<WordObjectRow> buildRows(AErkundungsstelle erkundungsstelle);

    String getHtmlHead();
}
