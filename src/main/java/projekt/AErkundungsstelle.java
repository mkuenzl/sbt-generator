package main.java.projekt;

import java.util.*;

public abstract class AErkundungsstelle implements Comparable<AErkundungsstelle>
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

    // Hier Schicht Map bearbeiten
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

    void sort(){
        Collections.sort(schichtList);
    }
}
