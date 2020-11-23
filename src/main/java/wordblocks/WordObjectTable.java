package main.java.wordblocks;

import java.util.ArrayList;
import java.util.List;

public class WordObjectTable extends AWordObject {

    private String tableHeader;
    private List<WordObjectRow> tableRows;

    public WordObjectTable(final String parameter){
        super(parameter);
        this.tableRows = new ArrayList<>();
    }

    public void addTableRows(List<WordObjectRow> tableRows){

        for (WordObjectRow row :
                tableRows)
        {
            this.tableRows.add(row);
        }
    }

    public void addTableRow(WordObjectRow row){
        this.tableRows.add(row);
    }

    public void setTableHeader(String tableHeader) {
        this.tableHeader = tableHeader;
    }

    @Override
    public String printToHtml() {
        StringBuilder strb = new StringBuilder();
        strb.append("<table").append(" ");
        strb.append(this.getParameter());
        strb.append(">");
        strb.append("\n");

        strb.append(tableHeader).append("\n");

        for (AWordObject row : tableRows)
        {
            strb.append(row.printToHtml());
            strb.append("\n");
        }
        strb.append("</table>");
        strb.append("\n");
        return strb.toString();
    }
}
