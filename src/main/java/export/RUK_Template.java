package main.java.export;

import main.java.generation.AErkundungsstelle;
import main.java.generation.Projekt;

import java.io.IOException;

public class RUK_Template extends ATemplate
{
    private final String rowStyle = "'mso-yfti-irow:1;height:19.85pt'";

    //Content Style
    TableCellContent content;
    String standardContentStyle = "MsoNormal";

    //Cell Style
    TableCell versuchNr = new TableCell(76, "'width:2.0cm;border:solid windowtext 1.0pt;border-top:\n" +
            "  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");

    TableCell erkName = new TableCell(76, "'width:2.0cm;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");

    TableCell probenart = new TableCell(95, "'width:70.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");

    TableCell pruefschicht = new TableCell(113, "'width:3.0cm;border:none;border-bottom:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
            "  height:19.85pt'");

    TableCell pruefschichtKG = new TableCell(57, "'width:42.55pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;\n" +
            "  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");

    TableCell prueftiefeFirstParameter = new TableCell(38, "'width:1.0cm;border:none;border-bottom:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
            "  height:19.85pt'");
    TableCell prueftiefeConnector = new TableCell(19, "'width:14.2pt;border:none;border-bottom:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;\n" +
            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
            "  height:19.85pt'");
    TableCell prueftiefeSecondParameter = new TableCell(38, "'width:1.0cm;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;mso-border-bottom-alt:\n" +
            "  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:\n" +
            "  0cm 2.85pt 0cm 2.85pt;height:19.85pt'");

    TableCell ringUndKugelWert = new TableCell(95, "'width:70.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");

    @Override
    public String format(final AErkundungsstelle erkundungsstelle)
    {
        TableRow tableRow = new TableRow(rowStyle);

        //Versuch
        //content = new TableCellContent(standardContentStyle).addContent(String.valueOf(count++));
        tableRow.addCell(versuchNr);

        //Erkundungsstelle
        content = new TableCellContent(standardContentStyle).addContent(erkundungsstelle.getName());
        tableRow.addCell(erkName.setContent(content));

        //Probenart
        tableRow.addCell(probenart);

        //Prüfschicht
        tableRow.addCell(pruefschicht);

        //Prüfschicht Korngröße
        tableRow.addCell(pruefschichtKG);

        //Prüftiefe1
        tableRow.addCell(prueftiefeFirstParameter);

        //Farbe / Geruch / Konsistenz
        content = new TableCellContent(standardContentStyle).addContent("-");
        tableRow.addCell(prueftiefeConnector.setContent(content));

        //Erkundungsstelle Name
        tableRow.addCell(prueftiefeSecondParameter);

        //Tiefe
        tableRow.addCell(ringUndKugelWert);

        return tableRow.printToHtml();
    }

    @Override
    protected String format(final Projekt projekt)
    {
        StringBuilder strb = new StringBuilder();

        try
        {
            strb.append(readTemplateHeader("/Users/moritzkunzl/Documents/GitHub/sbt-generator/RUK_Header"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        strb.append("\n");
        for (AErkundungsstelle erk : projekt.getErk())
        {
            strb.append(format(erk));
        }
        strb.append("\n");
        strb.append(htmlCloseTab);
        return strb.toString();
    }
}
