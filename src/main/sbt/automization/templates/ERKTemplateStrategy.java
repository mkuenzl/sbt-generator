package sbt.automization.templates;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.templates.styles.TableStyle;
import sbt.automization.util.html.*;

import java.util.List;

public final class ERKTemplateStrategy extends AHtmlTemplateStrategy
{
    private static ERKTemplateStrategy instance;

    private ERKTemplateStrategy(){}

    public static ERKTemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (ERKTemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new ERKTemplateStrategy();
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
    public void buildHtmlTable(final List<AErkundungsstelle> data)
    {
        HtmlCell cell11 = new HtmlCell.Builder()
                .appendAttribute("width", "75")
                .appendAttribute("class", "Normal")
                .appendContent("Erkund.-Stelle")
                .build();

        HtmlCell cell12 = new HtmlCell.Builder()
                .appendAttribute("class", "Normal")
                .appendAttribute("colspan", "3")
                .appendContent("12")
                .build();

        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("height", "1")
                .appendContent(cell11.appendTag())
                .appendContent(cell12.appendTag())
                .build();

        HtmlCell cell21 = new HtmlCell.Builder()
                .appendAttribute("width", "75")
                .appendAttribute("class", "Normal")
                .appendContent("Name")
                .build();

        HtmlCell cell22 = new HtmlCell.Builder()
                .appendAttribute("width", "150")
                .appendAttribute("class", "Normal")
                .appendContent("22")
                .build();

        HtmlCell cell23 = new HtmlCell.Builder()
                .appendAttribute("width", "75")
                .appendAttribute("class", "Normal")
                .appendContent("Datum")
                .build();

        HtmlCell cell24 = new HtmlCell.Builder()
                .appendAttribute("width", "150")
                .appendAttribute("class", "Normal")
                .appendContent("24")
                .build();

        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("height", "1")
                .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .appendContent(cell24.appendTag())
                .build();


        HtmlCell cell31 = new HtmlCell.Builder()
                .appendAttribute("class", "Normal")
                .appendContent("Koordinaten")
                .build();

        HtmlCell cell32 = new HtmlCell.Builder()
                .appendAttribute("class", "Normal")
                .appendContent("12")
                .build();


        HtmlCell cell33 = new HtmlCell.Builder()
                .appendAttribute("class", "Normal")
                .appendContent("Entnehmer")
                .build();

        HtmlCell cell34 = new HtmlCell.Builder()
                .appendAttribute("class", "Normal")
                .appendContent("12")
                .build();

        HtmlRow row3 = new HtmlRow.Builder()
                .appendAttribute("height", "1")
                .appendContent(cell31.appendTag())
                .appendContent(cell32.appendTag())
                .appendContent(cell33.appendTag())
                .appendContent(cell34.appendTag())
                .build();

        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", TableStyle.TABLE_STYLE1.getAttributes())
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(row1.appendTag())
                .appendContent(row2.appendTag())
                .appendContent(row3.appendTag())
                .build();

        ERK_GO_TemplateStrategy erk_GO_Tabelle = new ERK_GO_TemplateStrategy();
        erk_GO_Tabelle.buildHtmlTable(data);

        ERK_TOB_TemplateStrategy erk_TOB_Tabelle = new ERK_TOB_TemplateStrategy();
        erk_TOB_Tabelle.buildHtmlTable(data);

        ERK_UG_TemplateStrategy erk_UG_Tabelle = new ERK_UG_TemplateStrategy();
        erk_UG_Tabelle.buildHtmlTable(data);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(table.appendTag())
                .append("<br></br>")
                .append(erk_GO_Tabelle.getHtmlTable())
                .append("<br></br>")
                .append(erk_TOB_Tabelle.getHtmlTable())
                .append("<br></br>")
                .append(erk_UG_Tabelle.getHtmlTable());


        setHtmlTable(stringBuilder.toString());
    }

    @Override
    public String getExportFileName()
    {
        return "ERK_Tabelle.html";
    }
}
