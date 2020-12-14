package sbt.automization.data;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;

abstract class ADataFormatStrategy implements IDataStrategy
{
    abstract public String getDataFormat(AErkundungsstelle erkundungsstelle);

    abstract public String getDataFormat(ASchicht schicht);
}
