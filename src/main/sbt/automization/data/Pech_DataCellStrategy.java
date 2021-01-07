package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;
import sbt.automization.util.html.HtmlCell;

public class Pech_DataCellStrategy extends ADataCellStrategy
{
    private static Pech_DataCellStrategy instance;

    private Pech_DataCellStrategy(){}

    public static Pech_DataCellStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Pech_DataCellStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Pech_DataCellStrategy();
                }
            }
        }
        return instance;
    }


    @Override
    public HtmlCell getDataCell(final AErkundungsstelle erkundungsstelle)
    {
        return null;
    }

    @Override
    public HtmlCell getDataCell(final ASchicht schicht)
    {
        return null;
    }

    @Override
    public HtmlCell getDataCell(final String data)
    {
        HtmlCell htmlCell = new HtmlCell();

        switch (data)
        {
            case "nein":
                htmlCell.appendAttribute("class", "ChemieWhite");
                htmlCell.appendContent("FREI");
                break;
            case "ja":
                htmlCell.appendAttribute("class", "ChemieBlack");
                htmlCell.appendContent("PECH");
                break;
            default:
                break;
        }

        return htmlCell;
    }
}
