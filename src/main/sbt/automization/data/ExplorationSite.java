package sbt.automization.data;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExplorationSite implements Comparable<ExplorationSite>, IProjektData, Serializable
{
    /*
    Testing variables instead of map
     */
    private String identifier;
    private String datum;
    private String pruefer;
    private String bereich;
    private String ansprechpartner;
    private String koordinaten;
    private String ort;
    private String aufschlussOb;
    private String aufschlussTob;
    private String aufschlussUg;
    private String oberkante;
    private String belastungsklasse;
    private String belastungsklasseTafel;
    private String zielTiefe;

    private String ERK_PECH_HALBQUANTITATIV;
    private String ERK_PECH_QUANTITATIV;
    private String ERK_TRAG_PLANUM;
    private String ERK_TRAG_GRABENSOHLE;
    private String ERK_SOHLE_TIEFE;
    private String ERK_VERBUND_UNTERLAGE;

    private String lpIdentifier;
    private String lpEv;
    private String lpEv15;
    private String lpEv2;
    private String lpEv2SollTiefe;


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
     * Valid ERK_ID is String[A-Z]+ followed by Number[0-9]+, other IDs will not be sorted and put at the end of list
     * @param explorationSite expects a Erkundungsstelle with a data map, containing a valid ERK_ID
     * @return a compare to key for Collections.sort
     */
    @Override
    public int compareTo(final ExplorationSite explorationSite)
    {
        Pattern VALID_PATTERN = Pattern.compile("[0-9]+|[A-Z]+");

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
        return firstName.compareTo(secondName);
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

    public void addSchicht(Layer layer)
    {
        layerList.add(layer);
    }

    public List<Layer> getSchichtList()
    {
        return layerList;
    }

    public List<Layer> getSchichtAufschluss(final String aufschluss)
    {
        List<Layer> layerAufschlussList = new ArrayList<>();
        for (Layer layer : layerList)
        {
            if (aufschluss.equals(layer.getInformation("SCHICHT_AUFSCHLUSS")))
            {
                layerAufschlussList.add(layer);
            }
        }
        return layerAufschlussList;
    }

    public Double getDicke()
    {
        double d = 0;

        for (Layer layer : layerList) {
            String schicht_dicke = layer.getInformation("SCHICHT_DICKE").replace(",",".");
            d += Double.parseDouble(schicht_dicke);
        }

        return d;
    }

    public Double getAufschlussDicke(final String aufschluss)
    {
        double d = 0;

        for (Layer layer : getSchichtAufschluss(aufschluss)) {
            String schicht_dicke = layer.getInformation("SCHICHT_DICKE").replace(",",".");
            d += Double.parseDouble(schicht_dicke);
        }

        return d;
    }

    public void sortSchichten()
    {
        Collections.sort(layerList);
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public ExplorationSite setIdentifier(String identifier)
    {
        this.identifier = identifier;
        return this;
    }

    public String getDatum()
    {
        return datum;
    }

    public ExplorationSite setDatum(String datum)
    {
        this.datum = datum;
        return this;
    }

    public String getPruefer()
    {
        return pruefer;
    }

    public ExplorationSite setPruefer(String pruefer)
    {
        this.pruefer = pruefer;
        return this;
    }

    public String getKoordinaten()
    {
        return koordinaten;
    }

    public ExplorationSite setKoordinaten(String koordinaten)
    {
        this.koordinaten = koordinaten;
        return this;
    }

    public String getOrt()
    {
        return ort;
    }

    public ExplorationSite setOrt(String ort)
    {
        this.ort = ort;
        return this;
    }

    public String getAufschlussOb()
    {
        return aufschlussOb;
    }

    public ExplorationSite setAufschlussOb(String aufschlussOb)
    {
        this.aufschlussOb = aufschlussOb;
        return this;
    }

    public String getAufschlussTob()
    {
        return aufschlussTob;
    }

    public ExplorationSite setAufschlussTob(String aufschlussTob)
    {
        this.aufschlussTob = aufschlussTob;
        return this;
    }

    public String getAufschlussUg()
    {
        return aufschlussUg;
    }

    public ExplorationSite setAufschlussUg(String aufschlussUg)
    {
        this.aufschlussUg = aufschlussUg;
        return this;
    }

    public String getOberkante()
    {
        return oberkante;
    }

    public ExplorationSite setOberkante(String oberkante)
    {
        this.oberkante = oberkante;
        return this;
    }

    public String getBelastungsklasse()
    {
        return belastungsklasse;
    }

    public ExplorationSite setBelastungsklasse(String belastungsklasse)
    {
        this.belastungsklasse = belastungsklasse;
        return this;
    }

    public String getLpIdentifier()
    {
        return lpIdentifier;
    }

    public ExplorationSite setLpIdentifier(String lpIdentifier)
    {
        this.lpIdentifier = lpIdentifier;
        return this;
    }

    public String getLpEv()
    {
        return lpEv;
    }

    public ExplorationSite setLpEv(String lpEv)
    {
        this.lpEv = lpEv;
        return this;
    }

    public String getLpEv15()
    {
        return lpEv15;
    }

    public ExplorationSite setLpEv15(String lpEv15)
    {
        this.lpEv15 = lpEv15;
        return this;
    }

    public String getLpEv2()
    {
        return lpEv2;
    }

    public ExplorationSite setLpEv2(String lpEv2)
    {
        this.lpEv2 = lpEv2;
        return this;
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
