package sbt.automization.data;

import java.util.*;

public class Erkundungsstelle implements Comparable<Erkundungsstelle>, IProjektData
{
    private final Map<String, String> dataMap;
    private List<Schicht> schichtList = new ArrayList<>();

    public Erkundungsstelle(Map<String, String> data)
    {

        this.dataMap = new HashMap();

        for (String key : data.keySet())
        {
            if (key.contains("ERK_")) dataMap.put(key, data.get(key));
        }
    }

    @Override
    public int compareTo(final Erkundungsstelle o)
    {
        return this.getInformation("ERK_ID").compareTo(o.getInformation("ERK_ID"));
    }

    public String getInformation(String key)
    {
        return dataMap.get(key);
    }

    public void addSchicht(final Map<String, String> data)
    {
        Map<String, String> tmpMap = new HashMap<>();
        for (String key : data.keySet())
        {
            if (key.contains("SCHICHT_") || key.contains("CHEMIE_"))
            {
                tmpMap.put(key, data.get(key));
            }
        }
        schichtList.add(new Schicht(tmpMap));
    }

    public List<Schicht> getSchichtList()
    {
        return schichtList;
    }

    public List<Schicht> getSchichtAufschluss(final String aufschluss)
    {
        List<Schicht> schichtAufschlussList = new ArrayList<>();
        for (Schicht schicht : schichtList)
        {
            if (aufschluss.equals(schicht.getInformation("SCHICHT_AUFSCHLUSS")))
            {
                schichtAufschlussList.add(schicht);
            }
        }
        return schichtAufschlussList;
    }

    public void sort()
    {
        Collections.sort(schichtList);
    }
}
