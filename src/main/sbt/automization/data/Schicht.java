package sbt.automization.data;

import java.io.Serializable;
import java.util.Map;

public class Schicht implements Comparable<Schicht>, IProjektData, Cloneable, Serializable
{
    private Map<String, String> dataMap;

    public Schicht(Map<String, String> dataMap)
    {
        this.dataMap = dataMap;
    }

    public Schicht()
    {

    }

    public void setInformation(String key, String value)
    {
        this.dataMap.put(key, value);
    }

    @Override
    public int compareTo(final Schicht schicht)
    {
        int s1 = Integer.parseInt(this.getInformation("SCHICHT_NR"));
        int s2 = Integer.parseInt(schicht.getInformation("SCHICHT_NR"));
        return s1 - s2;
    }

    public String getInformation(String key)
    {
        String s = dataMap.get(key);
        if (s == null || s.equals("")) return "-";
        return s;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Schicht cloned = (Schicht) super.clone();
        return cloned;
    }
}
