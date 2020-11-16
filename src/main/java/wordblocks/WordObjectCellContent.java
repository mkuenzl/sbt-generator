package main.java.wordblocks;

import java.util.ArrayList;
import java.util.List;

public class WordObjectCellContent extends AWordObject
{
    private List<String> contents;

    //final String span; Dick,Dünn,Kursiv Text

    public WordObjectCellContent(final String parameter){
        super(parameter);
        this.contents = new ArrayList<>();
    }

    public WordObjectCellContent addCellContent(String content)
    {
        this.contents.add(content);
        return this;
    }

    @Override
    public String printToHtml()
    {
        StringBuilder strb = new StringBuilder();
        for (String content : contents)
        {
            strb.append("<p ");
            strb.append(this.getParameter()).append(" ");
            strb.append(">");
            strb.append(content);
            strb.append("</p>").append("\n");
        }
        if (contents.isEmpty()){
            strb.append("<p ").append(this.getParameter()).append(">");
            strb.append("><o:p>&nbsp;</o:p>");
            strb.append("</p>").append("\n");
        }
        return strb.toString();
    }
}
