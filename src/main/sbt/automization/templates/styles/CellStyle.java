package sbt.automization.templates.styles;

public enum CellStyle
{
    CELL_STYLE_NORMAL{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("border:solid windowtext .5pt")
                    .append(";")
                    .append("padding:0cm 5.4pt 0cm 5.4pt")
                    .append("'");
            return stringBuilder.toString();
        }
    },
    CELL_STYLE_RED{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("border:solid windowtext .5pt")
                    .append(";")
                    .append("background:red")
                    .append(";")
                    .append("padding:0cm 5.4pt 0cm 5.4pt")
                    .append("'");
            return stringBuilder.toString();
        }
    },
    CELL_STYLE_GREEN{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("border:solid windowtext .5pt")
                    .append(";")
                    .append("background:#538135")
                    .append(";")
                    .append("padding:0cm 5.4pt 0cm 5.4pt")
                    .append("'");
            return stringBuilder.toString();
        }
    },
    CELL_STYLE_BLACK{
        @Override
        public String getAttributes()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'")
                    .append("border:solid windowtext .5pt")
                    .append(";")
                    .append("background:black")
                    .append(";")
                    .append("padding:0cm 5.4pt 0cm 5.4pt")
                    .append("'");
            return stringBuilder.toString();
        }
    }
    ;

    public abstract String getAttributes();

}
