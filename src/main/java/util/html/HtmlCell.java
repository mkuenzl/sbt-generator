package main.java.util.html;

public class HtmlCell extends AHtml{

    public static class Builder extends BaseHtmlBuilder<HtmlCell, Builder> {

        @Override
        protected HtmlCell getActual()
        {
            return new HtmlCell();
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
                .append("td")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</td>");
        return strb.toString();    }
}
