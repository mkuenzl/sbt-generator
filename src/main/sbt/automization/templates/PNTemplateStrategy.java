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


        for (int i = 0; i < data.size(); i++)
        {
            AErkundungsstelle erkTmp = data.get(i);
            List<ASchicht> schichtList = erkTmp.getSchichtList();

            for (int j = 0; j < schichtList.size(); j++)
            {
                HtmlCell cell1 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell2 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell3 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell4 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell5 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell6 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell7 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell8 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell9 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
                        .build();

                HtmlCell cell10 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Cell" + counter++)
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
                        .build();

                table.appendContent(row.appendTag());
            }
        }

        setHtmlTable(table.appendTag());
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
                .appendContent("Vol. | L")
                .build();

        HtmlTableHeader cell5 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "57")
                .appendContent("Haufwerk Vol. | L")
                .build();

        HtmlTableHeader cell6 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
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
                .appendContent("Tiefe | cm")
                .build();

        HtmlTableHeader cell10 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendContent("Bemerkungen")
                .build();

        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("height", "2")
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

        return row.appendTag();
    }

    @Override
    public String getExportFileName()
    {
        return "PN_Tabelle.html";
    }
}
