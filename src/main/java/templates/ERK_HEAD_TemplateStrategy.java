package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.Projekt;
import main.java.wordblocks.*;

import java.util.ArrayList;
import java.util.List;

public final class ERK_HEAD_TemplateStrategy extends ATemplateStrategy
{

    private static ERK_HEAD_TemplateStrategy instance;

    private ERK_HEAD_TemplateStrategy(){}

    public static ERK_HEAD_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (ERK_HEAD_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new ERK_HEAD_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    private String htmlRowStyle = "style='mso-yfti-irow:0;mso-yfti-firstrow:yes;height:19.85pt'";

    private final String htmlContentStyle = "class=MsoNormal style='margin-bottom:0cm;line-height:normal'";

    private String htmlSpan = "style='font-size:10.0pt;font-family:\"Arial\",sans-serif'";


    public String buildHtmlTable(final AErkundungsstelle erkundungsstelle)
    {
        WordObjectTable ERK_HEAD_Table = new WordObjectTable("class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=604\n" +
                " style='width:453.35pt;border-collapse:collapse;border:none;mso-border-alt:\n" +
                " solid windowtext .5pt;mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt'");


        ERK_HEAD_Table.addTableRows(buildRows(erkundungsstelle));
        return ERK_HEAD_Table.printToHtml();
    }

    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
        return null;
    }

    @Override
    public void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList)
    {

    }

    @Override
    public List<WordObjectRow> buildRows(final AErkundungsstelle erkundungsstelle)
    {
        List<WordObjectRow> tableRows = new ArrayList<>();

        //Header
        WordObjectRow tableRow1 = new WordObjectRowBuilder()
                .setParameter(htmlRowStyle)
                .addCell(new WordObjectCell("width=151 style='width:113.15pt;border:solid windowtext 1.0pt;mso-border-alt:\n" +
                        "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'",
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent("Erkundungsstelle")))
                .addCell(new WordObjectCell("width=453 colspan=13 style='width:339.95pt;border:solid windowtext 1.0pt;\n" +
                        "  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
                        "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'",
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_ORT"))))
                .build();

        tableRows.add(tableRow1);

        String cellStyle = "width=151 colspan=3 style='width:113.15pt;border-top:none;border-left:\n" +
                "  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
                "  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

        WordObjectRow tableRow2 = new WordObjectRowBuilder()
                .setParameter("style='mso-yfti-irow:1;height:19.85pt'")
                .addCell(new WordObjectCell("width=151 style='width:113.15pt;border:solid windowtext 1.0pt;border-top:\n" +
                        "  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n" +
                        "  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'",
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent("Nummer")))
                .addCell(new WordObjectCell(cellStyle,
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_ID"))))
                .addCell(new WordObjectCell(cellStyle,
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent("GPS-Koordinaten")))
                .addCell(new WordObjectCell(cellStyle,
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_KOORDINATEN"))))
                .build();

        tableRows.add(tableRow2);

        WordObjectRow tableRow3 = new WordObjectRowBuilder()
                .setParameter("style='mso-yfti-irow:2;height:19.85pt'")
                .addCell(new WordObjectCell("width=151 style='width:113.15pt;border:solid windowtext 1.0pt;border-top:\n" +
                        "  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n" +
                        "  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'",
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent("Entnahmedatum")))
                .addCell(new WordObjectCell(cellStyle,
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_DATUM"))))
                .addCell(new WordObjectCell(cellStyle,
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent("Entnahme durch")))
                .addCell(new WordObjectCell(cellStyle,
                        new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_PRUEFER"))))
                .build();

        tableRows.add(tableRow3);

        return tableRows;
    }

    @Override
    public String getHtmlHead()
    {
        return null;
    }
}
