package main.java.util;

public class HtmlText extends AHtml implements IHtmlCode{
    //p , span

    @Override
    public StringBuilder appendTag(StringBuilder strb) {
        strb.append("<")
                .append("p")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append(content)
                .append("</p>");
        return strb;
    }
}
