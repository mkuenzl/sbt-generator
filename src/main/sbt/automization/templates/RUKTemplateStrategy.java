package sbt.automization.templates;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.templates.styles.TableStyle;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;
import java.util.List;

public final class RUKTemplateStrategy extends AHtmlTemplateStrategy
{
    private static RUKTemplateStrategy instance;

    private RUKTemplateStrategy(){}

    public static RUKTemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (RUKTemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new RUKTemplateStrategy();
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
                .appendAttribute("colspan", "2")
                .appendContent("Prüfschicht")
                .build();

        HtmlTableHeader cell15 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "100")
                .appendAttribute("colspan", "3")
                .appendContent("Prüftiefe | cm")
                .build();

        HtmlTableHeader cell16 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendContent("Erw. RuK | °C")
                .build();

        //First Header Row
        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("height", "2")
                .appendContent(cell11.appendTag())
                .appendContent(cell12.appendTag())
                .appendContent(cell13.appendTag())
                .appendContent(cell14.appendTag())
                .appendContent(cell15.appendTag())
                .appendContent(cell16.appendTag())
                .build();

        return row1.appendTag();
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


        for (int i = 0; i < data.size(); i++)
        {
            List<ASchicht> sList = data.get(i).getSchichtList();

            for (int j = 0; j < sList.size(); j++)
            {
                String rukValue = sList.get(j).getInformation("SCHICHT_RUK");

                if (!"".equals(rukValue)){
                    HtmlCell cell1 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent("1")
                            .build();

                    HtmlCell cell2 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent("1")
                            .build();

                    HtmlCell cell3 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent("1")
                            .build();

                    HtmlCell cell4 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "120")
                            .appendContent("1")
                            .build();

                    HtmlCell cell5 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "50")
                            .appendContent("1")
                            .build();

                    HtmlCell cell6 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "35")
                            .appendAttribute("align", "center")
                            .appendContent("1")
                            .build();

                    HtmlCell cell7 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "30")
                            .appendAttribute("align", "center")
                            .appendContent("-")
                            .build();

                    HtmlCell cell8 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("width", "35")
                            .appendAttribute("align", "center")
                            .appendContent("1")
                            .build();

                    HtmlCell cell9 = new HtmlCell.Builder()
                            .appendAttribute("class", "Normal")
                            .appendAttribute("align", "center")
                            .appendContent("1")
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
                            .appendContent(cell9.appendTag())
                            .build();

                    table.appendContent(htmlRow.appendTag());
                }
            }
        }

        setHtmlTable(table.appendTag());
    }
}
