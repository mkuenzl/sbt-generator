package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.*;

import java.util.List;

public final class Anlage_ERK_TemplateStrategy extends AHtmlTemplateStrategy
{
    private static Anlage_ERK_TemplateStrategy instance;

    private Anlage_ERK_TemplateStrategy(){}

    public static Anlage_ERK_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Anlage_ERK_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Anlage_ERK_TemplateStrategy();
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

        for (int i = 0; i < data.size(); i++)
        {
            Erkundungsstelle erkundungsstelle = data.get(i);

            HtmlCell cell11 = new HtmlCell.Builder()
                    .appendAttribute("width", "75")
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Erkund.-Stelle")
                    .build();

            HtmlCell cell12 = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendAttribute("colspan", "3")
                    .appendContent(erkundungsstelle.getInformation("ERK_ORT"))
                    .build();

            HtmlRow row1 = new HtmlRow.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(cell11.appendTag())
                    .appendContent(cell12.appendTag())
                    .build();

            HtmlCell cell21 = new HtmlCell.Builder()
                    .appendAttribute("width", "75")
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Bezeichnung")
                    .build();

            HtmlCell cell22 = new HtmlCell.Builder()
                    .appendAttribute("width", "150")
                    .appendAttribute("class", "Normal")
                    .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                    .build();

            HtmlCell cell23 = new HtmlCell.Builder()
                    .appendAttribute("width", "75")
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Datum")
                    .build();

            HtmlCell cell24 = new HtmlCell.Builder()
                    .appendAttribute("width", "150")
                    .appendAttribute("class", "Normal")
                    .appendContent(erkundungsstelle.getInformation("ERK_DATUM"))
                    .build();

            HtmlRow row2 = new HtmlRow.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(cell21.appendTag())
                    .appendContent(cell22.appendTag())
                    .appendContent(cell23.appendTag())
                    .appendContent(cell24.appendTag())
                    .build();


            HtmlCell cell31 = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Koordinaten")
                    .build();

            HtmlCell cell32 = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(erkundungsstelle.getInformation("ERK_KOORDINATEN"))
                    .build();


            HtmlCell cell33 = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalHeader")
                    .appendContent("Probenehmer")
                    .build();

            HtmlCell cell34 = new HtmlCell.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(erkundungsstelle.getInformation("ERK_PRUEFER"))
                    .build();

            HtmlRow row3 = new HtmlRow.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(cell31.appendTag())
                    .appendContent(cell32.appendTag())
                    .appendContent(cell33.appendTag())
                    .appendContent(cell34.appendTag())
                    .build();

            HtmlTable table = new HtmlTable.Builder()
                    .appendAttribute("class", "MsoNormalTable")
                    .appendAttribute("width", "605")
                    .appendAttribute("border", "1")
                    .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                    .appendAttribute("cellspacing", "0")
                    .appendAttribute("cellpadding", "0")
                    .appendContent(row1.appendTag())
                    .appendContent(row2.appendTag())
                    .appendContent(row3.appendTag())
                    .build();

            Anlage_ERK_OB_TemplateStrategy erk_GO_Tabelle = new Anlage_ERK_OB_TemplateStrategy();
            erk_GO_Tabelle.buildHtmlTable(erkundungsstelle);

            Anlage_ERK_TOB_TemplateStrategy erk_TOB_Tabelle = new Anlage_ERK_TOB_TemplateStrategy();
            erk_TOB_Tabelle.buildHtmlTable(erkundungsstelle);

            Anlage_ERK_UG_TemplateStrategy erk_UG_Tabelle = new Anlage_ERK_UG_TemplateStrategy();
            erk_UG_Tabelle.buildHtmlTable(erkundungsstelle);

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



            stringBuilder.append(table.appendTag())
                    //.append("<br></br>")
                    .append(erk_GO_Tabelle.getHtmlTable())
                    //.append("<br></br>")
                    .append(erk_TOB_Tabelle.getHtmlTable())
                    //.append("<br></br>")
                    .append(erk_UG_Tabelle.getHtmlTable())
                    .append(footNotes.appendTag())
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
