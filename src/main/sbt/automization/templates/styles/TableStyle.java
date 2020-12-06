package sbt.automization.templates.styles;

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
                    .append("border:solid windowtext .5pt")
                    .append(";")
                    .append("padding:0cm 5.4pt 0cm 5.4pt")
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
