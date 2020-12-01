package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.ASchicht;
import main.java.projekt.Projekt;
import main.java.wordblocks.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class RUK_TemplateStrategy extends ATemplateStrategy
{
    private static RUK_TemplateStrategy instance;


    private RUK_TemplateStrategy(){
        this.table = new WOTableBuilder()
                .setBorderAttribute(1)
                .setCellpaddingAttribute(0)
                .setCellspacingAttribute(0)
                .setClassAttribute("MsoTableGrid")
                .setStyleAttribute("'width:453.7pt;border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt;\" +\n" +
                        "    \"mso-yfti-tbllook:1184;mso-padding-alt:0cm 2.85pt 0cm 2.85pt'")
                .setWidthAttribute(604)
                .build();
    }

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
    private int count = 0;

    private WOTable table;

    private WOCell getCounterCell(){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:2.0cm;border:solid windowtext 1.0pt;border-top:\\n\" +\n" +
                        "            \"  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'")
                .setWidthAttribute(76)
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
                .setStyleAttribute("'width:2.0cm;border-top:none;border-left:none;border-bottom:\\n\" +\n" +
                        "            \"  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\\n\" +\n" +
                        "            \"  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\\n\" +\n" +
                        "            \"  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'")
                .setWidthAttribute(76)
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

    private WOCell getProbenArtCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:70.9pt;border-top:none;border-left:none;border-bottom:\\n\" +\n" +
                        "            \"  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\\n\" +\n" +
                        "            \"  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\\n\" +\n" +
                        "            \"  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'")
                .setWidthAttribute(95)
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

    private WOCell getPruefschichtCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:3.0cm;border:none;border-bottom:solid windowtext 1.0pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\\n\" +\n" +
                        "            \"  height:19.85pt'")
                .setWidthAttribute(113)
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

    private WOCell getPruefschichtKGCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:42.55pt;border-top:none;border-left:none;\\n\" +\n" +
                        "            \"  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'")
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

    private WOCell getTiefeCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:1.0cm;border:none;border-bottom:solid windowtext 1.0pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\\n\" +\n" +
                        "            \"  height:19.85pt'")
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

    private WOCell getConnectorCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:14.2pt;border:none;border-bottom:solid windowtext 1.0pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;\\n\" +\n" +
                        "            \"  height:19.85pt'")
                .setWidthAttribute(19)
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

    private WOCell getRUKCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute("'width:70.9pt;border-top:none;border-left:none;border-bottom:" +
                        "            \"  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:" +
                        "            \"  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:" +
                        "            \"  solid windowtext .5pt;padding:0cm 2.85pt 0cm 2.85pt;height:19.85pt'")
                .setWidthAttribute(95)
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

    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
            buildTableObject(projekt.getData());
            return table.printToHtml();
    }

    @Override
    public void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList) {
        try
        {
            table.setTableHeaderRow(readTemplateHeader(System.getProperty("user.dir").concat(File.separator).concat("WordTemplates").concat(File.separator).concat(
                    "RUK").concat(File.separator).concat("RUK_Template_TableHead")));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for (AErkundungsstelle erkundungsstelle : erkundungsstelleList) {
            //Wenn ERK einen RUK wert hat dann blablabla
            table.addTableRows(buildRows(erkundungsstelle));
        }
    }

    @Override
    public List<WORow> buildRows(final AErkundungsstelle erkundungsstelle)
    {
        List<WORow> tableRows = new ArrayList<>();

        for (ASchicht schicht : erkundungsstelle.getSchichtList())
        {
            if (!"".equals(schicht.getInformation("SCHICHT_RUK"))){
                WORow WORow = new WORowBuilder()
                        .setStyleAttribute("'mso-yfti-irow:1;height:19.85pt'")
                        .addCell(getCounterCell())
                        .addCell(getErkIdCell(erkundungsstelle.getInformation("ERK_ID")))
                        .addCell(getProbenArtCell("EP"))
                        .addCell(getPruefschichtCell(schicht.getInformation("SCHICHT_ART")))
                        .addCell(getPruefschichtKGCell(schicht.getInformation("SCHICHT_KOERNUNG")))
                        .addCell(getTiefeCell(schicht.getInformation("SCHICHT_DICKE")))
                        .addCell(getConnectorCell("-"))
                        .addCell(getTiefeCell(schicht.getInformation("SCHICHT_TIEFE")))
                        .addCell(getRUKCell(schicht.getInformation("SCHICHT_RUK")))
                        .build();

                tableRows.add(WORow);
            }

        }


        return tableRows;
    }

    @Override
    public String getHtmlHead() {
        try {
            return readHTMLHead(System.getProperty("user.dir")
                    .concat(File.separator).concat("WordTemplates")
                    .concat(File.separator).concat("RUK")
                    .concat(File.separator).concat("RUK_Template_Head"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO
        return "<head>HTML HEADER FILE NOT FOUND</head>";
    }
}
