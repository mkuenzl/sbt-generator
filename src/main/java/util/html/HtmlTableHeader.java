package main.java.util.html;

public class HtmlTableHeader extends AHtml
{
    public static class Builder extends BaseHtmlBuilder<HtmlTableHeader, Builder>{

        @Override
        protected HtmlTableHeader getActual()
        {
            return new HtmlTableHeader();
        }

        @Override
        protected Builder getActualBuilder()
        {
            return this;
        }
    }

    @Override
    public String appendTag()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("th")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</th>");
        return strb.toString();
    }
}
