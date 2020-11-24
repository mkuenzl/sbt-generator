package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.ASchicht;
import main.java.projekt.Projekt;
import main.java.wordblocks.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ERK_OB_TemplateStrategy extends ATemplateStrategy
{
    private static ERK_OB_TemplateStrategy instance;

    private ERK_OB_TemplateStrategy(){}

    public static ERK_OB_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (ERK_OB_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new ERK_OB_TemplateStrategy();
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

    private String pechCell = "width=66 style='width:49.6pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String rukCell = "width=161 style='width:120.5pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String bemerkungenCell = "width=104 style='width:77.95pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'";

    private String htmlContentStyle = "class=MsoNormal align=center style='margin-bottom:0cm;text-align:center;\n" +
            "  line-height:normal'";

    private String htmlContentStyleSpan = "style='font-size:10.0pt;font-family:\"Arial\",sans-serif'";


    public String buildHtmlTable(final AErkundungsstelle erkundungsstelle)
    {
        WordObjectTable ERK_OG_Table = new WordObjectTable("class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=604\\n\" +\n" +
                "                \" style='width:453.35pt;border-collapse:collapse;border:none;mso-border-alt:\\n\" +\n" +
                "                \" solid windowtext .5pt;mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt'");

        ERK_OG_Table.setTableHeader(getHtmlHead());
        ERK_OG_Table.addTableRows(buildRows(erkundungsstelle));

        return ERK_OG_Table.printToHtml();
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
            if ("OB".equals(schicht.getInformation("SCHICHT_AUFSCHLUSS"))){
                WordObjectRow tableRow = new WordObjectRowBuilder()
                        .setParameter("style='mso-yfti-irow:2;height:19.85pt'")
                        .addCell(new WordObjectCell(schichtArtCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("SCHICHT_ART"))))
                        .addCell(new WordObjectCell(dickeCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("SCHICHT_DICKE"))))
                        .addCell(new WordObjectCell(pechCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("SCHICHT_PECH"))))
                        .addCell(new WordObjectCell(rukCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("SCHICHT_RUK"))))
                        .addCell(new WordObjectCell(bemerkungenCell,
                                new WordObjectCellContent(htmlContentStyle, htmlContentStyleSpan).addCellContent(schicht.getInformation("SCHICHT_BEMERKUNGEN"))))
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
                    .concat(File.separator).concat("ERK_Template_TableHead_OG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO
        return "<head>HTML HEADER FILE NOT FOUND</head>";
    }
}
