package main.java.export;

import main.java.generation.AErkundungsstelle;
import main.java.generation.Projekt;

import java.io.File;
import java.io.IOException;

public class PN_Template extends ATemplate
{
    final String rowStyle = "'mso-yfti-irow:1;height:34.0pt'";

    private int count = 0;

    //Cell Style

    //Probe
    TableCell probe = new TableCell(38, "'width:28.15pt;border:solid windowtext 1.0pt;border-top:\n" +
            "  none;mso-border-top-alt:double windowtext 1.5pt;mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;\n" +
            "  height:34.0pt'");
    //Art
    TableCell art = new TableCell(30, "'width:22.2pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Behältnis
    TableCell behaeltnis = new TableCell(65, "'width:48.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Vol
    TableCell volumen = new TableCell(47, "'width:35.55pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Haufwerk
    TableCell haufwerk = new TableCell(57, "'width:42.65pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Abfallart
    TableCell abfallart = new TableCell(95, "'width:71.1pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Farbe / Geruch / Konsistenz
    TableCell attribute = new TableCell(76, "'width:56.9pt;border-top:none;border-left:none;border-bottom:\n" +
            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
            "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
            "  solid windowtext .5pt;padding:\n" +
            "  0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Erkundungsstelle Name
    TableCell erkName = new TableCell(38, "'width:28.45pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Tiefe
    TableCell tiefe = new TableCell(57, "'width:42.65pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");
    //Bemerkungen
    TableCell bemerkungen = new TableCell(102, "'width:76.8pt;border-top:none;border-left:none;\n" +
            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
            "  mso-border-alt:solid windowtext .5pt;\n" +
            "  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'");


    //Content Style
    TableCellContent content;
    String standardContentStyle = "Standard-TabellePN";


    @Override
    protected String format(final Projekt projekt)
    {
        StringBuilder strb = new StringBuilder();

        try
        {
            strb.append(readTemplateHeader("/Users/moritzkunzl/Documents/GitHub/sbt-generator/PN_Header"));
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



    @Override
    public String format(final AErkundungsstelle erkundungsstelle)
    {
        TableRow tableRow = new TableRow(rowStyle);

        //Probe
        content = new TableCellContent(standardContentStyle).addContent(String.valueOf(count++));
        tableRow.addCell(probe.setContent(content));

        //Art
        tableRow.addCell(art);

        //Behältnis
        tableRow.addCell(behaeltnis);

        //Vol
        tableRow.addCell(volumen);

        //Haufwerk
        tableRow.addCell(haufwerk);

        //Art
        tableRow.addCell(abfallart);

        //Farbe / Geruch / Konsistenz
        tableRow.addCell(attribute);

        //Erkundungsstelle Name
        content = new TableCellContent(standardContentStyle).addContent(erkundungsstelle.getName());
        tableRow.addCell(erkName.setContent(content));

        //Tiefe
        tableRow.addCell(tiefe);

        //Bemerkungen
        tableRow.addCell(bemerkungen);

        return tableRow.printToHtml();
    }

    

    //Helper Methods


}
