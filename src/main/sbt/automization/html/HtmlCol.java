package sbt.automization.html;

public class HtmlCol extends AHtml
{
    @Override
    public String appendTag()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("col style=\"width:")
                .append(" ")
                .append(content)
                .append("\">")
                .append("\n");
        return strb.toString();
    }

    public static class Builder extends BaseHtmlBuilder<HtmlCol, Builder>
    {

        @Override
        protected HtmlCol getActual()
        {
            return new HtmlCol();
        }

        @Override
        protected Builder getActualBuilder()
        {
            return this;
        }
    }
}
