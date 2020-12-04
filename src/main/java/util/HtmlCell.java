package main.java.util;

public class HtmlCell extends AHtml implements IHtmlCode{
    @Override
    public StringBuilder appendTag(StringBuilder strb) {
        strb.append("<")
                .append("td")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append(content)
                .append("</td>");
        return strb;    }
}
