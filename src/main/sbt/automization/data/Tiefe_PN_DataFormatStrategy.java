package sbt.automization.data;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.util.html.HtmlText;

public class Tiefe_PN_DataFormatStrategy extends ADataFormatStrategy
{
    private static Tiefe_PN_DataFormatStrategy instance;

    private Tiefe_PN_DataFormatStrategy(){}

    public static Tiefe_PN_DataFormatStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Tiefe_PN_DataFormatStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Tiefe_PN_DataFormatStrategy();
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
