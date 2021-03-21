package sbt.automization.util;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;

import java.util.*;

public class ObjectCreatorUtil
{
    public static List<Erkundungsstelle> createErkundungsstellen(List<Map<String, String>> data)
    {
        List<Erkundungsstelle> erkundungsstellen = new ArrayList<>();

        Set<String> erkIDs = new HashSet<>();

        // Läuft über die Map Liste einzelner Schichten und ordnet diese Erkundungsstellen zu
        //EntrySets
        for (Map<String, String> dataRow : data)
        {
            String erkID = String.valueOf(dataRow.get("ERK_ID"));

            if (! erkIDs.contains(erkID))
            {
                erkIDs.add(String.valueOf(erkID));
                Erkundungsstelle erkundungsstelle = new Erkundungsstelle(dataRow);

                Schicht schicht = createSchicht(dataRow);
                erkundungsstelle.addSchicht(schicht);
                erkundungsstellen.add(erkundungsstelle);
            } else
            {
                for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
                {
                    if (erkundungsstelle.getInformation("ERK_ID").equals(erkID))
                    {
                        Schicht schicht = createSchicht(dataRow);
                        erkundungsstelle.addSchicht(schicht);
                    }
                }
            }
        }

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            //Sortiert die Schichten in einer Erkundungsstellen
            erkundungsstelle.sortSchichten();
        }

        //Sortiert die Erkundungsstellen
        Collections.sort(erkundungsstellen);

        return erkundungsstellen;
    }

    public static Schicht createSchicht(Map<String, String> dataRow)
    {
        //EntrySets
        Map<String, String> tmpMap = new HashMap<>();

        for (String key : dataRow.keySet())
        {
            if (key.contains("SCHICHT_") || key.contains("CHEMIE_"))
            {
                tmpMap.put(key, dataRow.get(key));
            }
        }
        return new Schicht(tmpMap);
    }

    //Finish implementation
    private static Erkundungsstelle createErkundungsstelle(Map<String, String> dataRow)
    {
        Erkundungsstelle erkundungsstelle = new Erkundungsstelle();

        erkundungsstelle.setIdentifier(dataRow.get("ERK_ID"))
                    .setDatum(dataRow.get("ERK_DATUM"))
                    .setPruefer(dataRow.get("ERK_PRUEFER"))
                    .setKoordinaten(dataRow.get("ERK_KOORDINATEN"))
                    .setOrt(dataRow.get("ERK_ORT"))
                    .setAufschlussOb(dataRow.get("ERK_AUFSCHLUSS_OB"))
                    .setAufschlussTob(dataRow.get("ERK_AUFSCHLUSS_TOB"))
                    .setAufschlussUg(dataRow.get("ERK_AUFSCHLUSS_UG"))
                    .setOberkante(dataRow.get("ERK_OBERKANTE"))
                    .setBelastungsklasse(dataRow.get("ERK_BELASTUNGSKLASSE"))
                    .setLpIdentifier(dataRow.get("ERK_LP"))
                    .setLpEv(dataRow.get("ERK_LP_EV"))
                    .setLpEv15(dataRow.get("ERK_LP_EV15"))
                    .setLpEv2(dataRow.get("ERK_LP_EV2"));

        return erkundungsstelle;
    }


}
