package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;

abstract class ADataFormatStrategy implements IDataStrategy
{
    abstract public String getDataFormat(AErkundungsstelle erkundungsstelle);

    abstract public String getDataFormat(ASchicht schicht);
}
