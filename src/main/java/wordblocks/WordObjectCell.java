package main.java.wordblocks;

public class WordObjectCell extends AWordObject
{
    AWordObject content = null;

    public WordObjectCell(final String parameter){
        super(parameter);
    }

    AWordObject getContent()
    {
        return content;
    }

    public AWordObject setContent(final WordObjectCellContent content)
    {
        this.content = content;
        return this;
    }

    public String printToHtml(){
        StringBuilder strb = new StringBuilder();
        strb.append("<td ");
        strb.append(this.getParameter());
        strb.append(">");
        strb.append("\n");
        if (content != null) strb.append(content.printToHtml());
        strb.append("</td>");
        strb.append("\n");
        return strb.toString();
    }
}
