package sbt.automization.templates;


import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.templates.styles.TableStyle;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;
import java.util.List;

public final class LPTemplateStrategy extends AHtmlTemplateStrategy
{
    private static LPTemplateStrategy instance;


    private LPTemplateStrategy(){}

    public static LPTemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (LPTemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new LPTemplateStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    String setHtmlTableHeader()
    {
        HtmlTableHeader cell1 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "54")
                .appendAttribute("rowspan", "3")        //Zelle geht über 3 Reihen
                .appendContent("Versuch Nr.")
                .build();

        HtmlTableHeader cell2 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "67")
                .appendAttribute("rowspan", "3")        //Zelle geht über 3 Reihen
                .appendContent("Messstelle Nr.")
                .build();

        HtmlTableHeader cell3 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "196")
                .appendAttribute("rowspan", "3")        //Zelle geht über 3 Reihen
                .appendContent("Lage der Messstelle")
                .build();

        HtmlTableHeader cell4 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "151")
                .appendAttribute("colspan", "4")        //Zelle geht über 4 Spalten
                .appendContent("Setzung")
                .build();

        HtmlTableHeader cell5 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendAttribute("rowspan", "2")
                .appendContent("Evdyn")
                .build();

        HtmlTableHeader cell6 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendAttribute("rowspan", "2")
                .appendContent("Evdyn15")
                .build();

        HtmlTableHeader cell7 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendAttribute("rowspan", "2")
                .appendContent("Ev2")
                .build();

        //First Header Row
        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("height", "2")
                .appendContent(cell1.appendTag())
                .appendContent(cell2.appendTag())
                .appendContent(cell3.appendTag())
                .appendContent(cell4.appendTag())
                .appendContent(cell5.appendTag())
                .appendContent(cell6.appendTag())
                .appendContent(cell7.appendTag())
                .build();

        //Second Header
        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("S1")
                .build();

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("S2")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("S3")
                .build();

        HtmlTableHeader cell24 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("x")
                .build();

        //Second Header Row
        HtmlRow row2 = new HtmlRow.Builder()
                .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .appendContent(cell24.appendTag())
                .build();

        //Third Header
        HtmlTableHeader cell31 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("mm")
                .build();

        HtmlTableHeader cell32 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("mm")
                .build();

        HtmlTableHeader cell33 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("mm")
                .build();

        HtmlTableHeader cell34 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "38")
                .appendContent("mm")
                .build();

        HtmlTableHeader cell35 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendContent("MN")
                .build();

        HtmlTableHeader cell36 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendContent("MN")
                .build();

        HtmlTableHeader cell37 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendContent("MN")
                .build();

        //Third Header Row
        HtmlRow row3 = new HtmlRow.Builder()
                .appendContent(cell31.appendTag())
                .appendContent(cell32.appendTag())
                .appendContent(cell33.appendTag())
                .appendContent(cell34.appendTag())
                .appendContent(cell35.appendTag())
                .appendContent(cell36.appendTag())
                .appendContent(cell37.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag())
                .append(row3.appendTag());

        return stringBuilder.toString();
    }

    @Override
    public String getExportFileName()
    {
        return "LP_Tabelle.html";
    }

    @Override
    public void buildHtmlTable(final List<AErkundungsstelle> data)
    {
        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("style", TableStyle.TABLE_STYLE1.getAttributes())
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();

        setHtmlTable(table.appendTag());
    }
}
