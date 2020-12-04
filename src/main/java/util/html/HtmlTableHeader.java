package main.java.util.html;

public class HtmlTableHeader extends AHtml
{
    @Override
    public String appendTag()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("th")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</th>");
        return strb.toString();
    }
}
