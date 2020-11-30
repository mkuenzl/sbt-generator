package main.java.wordblocks;

public class WOTableBuilder
{
    private WOTable table;

    public WOTableBuilder(){
        this.table = new WOTable();
    }

    public WOTableBuilder setClassAttribute(final String classAttribute)
    {
        this.table.setClassAttribute(classAttribute);
        return this;
    }

    public WOTableBuilder setBorderAttribute(final int borderAttribute)
    {
        this.table.setBorderAttribute(borderAttribute);
        return this;
    }

    public WOTableBuilder setCellspacingAttribute(final int cellspacingAttribute)
    {
        this.table.setCellspacingAttribute(cellspacingAttribute);
        return this;
    }

    public WOTableBuilder setCellpaddingAttribute(final int cellpaddingAttribute)
    {
        this.table.setCellpaddingAttribute(cellpaddingAttribute);
        return this;
    }

    public WOTableBuilder setWidthAttribute(final int widthAttribute)
    {
        this.table.setWidthAttribute(widthAttribute);
        return this;
    }

    public WOTableBuilder setTableHeaderRow(String tableHeaderRow) {
        this.table.setTableHeaderRow(tableHeaderRow);
        return this;
    }

    public WOTableBuilder setStyleAttribute(String styleAttribute) {
        this.table.setStyleAttribute(styleAttribute);
        return this;
    }

    public WOTable build(){
        return this.table;
    }
}
