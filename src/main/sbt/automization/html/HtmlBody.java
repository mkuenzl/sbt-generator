package sbt.automization.html;

public class HtmlBody extends AHtml
{
    @Override
    public String appendTag()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("body")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</body>");
        return strb.toString();
    }

    public static class Builder extends BaseHtmlBuilder<HtmlBody, Builder>
    {

        @Override
        protected HtmlBody getActual()
        {
            return new HtmlBody();
        }

        @Override
        protected Builder getActualBuilder()
        {
            return this;
        }
    }
}
