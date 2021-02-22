package sbt.automization;


import sbt.automization.data.Erkundungsstelle;
import sbt.automization.export.ATemplateExportStrategy;
import java.util.*;

//Eigentlich Main Interface um mit allem zu interagieren
public class TableEngine
{
    private List<Erkundungsstelle> data;

    public TableEngine(final List<Map> data){
        build(data);
    }

    public void export(ATemplateExportStrategy templateExportStrategy)
    {
        templateExportStrategy.export(this);
    }

    // Auslagern
    private void build(final List<Map> data){

        this.data = new ArrayList<>();

        Set<String> erkIDs = new HashSet<>();

        // Läuft über die Map Liste einzelner Schichten und ordnet diese Erkundungsstellen zu
        for (Map dataRow : data)
        {
            String erkID = String.valueOf(dataRow.get("ERK_ID"));

            if (!erkIDs.contains(erkID)) {
                erkIDs.add(String.valueOf(erkID));
                Erkundungsstelle erk_tmp = new Erkundungsstelle(dataRow);
                erk_tmp.addSchicht(dataRow);
                this.data.add(erk_tmp);
            } else {
                for (Erkundungsstelle erk : this.data)
                {
                    if (erk.getInformation("ERK_ID").equals(erkID)){
                        erk.addSchicht(dataRow);
                    }
                }
            }
        }

        for (Erkundungsstelle erk : this.data)
        {
            //Sortiert die Schichten in einer Erkundungsstellen
            erk.sort();
        }
        //Sortiert die Erkundungsstellen
        Collections.sort(this.data);
    }

    //Kack Name
    public List<Erkundungsstelle> getData()
    {
        return data;
    }
}
