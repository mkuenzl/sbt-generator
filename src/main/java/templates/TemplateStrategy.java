package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.util.html.HtmlTable;

import java.util.List;

public class TemplateStrategy extends AHtmlTemplateStrategy
{
    private static TemplateStrategy instance;


    private TemplateStrategy(){}

    public static TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new TemplateStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public void buildHtmlTable(final List<AErkundungsstelle> data)
    {
        HtmlTable table = new HtmlTable();
        //Attribute Declaration
        table.appendAttribute("class", "MsoNormalTable");
        table.appendAttribute("width", "605");
        table.appendAttribute("border", "1");
        table.appendAttribute("cellspacing", "0");
        table.appendAttribute("cellpadding", "0");

        table.appendContent(setHtmlTableHeader());
        table.appendContent("<tr><td></td></tr>");

        setHtmlTable(table.appendTag());
    }

    @Override
    String setHtmlTableHeader()
    {
        return "<tr><th></th></tr>";
    }
}
