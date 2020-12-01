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


    //Berechnungen! Auslagern?!

    public String getLPMean(){

        double lp1 = Double.parseDouble(getInformation("ERK_LP1"));
        double lp2 = Double.parseDouble(getInformation("ERK_LP2"));
        double lp3 = Double.parseDouble(getInformation("ERK_LP3"));

        double mean = (lp1 + lp2 + lp3) / 3;

        return String.valueOf(mean);
    }

    public String getLPEv(){

        double evDyn = 22.5 / Double.valueOf(getLPMean());

        return String.valueOf(evDyn);
    }

    public String getLPEv15(){

        double evDyn15 = Double.valueOf(getLPEv()) * 0.85;

        return String.valueOf(evDyn15);
    }

    //TODO
    public String getLPEv2(){
        double ev15 = Double.parseDouble(getLPEv15());

        return null;
    }
}
