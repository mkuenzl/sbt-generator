package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.ConcreteFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportCONCRETE extends AReportTemplate
{

	private static ReportCONCRETE instance;

	private ReportCONCRETE()
	{
		layerId = "BETON";
	}

	public static ReportCONCRETE getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportCONCRETE.class)
			{
				if (instance == null)
				{
					instance = new ReportCONCRETE();
				}
			}
		}
		return instance;
	}

	@Override
	String constructAndGetTableHeader()
	{
		return null;
	}

	@Override
	public void constructTable(List<ExplorationSite> sites)
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

		setTable(strb.toString());
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
				.append(ConcreteFactory.createChemieIDRow(explorationSites))
				.append(ConcreteFactory.createChemieMufvRow(explorationSites))
				.append(ConcreteFactory.createChemieLagaRcRow(explorationSites))
				.append(ConcreteFactory.createChemieLagaRcOrientierungRow(explorationSites))
				.append(ConcreteFactory.createChemieTlGesteinRow(explorationSites))
				.append(ConcreteFactory.createChemieDepvRow(explorationSites))
				.append(ConcreteFactory.createAVVRow(explorationSites));

		return umweltTechBuilder.toString();
	}

	@Override
	public void constructTable(ExplorationSite site)
	{

	}

	@Override
	HtmlTable constructAndGetTableObject()
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();

		return table;
	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_BETON_Table.html";
	}
}
