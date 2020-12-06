package main.java.util.html;

public class HtmlTable extends AHtml{
    //table

    public static class Builder extends BaseHtmlBuilder<HtmlTable, Builder>{

        @Override
        protected HtmlTable getActual()
        {
            return new HtmlTable();
        }

        @Override
        protected Builder getActualBuilder()
        {
            return this;
        }
    }

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
