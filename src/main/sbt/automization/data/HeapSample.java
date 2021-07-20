package sbt.automization.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HeapSample implements ISample, Comparable<LayerSample>, Cloneable, Serializable {

    private Map<String, String> informationMap = new HashMap<>();

    /**
     * Constructs a new Layer based on a map.
     *
     * @param informationMap a map parsed from an excel template
     */
    public HeapSample(Map<String, String> informationMap)
    {
        this.informationMap = informationMap;
    }

    /**
     * Constructor
     */
    public HeapSample()
    { }


    @Override
    public int compareTo(LayerSample o) {
        return 0;
    }

    /**
     * Used to create multiple objects of the same layer. Necessary for PN Template.
     *
     * @return a new object with the same information
     * @throws CloneNotSupportedException only if there was a problem creating another map
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        LayerSample cloned = (LayerSample) super.clone();
        return cloned;
    }

    /**
     * Method used for testing purposes in reality every layer should be unique.
     *
     * @param obj an Object to compare to
     * @return a boolean if equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final HeapSample other = (HeapSample) obj;
        if (! Objects.equals(this.informationMap, other.informationMap)) {
            return false;
        }

        return true;
    }

    @Override
    public void addInformation(String key, String value) {

    }

    @Override
    public String getInformation(InformationTag tag) {
        return null;
    }

    @Override
    public String getInformation(String key) {
        return null;
    }

    @Override
    public Map<String, String> getInformationMap() {
        return null;
    }
}
