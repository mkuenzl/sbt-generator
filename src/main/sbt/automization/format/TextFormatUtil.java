package sbt.automization.format;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class TextFormatUtil {

    public static String formatSchichtProctor(final Schicht schicht){
        if ("".equals(schicht.getInformation("SCHICHT_FEUCHTIGKEIT"))){
            return "-";
        } else {
            return schicht.getInformation("SCHICHT_FEUCHTIGKEIT").concat(" W<sub>Pr</sub>");
        }
    }

    public static String formatErkAufschlussDicke(final Erkundungsstelle erkundungsstelle, String aufschluss){

        double height = 0.0;
        List<Schicht> schichtList = erkundungsstelle.getSchichtAufschluss(aufschluss);
        for (Schicht schicht : schichtList){

            height = height  + Double.parseDouble(schicht.getInformation("SCHICHT_DICKE").replace(",","."));
        }
        return String.valueOf(height);
    }

    public static String formatSchichtProbePN(final Schicht schicht)
    {
        String probenart;
        if ("".equals(schicht.getInformation("SCHICHT_BEHAELTNIS"))){
            probenart = "EP";
        } else {
            probenart = "MP";
        }
        return probenart;
    }

    public static String formatErkFootnotes(final Erkundungsstelle erkundungsstelle) {

        int counter = 1;
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(new HtmlText.Builder()
                        .appendAttribute("class","Normal")
                        .appendContent(String.valueOf(counter++))
                        .appendContent(".) ")
                        .appendContent("Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m")
                        .build()
                        .appendTag());

        if ("#".equals(erkundungsstelle.getInformation("ERK_LEITFADEN_AUSBAUASPHALT"))){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Bewertung unter Berücksichtigung der Angaben im Leitfaden Ausbauasphalt")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_TEILWEISE_VERFESTIGT"))){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("teilweise verfestigt")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_UEBERSCHREITUNG_ORIENT"))){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Aufgrund der Überschreitung eines Orientierungswertes ist eine Aufbereitung (z. B. als RC-Gemisch) ggf. nicht möglich.")
                    .build()
                    .appendTag())
                    .append(new HtmlText.Builder()
                            .appendAttribute("class","Normal")
                            .appendContent("Absprache mit Behörde empfohlen")
                            .build()
                            .appendTag());

        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_RAMMHINDERNIS"))){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Rammhindernis; keine tiefere Entnahme möglich")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_KABELTRASSE"))){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Kabeltrasse; keine tiefere Entnahme möglich")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_FREMDBESTANDTEILE"))){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("mit mineralischen Fremdbestandteilen < 10 V.-%")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_GUENSTIGE_EINSTUFUNG"))){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Ggf. günstigere Einstufung nach Rücksprache mit der Behörde möglich")
                    .build()
                    .appendTag());
        }

        if ("#".equals(erkundungsstelle.getInformation("ERK_VERNACHLAESSIGUNG_LEITFAEHIGKEIT"))){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Einstufung unter Vernachlässigung des Parameters elektrische Leitfähigkeit")
                    .build()
                    .appendTag());
        }

        if(erkundungsstelle.getInformation("ERK_VARIABLE_FOOTNOTE") != null){
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent(erkundungsstelle.getInformation("ERK_VARIABLE_FOOTNOTE"))
                    .build()
                    .appendTag());
        }


        if (!"".equals(erkundungsstelle.getInformation("ERK_LP"))){

            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class","Normal")
                    .appendContent(String.valueOf(counter++))
                    .appendContent(".) ")
                    .appendContent("Prüfergebnisse unter Berücksichtigung einer ca. 15 % Reduzierung aufgrund der Einspannung durch den ")
                    .build()
                    .appendTag())
                    .append(new HtmlText.Builder()
                            .appendAttribute("class","Normal")
                            .appendContent("gebundenen Oberbau")
                            .build()
                            .appendTag());;
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

    public static String formatSchichtBodenGruppe(final Schicht schicht) {

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
