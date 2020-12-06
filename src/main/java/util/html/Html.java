package main.java.util.html;

public class Html extends AHtml
{
    public static class Builder extends BaseHtmlBuilder<Html, Builder> {

        @Override
        protected Html getActual()
        {
            return new Html();
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
                .append("html")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</html>");
        return strb.toString();
    }
}
