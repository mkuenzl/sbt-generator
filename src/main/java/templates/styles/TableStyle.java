package main.java.templates.styles;

public enum TableStyle
{
    TABLE_STYLE1{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("border-collapse:collapse")
                    .append(";")
                    .append("border:none")
                    .append(";")
                    .append("mso-border-alt:solid windowtext .5pt")
                    .append(";")
                    .append("mso-yfti-tbllook:1184")
                    .append(";")
                    .append("mso-padding-alt:0cm 5.4pt 0cm 5.4pt")
                    .append("'");
            return stringBuilder.toString();
        }
    },
    TABLE_STYLE2{
        @Override
        public String getAttributes()
        {
            return null;
        }
    };

    public abstract String getAttributes();
}
