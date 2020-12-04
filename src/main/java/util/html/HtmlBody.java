package main.java.util.html;

public class HtmlBody extends  AHtml
{
    @Override
    public String appendTag()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("body")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</body>");
        return strb.toString();
    }
}
