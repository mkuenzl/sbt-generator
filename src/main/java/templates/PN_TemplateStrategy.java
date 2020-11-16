package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.AErkundungsstelleSchicht;
import main.java.projekt.Erkundungsstelle;
import main.java.projekt.Projekt;
import main.java.wordblocks.WordObjectCell;
import main.java.wordblocks.WordObjectCellContent;
import main.java.wordblocks.WordObjectRow;
import main.java.wordblocks.WordObjectTable;

import java.io.IOException;

public class PN_TemplateStrategy extends ATemplateStrategy
{
    private static PN_TemplateStrategy instance;

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

    private WordObjectCellContent content;

    private final String htmlRowStyle = "style='mso-yfti-irow:1;height:34.0pt'";
    private final String htmlContentStyle = "Standard-TabellePN";

    private int count = 0;

    WordObjectTable pnTable = new WordObjectTable("class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=605\n" +
            "    style='width:16.0cm;border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt;\n" +
            "   mso-yfti-tbllook:1184;mso-padding-alt:0cm 1.4pt 0cm 2.85pt'");

    //Probe
    WordObjectCell pnProbeCell = new WordObjectCell("width=38 style='width:28.15pt;border:solid windowtext 1.0pt;border-top:\n" +
            "  none;mso-border-top-alt:double windowtext 1.5pt;mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;\n" +
            "  height:34.0pt'");
    //Art
    WordObjectCell pnArtCell = new WordObjectCell("width=30 style='width:22.2pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Behältnis
    WordObjectCell pnBehaeltnisCell = new WordObjectCell("width=65 style='width:48.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Vol
    WordObjectCell pnVolumenCell = new WordObjectCell("width=47 style='width:35.55pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Haufwerk
    WordObjectCell pnHaufwerkCell = new WordObjectCell("width=57 style='width:42.65pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Abfallart
    WordObjectCell pnAbfallartCell = new WordObjectCell("width=95 style='width:71.1pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Farbe / Geruch / Konsistenz
    WordObjectCell pnAttributeCell = new WordObjectCell("width=76 style='width:56.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Erkundungsstelle Name
    String pnErkundungsstelleCell = "width=38 style='width:28.45pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'";
    //Tiefe
    WordObjectCell pnTiefeCell = new WordObjectCell("width=57 style='width:42.65pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Bemerkungen
    WordObjectCell pnBemerkungenCell = new WordObjectCell("width=102 style='width:76.8pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");


    public PN_TemplateStrategy(){}

    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
        buildTableObject(projekt);
        return pnTable.printToHtml();
    }

    @Override
    public void buildTableObject(final Projekt projekt) {
        try
        {
            pnTable.setTableHeader(readTemplateHeader("/Users/moritzkunzl/Documents/GitHub/sbt-generator/PN_Template_TableHead"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for (AErkundungsstelle erkundungsstelle : projekt.getErk()) {
            pnTable.addTableRow(buildRow(erkundungsstelle));
        }
    }

    //FormatRow
    @Override
    public WordObjectRow buildRow(AErkundungsstelle erkundungsstelle)
    {
        WordObjectRow wordTableRow = new WordObjectRow(htmlRowStyle);

        //Spalte 1 ProbeName
        content = new WordObjectCellContent(htmlContentStyle).addCellContent(String.valueOf(count++));
        wordTableRow.addCell(pnProbeCell.setContent(content));

        //Spalte 2 ProbeArt
        wordTableRow.addCell(pnArtCell);

        //Spalte 3 BehältnisArt
        wordTableRow.addCell(pnBehaeltnisCell);

        //Spalte 4 BehältnisVolumen
        wordTableRow.addCell(pnVolumenCell);

        //Spalte 5 Haufwerk
        wordTableRow.addCell(pnHaufwerkCell);

        //Spalte 6 Art
        wordTableRow.addCell(pnAbfallartCell);

        //Spalte 7 Farbe / Geruch / Konsistenz
        wordTableRow.addCell(pnAttributeCell);

        //Spalte 8 ErkundungsstellenName
        content = new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getName());
        WordObjectCell wordObjectCell = new WordObjectCell(pnErkundungsstelleCell);
        wordTableRow.addCell(wordObjectCell.setContent(content));

        //Spalte 9 Tiefe
        wordTableRow.addCell(pnTiefeCell);

        //Spalte 10 Bemerkungen
        wordTableRow.addCell(pnBemerkungenCell);

        return wordTableRow;
    }
}
