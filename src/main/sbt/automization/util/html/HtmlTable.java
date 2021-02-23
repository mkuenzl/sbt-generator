package sbt.automization.util.html;

public class HtmlTable extends AHtml
{
    //table

    @Override
    public String appendTag()
    {
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

    public static class Builder extends BaseHtmlBuilder<HtmlTable, Builder>
    {

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

    //Tags table,tr,td
}
