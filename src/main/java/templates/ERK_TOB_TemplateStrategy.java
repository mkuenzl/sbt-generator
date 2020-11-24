package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.ASchicht;
import main.java.projekt.Projekt;
import main.java.wordblocks.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ERK_TOB_TemplateStrategy extends ATemplateStrategy
{
    private static ERK_TOB_TemplateStrategy instance;

    private ERK_TOB_TemplateStrategy(){}

    public static ERK_TOB_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (ERK_TOB_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new ERK_TOB_TemplateStrategy();
                }
            }
        }
        return instance;
    }


    private String schichtArtCell = "width=189 style='width:141.5pt;border:solid windowtext 1.0pt;border-top:\n" +
            "  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String dickeCell = "width=85 style='width:63.8pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String mufvCell = "width=66 style='width:49.6pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String lagaBoCell = "width=66 style='width:49.6pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String lagaRcCell = "width=66 style='width:49.6pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String tlGesteinCell = "width=66 style='width:49.6pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String lpCell = "width=66 style='width:49.6pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String htmlRowStyle = "style='mso-yfti-irow:2;height:19.85pt'";

    private final String htmlContentStyle = "class=MsoNormal style='margin-bottom:0cm;line-height:normal'";

    private String htmlContentStyleSpan = "style='font-size:10.0pt;font-family:\"Arial\",sans-serif'";

    public String buildHtmlTable(final AErkundungsstelle erkundungsstelle)
    {
        WordObjectTable ERK_TOB_Table = new WordObjectTable("class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=604\\n\" +\n" +
                "                \" style='width:453.35pt;border-collapse:collapse;border:none;mso-border-alt:\\n\" +\n" +
                "                \" solid windowtext .5pt;mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt'");

        ERK_TOB_Table.setTableHeader(getHtmlHead());
        ERK_TOB_Table.addTableRows(buildRows(erkundungsstelle));

        return ERK_TOB_Table.printToHtml();
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

        for (ASchicht schicht : erkundungsstelle.getSchichtList())
        {
            if ("ToB".equals(schicht.getInformation("SCHICHT_AUFSCHLUSS"))){
                WordObjectRow tableRow = new WordObjectRowBuilder()
                        .setParameter("style='mso-yfti-irow:2;height:19.85pt'")
                        .addCell(new WordObjectCell(schichtArtCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("SCHICHT_ART"))))
                        .addCell(new WordObjectCell(dickeCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("SCHICHT_DICKE"))))
                        .addCell(new WordObjectCell(mufvCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("CHEMIE_MUFV"))))
                        .addCell(new WordObjectCell(lagaBoCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("CHEMIE_LAGA_BO"))))
                        .addCell(new WordObjectCell(lagaRcCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("CHEMIE_LAGA_RC"))))
                        .addCell(new WordObjectCell(tlGesteinCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("CHEMIE_TLGESTEIN"))))
                        .addCell(new WordObjectCell(lpCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(erkundungsstelle.getInformation(
                                        "ERK_LP1"))))
                        .build();

                tableRows.add(tableRow);
            }
        }
        return tableRows;
    }

    @Override
    public String getHtmlHead()
    {
        try {
            return readHTMLHead(System.getProperty("user.dir")
                    .concat(File.separator).concat("WordTemplates")
                    .concat(File.separator).concat("ERK")
                    .concat(File.separator).concat("ERK_Template_TableHead_TOB"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO
        return "<head>HTML HEADER FILE NOT FOUND</head>";
    }
}
