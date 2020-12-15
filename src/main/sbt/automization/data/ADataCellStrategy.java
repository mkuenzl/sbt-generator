package sbt.automization.data;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.util.html.HtmlCell;

abstract class ADataCellStrategy
{
    abstract public HtmlCell getDataCell(AErkundungsstelle erkundungsstelle);

    abstract public HtmlCell getDataCell(ASchicht schicht);

    abstract public HtmlCell getDataCell(String data);
}
