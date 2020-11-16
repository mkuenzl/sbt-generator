//package main.java.templates;
//
//import main.java.projekt.AErkundungsstelle;
//import main.java.projekt.AErkundungsstelleSchicht;
//import main.java.projekt.Projekt;
//import main.java.wordblocks.WordObjectCell;
//import main.java.wordblocks.WordObjectCellContent;
//import main.java.wordblocks.WordObjectRow;
//
//import java.io.IOException;
//
//public class RUK_TemplateStrategy extends ATemplateStrategy
//{
//    private final String htmlRowStyle = "'mso-yfti-irow:1;height:19.85pt'";
//    private final String htmlContentStyle = "MsoNormal";
//    private WordObjectCellContent content;
//
//    //Cell Style
//    private WordObjectCell versuchNr = new WordObjectCell("width=76 style='width:2.0cm;border:solid windowtext 1.0pt;border-top:\n" +
//            "  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n" +
//            "  padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");
//
//    private WordObjectCell erkName = new WordObjectCell("width=76 style='width:2.0cm;border-top:none;border-left:none;border-bottom:\n" +
//            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
//            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
//            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");
//
//    private WordObjectCell probenart = new WordObjectCell("width=95 style='width:70.9pt;border-top:none;border-left:none;border-bottom:\n" +
//            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
//            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
//            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");
//
//    private WordObjectCell pruefschicht = new WordObjectCell("width=113 style='width:3.0cm;border:none;border-bottom:solid windowtext 1.0pt;\n" +
//            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
//            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
//            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
//            "  height:19.85pt'");
//
//    private WordObjectCell pruefschichtKG = new WordObjectCell("width=57 style='width:42.55pt;border-top:none;border-left:none;\n" +
//            "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
//            "  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;\n" +
//            "  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;\n" +
//            "  padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");
//
//    private WordObjectCell prueftiefeFirstParameter = new WordObjectCell("width=38 style='width:1.0cm;border:none;border-bottom:solid windowtext 1.0pt;\n" +
//            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
//            "  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
//            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
//            "  height:19.85pt'");
//    private WordObjectCell prueftiefeConnector = new WordObjectCell("width=19 style='width:14.2pt;border:none;border-bottom:solid windowtext 1.0pt;\n" +
//            "  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;\n" +
//            "  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\n" +
//            "  height:19.85pt'");
//    private WordObjectCell prueftiefeSecondParameter = new WordObjectCell("width=38 style='width:1.0cm;border-top:none;border-left:none;border-bottom:\n" +
//            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
//            "  solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;mso-border-bottom-alt:\n" +
//            "  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:\n" +
//            "  0cm 2.85pt 0cm 2.85pt;height:19.85pt'");
//
//    private WordObjectCell ringUndKugelWert = new WordObjectCell("width=95 style='width:70.9pt;border-top:none;border-left:none;border-bottom:\n" +
//            "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
//            "  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
//            "  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'");
//
//    @Override
//    public String format(final AErkundungsstelle erkundungsstelle)
//    {
//        WordObjectRow wordTableRow = new WordObjectRow(htmlRowStyle);
//
//        //Versuch
//        //content = new TableCellContent(standardContentStyle).addContent(String.valueOf(count++));
//        wordTableRow.addCell(versuchNr);
//
//        //Erkundungsstelle
//        content = new WordObjectCellContent(htmlContentStyle).addCellContent(erkundungsstelle.getName());
//        wordTableRow.addCell(erkName.setContent(content));
//
//        //Probenart
//        wordTableRow.addCell(probenart);
//
//        //Prüfschicht
//        wordTableRow.addCell(pruefschicht);
//
//        //Prüfschicht Korngröße
//        wordTableRow.addCell(pruefschichtKG);
//
//        //Prüftiefe1
//        wordTableRow.addCell(prueftiefeFirstParameter);
//
//        //Farbe / Geruch / Konsistenz
//        content = new WordObjectCellContent(htmlContentStyle).addCellContent("-");
//        wordTableRow.addCell(prueftiefeConnector.setContent(content));
//
//        //Erkundungsstelle Name
//        wordTableRow.addCell(prueftiefeSecondParameter);
//
//        //Tiefe
//        wordTableRow.addCell(ringUndKugelWert);
//
//        return wordTableRow.printToHtml();
//    }
//
//    @Override
//    public String format(AErkundungsstelleSchicht erkundungsstelleSchicht) {
//        return null;
//    }
//
//    @Override
//    public String buildHtmlTable(final Projekt projekt)
//    {
//        StringBuilder strb = new StringBuilder();
//
//        try
//        {
//            strb.append(readTemplateHeader("/Users/moritzkunzl/Documents/GitHub/sbt-generator/RUK_Header"));
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        strb.append("\n");
//        for (AErkundungsstelle erk : projekt.getErk())
//        {
//            strb.append(format(erk));
//        }
//        strb.append("\n");
//        strb.append(htmlCloseTab);
//        return strb.toString();
//    }
//}
