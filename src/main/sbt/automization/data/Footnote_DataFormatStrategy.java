package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;
import sbt.automization.util.html.HtmlText;

public class Footnote_DataFormatStrategy extends ADataFormatStrategy{

    private static Footnote_DataFormatStrategy instance;

    private Footnote_DataFormatStrategy(){}

    public static Footnote_DataFormatStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Footnote_DataFormatStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Footnote_DataFormatStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public String getDataFormat(AErkundungsstelle erkundungsstelle) {

        int counter = 1;
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.valueOf(counter++))
                .append(" .) ")
                .append("Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m");

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

    @Override
    public String getDataFormat(ASchicht schicht) {
        return null;
    }

//    •	Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m  kommt immer rein
//  •	Bewertung unter Berücksichtigung der Angaben im Leitfaden Ausbauasphalt  Leitf. Ausbauasph.
//  •	teilweise verfestigt  teilw. verfest.
//  •	Aufgrund der Überschreitung eines Orientierungswertes ist eine Aufbereitung (z. B. als RC-Gemisch)
//    ggf. nicht möglich. Absprache mit Behörde empfohlen  überschr. Orientierungsw.
//  •	Rammhindernis; keine tiefere Entnahme möglich  Rammhindernis
//  •	Kabeltrasse; keine tiefere Entnahme möglich  Kabeltrasse
//  •	mit mineralischen Fremdbestandteilen < 10 V.-%  Fremdbest. < 10%
//            •	Ggf. günstigere Einstufung nach Rücksprache mit der Behörde möglich  günsti. Einstufung
//  •	Einstufung unter Vernachlässigung des Parameters elektrische Leitfähigkeit  vernachl. eL
//  •	Prüfergebnisse unter Berücksichtigung einer ca. 15 % Reduzierung aufgrund der Einspannung durch den gebundenen Oberbau  immer dann, wenn LPdyn gemacht wurde

}
