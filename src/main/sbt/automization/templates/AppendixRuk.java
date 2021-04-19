package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.List;

public final class AppendixRuk extends AHtmlTemplate
{
    private static AppendixRuk instance;

    private AppendixRuk() {}

    public static AppendixRuk getInstance()
    {
        if (instance == null)
        {
            synchronized (AppendixRuk.class)
            {
                if (instance == null)
                {
                    instance = new AppendixRuk();
                }
            }
        }
        return instance;
    }

    @Override
    public void buildHtmlTable(final List<ExplorationSite> sites)
    {
        HtmlTable tableRuK = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();

        int rowCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (ExplorationSite explorationSite : sites)
        {
            List<Layer> sList = explorationSite.getSchichtList();

            for (Layer layer : sList)
            {
                if (rowCounter >= 20)
                {
                    stringBuilder.append(tableRuK.appendTag())
                            .append("<br>")
                            .append("<br>");

                    tableRuK = new HtmlTable.Builder()
                            .appendAttribute("class", "MsoNormalTable")
                            .appendAttribute("width", "605")
                            .appendAttribute("border", "1")
                            .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                            .appendAttribute("cellspacing", "0")
                            .appendAttribute("cellpadding", "0")
                            .appendContent(setHtmlTableHeader())
                            .build();

                    rowCounter = 0;
                }

                String rukNumber = layer.getInformation("SCHICHT_RUK_NR");

                if (! "-".equals(rukNumber))
                {

                    HtmlCell cellErkIdentifier = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent(explorationSite.getInformation("ERK_ID"))
                            .build();

                    HtmlCell cellRukVersuchNr = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent("A".concat(rukNumber))
                            .build();


                    HtmlCell cellRukProbenArt = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent(layer.getInformation("SCHICHT_RUK_PROBE"))
                            .build();

                    HtmlCell cellSchichtArtAndKoernung = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "170")
                            .appendContent(layer.getInformation("SCHICHT_ART").concat("  ").concat(layer.getInformation("SCHICHT_KOERNUNG")))
                            .build();


                    HtmlCell cellSchichtTiefeStart = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "35")
                            .appendAttribute("align", "center")
                            .appendContent(layer.getInformation("SCHICHT_TIEFE_START"))
                            .build();

                    HtmlCell cellSchichtTiefeConcatination = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "30")
                            .appendAttribute("align", "center")
                            .appendContent("-")
                            .build();

                    HtmlCell cellSchichtTiefeEnde = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "35")
                            .appendAttribute("align", "center")
                            .appendContent(layer.getInformation("SCHICHT_TIEFE_ENDE"))
                            .build();

                    HtmlCell cellSchichtRuk = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent(layer.getInformation("SCHICHT_RUK"))
                            .build();

                    HtmlRow htmlRow = new HtmlRow.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent(cellErkIdentifier.appendTag())
                            .appendContent(cellRukVersuchNr.appendTag())
                            .appendContent(cellRukProbenArt.appendTag())
                            .appendContent(cellSchichtArtAndKoernung.appendTag())
                            .appendContent(cellSchichtTiefeStart.appendTag())
                            .appendContent(cellSchichtTiefeConcatination.appendTag())
                            .appendContent(cellSchichtTiefeEnde.appendTag())
                            .appendContent(cellSchichtRuk.appendTag())
                            .build();

                    tableRuK.appendContent(htmlRow.appendTag());

                    rowCounter++;
                }
            }
        }
        stringBuilder.append(tableRuK.appendTag());

        setHtmlTable(stringBuilder.toString());
    }

    @Override
    String setHtmlTableHeader()
    {
        HtmlTableHeader cellERK = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "75")
                .appendAttribute("rowspan", "2")
                .appendContent("Erk. St.")
                .build();

        HtmlTableHeader cellVersuchNr = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "75")
                .appendAttribute("rowspan", "2")
                .appendContent("Versuch Nr.")
                .build();

        HtmlTableHeader cellProbenArt = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendAttribute("rowspan", "2")
                .appendContent("Probenart")
                .build();

        HtmlTableHeader cellPruefschicht = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "165")
                .appendAttribute("rowspan", "2")
                .appendContent("Prüfschicht")
                .build();

        HtmlTableHeader cellTiefe = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "100")
                .appendAttribute("colspan", "3")
                .appendContent("Prüftiefe")
                .build();

        HtmlTableHeader cellRuK = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendContent("Erw. RuK")
                .build();

        HtmlTableHeader cellTiefeCm = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "100")
                .appendAttribute("colspan", "3")
                .appendContent("cm")
                .build();

        HtmlTableHeader cellRuKC = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "95")
                .appendContent("°C")
                .build();

        //First Header Row
        HtmlRow firstHeaderRow = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cellERK.appendTag())
                .appendContent(cellVersuchNr.appendTag())
                .appendContent(cellProbenArt.appendTag())
                .appendContent(cellPruefschicht.appendTag())
                .appendContent(cellTiefe.appendTag())
                .appendContent(cellRuK.appendTag())
                .build();

        HtmlRow secondHeaderRow = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                .appendContent(cellTiefeCm.appendTag())
                .appendContent(cellRuKC.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstHeaderRow.appendTag())
                .append(secondHeaderRow.appendTag());

        return stringBuilder.toString();
    }

    @Override
    public void buildHtmlTable(final ExplorationSite site)
    {

    }

    @Override
    public String getExportFileName()
    {
        return "RUK_Tabelle.html";
    }
}
