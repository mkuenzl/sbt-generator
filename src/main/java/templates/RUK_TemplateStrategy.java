package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.AErkundungsstelleSchicht;
import main.java.projekt.Projekt;
import main.java.wordblocks.*;

import java.io.File;
import java.io.IOException;

public class RUK_TemplateStrategy extends ATemplateStrategy
{
    private static RUK_TemplateStrategy instance;

    private RUK_TemplateStrategy(){}

    public static RUK_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (RUK_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new RUK_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    private final String htmlRowStyle = "'mso-yfti-irow:1;height:19.85pt'";
    private final String htmlContentStyle = "class=MsoNormal";

    WordObjectTable rukTable = new WordObjectTable("class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=605"+
    "style='width:453.7pt;border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt;" +
    "mso-yfti-tbllook:1184;mso-padding-alt:0cm 2.85pt 0cm 2.85pt'");


    //Cell Style
    String versuchNr = "width=76 style='width:2.0cm;border:solid windowtext 1.0pt;border-top:\n" +
            "  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'";

    String erkName = "width=76 style='width:2.0cm;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'";

    String probenart = "width=95 style='width:70.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'";

    String pruefschicht = "width=113 style='width:3.0cm;border:none;border-bottom:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
            "  height:19.85pt'";

    String pruefschichtKG = "width=57 style='width:42.55pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;\n" +
            "  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'";

    String prueftiefeFirstParameter = "width=38 style='width:1.0cm;border:none;border-bottom:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
            "  height:19.85pt'";
    String prueftiefeConnector = "width=19 style='width:14.2pt;border:none;border-bottom:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;\n" +
            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
            "  height:19.85pt'";
    String prueftiefeSecondParameter = "width=38 style='width:1.0cm;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;mso-border-bottom-alt:\n" +
            "  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:\n" +
            "  0cm 2.85pt 0cm 2.85pt;height:19.85pt'";

    String ringUndKugelWert = "width=95 style='width:70.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'";


    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
            buildTableObject(projekt);
            return rukTable.printToHtml();
    }

    @Override
    public void buildTableObject(final Projekt projekt) {
        try
        {
            rukTable.setTableHeader(readTemplateHeader(System.getProperty("user.dir").concat(File.separator).concat("WordTemplates").concat(File.separator).concat(
                    "RUK").concat(File.separator).concat("RUK_Template_TableHead")));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for (AErkundungsstelle erkundungsstelle : projekt.getErk()) {
            //Wenn ERK einen RUK wert hat dann blablabla
            rukTable.addTableRow(buildRow(erkundungsstelle));
        }
    }

    @Override
    public WordObjectRow buildRow(final AErkundungsstelle erkundungsstelle)
    {
        WordObjectRow wordObjectRow = new WordObjectRowBuilder()
                .setParameter(htmlRowStyle)
                .addCell(new WordObjectCell(versuchNr, new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getId())))
                .addCell(new WordObjectCell(erkName, new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getName())))
                .addCell(new WordObjectCell(probenart, new WordObjectCellContent(htmlContentStyle).addCellContent("EP")))
                .addCell(new WordObjectCell(pruefschicht, new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getSchichtArt())))
                .addCell(new WordObjectCell(pruefschichtKG, new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getGeruch())))
                .addCell(new WordObjectCell(prueftiefeFirstParameter, new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getTiefe())))
                .addCell(new WordObjectCell(prueftiefeConnector, new WordObjectCellContent(htmlContentStyle).addCellContent("-")))
                .addCell(new WordObjectCell(prueftiefeSecondParameter, new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getTiefe())))
                .addCell(new WordObjectCell(ringUndKugelWert, new WordObjectCellContent(htmlContentStyle).addCellContent("17.5")))
                .build();

        return wordObjectRow;
    }
}
