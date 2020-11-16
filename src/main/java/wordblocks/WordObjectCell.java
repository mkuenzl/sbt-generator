package main.java.wordblocks;

public class WordObjectCell extends AWordObject
{
    AWordObject content;

    public WordObjectCell(final String parameter){
        super(parameter);
    }

    public WordObjectCell(final String parameter, WordObjectCellContent content){
        super(parameter);
        this.content = content;
    }


    AWordObject getContent()
    {
        return content;
    }

    public void setContent(final WordObjectCellContent content)
    {
        this.content = content;
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
