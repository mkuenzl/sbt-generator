package sbt.automization.templates;

import sbt.automization.format.TextFormatUtil;
import sbt.automization.engine.Erkundungsstelle;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class Bericht_OB_TemplateStrategy extends AHtmlTemplateStrategy
{
    private static Bericht_OB_TemplateStrategy instance;

    private Bericht_OB_TemplateStrategy(){}

    public static Bericht_OB_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Bericht_OB_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Bericht_OB_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    String setHtmlTableHeader()
    {
        return null;
    }

    @Override
    public void buildHtmlTable(final List<Erkundungsstelle> data)
    {
        //Sort Data nach OB
        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .build();

        //First Row
        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "100")
                        .appendContent("Erkundungsstelle")
                        .build()
                        .appendTag())
                .build();

        for (Erkundungsstelle erkundungsstelle :
                data)
        {
            HtmlCell htmlCell = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalBericht")
                    .appendAttribute("width", "50")
                    .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                    .build();

            row1.appendContent(htmlCell.appendTag());
        }

        //Second Row
        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "100")
                        .appendContent("Aufschlussart")
                        .build()
                        .appendTag())
                .build();

        for (Erkundungsstelle erkundungsstelle :
                data)
        {
            HtmlCell htmlCell = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalBericht")
                    .appendAttribute("width", "50")
                    .appendContent(erkundungsstelle.getInformation("ERK_AUFSCHLUSSARTEN"))
                    .build();

            row2.appendContent(htmlCell.appendTag());
        }

        //Third Row
        HtmlRow row3 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("colspan", String.valueOf(1+data.size()))
                        .appendContent("Technische Merkmale")
                        .build()
                        .appendTag())
                .build();

        //Forth Row
        HtmlRow row4 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "100")
                        .appendContent("Gesamtdicke geb. Oberbau cm")
                        .build()
                        .appendTag())
                .build();

        for (Erkundungsstelle erkundungsstelle :
                data)
        {
            HtmlCell htmlCell = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalBericht")
                    .appendAttribute("width", "50")
                    .appendContent(TextFormatUtil.formatErkAufschlussDicke(erkundungsstelle, "GOB"))
                    .build();

            row4.appendContent(htmlCell.appendTag());
        }


        table.appendContent(row1.appendTag());
        table.appendContent(row2.appendTag());
        table.appendContent(row3.appendTag());
        table.appendContent(row4.appendTag());

        setHtmlTable(table.appendTag());
    }

    @Override
    public void buildHtmlTable(final Erkundungsstelle data)
    {

    }

    @Override
    public String getExportFileName()
    {
        return "Bericht_OB_Table.html";
    }
}
