package sbt.automization.engine;

import java.util.Map;

public abstract class ASchicht implements Comparable<ASchicht>, IProjektData, Cloneable
{
    private final Map<String, String> information;

    ASchicht(Map<String, String> information) {
        this.information = information;
    }

    // Schicht Parameter
    public String getInformation(String key){
        return information.get(key);
    }

    public void setInformation(String key, String value){
        this.information.put(key,value);
    }

    // Chemie Parameter

    @Override
    public int compareTo(final ASchicht schicht)
    {
        int s1 = Integer.parseInt(this.getInformation("SCHICHT_NR"));
        int s2 = Integer.parseInt(schicht.getInformation("SCHICHT_NR"));
        return s1 - s2;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        ASchicht cloned = (ASchicht)super.clone();
        return cloned;
    }
}
