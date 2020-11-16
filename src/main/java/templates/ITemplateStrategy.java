package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.AErkundungsstelleSchicht;
import main.java.projekt.Erkundungsstelle;
import main.java.projekt.Projekt;
import main.java.wordblocks.WordObjectRow;

public interface ITemplateStrategy {
    String buildHtmlTable(final Projekt projekt);

    void buildTableObject(final Projekt projekt);

    WordObjectRow buildRow(AErkundungsstelle erkundungsstelle);
}
