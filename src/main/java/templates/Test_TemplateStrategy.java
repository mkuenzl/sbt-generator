package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.Projekt;
import main.java.templates.styles.CellStyle;
import main.java.templates.styles.TableStyle;
import main.java.templates.styles.TextStyle;
import main.java.wordblocks.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Test_TemplateStrategy extends ATemplateStrategy
{
    private static Test_TemplateStrategy instance;
    private WOTable table;

    private Test_TemplateStrategy(){
        this.table = new WOTableBuilder()
                .setBorderAttribute(1)
                .setCellpaddingAttribute(0)
                .setCellspacingAttribute(0)
                .setClassAttribute("MsoTableGrid")
                .setStyleAttribute(TableStyle.TABLE_STYLE1.getAttributes())
                .setWidthAttribute(604)
                .build();
    }

    public static Test_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Test_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Test_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
        WOCell cell = new WOCellBuilder()
                .setStyleAttribute(CellStyle.CELL_STYLE_BLACK.getAttributes())
                .setWidthAttribute(95)
                .build();

        WOText text = new WOText(TextStyle.TEXT_STYLE_WHITE.getAttributes(), "textContent");

        cell.setContent(new WOCellContentBuilder()
                .setClassAttribute("MsoNormal")
                .setAlignAttribute("center")
                .setStyleAttribute("'margin-top:3.0pt;margin-right:0cm;" +
                        "margin-bottom:3.0pt;margin-left:0cm;tab-stops:-49.0pt -13.0pt'")
                .addText(text)
                .build());

        WOCell cell2 = new WOCellBuilder()
                .setStyleAttribute(CellStyle.CELL_STYLE_RED.getAttributes())
                .setWidthAttribute(15)
                .build();

        WOText text2 = new WOText("textContent2");

        cell2.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("MsoNormal")
                .addText(text2)
                .build());

        WOCell cell3 = new WOCellBuilder()
                .setStyleAttribute(CellStyle.CELL_STYLE_GREEN.getAttributes())
                .setWidthAttribute(30)
                .build();

        WOText text3 = new WOText("textContent2");

        cell3.setContent(new WOCellContentBuilder()
                .setAlignAttribute("center")
                .setClassAttribute("MsoNormal")
                .addText(text3)
                .build());

        WORow row = new WORowBuilder().setHeightAttribute(10).addCell(cell).addCell(cell2).addCell(cell3).build();

        WORow row2 = new WORowBuilder().setHeightAttribute(10).addCell(cell).addCell(cell2).addCell(cell3).build();

        table.addTableRow(row);
        table.addTableRow(row2);

        return table.printToHtml();
    }

    @Override
    public void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList)
    {

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
                    .concat(File.separator).concat("Testing")
                    .concat(File.separator).concat("CSS"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO
        return "<head>HTML HEADER FILE NOT FOUND</head>";
    }
}
