package main.java.export;

import java.util.ArrayList;
import java.util.List;

public class TableRow extends ATable
{
    List<ATable> cells;
    final String style;

    TableRow(final String style){
        this.cells = new ArrayList<>();
        this.style = style;
    }

    void addCell(final ATable cell)
    {
        this.cells.add(cell);
    }

    @Override
    String printToHtml()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<tr").append(" ");
        strb.append("style=").append(style).append(" ");
        strb.append(">");
        strb.append("\n");
        for (ATable cell:cells)
        {
            strb.append(cell.printToHtml());
            strb.append("\n");
        }
        strb.append("</tr>");
        strb.append("\n");

        return strb.toString();
    }
}
