package sbt.automization.data;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;

public class PNProbenart_DataFormatStrategy extends ADataFormatStrategy
{
    private static PNProbenart_DataFormatStrategy instance;

    private PNProbenart_DataFormatStrategy(){}

    public static PNProbenart_DataFormatStrategy getInstance(){
        if (instance == null)
        {
            synchronized (PNProbenart_DataFormatStrategy.class)
            {
                if (instance == null)
                {
                    instance = new PNProbenart_DataFormatStrategy();
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
        String probenart;
        if ("".equals(schicht.getInformation("SCHICHT_BEHAELTNIS"))){
            probenart = "EP";
        } else {
            probenart = "MP";
        }
        return probenart;
    }
}
