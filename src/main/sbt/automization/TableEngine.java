package sbt.automization;


import sbt.automization.data.Erkundungsstelle;
import sbt.automization.export.ATemplateExportStrategy;
import sbt.automization.util.ObjectCreatorUtil;

import java.util.List;
import java.util.Map;

//Eigentlich Main Interface um mit allem zu interagieren
public class TableEngine
{
    private final List<Erkundungsstelle> erkundungsstellen;

    public TableEngine(final List<Map<String, String>> parsedExcelData)
    {
        erkundungsstellen = ObjectCreatorUtil.createErkundungsstellen(parsedExcelData);
    }

    public void export(ATemplateExportStrategy templateExportStrategy)
    {
        templateExportStrategy.export(this);
    }

    public List<Erkundungsstelle> getErkundungsstellen()
    {
        return erkundungsstellen;
    }
}
