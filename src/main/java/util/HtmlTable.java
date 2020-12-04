package main.java.util;

public class HtmlTable extends AHtml implements IHtmlCode{
    //table

    @Override
    public StringBuilder appendTag(StringBuilder strb) {
        strb.append("<")
                .append("table")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append(content)
                .append("</table>");
        return strb;
    }

    //Tags table,tr,td
}
