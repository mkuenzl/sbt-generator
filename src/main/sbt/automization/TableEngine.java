package sbt.automization;


import sbt.automization.data.ExplorationSite;
import sbt.automization.export.ATemplateExportStrategy;
import sbt.automization.gui.ErrorPopup;
import sbt.automization.util.ObjectCreatorUtil;

import java.util.List;
import java.util.Map;

public class TableEngine
{
	public static String exportPath;
	private final List<ExplorationSite> explorationSites;

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
