package main.java.wordblocks;

import java.util.ArrayList;
import java.util.List;

public class WordObjectRow extends AWordObject
{
    private List<WordObjectCell> cellList = new ArrayList<>();

    public WordObjectRow(final String parameter){
        super(parameter);
    }

    public WordObjectRow(){ }

    public void addCell(WordObjectCell cell)
    {
        this.cellList.add(cell);
    }

    @Override
    public String printToHtml()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<tr").append(" ");
        strb.append(this.getParameter());
        strb.append(">");
        strb.append("\n");
        for (WordObjectCell cell: cellList)
        {
            strb.append(cell.printToHtml());
            strb.append("\n");
        }
        strb.append("</tr>");
        strb.append("\n");

        return strb.toString();
    }
}
