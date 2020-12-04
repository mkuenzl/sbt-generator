package main.java.util;

public class HtmlRow extends AHtml implements IHtmlCode{
    //Tags
    //String Content

    @Override
    public void appendAttribute(String attribute, String content) {

    }

    @Override
    public StringBuilder appendTag(StringBuilder strb) {
        strb.append("<")
                .append("tr")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append(content)
                .append("</tr>");
        return strb;    }

    @Override
    public void appendContent(String content) {

    }
}
