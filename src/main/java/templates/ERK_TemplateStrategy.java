package main.java.templates;

import main.java.projekt.AErkundungsstelle;
import main.java.projekt.Projekt;
import main.java.wordblocks.*;


import java.io.File;
import java.io.IOException;
import java.util.List;

public final class ERK_TemplateStrategy extends  ATemplateStrategy
{
    private static ERK_TemplateStrategy instance;

    private ERK_TemplateStrategy(){}

    public static ERK_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (ERK_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new ERK_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public String buildHtmlTable(final Projekt projekt)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (AErkundungsstelle erkundungsstelle :
                projekt.getData())
        {
            stringBuilder.append(ERK_HEAD_TemplateStrategy.getInstance().buildHtmlTable(erkundungsstelle)).append("<p></p>");
            stringBuilder.append(ERK_OB_TemplateStrategy.getInstance().buildHtmlTable(erkundungsstelle)).append("<p></p>");
            stringBuilder.append(ERK_TOB_TemplateStrategy.getInstance().buildHtmlTable(erkundungsstelle)).append("<p></p>");
            stringBuilder.append(ERK_UG_TemplateStrategy.getInstance().buildHtmlTable(erkundungsstelle)).append("<p></p>");
        }

        return stringBuilder.toString();
    }


    @Override
    public void buildTableObject(final List<AErkundungsstelle> erkundungsstelleList)
    {

    }

    @Override
    public List<WordObjectRow> buildRows(final AErkundungsstelle erkundungsstelle)
    {
        return null;
    }

    @Override
    public String getHtmlHead()
    {
        try {
            return readHTMLHead(System.getProperty("user.dir")
                    .concat(File.separator).concat("WordTemplates")
                    .concat(File.separator).concat("ERK")
                    .concat(File.separator).concat("ERK_Template_Head"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO
        return "<head>HTML HEADER FILE NOT FOUND</head>";
    }
}
