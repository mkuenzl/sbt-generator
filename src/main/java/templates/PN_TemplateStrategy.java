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

    private PN_TemplateStrategy(){
        table = new WOTableBuilder()
                .setClassAttribute("MsoTableGrid")
                .setBorderAttribute(1)
                .setCellpaddingAttribute(0)
                .setCellspacingAttribute(0)
                .setStyleAttribute("'width:16.0cm;border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt;\n" +
                        "   mso-yfti-tbllook:1184;mso-padding-alt:0cm 1.4pt 0cm 2.85pt'")
                .setWidthAttribute(605)
                .build();
    }

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
    
    private int count = 0;
    private WOTable table;

    private WOCell createProbenCounterCell(){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(38)
                .setStyleAttribute("'width:28.15pt;border:solid windowtext 1.0pt;border-top:\\n\" +\n" +
                        "            \"  none;mso-border-top-alt:double windowtext 1.5pt;mso-border-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  padding:0cm 1.4pt 0cm 2.85pt;\\n\" +\n" +
                        "            \"  height:34.0pt'")
                .build();

        WOText text = new WOText(String.valueOf(count++));

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell createProbenArtCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(30)
                .setStyleAttribute("'width:22.2pt;border-top:none;border-left:none;border-bottom:\\n\" +\n" +
                        "            \"  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\\n\" +\n" +
                        "            \"  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\\n\" +\n" +
                        "            \"  solid windowtext .5pt;padding:\\n\" +\n" +
                        "            \"  0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell createSchichtBehaeltnisCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(65)
                .setStyleAttribute("'width:48.9pt;border-top:none;border-left:none;border-bottom:\\n\" +\n" +
                        "            \"  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\\n\" +\n" +
                        "            \"  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\\n\" +\n" +
                        "            \"  solid windowtext .5pt;padding:\\n\" +\n" +
                        "            \"  0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell createSchichtBehaeltnisVolumenCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(47)
                .setStyleAttribute("'width:35.55pt;border-top:none;border-left:none;" +
                        "            border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;" +
                        "            mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;" +
                        "            mso-border-alt:solid windowtext .5pt;" +
                        "            padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell createSchichtHaufwerkCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(57)
                .setStyleAttribute("'width:42.65pt;border-top:none;border-left:none;\\n\" +\n" +
                        "            \"  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell createSchichtArtCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(95)
                .setStyleAttribute("'width:71.1pt;border-top:none;border-left:none;border-bottom:\\n\" +\n" +
                        "            \"  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\\n\" +\n" +
                        "            \"  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\\n\" +\n" +
                        "            \"  solid windowtext .5pt;padding:\\n\" +\n" +
                        "            \"  0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    //Farbe, Geruch, Konsistenz
    private WOCell createSchichtAttributeCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(76)
                .setStyleAttribute("'width:56.9pt;border-top:none;border-left:none;border-bottom:\\n\" +\n" +
                        "            \"  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:\\n\" +\n" +
                        "            \"  double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:\\n\" +\n" +
                        "            \"  solid windowtext .5pt;padding:\\n\" +\n" +
                        "            \"  0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell createErkIdCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(38)
                .setStyleAttribute("'width:28.45pt;border-top:none;border-left:none;\\n\" +\n" +
                        "            \"  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell createSchichtTiefeCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(57)
                .setStyleAttribute("'width:42.65pt;border-top:none;border-left:none;\\n\" +\n" +
                        "            \"  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
                .addText(text)
                .build());

        return cell;
    }

    private WOCell createSchichtBemerkungenCell(String textContent){
        WOCell cell = new WOCellBuilder()
                .setWidthAttribute(102)
                .setStyleAttribute("'width:76.8pt;border-top:none;border-left:none;\\n\" +\n" +
                        "            \"  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\\n\" +\n" +
                        "            \"  mso-border-top-alt:double windowtext 1.5pt;mso-border-left-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  mso-border-alt:solid windowtext .5pt;\\n\" +\n" +
                        "            \"  padding:0cm 1.4pt 0cm 2.85pt;height:34.0pt'")
                .build();

        WOText text = new WOText(textContent);

        cell.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("Standard-TabellePN")
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
            table.setTableHeaderRow(readTemplateHeader(System.getProperty("user.dir").concat(File.separator).concat("WordTemplates").concat(File.separator).concat("PN98").concat(File.separator).concat("PN_Template_TableHead")));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for (AErkundungsstelle erkundungsstelle : erkundungsstelleList) {
            table.addTableRows(buildRows(erkundungsstelle));
        }
    }

    //FormatRow
    @Override
    public List<WORow> buildRows(AErkundungsstelle erkundungsstelle)
    {

        List<WORow> tableRows = new ArrayList<>();

        for (ASchicht schicht :
                erkundungsstelle.getSchichtList())
        {
            WORow WORow = new WORowBuilder()
                    .setStyleAttribute("style='mso-yfti-irow:1;height:34.0pt'")
                    .addCell(createProbenCounterCell())
                    .addCell(createProbenArtCell("EP"))
                    .addCell(createSchichtBehaeltnisCell(schicht.getInformation("SCHICHT_BEHAELTNIS")))
                    .addCell(createSchichtBehaeltnisVolumenCell("10"))
                    .addCell(createSchichtHaufwerkCell(""))
                    .addCell(createSchichtArtCell(schicht.getInformation("SCHICHT_ART")))
                    .addCell(createSchichtAttributeCell(schicht.getInformation("SCHICHT_FARBE")))
                    .addCell(createErkIdCell(erkundungsstelle.getInformation("ERK_ID")))
                    .addCell(createSchichtTiefeCell(schicht.getInformation("SCHICHT_TIEFE")))
                    .addCell(createSchichtBemerkungenCell(schicht.getInformation("SCHICHT_BEMERKUNGEN")))
                    .build();

            tableRows.add(WORow);
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
