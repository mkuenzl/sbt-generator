package sbt.automization.util;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;

import java.util.*;

public class ObjectCreatorUtil
{
    public static List<ExplorationSite> createExplorationSites(List<Map<String, String>> data)
    {
        List<ExplorationSite> explorationSites = new ArrayList<>();

        Set<String> erkIDs = new HashSet<>();

        // Läuft über die Map Liste einzelner Schichten und ordnet diese Erkundungsstellen zu
        //EntrySets
        for (Map<String, String> dataRow : data)
        {
            String erkID = String.valueOf(dataRow.get("ERK_ID"));

            if (! erkIDs.contains(erkID))
            {
                erkIDs.add(String.valueOf(erkID));
                ExplorationSite explorationSite = new ExplorationSite(dataRow);

                Layer layer = createLayer(dataRow);
                explorationSite.addSchicht(layer);
                explorationSites.add(explorationSite);
            } else
            {
                for (ExplorationSite explorationSite : explorationSites)
                {
                    if (explorationSite.getInformation("ERK_ID").equals(erkID))
                    {
                        Layer layer = createLayer(dataRow);
                        explorationSite.addSchicht(layer);
                    }
                }
            }
        }

        for (ExplorationSite explorationSite : explorationSites)
        {
            //Sortiert die Schichten in einer Erkundungsstellen
            explorationSite.sortSchichten();
        }

        //Sortiert die Erkundungsstellen
        //BA12 FB123 GEW12345
        //BA1 FB2 FB1 BA2 GEW1 FB3 GEW2
        //Liste{Liste{BA1, BA2}, Liste{FB2, FB1, FB3}, Liste{GEW1, GEW2}}
        //Collections.sort(explorationSites);

        //TODO kännte funktionieren, muss aber nicht unbedingt
        //java.util.Collections.sort(explorationSites);

        return explorationSites;
    }

    public static Layer createLayer(Map<String, String> dataRow)
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
        return new Layer(tmpMap);
    }

    //Finish implementation
    @Deprecated
    private static ExplorationSite createExplorationSite(Map<String, String> dataRow)
    {
        ExplorationSite explorationSite = new ExplorationSite();

        explorationSite.setIdentifier(dataRow.get("ERK_ID"))
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

        return explorationSite;
    }


}
