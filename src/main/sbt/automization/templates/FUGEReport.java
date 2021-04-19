package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.FugeFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class FUGEReport extends AReportTemplate
{
	private static FUGEReport instance;

	private FUGEReport() {
		layerId = "FUGE";
	}

	public static FUGEReport getInstance()
	{
		if (instance == null)
		{
			synchronized (FUGEReport.class)
			{
				if (instance == null)
				{
					instance = new FUGEReport();
				}
			}
		}
		return instance;
	}

	@Override
	String setHtmlTableHeader()
	{
		return null;
	}

	@Override
	public void buildHtmlTable(List<ExplorationSite> sites)
	{
		StringBuilder strb = new StringBuilder();

		for (List<ExplorationSite> portion : divideExplorationSites(sites))
		{
			//Sort Data nach TOB
			HtmlTable reportTable = new HtmlTable.Builder()
					.appendAttribute("class", "MsoNormalTable")
					.appendAttribute("border", "1")
					.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
					.appendAttribute("cellspacing", "0")
					.appendAttribute("cellpadding", "0")
					.build();

			reportTable.appendContent(FugeFactory.createIDRow(portion));
			reportTable.appendContent(FugeFactory.createAufschlussRow(portion));

			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));
			//reportTable.appendContent(FugeFactory.createLegendeRow(portion));

			strb.append(reportTable.appendTag());
		}

		setHtmlTable(strb.toString());
	}

	@Override
	public void buildHtmlTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_FUGE_Table.html";
	}

	@Override
	String buildTechnicalFeatures(List<ExplorationSite> explorationSites)
	{
		return null;
	}

	@Override
	String buildEnvironmentTechnicalFeatures(List<ExplorationSite> explorationSites)
	{
		StringBuilder umweltTechBuilder = new StringBuilder();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowUMWELTMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendContent("Umwelttechnische Merkmale")
						.build()
						.appendTag())
				.build();

		umweltTechBuilder.append(rowUMWELTMERKMALE.appendTag())
				.append(FugeFactory.createChemieIDRow(explorationSites))
				.append(FugeFactory.createChemieMufvRow(explorationSites))
				.append(FugeFactory.createAVVRow(explorationSites));

		return umweltTechBuilder.toString();
	}

}
