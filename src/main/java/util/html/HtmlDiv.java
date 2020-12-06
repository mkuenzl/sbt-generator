package main.java.util.html;

public class HtmlDiv extends AHtml
{
    public static class Builder extends BaseHtmlBuilder<HtmlDiv, Builder>{

        @Override
        protected HtmlDiv getActual()
        {
            return new HtmlDiv();
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
                .append("div")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</div>");
        return strb.toString();
    }
}
