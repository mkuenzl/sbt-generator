package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;
import sbt.automization.util.html.HtmlText;

public class BodenGruppe_DataFormatStrategy extends ADataFormatStrategy
{
    private static BodenGruppe_DataFormatStrategy instance;

    private BodenGruppe_DataFormatStrategy(){}

    public static BodenGruppe_DataFormatStrategy getInstance(){
        if (instance == null)
        {
            synchronized (BodenGruppe_DataFormatStrategy.class)
            {
                if (instance == null)
                {
                    instance = new BodenGruppe_DataFormatStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public String getDataFormat(AErkundungsstelle erkundungsstelle) {
        return null;
    }

    @Override
    public String getDataFormat(ASchicht schicht) {

        String schicht_art = schicht.getInformation("SCHICHT_ART");

        switch (schicht_art){
            case "GE":
                schicht_art = "Engestufte Kiese";
                break;
            case "GW":
                schicht_art = "Weitgestufte Kies-Sand-Gemische";
                break;
            case "GI":
                schicht_art = "Intermittierend gestufte Kies-Sand-Gemische";
                break;
            case "SE":
                schicht_art = "Enggestufte Sande";
                break;
            case "SW":
                schicht_art = "Weitgestufte Sand-Kies-Gemische";
                break;
            case "SI":
                schicht_art = "Intermittierend gestufte Sand-Kies-Gemische";
                break;
            case "GU":
            case "GU*":
                schicht_art = "Kies-Schluff-Gemisch";
                break;
            case "GT":
            case "GT*":
                schicht_art = "Kies-Ton-Gemisch";
                break;
            case "SU":
            case "SU*":
                schicht_art = "Sand-Schluff-Gemisch";
                break;
            case "ST":
            case "ST*":
                schicht_art = "Sand-Ton-Gemisch";
                break;
            case "UL":
                schicht_art = "Leicht plastische Schluffe";
                break;
            case "UM":
                schicht_art = "Mittelplastische Schluffe";
                break;
            case "UA":
                schicht_art = "Ausgeprägt plastische Schluffe";
                break;
            case "TL":
                schicht_art = "Leicht plastische Tone";
                break;
            case "TM":
                schicht_art = "Mittelplastische Tone";
                break;
            case "TA":
                schicht_art = "Ausgeprägt plastische Tone";
                break;
            case "OU":
                schicht_art = "Organogene Schluffe";
                break;
            case "OT":
                schicht_art = "Organogene Tone";
                break;
            case "OH":
                schicht_art = "Grob bis gemischtkörnige Böden mit humosen Beimengungen";
                break;
            case "OK":
                schicht_art = "Grob bis gemischtkörnige Böden mit kalkigen, kieseligen Bildungen";
                break;
            case "HN":
                schicht_art = "Nicht bis mäßig zersetzte Torfe";
                break;
            case "HZ":
                schicht_art = "Zersetzte Torfe";
                break;
            case "F":
                schicht_art = "Mudden";
                break;
            default:
                break;
        }
        if (schicht_art.contains("[")) {
            String tmp = new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent("Fremdstoffen " + schicht_art)
                    .build()
                    .appendTag();

            schicht_art = "Auffüllung aus" + tmp;
        }

        return schicht_art;
    }
}
