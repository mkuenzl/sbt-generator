package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.HeapFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportHEAP extends AReportTemplate
{
	private static ReportHEAP instance;

	private ReportHEAP() {
		layerId = "HAUFWERK";
	}

	public static ReportHEAP getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportHEAP.class)
			{
				if (instance == null)
				{
					instance = new ReportHEAP();
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
	String buildTechnicalFeatures(List<ExplorationSite> explorationSites)
	{
		StringBuilder techBuilder = new StringBuilder();

		//Technische Merkmale Trennzeile
		HtmlRow rowTECHMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendContent("Technische Merkmale")
						.build()
						.appendTag())
				.build();

		techBuilder.append(rowTECHMERKMALE.appendTag())
				.append(HeapFactory.createMaterialRow(explorationSites))
				.append(HeapFactory.createDIN18300Row(explorationSites))
				.append(HeapFactory.createDIN18196Row(explorationSites));

		return techBuilder.toString();
	}

	@Override
	String buildEnvironmentTechnicalFeatures(List<ExplorationSite> explorationSites)
	{
		StringBuilder environmentTechBuilder = new StringBuilder();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowEnvironmentFeatures = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendContent("Umwelttechnische Merkmale")
						.build()
						.appendTag())
				.build();

		environmentTechBuilder.append(rowEnvironmentFeatures.appendTag())
				.append(HeapFactory.createChemieIDRow(explorationSites))
				.append(HeapFactory.createChemieMufvRow(explorationSites))
				.append(HeapFactory.createChemieLagaBoRow(explorationSites))
				.append(HeapFactory.createChemieLagaRcRow(explorationSites))
				.append(HeapFactory.createChemieLagaRcOrientierungRow(explorationSites))
				.append(HeapFactory.createChemieTlGesteinRow(explorationSites))
				.append(HeapFactory.createREKUROW(explorationSites))
				.append(HeapFactory.createChemieDepvRow(explorationSites))
				.append(HeapFactory.createAVVRow(explorationSites))
				.append(HeapFactory.createChemieEntscheidungshilfeRow(explorationSites));

		return environmentTechBuilder.toString();
	}

	@Override
	public void constructTable(List<ExplorationSite> sites)
	{
		StringBuilder strb = new StringBuilder();

		for (List<ExplorationSite> portion : divideExplorationSites(sites))
		{
			//Sort Data nach BETON
			HtmlTable reportTable = constructAndGetTableObject();

			reportTable.appendContent(HeapFactory.createIDRow(portion));
			reportTable.appendContent(HeapFactory.createOutcropRow(portion));

			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));

			reportTable.appendContent(HeapFactory.createLegendeRow(portion));

			strb.append(reportTable.appendTag());
		}

		setTable(strb.toString());
	}

	@Override
	public void constructTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht-HAUFWERK.html";
	}
}
