package sbt.automization;


import sbt.automization.data.ExplorationSite;
import sbt.automization.export.ATemplateExportStrategy;
import sbt.automization.util.ObjectCreatorUtil;

import java.util.List;
import java.util.Map;

//Eigentlich Main Interface um mit allem zu interagieren
public class TableEngine
{
    private final List<ExplorationSite> explorationSites;
    public static String exportPath;

    public TableEngine(final List<Map<String, String>> parsedExcelData)
    {
        explorationSites = ObjectCreatorUtil.createExplorationSites(parsedExcelData);
    }

    public TableEngine(List<Map<String, String>> parsedExcelData, String path)
    {
        explorationSites = ObjectCreatorUtil.createExplorationSites(parsedExcelData);
        exportPath = path;
    }

    public void export(ATemplateExportStrategy templateExportStrategy)
    {
        templateExportStrategy.export(this);
    }

    public List<ExplorationSite> getExplorationSites()
    {
        return explorationSites;
    }
}
