package sbt.automization.data;

import java.io.Serializable;
import java.util.Map;

public class Layer implements Comparable<Layer>, IProjektData, Cloneable, Serializable
{
    private Map<String, String> dataMap;

    public Layer(Map<String, String> dataMap)
    {
        this.dataMap = dataMap;
    }

    public Layer()
    {

    }

    public void setInformation(String key, String value)
    {
        this.dataMap.put(key, value);
    }

    @Override
    public int compareTo(final Layer layer)
    {
        int s1 = Integer.parseInt(this.getInformation("SCHICHT_NR"));
        int s2 = Integer.parseInt(layer.getInformation("SCHICHT_NR"));
        return s1 - s2;
    }

    public String getInformation(String key)
    {
        String s = dataMap.get(key);
        if (s == null || s.equals("")) return "-";
        return s;
    }

    public Map<String, String> getDataMap()
    {
        return dataMap;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Layer cloned = (Layer) super.clone();
        return cloned;
    }
}
