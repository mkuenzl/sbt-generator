package main.java.export;

class TableCell extends ATable
{
    final int width;
    final String style;
    ATable content = null;

    TableCell(final int width, final String style){
        this.width = width;
        this.style = style;
    }

    ATable getContent()
    {
        return content;
    }

    public ATable setContent(final TableCellContent content)
    {
        this.content = content;
        return this;
    }

    String printToHtml(){
        StringBuilder strb = new StringBuilder();
        strb.append("<td ");
        strb.append("width=").append(width).append(" ");
        strb.append("style=").append(style).append(">");
        strb.append("\n");
        if (content != null) strb.append(content.printToHtml());
        strb.append("</td>");
        strb.append("\n");
        return strb.toString();
    }
}
