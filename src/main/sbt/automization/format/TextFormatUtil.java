package sbt.automization.format;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class TextFormatUtil
{
    public static String formatBelastungsklasse(final Erkundungsstelle erkundungsstelle)
    {
        String s1 = new HtmlText.Builder().appendAttribute("class", "Normal")
                .appendContent("Belastungsklasse")
                .build()
                .appendTag();
        String s2 = new HtmlText.Builder().appendAttribute("class", "Normal")
                .appendContent("Bk" + erkundungsstelle.getInformation("ERK_BELASTUNGSKLASSE"))
                .build()
                .appendTag();

        return s1 + s2;
    }


    public static String formatSchichtProctor(final Schicht schicht)
    {
        if ("".equals(schicht.getInformation("SCHICHT_FEUCHTIGKEIT")))
        {
            return "-";
        } else
        {
            return schicht.getInformation("SCHICHT_FEUCHTIGKEIT").concat(" W<sub>Pr</sub>");
        }
    }

    public static String formatErkAufschlussDicke(final Erkundungsstelle erkundungsstelle, String aufschluss)
    {

        double height = 0.0;
        List<Schicht> schichtList = erkundungsstelle.getSchichtAufschluss(aufschluss);
        for (Schicht schicht : schichtList)
        {

            height = height + Double.parseDouble(schicht.getInformation("SCHICHT_DICKE").replace(",", "."));
        }
        return String.valueOf(height);
    }

    public static String formatSchichtProbePN(final Schicht schicht)
    {
        String probenart;
        if ("".equals(schicht.getInformation("SCHICHT_BEHAELTNIS")))
        {
            probenart = "EP";
        } else
        {
            probenart = "MP";
        }
        return probenart;
    }

    public static String formatErkFootnotes(final Erkundungsstelle erkundungsstelle)
    {

        int counter = 1;
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(new HtmlText.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(String.valueOf(counter++))
                .appendContent(".) ")
                .appendContent("Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m")
                .build()
                .appendTag());

        if ("#".equals(erkundungsstelle.getInformation("ERK_LEITFADEN_AUSBAUASPHALT")))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Bewertung unter Berücksichtigung der Angaben im Leitfaden Ausbauasphalt")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_TEILWEISE_VERFESTIGT")))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("teilweise verfestigt")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_UEBERSCHREITUNG_ORIENT")))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Aufgrund der Überschreitung eines Orientierungswertes ist eine Aufbereitung (z. B. als RC-Gemisch) ggf. nicht möglich.")
                    .build()
                    .appendTag())
                    .append(new HtmlText.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent("Absprache mit Behörde empfohlen")
                            .build()
                            .appendTag());

        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_RAMMHINDERNIS")))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Rammhindernis; keine tiefere Entnahme möglich")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_KABELTRASSE")))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Kabeltrasse; keine tiefere Entnahme möglich")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_FREMDBESTANDTEILE")))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("mit mineralischen Fremdbestandteilen < 10 V.-%")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_GUENSTIGE_EINSTUFUNG")))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Ggf. günstigere Einstufung nach Rücksprache mit der Behörde möglich")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_VERNACHLAESSIGUNG_LEITFAEHIGKEIT")))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Einstufung unter Vernachlässigung des Parameters elektrische Leitfähigkeit")
                    .build()
                    .appendTag());
        }

        if (erkundungsstelle.getInformation("ERK_VARIABLE_FOOTNOTE") != null)
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent(erkundungsstelle.getInformation("ERK_VARIABLE_FOOTNOTE"))
                    .build()
                    .appendTag());
        }


        if (! "".equals(erkundungsstelle.getInformation("ERK_LP")))
        {

            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Prüfergebnisse unter Berücksichtigung einer ca. 15 % Reduzierung aufgrund der Einspannung durch den ")
                    .build()
                    .appendTag())
                    .append(new HtmlText.Builder()
                            .appendAttribute("class", "Normal")
                            .appendContent("gebundenen Oberbau")
                            .build()
                            .appendTag());

        }
        return stringBuilder.toString();
    }


    public static String formatSchichtTiefe(final Schicht schicht)
    {
        HtmlText htmlText = new HtmlText.Builder()
                .appendAttribute("class", "Normal")
                .appendContent("")
                .appendContent(schicht.getInformation("SCHICHT_TIEFE_START"))
                .appendContent("-")
                .appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
                .appendContent("")
                .build();

        return htmlText.appendTag();
    }

    /**
     * Expects a valid Bodengruppe and formats the String for representation in the ERK_Anlage
     *
     * @param schichtArt a valid Bodengruppe as String either with [] or without
     * @return a formated String of the long text and short text of a Bodengruppe
     */
    public static String formatSchichtBodenGruppe(final String schichtArt)
    {
        boolean isFillUp;
        String kind;
        String kindText;

        if (schichtArt == null)
        {
            return "-";
        }

        if (schichtArt.contains("["))
        {
            kind = schichtArt.replaceAll("[\\[\\]]", "");
            isFillUp = true;
        } else
        {
            kind = schichtArt;
            isFillUp = false;
        }

        switch (kind)
        {
            case "GE":
                kindText = "Engestufte Kiese";
                break;
            case "GW":
                kindText = "Weitgestufte Kies-Sand-Gemische";
                break;
            case "GI":
                kindText = "Intermittierend gestufte Kies-Sand-Gemische";
                break;
            case "SE":
                kindText = "Enggestufte Sande";
                break;
            case "SW":
                kindText = "Weitgestufte Sand-Kies-Gemische";
                break;
            case "SI":
                kindText = "Intermittierend gestufte Sand-Kies-Gemische";
                break;
            case "GU":
            case "GU*":
                kindText = "Kies-Schluff-Gemisch";
                break;
            case "GT":
            case "GT*":
                kindText = "Kies-Ton-Gemisch";
                break;
            case "SU":
            case "SU*":
                kindText = "Sand-Schluff-Gemisch";
                break;
            case "ST":
            case "ST*":
                kindText = "Sand-Ton-Gemisch";
                break;
            case "UL":
                kindText = "Leicht plastische Schluffe";
                break;
            case "UM":
                kindText = "Mittelplastische Schluffe";
                break;
            case "UA":
                kindText = "Ausgeprägt plastische Schluffe";
                break;
            case "TL":
                kindText = "Leicht plastische Tone";
                break;
            case "TM":
                kindText = "Mittelplastische Tone";
                break;
            case "TA":
                kindText = "Ausgeprägt plastische Tone";
                break;
            case "OU":
                kindText = "Organogene Schluffe";
                break;
            case "OT":
                //schicht_art = "Organogene Tone";
                //break;
            case "OH":
                kindText = "Oberboden";
                break;
            case "OK":
                kindText = "Grob bis gemischtkörnige Böden mit kalkigen, kieseligen Bildungen";
                break;
            case "HN":
                kindText = "Nicht bis mäßig zersetzte Torfe";
                break;
            case "HZ":
                kindText = "Zersetzte Torfe";
                break;
            case "F":
                kindText = "Mudden";
                break;
            default:
                kindText = "Invalid Bodengruppe";
                break;
        }

        if (isFillUp)
        {
            return kindText + " " + "[" + kind + "]";
        }

        return kindText + " " + kind;
    }
}
