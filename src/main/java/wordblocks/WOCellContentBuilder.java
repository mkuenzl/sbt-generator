package main.java.wordblocks;

public class WOCellContentBuilder
{
    private WOCellContent cellContent;

    public WOCellContentBuilder()
    {
        this.cellContent = new WOCellContent();
    }

    public WOCellContentBuilder setStyleAttribute(String styleAttribute){
        this.cellContent.setStyleAttribute(styleAttribute);
        return this;
    }

    public WOCellContentBuilder setClassAttribute(String classAttribute){
        this.cellContent.setClassAttribute(classAttribute);
        return this;
    }

    public WOCellContentBuilder setAlignAttribute(String alignAttribute){
        this.cellContent.setAlignAttribute(alignAttribute);
        return this;
    }

    public WOCellContentBuilder addText(WOText text){
        this.cellContent.addText(text);
        return this;
    }

    public WOCellContent build(){
        return this.cellContent;
    }

}
