package sbt.automization.data;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.util.html.HtmlCell;

public class Chemie_DataCellStrategy extends ADataCellStrategy
{
    private static Chemie_DataCellStrategy instance;

    private Chemie_DataCellStrategy(){}

    public static Chemie_DataCellStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Chemie_DataCellStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Chemie_DataCellStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    HtmlCell getDataCell(final AErkundungsstelle erkundungsstelle)
    {
        return null;
    }

    @Override
    HtmlCell getDataCell(final ASchicht schicht)
    {
        return null;
    }
}
