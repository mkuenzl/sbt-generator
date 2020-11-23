package main.java.projekt;

import main.java.export.ATemplateExportStrategy;

import java.util.*;

public class Projekt
{
    private List<AErkundungsstelle> data;

    public Projekt(final List<Map> dataRows){
        build(dataRows);
    }

    public void export(ATemplateExportStrategy templateExportStrategy)
    {
        templateExportStrategy.export(this);
    }

    // Auslagern
    private void build(final List<Map> dataRows){

        data = new ArrayList<>();

        Set<String> erkIDs = new HashSet<>();

        // Läuft über die Map Liste einzelner Schichten und ordnet diese Erkundungsstellen zu
        for (Map row : dataRows)
        {
            String erkID = String.valueOf(row.get("ERK_ID"));

            if (!erkIDs.contains(erkID)) {
                erkIDs.add(String.valueOf(erkID));
                Erkundungsstelle erk_tmp = new Erkundungsstelle(row);
                erk_tmp.addSchicht(row);
                data.add(erk_tmp);
            } else {
                for (AErkundungsstelle erk : data)
                {
                    if (erk.getInformation("ERK_ID").equals(erkID)){
                        erk.addSchicht(row);
                    }
                }
            }
        }

        for (AErkundungsstelle erk : data)
        {
            erk.sort();
        }

        Collections.sort(data);
    }

    public List<AErkundungsstelle> getData()
    {
        return data;
    }
}
