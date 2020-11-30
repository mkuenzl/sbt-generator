package main.java.wordblocks;

abstract class AWordObject implements IWordObject
{
    private String styleAttribute;

    AWordObject(final String styleAttribute){
        this.styleAttribute = styleAttribute;
    }

    AWordObject(){}

    public String getStyleAttribute() {
        return styleAttribute;
    }

    void setStyleAttribute(final String style)
    {
        this.styleAttribute = style;
    }
}
