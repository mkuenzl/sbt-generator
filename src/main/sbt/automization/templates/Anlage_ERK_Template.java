package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class Anlage_ERK_Template extends AHtmlTemplate
{
    private static Anlage_ERK_Template instance;

    private Anlage_ERK_Template() {}

    public static Anlage_ERK_Template getInstance()
    {
        if (instance == null)
        {
            synchronized (Anlage_ERK_Template.class)
            {
                if (instance == null)
                {
                    instance = new Anlage_ERK_Template();
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
    public void buildHtmlTable(final List<Erkundungsstelle> data)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0 ; i < data.size() ; i++)
        {
            Erkundungsstelle erkundungsstelle = data.get(i);

            HtmlCell cellTextERKORT = new HtmlCell.Builder()
                    .appendAttribute("width", "75")
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Erkund.-Stelle")
                    .build();

            HtmlCell cellERKORT = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendAttribute("colspan", "3")
                    .appendContent(erkundungsstelle.getInformation("ERK_ORT"))
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
                    .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                    .build();

            HtmlCell cellTextERKDATUM = new HtmlCell.Builder()
                    .appendAttribute("width", "75")
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Datum")
                    .build();

            HtmlCell cellERKDATUM = new HtmlCell.Builder()
                    .appendAttribute("width", "150")
                    .appendAttribute("class", "Normal")
                    .appendContent(erkundungsstelle.getInformation("ERK_DATUM"))
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
                    .appendContent("Koordinaten")
                    .build();

            HtmlCell cellERK_KOORDINATEN = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(erkundungsstelle.getInformation("ERK_KOORDINATEN"))
                    .build();


            HtmlCell cellTextERK_PRUEFER = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Probenehmer")
                    .build();

            HtmlCell cellERK_PRUEFER = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(erkundungsstelle.getInformation("ERK_PRUEFER"))
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
                    .appendContent(erkundungsstelle.getInformation("ERK_BEREICH"))
                    .build();


            HtmlCell cellTextERK_ANSPRECHPARNTER = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Ansprechpartner")
                    .build();

            HtmlCell cellERK_ANSPRECHPARNTER = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(erkundungsstelle.getInformation("ERK_ANSPRECHPARTNER"))
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

            if (erkundungsstelle.getSchichtAufschluss("FUGE").size() > 0)
            {
                Anlage_ERK_FUGE_Template erk_FUGE_Tabelle = new Anlage_ERK_FUGE_Template();
                erk_FUGE_Tabelle.buildHtmlTable(erkundungsstelle);
                stringBuilder.append(erk_FUGE_Tabelle.getHtmlTable());
            }

            if (erkundungsstelle.getSchichtAufschluss("OH").size() > 0)
            {
                Anlage_ERK_OH_Template erk_OH_Tabelle = new Anlage_ERK_OH_Template();
                erk_OH_Tabelle.buildHtmlTable(erkundungsstelle);
                stringBuilder.append(erk_OH_Tabelle.getHtmlTable());
            }

            if (erkundungsstelle.getSchichtAufschluss("GOB").size() > 0) {
                Anlage_ERK_OB_Template erk_OB_Tabelle = new Anlage_ERK_OB_Template();
                erk_OB_Tabelle.buildHtmlTable(erkundungsstelle);
                stringBuilder.append(erk_OB_Tabelle.getHtmlTable());
            }

            if (erkundungsstelle.getSchichtAufschluss("TOB").size() > 0) {
                Anlage_ERK_TOB_Template erk_TOB_Tabelle = new Anlage_ERK_TOB_Template();
                erk_TOB_Tabelle.buildHtmlTable(erkundungsstelle);
                stringBuilder.append(erk_TOB_Tabelle.getHtmlTable());
            }

            if (erkundungsstelle.getSchichtAufschluss("UG").size() > 0) {
                Anlage_ERK_UG_Template erk_UG_Tabelle = new Anlage_ERK_UG_Template();
                erk_UG_Tabelle.buildHtmlTable(erkundungsstelle);
                stringBuilder.append(erk_UG_Tabelle.getHtmlTable());
            }

            HtmlCell footer = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendAttribute("colspan", "3")
                    .appendContent(TextFormatUtil.formatErkFootnotes(erkundungsstelle))
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
    public void buildHtmlTable(final Erkundungsstelle data)
    {

    }

    @Override
    public String getExportFileName()
    {
        return "ERK_Tabelle.html";
    }
}
