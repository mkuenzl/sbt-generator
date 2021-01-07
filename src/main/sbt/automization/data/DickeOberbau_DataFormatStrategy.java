package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;

import java.util.List;

public class DickeOberbau_DataFormatStrategy extends ADataFormatStrategy
{
    private double height = 0;
    private static DickeOberbau_DataFormatStrategy instance;

    private DickeOberbau_DataFormatStrategy(){}

    public static DickeOberbau_DataFormatStrategy getInstance(){
        if (instance == null)
        {
            synchronized (DickeOberbau_DataFormatStrategy.class)
            {
                if (instance == null)
                {
                    instance = new DickeOberbau_DataFormatStrategy();
                }
            }
        }
        return instance;
    }


    @Override
    public String getDataFormat(final AErkundungsstelle erkundungsstelle)
    {
        List<ASchicht> schichtList = erkundungsstelle.getSchichtAufschluss("GOB");
        for (ASchicht schicht : schichtList){

            height = height  + Double.parseDouble(schicht.getInformation("SCHICHT_DICKE").replace(",","."));
        }
        return String.valueOf(height);
    }

    @Override
    public String getDataFormat(final ASchicht schicht)
    {
        return null;
    }
}
