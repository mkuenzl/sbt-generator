package main.java.wordblocks;

public class WORowBuilder
{
    private WORow WORow;

    public WORowBuilder(){
        WORow = new WORow();
    }

    public WORowBuilder setStyleAttribute(String styleAttribute){
        WORow.setStyleAttribute(styleAttribute);
        return this;
    }

    public WORowBuilder addCell(WOCell cell){
        WORow.addCell(cell);
        return this;
    }

    public WORowBuilder setHeightAttribute(final int heightAttribute)
    {
        this.WORow.setHeightAttribute(heightAttribute);
        return this;
    }

    public WORowBuilder setWidthAttribute(final int widthAttribute)
    {
        this.WORow.setWidthAttribute(widthAttribute);
        return this;
    }

    public WORow build(){
        return this.WORow;
    }
}
