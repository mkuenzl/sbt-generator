package main.java.util.html;

public class HtmlRow extends AHtml{
    //Tags
    //String Content
    public static class Builder extends BaseHtmlBuilder<HtmlRow, Builder>{

        @Override
        protected HtmlRow getActual()
        {
            return new HtmlRow();
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
                .append("tr")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</tr>");
        return strb.toString();
    }

}
