package sbt.automization.templates;


import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.templates.styles.TableStyle;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.List;

public final class PNTemplateStrategy extends AHtmlTemplateStrategy
{
    private static PNTemplateStrategy instance;


    private PNTemplateStrategy(){}

    public static PNTemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (PNTemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new PNTemplateStrategy();
                }
            }
        }
        return instance;
    }

    private int counter = 0;

    @Override
    public void buildHtmlTable(final List<AErkundungsstelle> data)
    {
        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", TableStyle.TABLE_STYLE1.getAttributes())
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();


        for (AErkundungsstelle erkundungsstelle : data)
        {
            List<ASchicht> schichtList = erkundungsstelle.getSchichtList();

            for (ASchicht schicht : schichtList)
            {
                HtmlCell cell1 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_ID"))
                        .build();

                HtmlCell cell2 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("EP")
                        .build();

                HtmlCell cell3 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_BEHAELTNIS"))
                        .build();

                HtmlCell cell4 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell5 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("")
                        .build();

                //HIER MUSS MEHR CODE STRATEGY?? TODO
                HtmlCell cell6 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "95")
                        .appendContent(schicht.getInformation("SCHICHT_ART"))
                        .build();

                HtmlCell cell7 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "30")
                        .appendContent(schicht.getInformation("SCHICHT_KOERNUNG"))
                        .build();

                HtmlCell cell8 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_FARBE"))
                        .build();

                HtmlCell cell9 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                        .build();

                HtmlCell cell10 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_TIEFE"))
                        .build();

                HtmlCell cell11 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("")
                        .build();

                HtmlRow row = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cell1.appendTag())
                        .appendContent(cell2.appendTag())
                        .appendContent(cell3.appendTag())
                        .appendContent(cell4.appendTag())
                        .appendContent(cell5.appendTag())
                        .appendContent(cell6.appendTag())
                        .appendContent(cell7.appendTag())
                        .appendContent(cell8.appendTag())
                        .appendContent(cell9.appendTag())
                        .appendContent(cell10.appendTag())
                        .appendContent(cell11.appendTag())
                        .build();

                table.appendContent(row.appendTag());
            }
        }

        setHtmlTable(table.appendTag());
    }

    @Override
    public void buildHtmlTable(final AErkundungsstelle data)
    {

    }

    @Override
    String setHtmlTableHeader()
    {
        HtmlTableHeader cell1 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("Probe")
                .build();

        HtmlTableHeader cell2 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "30")
                .appendContent("Art")
                .build();

        HtmlTableHeader cell3 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "65")
                .appendContent("Behältnis")
                .build();

        HtmlTableHeader cell4 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "47")
                .appendContent("Vol.")
                .build();

        HtmlTableHeader cell5 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "57")
                .appendContent("Haufwerk Vol.")
                .build();

        HtmlTableHeader cell6 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "125")
                .appendAttribute("colspan", "2")
                .appendContent("Abfallart")
                .build();

        HtmlTableHeader cell7 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "76")
                .appendContent("Farbe")
                .build();

        HtmlTableHeader cell8 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendContent("Erk. St.")
                .build();

        HtmlTableHeader cell9 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "57")
                .appendContent("Tiefe")
                .build();

        HtmlTableHeader cell10 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "65")
                .appendContent("Notiz")
                .build();

        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("colspan", "3")
                .appendContent("")
                .build();

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("L")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("L")
                .build();

        HtmlTableHeader cell24 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("colspan", "4")
                .appendContent("")
                .build();

        HtmlTableHeader cell25 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();

        HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("")
                .build();


        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell1.appendTag())
                .appendContent(cell2.appendTag())
                .appendContent(cell3.appendTag())
                .appendContent(cell4.appendTag())
                .appendContent(cell5.appendTag())
                .appendContent(cell6.appendTag())
                .appendContent(cell7.appendTag())
                .appendContent(cell8.appendTag())
                .appendContent(cell9.appendTag())
                .appendContent(cell10.appendTag())
                .build();

        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .appendContent(cell24.appendTag())
                .appendContent(cell25.appendTag())
                .appendContent(cell26.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag());

        return stringBuilder.toString();
    }

    @Override
    public String getExportFileName()
    {
        return "PN_Tabelle.html";
    }
}
