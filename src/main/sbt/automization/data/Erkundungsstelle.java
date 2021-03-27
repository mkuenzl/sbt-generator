package sbt.automization.data;

import java.io.Serializable;
import java.util.*;

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

    //TODO split nach Zahl und vergleiche Zahl anstatt ID
    @Override
    public int compareTo(final Erkundungsstelle o)
    {
        String erk_id1 = this.getInformation("ERK_ID");
        String[] split1 = erk_id1.split("\\D+");
        int id1;
        if (split1.length > 1)
        {
            id1 = Integer.parseInt(split1[1]);
        } else
        {
            id1 = Integer.parseInt(split1[0]);
        }

        String erk_id2 = o.getInformation("ERK_ID");
        String[] split2 = erk_id2.split("\\D+");
        int id2;
        if (split2.length > 1)
        {
            id2 = Integer.parseInt(split2[1]);
        } else
        {
            id2 = Integer.parseInt(split2[0]);
        }

        return id1 > id2 ? 1 : -1;
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
