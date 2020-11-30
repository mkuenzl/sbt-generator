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

    private LP_TemplateStrategy(){
        this.table = new WOTableBuilder()
                .setBorderAttribute(1)
                .setCellpaddingAttribute(0)
                .setCellspacingAttribute(0)
                .setClassAttribute("MsoNormalTable")
                .setStyleAttribute("'width:453.05pt;border-collapse:collapse;border:none;mso-border-alt:\n" +
                        " solid windowtext .5pt;mso-yfti-tbllook:480;mso-padding-alt:0cm 5.4pt 0cm 5.4pt;\n" +
                        " mso-border-insideh:.5pt solid windowtext;mso-border-insidev:.5pt solid windowtext'")
                .setWidthAttribute(604)
                .build();
    }

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

    private int count = 1;
    private WOTable table;

    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
        buildTableObject(projekt.getData());
        return table.printToHtml();
    }

    @Override
    public void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList)
    {
        try
        {
            table.setTableHeaderRow(readTemplateHeader(System.getProperty("user.dir")
                    .concat(File.separator).concat("WordTemplates")
                    .concat(File.separator).concat("LP")
                    .concat(File.separator).concat("LP_Template_TableHead")));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for (AErkundungsstelle erkundungsstelle : erkundungsstelleList) {

            if (!"".equals(erkundungsstelle.getInformation("ERK_LP1"))){

                WORow tableRow = new WORowBuilder()
                        .setStyleAttribute("'mso-yfti-irow:1;page-break-inside:avoid;height:1.0cm'")
                        .addCell(getVersuchCell())
                        .addCell(getErkIdCell(erkundungsstelle.getInformation("ERK_ID")))
                        .addCell(getErkOrtCell(erkundungsstelle.getInformation("ERK_ORT")))
                        .addCell(getErkLpCell(erkundungsstelle.getInformation("ERK_LP1")))
                        .addCell(getErkLpCell(erkundungsstelle.getInformation("ERK_LP2")))
                        .addCell(getErkLpCell(erkundungsstelle.getInformation("ERK_LP3")))
                        .addCell(getErkLpCell("Mean"))
                        .addCell(getErkEvCell("Ev1"))
                        .addCell(getErkEvCell("Ev-15"))
                        .addCell(getErkEvCell("Ev2"))
                        .build();

                table.addTableRow(tableRow);
            }
        }
    }

    @Override
    public List<WORow> buildRows(final AErkundungsstelle erkundungsstelle)
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

    private WOCell getVersuchCell(){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:48.1pt;border:solid windowtext 1.0pt;border-top:\n" +
                        "  none;mso-border-top-alt:double windowtext 1.5pt;mso-border-top-alt:double 1.5pt;\n" +
                        "  mso-border-left-alt:solid 1.0pt;mso-border-bottom-alt:solid .5pt;mso-border-right-alt:\n" +
                        "  solid .5pt;mso-border-color-alt:windowtext;padding:0cm 5.4pt 0cm 5.4pt;\n" +
                        "  height:1.0cm'")
                .setWidthAttribute(64)
                .build();

        WOText text = new WOText("'font-size:8.5pt;line-height:107%;mso-bidi-font-family:Arial'", String.valueOf(count++));

        cell.setContent(new WOCellContentBuilder()
                .setClassAttribute("MsoNormal")
                .setAlignAttribute("center")
                .setStyleAttribute("'margin-top:3.0pt;margin-right:0cm;\n" +
                        "  margin-bottom:3.0pt;margin-left:0cm;text-align:center;tab-stops:-49.0pt -13.0pt'")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell getErkIdCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:42.5pt;border-top:none;border-left:none;border-bottom:\n" +
                        "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
                        "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
                        "  solid windowtext .5pt;mso-border-top-alt:double windowtext 1.5pt;padding:\n" +
                        "  0cm 5.4pt 0cm 5.4pt;height:1.0cm'")
                .setWidthAttribute(57)
                .build();

        WOText text = new WOText("'font-size:8.5pt;line-height:107%;mso-bidi-font-family:Arial'", textContent);

        cell.setContent(new WOCellContentBuilder()
                .setClassAttribute("MsoNormal")
                .setAlignAttribute("center")
                .setStyleAttribute("'margin-top:3.0pt;margin-right:0cm;\n" +
                        "  margin-bottom:3.0pt;margin-left:0cm;text-align:center;tab-stops:-49.0pt -13.0pt'")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell getErkOrtCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:147.2pt;border-top:none;border-left:none;\n" +
                        "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                        "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
                        "  mso-border-alt:solid windowtext .5pt;mso-border-top-alt:double windowtext 1.5pt;\n" +
                        "  padding:0cm 2.85pt 0cm 2.85pt;height:1.0cm'")
                .setWidthAttribute(196)
                .build();

        WOText text = new WOText("'font-size:8.5pt;line-height:107%;mso-bidi-font-family:Arial'", textContent);

        cell.setContent(new WOCellContentBuilder()
                .setClassAttribute("MsoNormal")
                .setAlignAttribute("center")
                .setStyleAttribute("'margin-top:3.0pt;margin-right:0cm;\n" +
                        "  margin-bottom:3.0pt;margin-left:0cm;text-align:center;tab-stops:-49.0pt -13.0pt'")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell getErkLpCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:1.0cm;border-top:none;border-left:none;border-bottom:\n" +
                        "  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\n" +
                        "  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\n" +
                        "  solid windowtext .5pt;mso-border-top-alt:double windowtext 1.5pt;padding:\n" +
                        "  0cm 5.4pt 0cm 5.4pt;height:1.0cm'")
                .setWidthAttribute(38)
                .build();

        WOText text = new WOText("'font-size:8.5pt;line-height:107%;mso-bidi-font-family:Arial'", textContent);

        cell.setContent(new WOCellContentBuilder()
                .setClassAttribute("MsoNormal")
                .setAlignAttribute("center")
                .setStyleAttribute("'margin-top:3.0pt;margin-right:0cm;\n" +
                        "  margin-bottom:3.0pt;margin-left:0cm;text-align:center;tab-stops:-49.0pt -13.0pt'")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell getErkEvCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:33.95pt;border-top:none;border-left:none;\n" +
                        "  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                        "  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
                        "  mso-border-alt:solid windowtext .5pt;mso-border-top-alt:double windowtext 1.5pt;\n" +
                        "  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'")
                .setWidthAttribute(45)
                .build();

        WOText text = new WOText("'font-size:8.5pt;line-height:107%;mso-bidi-font-family:Arial'", textContent);

        cell.setContent(new WOCellContentBuilder()
                .setClassAttribute("MsoNormal")
                .setAlignAttribute("center")
                .setStyleAttribute("'margin-top:3.0pt;margin-right:0cm;\n" +
                        "  margin-bottom:3.0pt;margin-left:0cm;text-align:center;tab-stops:-49.0pt -13.0pt'")
                .addText(text)
                .build());

        return cell;
    }
}
