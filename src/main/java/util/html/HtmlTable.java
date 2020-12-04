package main.java.util.html;

public class HtmlTable extends AHtml{
    //table

    @Override
    public String appendTag() {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("table")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</table>");
        return strb.toString();
    }

    //Tags table,tr,td
}
