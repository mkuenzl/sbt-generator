package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.ConcreteFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class CONCRETEReport extends AReportTemplate
{

	private static CONCRETEReport instance;

	private CONCRETEReport()
	{
		layerId = "BETON";
	}

	public static CONCRETEReport getInstance()
	{
		if (instance == null)
		{
			synchronized (CONCRETEReport.class)
			{
				if (instance == null)
				{
					instance = new CONCRETEReport();
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
			//Sort Data nach BETON
			HtmlTable reportTable = new HtmlTable.Builder()
					.appendAttribute("class", "MsoNormalTable")
					.appendAttribute("border", "1")
					.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
					.appendAttribute("cellspacing", "0")
					.appendAttribute("cellpadding", "0")
					.build();

			reportTable.appendContent(ConcreteFactory.createIDRow(portion));
			reportTable.appendContent(ConcreteFactory.createAufschlussRow(portion));

			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));

			reportTable.appendContent(ConcreteFactory.createLegendeRow(portion));

			strb.append(reportTable.appendTag());
		}

		setHtmlTable(strb.toString());
	}

	@Override
	String buildTechnicalFeatures(List<ExplorationSite> erkundungsstellen)
	{
		StringBuilder techBuilder = new StringBuilder();

		//Technische Merkmale Trennzeile
		HtmlRow rowTECHMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Technische Merkmale")
						.build()
						.appendTag())
				.build();

		techBuilder.append(rowTECHMERKMALE.appendTag())
				.append(ConcreteFactory.createDickenRow(erkundungsstellen))
				.append(ConcreteFactory.createDruckfestigkeitRow(erkundungsstellen));

		return techBuilder.toString();
	}

	@Override
	String buildEnvironmentTechnicalFeatures(List<ExplorationSite> erkundungsstellen)
	{
		StringBuilder umweltTechBuilder = new StringBuilder();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowUMWELTMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Umwelttechnische Merkmale")
						.build()
						.appendTag())
				.build();

		umweltTechBuilder.append(rowUMWELTMERKMALE.appendTag())
				.append(ConcreteFactory.createChemieIDRow(erkundungsstellen))
				.append(ConcreteFactory.createChemieMufvRow(erkundungsstellen))
				.append(ConcreteFactory.createChemieLagaRcRow(erkundungsstellen))
				.append(ConcreteFactory.createChemieLagaRcOrientierungRow(erkundungsstellen))
				.append(ConcreteFactory.createChemieTlGesteinRow(erkundungsstellen))
				.append(ConcreteFactory.createChemieDepvRow(erkundungsstellen))
				.append(ConcreteFactory.createAVVRow(erkundungsstellen));

		return umweltTechBuilder.toString();
	}

	@Override
	public void buildHtmlTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_BETON_Table.html";
	}
}
