package main.java.templates.styles;

public enum RowStyle
{
    ROW_STYLE1{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("mso-yfti-irow:1")
                    .append(";")
                    .append("page-break-inside:avoid")
                    .append(";")
                    .append("height:1.0cm")
                    .append("'");
            return stringBuilder.toString();
        }
    },
    ROW_STYLE2{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("mso-yfti-irow:1")
                    .append(";")
                    .append("page-break-inside:avoid")
                    .append(";")
                    .append("height:0.7cm")
                    .append("'");
            return stringBuilder.toString();
        }
    };

    public abstract String getAttributes();
}
