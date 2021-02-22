package sbt.automization.data;

import java.util.Map;

public class Schicht implements Comparable<Schicht>, IProjektData, Cloneable
{
    private final Map<String, String> dataMap;

    Schicht(Map<String, String> dataMap)
    {
        this.dataMap = dataMap;
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

    // Chemie Parameter

    // Schicht Parameter
    public String getInformation(String key)
    {
        return dataMap.get(key);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Schicht cloned = (Schicht) super.clone();
        return cloned;
    }
}
