package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.Projekt;
import main.java.wordblocks.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class LP_TemplateStrategy extends  ATemplateStrategy
{
    private static LP_TemplateStrategy instance;

    private LP_TemplateStrategy(){}

    public static LP_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (LP_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new LP_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    private String htmlRowStyle = "style='mso-yfti-irow:4;page-break-inside:avoid;height:1.0cm'";
    private final String htmlContentStyle = "class=MsoNormal align=center style='margin-top:3.0pt;margin-right:0cm;\n" +
            "  margin-bottom:3.0pt;margin-left:0cm;text-align:center;tab-stops:-49.0pt -13.0pt'";
    private String htmlSpan = "style='font-size:8.5pt;line-height:107%;mso-bidi-font-family:Arial'";


    WordObjectTable lpTable = new WordObjectTable("class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width=604\n" +
            " style='width:453.05pt;border-collapse:collapse;border:none;mso-border-alt:\n" +
            " solid windowtext .5pt;mso-yfti-tbllook:480;mso-padding-alt:0cm 5.4pt 0cm 5.4pt;\n" +
            " mso-border-insideh:.5pt solid windowtext;mso-border-insidev:.5pt solid windowtext'");


    // Cell Style

    private String versuchNr = "width=64 style='width:48.1pt;border:solid windowtext 1.0pt;border-top:\n" +
            "  none;mso-border-top-alt:double windowtext 1.5pt;mso-border-top-alt:double 1.5pt;\n" +
            "  mso-border-left-alt:solid 1.0pt;mso-border-bottom-alt:solid .5pt;mso-border-right-alt:\n" +
            "  solid .5pt;mso-border-color-alt:windowtext;padding:0cm 5.4pt 0cm 5.4pt;\n" +
            "  height:1.0cm'";

    private String erkNr = "width=57 style='width:42.5pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;mso-border-top-alt:double windowtext 1.5pt;padding:\n" +
            "  0cm 5.4pt 0cm 5.4pt;height:1.0cm'";

    private String lageErk = "width=196 style='width:147.2pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;mso-border-top-alt:double windowtext 1.5pt;\n" +
            "  padding:0cm 2.85pt 0cm 2.85pt;height:1.0cm'";

    private String setzung = "width=38 style='width:1.0cm;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;mso-border-top-alt:double windowtext 1.5pt;padding:\n" +
            "  0cm 5.4pt 0cm 5.4pt;height:1.0cm'";

    private String ev = "width=45 style='width:33.95pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;mso-border-top-alt:double windowtext 1.5pt;\n" +
            "  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'";

    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
        buildTableObject(projekt.getData());
        return lpTable.printToHtml();
    }

    @Override
    public void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList)
    {
        try
        {
            lpTable.setTableHeader(readTemplateHeader(System.getProperty("user.dir").concat(File.separator).concat("WordTemplates").concat(File.separator).concat("LP").concat(File.separator).concat("LP_Template_TableHead")));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for (AErkundungsstelle erkundungsstelle : erkundungsstelleList) {

            if (!erkundungsstelle.getInformation("ERK_LP1").equals("")){
                WordObjectRow tableRow = new WordObjectRowBuilder()
                        .setParameter(htmlRowStyle)
                        .addCell(new WordObjectCell(versuchNr, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent("1")))
                        .addCell(new WordObjectCell(erkNr, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_ID"))))
                        .addCell(new WordObjectCell(lageErk, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_ORT"))))
                        .addCell(new WordObjectCell(setzung, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_LP1"))))
                        .addCell(new WordObjectCell(setzung, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_LP2"))))
                        .addCell(new WordObjectCell(setzung, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_LP3"))))
                        .addCell(new WordObjectCell(setzung, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_LP1"))))
                        .addCell(new WordObjectCell(ev, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_LP1"))))
                        .addCell(new WordObjectCell(ev, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_LP1"))))
                        .addCell(new WordObjectCell(ev, new WordObjectCellContent(htmlContentStyle, htmlSpan).addCellContent(erkundungsstelle.getInformation("ERK_LP1"))))
                        .build();

                lpTable.addTableRow(tableRow);
            }
        }
    }

    @Override
    public List<WordObjectRow> buildRows(final AErkundungsstelle erkundungsstelle)
    {
        return null;
    }

    @Override
    public String getHtmlHead()
    {
        try {
            return readHTMLHead(System.getProperty("user.dir")
                    .concat(File.separator).concat("WordTemplates")
                    .concat(File.separator).concat("LP")
                    .concat(File.separator).concat("LP_Template_Head"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<head>No Head File</head>";
    }
}
