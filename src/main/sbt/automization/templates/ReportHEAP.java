package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.HeapFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportHEAP extends AReportTable
{
	private static ReportHEAP instance;
	private final HeapFactory factory;

	private ReportHEAP() {
		layerKind = "HAUFWERK";
		factory = new HeapFactory();
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
				.append(factory.createMaterialRow(explorationSites))
				.append(factory.createDIN18300Row(explorationSites))
				.append(factory.createDIN18196Row(explorationSites));

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
				.append(factory.createChemieIDRow(explorationSites))
				.append(factory.createChemieMufvRow(explorationSites))
				.append(factory.createChemieLagaBoRow(explorationSites))
				.append(factory.createChemieLagaRcRow(explorationSites))
				.append(factory.createChemieLagaRcOrientationRow(explorationSites))
				.append(factory.createChemieTlRockRow(explorationSites))
				.append(factory.createREKUROW(explorationSites))
				.append(factory.createChemieDepvRow(explorationSites))
				.append(factory.createAVVRow(explorationSites))
				.append(factory.createChemieDecisionSupportRow(explorationSites));

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

			reportTable.appendContent(factory.createIDRow(portion));
			reportTable.appendContent(factory.createOutcropRow(portion));

			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));

			reportTable.appendContent(factory.createLegendRow(portion));

			strb.append(reportTable.appendTag());
			strb.append("<br>");
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
		return "Bericht-HAUFWERK";
	}
}
