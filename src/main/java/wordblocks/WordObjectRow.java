package main.java.wordblocks;

import java.util.ArrayList;
import java.util.List;

public class WordObjectRow extends AWordObject
{
    private List<AWordObject> cellList;

    public WordObjectRow(final String parameter){
        super(parameter);
        this.cellList = new ArrayList<>();
    }

    public void addCell(final AWordObject cell)
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
        for (AWordObject cell: cellList)
        {
            strb.append(cell.printToHtml());
            strb.append("\n");
        }
        strb.append("</tr>");
        strb.append("\n");

        return strb.toString();
    }
}
