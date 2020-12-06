package sbt.automization.templates;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;
import java.util.List;

public final class RUKTemplateStrategy extends AHtmlTemplateStrategy
{
    private static RUKTemplateStrategy instance;

    private RUKTemplateStrategy(){}

    public static RUKTemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (RUKTemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new RUKTemplateStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    String setHtmlTableHeader()
    {
        HtmlTableHeader cell11 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "75")
                .appendContent("Versuch Nr.")
                .build();

        HtmlTableHeader cell12 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "75")
                .appendContent("Erk. St.")
                .build();

        HtmlTableHeader cell13 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendContent("Probenart")
                .build();

        HtmlTableHeader cell14 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "170")
                //.appendAttribute("colspan", "2")
                .appendContent("Prüfschicht")
                .build();

        HtmlTableHeader cell15 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendAttribute("colspan", "3")
                .appendContent("Prüftiefe | cm")
                .build();

        HtmlTableHeader cell16 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "95")
                .appendContent("Erw. RuK | °C")
                .build();

        //First Header Row
        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("height", "2")
                .appendContent(cell11.appendTag())
                .appendContent(cell12.appendTag())
                .appendContent(cell13.appendTag())
                .appendContent(cell14.appendTag())
                .appendContent(cell15.appendTag())
                .appendContent(cell16.appendTag())
                .build();

        return row1.appendTag();
    }

    @Override
    public String getExportFileName()
    {
        return "RUK_Tabelle.html";
    }

    @Override
    public void buildHtmlTable(final List<AErkundungsstelle> data)
    {
        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();

        setHtmlTable(table.appendTag());
    }
}
