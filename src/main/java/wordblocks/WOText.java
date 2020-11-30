package main.java.wordblocks;

public class WOText extends AWordObject
{
    private Boolean span = false;
    private String text;

    public WOText(final String text){
        this.text = text;
    }

    public WOText(final String styleAttribute, final String text)
    {
        super(styleAttribute);
        this.text = text;
        this.span = true;
    }

    @Override
    public String printToHtml()
    {
        if (span){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<span ")
                .append(attributesToString())
                .append(">")
                .append(text)
                .append("</span>");
            return stringBuilder.toString();
        } else {
            return text;
        }
    }

    @Override
    public String attributesToString()
    {
        String attributes = "style=".concat(getStyleAttribute());
        return attributes;
    }
}
