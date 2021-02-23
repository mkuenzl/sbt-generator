package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.List;

public final class Anlage_RUK_TemplateStrategy extends AHtmlTemplateStrategy
{
    private static Anlage_RUK_TemplateStrategy instance;

    private Anlage_RUK_TemplateStrategy() {}

    public static Anlage_RUK_TemplateStrategy getInstance()
    {
        if (instance == null)
        {
            synchronized (Anlage_RUK_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Anlage_RUK_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public void buildHtmlTable(final List<Erkundungsstelle> data)
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


        for (Erkundungsstelle erkundungsstelle : data)
        {
            List<Schicht> sList = erkundungsstelle.getSchichtList();

            for (Schicht schicht : sList)
            {
                String rukNumber = schicht.getInformation("SCHICHT_RUK_NR");

                if (! "".equals(rukNumber))
                {

                    HtmlCell cell1 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                            .build();

                    HtmlCell cell2 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent("A".concat(rukNumber))
                            .build();


                    HtmlCell cell3 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent(schicht.getInformation("SCHICHT_RUK_PROBE"))
                            .build();

                    HtmlCell cell4 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "170")
                            .appendContent(schicht.getInformation("SCHICHT_ART").concat("  ").concat(schicht.getInformation("SCHICHT_KOERNUNG")))
                            .build();


                    HtmlCell cell5 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "35")
                            .appendAttribute("align", "center")
                            .appendContent(schicht.getInformation("SCHICHT_TIEFE_START"))
                            .build();

                    HtmlCell cell6 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "30")
                            .appendAttribute("align", "center")
                            .appendContent("-")
                            .build();

                    HtmlCell cell7 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "35")
                            .appendAttribute("align", "center")
                            .appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
                            .build();

                    HtmlCell cell8 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent(schicht.getInformation("SCHICHT_RUK"))
                            .build();

                    HtmlRow htmlRow = new HtmlRow.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent(cell1.appendTag())
                            .appendContent(cell2.appendTag())
                            .appendContent(cell3.appendTag())
                            .appendContent(cell4.appendTag())
                            .appendContent(cell5.appendTag())
                            .appendContent(cell6.appendTag())
                            .appendContent(cell7.appendTag())
                            .appendContent(cell8.appendTag())
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


        HtmlTableHeader cell11 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "75")
                .appendAttribute("rowspan", "2")
                .appendContent("Erk. St.")
                .build();

        HtmlTableHeader cell12 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "75")
                .appendAttribute("rowspan", "2")
                .appendContent("Versuch Nr.")
                .build();

        HtmlTableHeader cell13 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendAttribute("rowspan", "2")
                .appendContent("Probenart")
                .build();

        HtmlTableHeader cell14 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "165")
                .appendAttribute("rowspan", "2")
                .appendContent("Prüfschicht")
                .build();

        HtmlTableHeader cell15 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "100")
                .appendAttribute("colspan", "3")
                .appendContent("Prüftiefe")
                .build();

        HtmlTableHeader cell16 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendContent("Erw. RuK")
                .build();

//        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("colspan", "4")
//                .appendContent("")
//                .build();

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "100")
                .appendAttribute("colspan", "3")
                .appendContent("cm")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendContent("°C")
                .build();

        //First Header Row
        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell11.appendTag())
                .appendContent(cell12.appendTag())
                .appendContent(cell13.appendTag())
                .appendContent(cell14.appendTag())
                .appendContent(cell15.appendTag())
                .appendContent(cell16.appendTag())
                .build();

        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                // .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag());

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
