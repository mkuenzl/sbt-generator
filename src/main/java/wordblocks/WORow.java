package main.java.wordblocks;

import java.util.ArrayList;
import java.util.List;

public class WORow extends AWordObject
{
    private List<WOCell> cellList = new ArrayList<>();

    private int heightAttribute;
    private int widthAttribute;

    public WORow(final String style){
        super(style);
    }

    public WORow(){ }

    public void addCell(WOCell cell)
    {
        this.cellList.add(cell);
    }

    @Override
    public String printToHtml()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<tr ")
            .append(attributesToString())
            .append(">")
            .append("\n");

        for (WOCell cell: cellList)
        {
            strb.append(cell.printToHtml());
            strb.append("\n");
        }

        strb.append("</tr>");
        strb.append("\n");

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
        if (heightAttribute != 0){
            stringBuilder.append("height=")
                    .append(heightAttribute)
                    .append(" ");
        }
        if (widthAttribute != 0){
            stringBuilder.append("width=")
                    .append(widthAttribute)
                    .append(" ");
        }
        return stringBuilder.toString();
    }

    void setHeightAttribute(final int heightAttribute)
    {
        this.heightAttribute = heightAttribute;
    }

    void setWidthAttribute(final int widthAttribute)
    {
        this.widthAttribute = widthAttribute;
    }
}
