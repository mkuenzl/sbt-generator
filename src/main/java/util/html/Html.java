package main.java.util.html;

public class Html extends AHtml
{
    @Override
    public String appendTag()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("html")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</html>");
        return strb.toString();
    }
}
