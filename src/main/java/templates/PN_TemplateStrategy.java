package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.ASchicht;
import main.java.projekt.Projekt;
import main.java.wordblocks.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PN_TemplateStrategy extends ATemplateStrategy
{
    private static PN_TemplateStrategy instance;

    private PN_TemplateStrategy(){}

    public static PN_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (PN_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new PN_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    private final String htmlRowStyle = "style='mso-yfti-irow:1;height:34.0pt'";
    private final String htmlContentStyle = "class=Standard-TabellePN";

    private int count = 0;

    WordObjectTable pn98Table = new WordObjectTable("class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=605\n" +
            "    style='width:16.0cm;border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt;\n" +
            "   mso-yfti-tbllook:1184;mso-padding-alt:0cm 1.4pt 0cm 2.85pt'");

    //Probe
    String pnProbeCell = "width=38 style='width:28.15pt;border:solid windowtext 1.0pt;border-top:\n" +
            "  none;mso-border-top-alt:double windowtext 1.5pt;mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;\n" +
            "  height:34.0pt'";
    //Art
    String pnArtCell = "width=30 style='width:22.2pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Behältnis
    String pnBehaeltnisCell = "width=65 style='width:48.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Vol
    String pnVolumenCell = "width=47 style='width:35.55pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Haufwerk
    String pnHaufwerkCell = "width=57 style='width:42.65pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Abfallart
    String pnSchichtArtCell = "width=95 style='width:71.1pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Farbe / Geruch / Konsistenz
    String pnAttributeCell = "width=76 style='width:56.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Erkundungsstelle Name
    String pnErkundungsstelleCell = "width=38 style='width:28.45pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Tiefe
    String pnTiefeCell = "width=57 style='width:42.65pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Bemerkungen
    String pnBemerkungenCell = "width=102 style='width:76.8pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'";


    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
        buildTableObject(projekt.getData());
        return pn98Table.printToHtml();
    }

    @Override
    public void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList) {
        try
        {
            pn98Table.setTableHeader(readTemplateHeader(System.getProperty("user.dir").concat(File.separator).concat("WordTemplates").concat(File.separator).concat("PN98").concat(File.separator).concat("PN_Template_TableHead")));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for (AErkundungsstelle erkundungsstelle : erkundungsstelleList) {
            pn98Table.addTableRows(buildRows(erkundungsstelle));
        }
    }

    //FormatRow
    @Override
    public List<WordObjectRow> buildRows(AErkundungsstelle erkundungsstelle)
    {

        List<WordObjectRow> tableRows = new ArrayList<>();

        for (ASchicht schicht :
                erkundungsstelle.getSchichtList())
        {
            WordObjectRow wordObjectRow = new WordObjectRowBuilder()
                    .setParameter(htmlRowStyle)
                    .addCell(new WordObjectCell(pnProbeCell, new WordObjectCellContent(htmlContentStyle).addCellContent(String.valueOf(count++))))
                    .addCell(new WordObjectCell(pnArtCell, new WordObjectCellContent(htmlContentStyle).addCellContent("MP")))
                    .addCell(new WordObjectCell(pnBehaeltnisCell,
                            new WordObjectCellContent(htmlContentStyle).addCellContent(schicht.getInformation("SCHICHT_BEHAELTNIS"))))
                    .addCell(new WordObjectCell(pnVolumenCell, new WordObjectCellContent(htmlContentStyle).addCellContent("10")))
                    .addCell(new WordObjectCell(pnHaufwerkCell, new WordObjectCellContent(htmlContentStyle).addCellContent("TODO")))
                    .addCell(new WordObjectCell(pnSchichtArtCell,
                            new WordObjectCellContent(htmlContentStyle).addCellContent(schicht.getInformation("SCHICHT_ART"))))
                    .addCell(new WordObjectCell(pnAttributeCell,
                            new WordObjectCellContent(htmlContentStyle).addCellContent(schicht.getInformation("SCHICHT_FARBE"))))
                    .addCell(new WordObjectCell(pnErkundungsstelleCell,
                            new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getInformation("ERK_ID"))))
                    .addCell(new WordObjectCell(pnTiefeCell,
                            new WordObjectCellContent(htmlContentStyle).addCellContent(schicht.getInformation("SCHICHT_TIEFE"))))
                    .addCell(new WordObjectCell(pnBemerkungenCell,
                            new WordObjectCellContent(htmlContentStyle).addCellContent(schicht.getInformation("SCHICHT_BEMERKUNGEN"))))
                    .build();

            tableRows.add(wordObjectRow);
        }


        return tableRows;
    }

    @Override
    public String getHtmlHead() {
        try {
            return readHTMLHead(System.getProperty("user.dir")
                    .concat(File.separator).concat("WordTemplates")
                    .concat(File.separator).concat("PN98")
                    .concat(File.separator).concat("PN_Template_Head"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<head>No Head File</head>";
    }
}
