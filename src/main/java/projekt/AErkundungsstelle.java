package main.java.projekt;


import java.util.Map;

public abstract class AErkundungsstelle
{
    final String name;
    final String id;
    final String pruefer;
    final String date;

    AErkundungsstelle(Map<String,String> data){
        this.name = data.get("ERK_NAME");
        this.id = data.get("ERK_ID");
        this.pruefer = data.get("ERK_PRUEFER");
        this.date = data.get("ERK_DATUM");
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

}
