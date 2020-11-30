package main.java.wordblocks;

import java.util.ArrayList;
import java.util.List;

public class WOTable extends AWordObject {

    private String tableHeaderRow = "";
    private List<WORow> tableRows;

    private String classAttribute = "MsoNormalTable";
    private int borderAttribute = 1;
    private int cellspacingAttribute = 0;
    private int cellpaddingAttribute = 0;
    private int widthAttribute = 600;


    public WOTable(final String style){
        super(style);
        this.tableRows = new ArrayList<>();
    }

    public WOTable(){
        this.tableRows = new ArrayList<>();
    }

    public void addTableRows(List<WORow> tableRows)
    {
        for (WORow row :
                tableRows)
        {
            this.tableRows.add(row);
        }
    }

    public void addTableRow(WORow row){
        this.tableRows.add(row);
    }

    @Override
    public String printToHtml() {
        StringBuilder strb = new StringBuilder();
        strb.append("<table ")
            .append(attributesToString())
            .append(">")
            .append("\n")
            .append(tableHeaderRow)
            .append("\n");

        for (AWordObject row : tableRows)
        {
            strb.append(row.printToHtml())
                .append("\n");
        }
        strb.append("</table>")
            .append("\n");
        return strb.toString();
    }

    @Override
    public String attributesToString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("class=")
                .append(classAttribute)
                .append(" ")
                .append("border=")
                .append(borderAttribute)
                .append(" ")
                .append("cellspacing=")
                .append(cellspacingAttribute)
                .append(" ")
                .append("cellpadding=")
                .append(cellpaddingAttribute)
                .append(" ")
                .append("width=")
                .append(widthAttribute)
                .append(" ")
                .append("style=")
                .append(getStyleAttribute());

        return stringBuilder.toString();
    }

    void setClassAttribute(final String classAttribute)
    {
        this.classAttribute = classAttribute;
    }

    public void setTableHeaderRow(String tableHeaderRow)
    {
        this.tableHeaderRow = tableHeaderRow;
    }

    void setBorderAttribute(final int borderAttribute)
    {
        this.borderAttribute = borderAttribute;
    }

    void setCellspacingAttribute(final int cellspacingAttribute)
    {
        this.cellspacingAttribute = cellspacingAttribute;
    }

    void setCellpaddingAttribute(final int cellpaddingAttribute)
    {
        this.cellpaddingAttribute = cellpaddingAttribute;
    }

    void setWidthAttribute(final int widthAttribute)
    {
        this.widthAttribute = widthAttribute;
    }
}
