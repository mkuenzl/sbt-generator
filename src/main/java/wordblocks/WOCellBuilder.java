package main.java.wordblocks;

public class WOCellBuilder
{
    private WOCell cell;

    public WOCellBuilder(){
        this.cell = new WOCell();
    }

    public WOCellBuilder setWidthAttribute(final int widthAttribute)
    {
        this.cell.setWidthAttribute(widthAttribute);
        return this;
    }

    public WOCellBuilder setContent(final WOCellContent cellContent)
    {
        this.cell.setContent(cellContent);
        return this;
    }

    public WOCellBuilder setHeightAttribute(final int heightAttribute)
    {
        this.cell.setHeightAttribute(heightAttribute);
        return this;
    }

    public WOCellBuilder setColspanAttribute(final int colspanAttribute)
    {
        this.cell.setColspanAttribute(colspanAttribute);
        return this;
    }

    public WOCellBuilder setRowspanAttribute(final int rowspanAttribute)
    {
        this.cell.setRowspanAttribute(rowspanAttribute);
        return this;
    }

    public WOCellBuilder setStyleAttribute(final String styleAttribute){
        this.cell.setStyleAttribute(styleAttribute);
        return this;
    }

    public WOCell build(){
        return this.cell;
    }

    public WOCellBuilder setClass(final String classAttribute) {
        this.cell.setClass(classAttribute);
        return this;
    }
}
