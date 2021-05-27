package sbt.automization.data;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExplorationSite implements Comparable<ExplorationSite>, IProjektData, Serializable
{
    private Map<String, String> dataMap;
    private final List<Layer> layerList = new ArrayList<>();

    public ExplorationSite(Map<String, String> data)
    {

        this.dataMap = new HashMap<>();

        for (String key : data.keySet())
        {
            if (key.contains("ERK_")) dataMap.put(key, data.get(key));
        }
    }

    public ExplorationSite()
    {

    }

    /**
     * Is used to prepare the order of exploration sites shown in templates
     * @param explorationSite expects a explorationSite with a data map, containing a valid ERK_NUMMER
     * @return a compare to key for Collections.sort
     */
    @Override
    public int compareTo(final ExplorationSite explorationSite)
    {
/*        Pattern VALID_PATTERN = Pattern.compile("[0-9]+|[A-Z]+");

        String firstIdentifier = this.getInformation("ERK_ID");
        String secondIdentifier = explorationSite.getInformation("ERK_ID");

        Matcher firstMatcher = VALID_PATTERN.matcher(firstIdentifier);
        Matcher secondMatcher = VALID_PATTERN.matcher(secondIdentifier);

        List<String> firstIdentifierSplit = new LinkedList<>();
        while (firstMatcher.find()) {
            firstIdentifierSplit.add( firstMatcher.group() );
        }

        List<String> secondIdentifierSplit = new LinkedList<>();
        while (secondMatcher.find()) {
            secondIdentifierSplit.add( secondMatcher.group() );
        }

        int firstId;
        int secondId;
        String firstName;
        String secondName;

        int sizeFirst = firstIdentifierSplit.size();
        int sizeSecond = secondIdentifierSplit.size();
        if (sizeFirst > 1 && sizeSecond > 1)
        {
            firstId = Integer.parseInt(firstIdentifierSplit.get(1));
            firstName = firstIdentifierSplit.get(0);

            secondId = Integer.parseInt(secondIdentifierSplit.get(1));
            secondName = secondIdentifierSplit.get(0);

            if (firstName.equals(secondName))
            {
                return firstId > secondId ? 1 : -1;
            }
        } else
        {
            return sizeFirst > sizeSecond ? -1 : 1;
        }
        return firstName.compareTo(secondName);*/
        String thisOrder = this.getInformation("ERK_NUMMER");
        String otherOrder = explorationSite.getInformation("ERK_NUMMER");

        if (thisOrder == null || otherOrder == null)
        {
            //something went wrong exploration order will not change
            return 0;
        }

        int thisOrderValue = Integer.parseInt(thisOrder);
        int otherOrderValue = Integer.parseInt(otherOrder);

        // value < 0 means order is smaller value > 0 means order is greater
        return thisOrderValue > otherOrderValue ? 1 : -1;

    }

    public String getInformation(String key)
    {
        if (dataMap == null)
        {
            return "Missing Values";
        }
        String s = dataMap.get(key);
        if (s == null || s.equals("")) return "-";
        return s;
    }

    public void addLayer(Layer layer)
    {
        layerList.add(layer);
    }

    public List<Layer> getLayers()
    {
        return layerList;
    }

    /**
     * Used to get specific layers based on outcrop
     * @param outcrop expects a valid outcrop identifier
     * @return a list of layers associated with the specified outcrop
     */
    public List<Layer> getLayersWithOutcrop(final String outcrop)
    {
        List<Layer> layers = new ArrayList<>();
        for (Layer layer : layerList)
        {
            if (outcrop.equals(layer.getInformation("SCHICHT_AUFSCHLUSS")))
            {
                layers.add(layer);
            }
        }
        return layers;
    }

    public Double getThickness()
    {
        double d = 0;

        for (Layer layer : layerList) {
            String layerThickness = layer.getInformation("SCHICHT_DICKE").replace(",",".");
            d += Double.parseDouble(layerThickness);
        }

        return d;
    }

    public Double getOutcropThickness(final String outcrop)
    {
        double d = 0;

        for (Layer layer : getLayersWithOutcrop(outcrop)) {
            String layerThickness = layer.getInformation("SCHICHT_DICKE").replace(",",".");
            d += Double.parseDouble(layerThickness);
        }

        return d;
    }

    /**
     * sorts all layers in this exploration site based on their ID
     */
    public void sortLayers()
    {
        Collections.sort(layerList);
    }

    public Map<String, String> getDataMap()
    {
        return dataMap;
    }

    public ExplorationSite setDataMap(Map<String, String> dataMap)
    {
        this.dataMap = dataMap;
        return this;
    }
}
