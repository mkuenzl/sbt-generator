package main.java.util.html;

public class HtmlCell extends AHtml{
    @Override
    public String appendTag() {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("td")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</td>");
        return strb.toString();    }
}
