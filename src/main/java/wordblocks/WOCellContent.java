package main.java.wordblocks;

import java.util.ArrayList;
import java.util.List;

public class WOCellContent extends AWordObject
{
    private List<AWordObject> textContent;
    private String classAttribute = "MsoNormal";
    //ENUM center;left;right
    private String alignAttribute;

    public WOCellContent(final String styleAttribute){
        super(styleAttribute);
        this.textContent = new ArrayList<>();
    }

    public WOCellContent(){
        this.textContent = new ArrayList<>();
    }

    @Override
    public String printToHtml()
    {
        StringBuilder strb = new StringBuilder();
        for (AWordObject content : textContent)
        {
                strb.append("<p ")
                    .append(attributesToString())
                    .append(">")
                    .append(content.printToHtml())
                    .append("</p>").append("\n");
        }
        return strb.toString();
    }


    @Override
    public String attributesToString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        if (getStyleAttribute() != null){
            stringBuilder.append("style=")
                    .append(getStyleAttribute())
                    .append(" ");
        }
        if (alignAttribute != null){
            stringBuilder.append("align=")
                    .append(alignAttribute)
                    .append(" ");
        }
        if (classAttribute != null){
            stringBuilder.append("class=")
                    .append(classAttribute)
                    .append(" ");
        }
        return stringBuilder.toString();
    }

    void addText(final WOText text)
    {
        this.textContent.add(text);
    }

    void setClassAttribute(final String classAttribute)
    {
        this.classAttribute = classAttribute;
    }

    void setAlignAttribute(final String alignAttribute)
    {
        this.alignAttribute = alignAttribute;
    }
}
