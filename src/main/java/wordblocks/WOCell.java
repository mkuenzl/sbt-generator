package main.java.wordblocks;

public class WOCell extends AWordObject
{
    private AWordObject content;

    private int heightAttribute;
    private int widthAttribute;
    private int colspanAttribute;
    private int rowspanAttribute;
    private String classAttribute;

    public WOCell(){

    }

    public WOCell(final String parameter, WOCellContent content){
        super(parameter);
        this.content = content;
    }

    public String printToHtml(){
        StringBuilder strb = new StringBuilder();
        strb.append("<td ");
        strb.append(attributesToString());
        strb.append(">");
        strb.append("\n");
        if (content != null) strb.append(content.printToHtml());
        strb.append("</td>");
        strb.append("\n");
        return strb.toString();
    }

    @Override
    public String attributesToString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        if (classAttribute == null){
            stringBuilder.append("style=")
                    .append(getStyleAttribute())
                    .append(" ");
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
            if (colspanAttribute != 0){
                stringBuilder.append("colspan=")
                        .append(colspanAttribute)
                        .append(" ");
            }
            if (rowspanAttribute != 0){
                stringBuilder.append("rowspan=")
                        .append(rowspanAttribute)
                        .append(" ");
            }
        } else {
            stringBuilder.append("class=")
                    .append(classAttribute);
        }

        return stringBuilder.toString();
    }

    public void setContent(final WOCellContent content)
    {
        this.content = content;
    }

    void setWidthAttribute(final int widthAttribute)
    {
        this.widthAttribute = widthAttribute;
    }

    void setColspanAttribute(final int colspanAttribute)
    {
        this.colspanAttribute = colspanAttribute;
    }

    void setRowspanAttribute(final int rowspanAttribute)
    {
        this.rowspanAttribute = rowspanAttribute;
    }

    void setHeightAttribute(final int heightAttribute)
    {
        this.heightAttribute = heightAttribute;
    }

    void setClass(String classAttribute) {
        this.classAttribute = classAttribute;
    }
}
