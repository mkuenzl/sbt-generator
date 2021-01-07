package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;

public class WasserProctor_DataFormatStrategy extends ADataFormatStrategy
{
    private static WasserProctor_DataFormatStrategy instance;

    private WasserProctor_DataFormatStrategy(){}

    public static WasserProctor_DataFormatStrategy getInstance(){
        if (instance == null)
        {
            synchronized (WasserProctor_DataFormatStrategy.class)
            {
                if (instance == null)
                {
                    instance = new WasserProctor_DataFormatStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public String getDataFormat(final AErkundungsstelle erkundungsstelle)
    {
        return null;
    }

    @Override
    public String getDataFormat(final ASchicht schicht)
    {
        if ("".equals(schicht.getInformation("SCHICHT_FEUCHTIGKEIT"))){
            return "-";
        } else {
            return schicht.getInformation("SCHICHT_FEUCHTIGKEIT").concat(" W<sub>Pr</sub>");
        }
    }
}
