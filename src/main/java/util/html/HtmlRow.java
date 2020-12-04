package main.java.util.html;

public class HtmlRow extends AHtml{
    //Tags
    //String Content

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
