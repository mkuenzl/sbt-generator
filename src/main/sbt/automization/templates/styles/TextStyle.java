package sbt.automization.templates.styles;

public enum TextStyle
{
    TEXT_STYLE_NORMAL{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("font-size:8.5pt")
                    .append(";")
                    .append("color:black")
                    .append(";")
                    .append("line-height:100%")
                    .append(";")
                    .append("font-family:\"Arial\"")
                    .append("'");
            return stringBuilder.toString();
        }
    },
    TEXT_STYLE_WHITE{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("font-size:8.5pt")
                    .append(";")
                    .append("color:white")
                    .append(";")
                    .append("line-height:100%")
                    .append(";")
                    .append("font-family:\"Arial\"")
                    .append("'");
            return stringBuilder.toString();
        }
    },
    TEXT_STYLE2{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("font-size:8.5pt")
                    .append(";")
                    .append("line-height:100%")
                    .append(";")
                    .append("font-family:\"Arial\"")
                    .append("'");
            return stringBuilder.toString();
        }
    };

    public abstract String getAttributes();
}
