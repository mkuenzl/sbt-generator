package main.java.projekt;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TODO Name zu DataStructure
public abstract class AErkundungsstelle implements IDataSet
{
    final String name;
    final String id;
    final String pruefer;
    final String date;
    final String behaeltnis;
    final String schichtArt;
    final String farbe;
    final String geruch;
    final String konsistenz;
    final String tiefe;
    final String bemerkungen;

    List<AErkundungsstelle> aErkundungsstelleList = new ArrayList<>();

    //Builder
    AErkundungsstelle(Map<String,String> data){
        this.name = data.get("ERK_NAME");
        this.id = data.get("ERK_ID");
        this.pruefer = data.get("ERK_PRUEFER");
        this.date = data.get("ERK_DATUM");
        this.behaeltnis = data.get("SCHICHT_BEHAELTNIS");
        this.schichtArt = data.get("SCHICHT_ART");
        this.farbe = data.get("SCHICHT_FARBE");
        this.geruch = data.get("SCHICHT_GERUCH");
        this.konsistenz = data.get("SCHICHT_KONSISTENZ");
        this.tiefe = data.get("SCHICHT_TIEFE");
        this.bemerkungen = data.get("SCHICHT_BEMERKUNGEN");
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public String getPruefer()
    {
        return pruefer;
    }

    public String getDate()
    {
        return date;
    }

    public String getBehaeltnis()
    {
        return behaeltnis;
    }

    public String getSchichtArt()
    {
        return schichtArt;
    }

    public String getFarbe()
    {
        return farbe;
    }

    public String getGeruch()
    {
        return geruch;
    }

    public String getKonsistenz()
    {
        return konsistenz;
    }

    public String getTiefe()
    {
        return tiefe;
    }

    public String getBemerkungen()
    {
        return bemerkungen;
    }

    public List<AErkundungsstelle> getaErkundungsstelleList() {
        return aErkundungsstelleList;
    }
}
