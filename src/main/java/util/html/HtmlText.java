package main.java.util.html;

public class HtmlText extends AHtml{
    //p , span

    @Override
    public String appendTag() {
        StringBuilder strb = new StringBuilder();
        strb.append("<")
                .append("p")
                .append(" ")
                .append(appendAttributes())
                .append(">")
                .append("\n")
                .append(content)
                .append("\n")
                .append("</p>");
        return strb.toString();
    }
}
