package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.Projekt;
import main.java.wordblocks.WORow;

import java.util.List;

public interface ITemplateStrategy {
    String buildHtmlTable(final Projekt projekt);

    void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList);

    List<WORow> buildRows(AErkundungsstelle erkundungsstelle);

    String getHtmlHead();
}
