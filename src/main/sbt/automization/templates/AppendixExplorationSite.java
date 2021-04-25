package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class AppendixExplorationSite extends AHtmlTemplate
{
    private static AppendixExplorationSite instance;

    private AppendixExplorationSite() {}

    public static AppendixExplorationSite getInstance()
    {
        if (instance == null)
        {
            synchronized (AppendixExplorationSite.class)
            {
                if (instance == null)
                {
                    instance = new AppendixExplorationSite();
                }
            }
        }
        return instance;
    }

    //Hat sogesehen keinen Header
    @Override
    String setHtmlTableHeader()
    {
        return null;
    }

    //Erstellt die Informationen und ruft dann Methoden einzelner Klassen auf
    @Override
    public void buildHtmlTable(final List<ExplorationSite> sites)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0 ; i < sites.size() ; i++)
        {
            ExplorationSite explorationSite = sites.get(i);

            HtmlCell cellTextERKORT = new HtmlCell.Builder()
                    .appendAttribute("width", "75")
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Erkund.-Stelle")
                    .build();

            HtmlCell cellERKORT = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendAttribute("colspan", "3")
                    .appendContent(explorationSite.getInformation("ERK_ORT"))
                    .build();

            HtmlRow firstErkRow = new HtmlRow.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(cellTextERKORT.appendTag())
                    .appendContent(cellERKORT.appendTag())
                    .build();

            HtmlCell cellTextERKID = new HtmlCell.Builder()
                    .appendAttribute("width", "75")
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Bezeichnung")
                    .build();

            HtmlCell cellERKID = new HtmlCell.Builder()
                    .appendAttribute("width", "150")
                    .appendAttribute("class", "Normal")
                    .appendContent(explorationSite.getInformation("ERK_ID"))
                    .build();

            HtmlCell cellTextERKDATUM = new HtmlCell.Builder()
                    .appendAttribute("width", "75")
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Datum")
                    .build();

            HtmlCell cellERKDATUM = new HtmlCell.Builder()
                    .appendAttribute("width", "150")
                    .appendAttribute("class", "Normal")
                    .appendContent(explorationSite.getInformation("ERK_DATUM"))
                    .build();

            HtmlRow secondErkRow = new HtmlRow.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(cellTextERKID.appendTag())
                    .appendContent(cellERKID.appendTag())
                    .appendContent(cellTextERKDATUM.appendTag())
                    .appendContent(cellERKDATUM.appendTag())
                    .build();


            HtmlCell cellTextERK_KOORDINATEN = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Koordinaten<sup>1)</sup>")
                    .build();

            HtmlCell cellERK_KOORDINATEN = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(explorationSite.getInformation("ERK_KOORDINATEN"))
                    .build();


            HtmlCell cellTextERK_PRUEFER = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Probenehmer")
                    .build();

            HtmlCell cellERK_PRUEFER = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(explorationSite.getInformation("ERK_PRUEFER"))
                    .build();

            HtmlRow thirdErkRow = new HtmlRow.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(cellTextERK_KOORDINATEN.appendTag())
                    .appendContent(cellERK_KOORDINATEN.appendTag())
                    .appendContent(cellTextERK_PRUEFER.appendTag())
                    .appendContent(cellERK_PRUEFER.appendTag())
                    .build();

            HtmlCell cellTextERK_BEREICH = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Bereich")
                    .build();

            HtmlCell cellERK_BEREICH = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(explorationSite.getInformation("ERK_BEREICH"))
                    .build();


            HtmlCell cellTextERK_ANSPRECHPARNTER = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Ansprechpartner")
                    .build();

            HtmlCell cellERK_ANSPRECHPARNTER = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(explorationSite.getInformation("ERK_ANSPRECHPARTNER"))
                    .build();

            HtmlRow fourthErkRow = new HtmlRow.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(cellTextERK_BEREICH.appendTag())
                    .appendContent(cellERK_BEREICH.appendTag())
                    .appendContent(cellTextERK_ANSPRECHPARNTER.appendTag())
                    .appendContent(cellERK_ANSPRECHPARNTER.appendTag())
                    .build();

            HtmlTable table = new HtmlTable.Builder()
                    .appendAttribute("class", "MsoNormalTable")
                    .appendAttribute("width", "605")
                    .appendAttribute("border", "1")
                    .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                    .appendAttribute("cellspacing", "0")
                    .appendAttribute("cellpadding", "0")
                    .appendContent(firstErkRow.appendTag())
                    .appendContent(secondErkRow.appendTag())
                    .appendContent(thirdErkRow.appendTag())
                    .appendContent(fourthErkRow.appendTag())
                    .build();

            stringBuilder.append(table.appendTag());

            if (explorationSite.getLayersWithOutcrop("FUGE").size() > 0)
            {
                AppendixSiteFUGE erk_FUGE_Tabelle = new AppendixSiteFUGE();
                erk_FUGE_Tabelle.buildHtmlTable(explorationSite);
                stringBuilder.append(erk_FUGE_Tabelle.getHtmlTable());
            }

            if (explorationSite.getLayersWithOutcrop("OH").size() > 0)
            {
                AppendixSiteOH erk_OH_Tabelle = new AppendixSiteOH();
                erk_OH_Tabelle.buildHtmlTable(explorationSite);
                stringBuilder.append(erk_OH_Tabelle.getHtmlTable());
            }

            if (explorationSite.getLayersWithOutcrop("GOB").size() > 0) {
                AppendixSiteGOB erk_OB_Tabelle = new AppendixSiteGOB();
                erk_OB_Tabelle.buildHtmlTable(explorationSite);
                stringBuilder.append(erk_OB_Tabelle.getHtmlTable());
            }

            if (explorationSite.getLayersWithOutcrop("TOB").size() > 0) {
                AppendixSiteTOB erk_TOB_Tabelle = new AppendixSiteTOB();
                erk_TOB_Tabelle.buildHtmlTable(explorationSite);
                stringBuilder.append(erk_TOB_Tabelle.getHtmlTable());
            }

            if (explorationSite.getLayersWithOutcrop("UG").size() > 0) {
                AppendixSiteUG erk_UG_Tabelle = new AppendixSiteUG();
                erk_UG_Tabelle.buildHtmlTable(explorationSite);
                stringBuilder.append(erk_UG_Tabelle.getHtmlTable());
            }

            HtmlCell footer = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendAttribute("colspan", "3")
                    .appendContent(TextFormatUtil.formatSiteFootnotes(explorationSite))
                    .build();

            HtmlRow footerRow = new HtmlRow.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(footer.appendTag())
                    .build();

            HtmlTable footNotes = new HtmlTable.Builder()
                    .appendAttribute("class", "MsoNormalTable")
                    .appendAttribute("width", "605")
                    .appendAttribute("border", "1")
                    .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                    .appendAttribute("cellspacing", "0")
                    .appendAttribute("cellpadding", "0")
                    .appendContent(footerRow.appendTag())
                    .build();


            stringBuilder.append(footNotes.appendTag())
                    .append("<br></br>");
        }

        setHtmlTable(stringBuilder.toString());
    }

    @Override
    public void buildHtmlTable(final ExplorationSite site)
    {

    }

    @Override
    public String getExportFileName()
    {
        return "ERK_Tabelle.html";
    }
}
