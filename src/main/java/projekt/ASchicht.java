package main.java.projekt;

import java.util.Map;

public abstract class ASchicht implements Comparable<ASchicht>
{
    private final Map<String, String> information;

    ASchicht(Map<String, String> information) {
        this.information = information;
    }

    // Schicht Parameter
    public String getInformation(String key){
        return information.get(key);
    }

    // Chemie Parameter

    @Override
    public int compareTo(final ASchicht schicht)
    {
        int s1 = Integer.parseInt(this.getInformation("SCHICHT_NR"));
        int s2 = Integer.parseInt(schicht.getInformation("SCHICHT_NR"));
        return s1 - s2;
    }
}
