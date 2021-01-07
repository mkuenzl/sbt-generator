package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;
import sbt.automization.util.html.HtmlText;

public class TiefeVonBis_DataFormatStrategy extends ADataFormatStrategy
{
    private static TiefeVonBis_DataFormatStrategy instance;

    private TiefeVonBis_DataFormatStrategy(){}

    public static TiefeVonBis_DataFormatStrategy getInstance(){
        if (instance == null)
        {
            synchronized (TiefeVonBis_DataFormatStrategy.class)
            {
                if (instance == null)
                {
                    instance = new TiefeVonBis_DataFormatStrategy();
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
        HtmlText htmlText = new HtmlText.Builder()
                .appendAttribute("class", "Normal7")
                .appendContent("[")
                .appendContent(schicht.getInformation("SCHICHT_TIEFE_START"))
                .appendContent("-")
                .appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
                .appendContent("]")
                .build();

        return htmlText.appendTag();
    }
}
