package main.java.export;

import java.util.ArrayList;
import java.util.List;

public class TableCellContent extends ATable
{
    final String classStyle;
    final String align;
    final String style;

    private List<String> contents;

    //final String span; Dick,Dünn,Kursiv Text


    TableCellContent(final String classStyle, final String align, final String style){
        this.classStyle = classStyle;
        this.align = align;
        this.style = style;
        this.contents = new ArrayList<>();
    }

    TableCellContent(final String classStyle){
        this.classStyle = classStyle;
        this.align = null;
        this.style = null;
        this.contents = new ArrayList<>();
    }

    TableCellContent addContent(String content)
    {
        this.contents.add(content);
        return this;
    }

    @Override
    String printToHtml()
    {
        StringBuilder strb = new StringBuilder();
        for (String content : contents)
        {
            strb.append("<p ");
            strb.append("class=").append(classStyle).append(" ");
            strb.append("align=").append(align).append(" ");
            strb.append("style=").append(style).append(" ");
            strb.append(">");
            strb.append(content);
            strb.append("</p>").append("\n");
        }
        if (contents.isEmpty()){
            strb.append("<p class=").append(classStyle).append(">");
            strb.append("><o:p>&nbsp;</o:p>");
            strb.append("</p>").append("\n");
        }
        return strb.toString();
    }
}
