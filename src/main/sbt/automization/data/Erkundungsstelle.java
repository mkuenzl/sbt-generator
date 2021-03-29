package sbt.automization.data;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Erkundungsstelle implements Comparable<Erkundungsstelle>, IProjektData, Serializable
{
    /*
    Testing variables instead of map
     */
    private String identifier;
    private String datum;
    private String pruefer;
    private String koordinaten;
    private String ort;
    private String aufschlussOb;
    private String aufschlussTob;
    private String aufschlussUg;
    private String oberkante;
    private String belastungsklasse;
    private String lpIdentifier;
    private String lpEv;
    private String lpEv15;
    private String lpEv2;

    private Map<String, String> dataMap;
    private final List<Schicht> schichtList = new ArrayList<>();

    public Erkundungsstelle(Map<String, String> data)
    {

        this.dataMap = new HashMap<>();

        for (String key : data.keySet())
        {
            if (key.contains("ERK_")) dataMap.put(key, data.get(key));
        }
    }

    public Erkundungsstelle()
    {

    }

    /**
     * Valid ERK_ID is String[A-Z]+ followed by Number[0-9]+, other IDs will not be sorted and put at the end of list
     * @param erkundungsstelle expects a Erkundungsstelle with a data map, containing a valid ERK_ID
     * @return a compare to key for Collections.sort
     */
    @Override
    public int compareTo(final Erkundungsstelle erkundungsstelle)
    {
        Pattern VALID_PATTERN = Pattern.compile("[0-9]+|[A-Z]+");

        String firstIdentifier = this.getInformation("ERK_ID");
        String secondIdentifier = erkundungsstelle.getInformation("ERK_ID");

        Matcher firstMatcher = VALID_PATTERN.matcher(firstIdentifier);
        Matcher secondMatcher = VALID_PATTERN.matcher(secondIdentifier);

        List<String> firstIdentifierSplit = new LinkedList<String>();
        while (firstMatcher.find()) {
            firstIdentifierSplit.add( firstMatcher.group() );
        }

        List<String> secondIdentifierSplit = new LinkedList<String>();
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

    public void addSchicht(Schicht schicht)
    {
        schichtList.add(schicht);
    }

    public List<Schicht> getSchichtList()
    {
        return schichtList;
    }

    public List<Schicht> getSchichtAufschluss(final String aufschluss)
    {
        List<Schicht> schichtAufschlussList = new ArrayList<>();
        for (Schicht schicht : schichtList)
        {
            if (aufschluss.equals(schicht.getInformation("SCHICHT_AUFSCHLUSS")))
            {
                schichtAufschlussList.add(schicht);
            }
        }
        return schichtAufschlussList;
    }

    public Double getDicke()
    {
        double d = 0;

        for (Schicht schicht : schichtList) {
            String schicht_dicke = schicht.getInformation("SCHICHT_DICKE").replace(",",".");
            d += Double.parseDouble(schicht_dicke);
        }

        return d;
    }

    public void sortSchichten()
    {
        Collections.sort(schichtList);
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public Erkundungsstelle setIdentifier(String identifier)
    {
        this.identifier = identifier;
        return this;
    }

    public String getDatum()
    {
        return datum;
    }

    public Erkundungsstelle setDatum(String datum)
    {
        this.datum = datum;
        return this;
    }

    public String getPruefer()
    {
        return pruefer;
    }

    public Erkundungsstelle setPruefer(String pruefer)
    {
        this.pruefer = pruefer;
        return this;
    }

    public String getKoordinaten()
    {
        return koordinaten;
    }

    public Erkundungsstelle setKoordinaten(String koordinaten)
    {
        this.koordinaten = koordinaten;
        return this;
    }

    public String getOrt()
    {
        return ort;
    }

    public Erkundungsstelle setOrt(String ort)
    {
        this.ort = ort;
        return this;
    }

    public String getAufschlussOb()
    {
        return aufschlussOb;
    }

    public Erkundungsstelle setAufschlussOb(String aufschlussOb)
    {
        this.aufschlussOb = aufschlussOb;
        return this;
    }

    public String getAufschlussTob()
    {
        return aufschlussTob;
    }

    public Erkundungsstelle setAufschlussTob(String aufschlussTob)
    {
        this.aufschlussTob = aufschlussTob;
        return this;
    }

    public String getAufschlussUg()
    {
        return aufschlussUg;
    }

    public Erkundungsstelle setAufschlussUg(String aufschlussUg)
    {
        this.aufschlussUg = aufschlussUg;
        return this;
    }

    public String getOberkante()
    {
        return oberkante;
    }

    public Erkundungsstelle setOberkante(String oberkante)
    {
        this.oberkante = oberkante;
        return this;
    }

    public String getBelastungsklasse()
    {
        return belastungsklasse;
    }

    public Erkundungsstelle setBelastungsklasse(String belastungsklasse)
    {
        this.belastungsklasse = belastungsklasse;
        return this;
    }

    public String getLpIdentifier()
    {
        return lpIdentifier;
    }

    public Erkundungsstelle setLpIdentifier(String lpIdentifier)
    {
        this.lpIdentifier = lpIdentifier;
        return this;
    }

    public String getLpEv()
    {
        return lpEv;
    }

    public Erkundungsstelle setLpEv(String lpEv)
    {
        this.lpEv = lpEv;
        return this;
    }

    public String getLpEv15()
    {
        return lpEv15;
    }

    public Erkundungsstelle setLpEv15(String lpEv15)
    {
        this.lpEv15 = lpEv15;
        return this;
    }

    public String getLpEv2()
    {
        return lpEv2;
    }

    public Erkundungsstelle setLpEv2(String lpEv2)
    {
        this.lpEv2 = lpEv2;
        return this;
    }

    public Map<String, String> getDataMap()
    {
        return dataMap;
    }

    public Erkundungsstelle setDataMap(Map<String, String> dataMap)
    {
        this.dataMap = dataMap;
        return this;
    }
}
