package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.List;

public final class Anlage_RUK_Template extends AHtmlTemplate
{
    private static Anlage_RUK_Template instance;

    private Anlage_RUK_Template() {}

    public static Anlage_RUK_Template getInstance()
    {
        if (instance == null)
        {
            synchronized (Anlage_RUK_Template.class)
            {
                if (instance == null)
                {
                    instance = new Anlage_RUK_Template();
                }
            }
        }
        return instance;
    }

    @Override
    public void buildHtmlTable(final List<Erkundungsstelle> erkundungsstellen)
    {
        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();


        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> sList = erkundungsstelle.getSchichtList();

            for (Schicht schicht : sList)
            {
                String rukNumber = schicht.getInformation("SCHICHT_RUK_NR");

                if (! "-".equals(rukNumber))
                {

                    HtmlCell cellErkIdentifier = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                            .build();

                    HtmlCell cellRukVersuchNr = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent("A".concat(rukNumber))
                            .build();


                    HtmlCell cellRukProbenArt = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent(schicht.getInformation("SCHICHT_RUK_PROBE"))
                            .build();

                    HtmlCell cellSchichtArtAndKoernung = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "170")
                            .appendContent(schicht.getInformation("SCHICHT_ART").concat("  ").concat(schicht.getInformation("SCHICHT_KOERNUNG")))
                            .build();


                    HtmlCell cellSchichtTiefeStart = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "35")
                            .appendAttribute("align", "center")
                            .appendContent(schicht.getInformation("SCHICHT_TIEFE_START"))
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
                            .appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
                            .build();

                    HtmlCell cellSchichtRuk = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent(schicht.getInformation("SCHICHT_RUK"))
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

                    table.appendContent(htmlRow.appendTag());
                }
            }
        }

        setHtmlTable(table.appendTag());
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
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "100")
                .appendAttribute("colspan", "3")
                .appendContent("cm")
                .build();

        HtmlTableHeader cellRuKC = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
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
    public void buildHtmlTable(final Erkundungsstelle data)
    {

    }

    @Override
    public String getExportFileName()
    {
        return "RUK_Tabelle.html";
    }
}
