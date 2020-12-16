package sbt.automization.projekt;

import java.util.*;

public abstract class AErkundungsstelle implements Comparable<AErkundungsstelle>, IProjektData
{
    private final Map<String,String> information;
    private List<ASchicht> schichtList = new ArrayList<>();

    AErkundungsstelle(Map<String,String> data) {

        this.information = new HashMap();

        for (String key : data.keySet())
        {
            if (key.contains("ERK_")) information.put(key, data.get(key));
        }
    }

    public String getInformation(String key){
        return information.get(key);
    }

    @Override
    public int compareTo(final AErkundungsstelle o)
    {
        return this.getInformation("ERK_ID").compareTo(o.getInformation("ERK_ID"));
    }

    void addSchicht(final Map<String,String> data)
    {
        Map<String, String> tmpMap = new HashMap<>();
        for (String key : data.keySet())
        {
            if (key.contains("SCHICHT_") || key.contains("CHEMIE_")){
                tmpMap.put(key, data.get(key));
            }
        }
        schichtList.add(new Schicht(tmpMap));
    }

    public List<ASchicht> getSchichtList()
    {
        return schichtList;
    }

    public List<ASchicht> getSchichtAufschluss(final String aufschluss) {
        List<ASchicht> schichtAufschlussList = new ArrayList<>();
        for (ASchicht schicht :
                schichtList)
        {
            if (aufschluss.equals(schicht.getInformation("SCHICHT_AUFSCHLUSS")))
            {
                schichtAufschlussList.add(schicht);
            }
        }
        return schichtAufschlussList;
    }

    void sort(){
        Collections.sort(schichtList);
    }
}
