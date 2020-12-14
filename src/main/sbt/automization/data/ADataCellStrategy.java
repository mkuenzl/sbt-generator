package sbt.automization.data;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.util.html.HtmlCell;

abstract class ADataCellStrategy
{
    abstract HtmlCell getDataCell(AErkundungsstelle erkundungsstelle);

    abstract HtmlCell getDataCell(ASchicht schicht);
}
