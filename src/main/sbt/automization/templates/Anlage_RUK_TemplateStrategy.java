package sbt.automization.templates;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.templates.styles.TableStyle;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;
import java.util.List;

public final class Anlage_RUK_TemplateStrategy extends AHtmlTemplateStrategy
{
    private int counter = 1;

    private static Anlage_RUK_TemplateStrategy instance;

    private Anlage_RUK_TemplateStrategy(){}

    public static Anlage_RUK_TemplateStrategy getInstance(){
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
    String setHtmlTableHeader()
    {
        HtmlTableHeader cell11 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "75")
                .appendContent("Versuch Nr.")
                .build();

        HtmlTableHeader cell12 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "75")
                .appendContent("Erk. St.")
                .build();

        HtmlTableHeader cell13 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendContent("Probenart")
                .build();

        HtmlTableHeader cell14 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "165")
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

        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("colspan", "4")
                .appendContent("")
                .build();

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
                .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag());

        return stringBuilder.toString();
    }

    @Override
    public String getExportFileName()
    {
        return "RUK_Tabelle.html";
    }

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
            List<ASchicht> sList = erkundungsstelle.getSchichtList();

            for (ASchicht schicht : sList)
            {
                String rukValue = schicht.getInformation("SCHICHT_RUK");

                if (!"".equals(rukValue)){
                    HtmlCell cell1 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent(String.valueOf(counter++))
                            .build();

                    HtmlCell cell2 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                            .build();

                    HtmlCell cell3 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent("Einzelprobe")
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
    public void buildHtmlTable(final AErkundungsstelle data)
    {

    }
}
