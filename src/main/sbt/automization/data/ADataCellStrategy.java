package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;
import sbt.automization.util.html.HtmlCell;

abstract class ADataCellStrategy
{
    abstract public HtmlCell getDataCell(AErkundungsstelle erkundungsstelle);

    abstract public HtmlCell getDataCell(ASchicht schicht);

    abstract public HtmlCell getDataCell(String data);
}
