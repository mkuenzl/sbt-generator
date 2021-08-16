package sbt.automization.templates.report;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.HeapFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class Heap extends Report
{
	private static Heap instance;
	private final HeapFactory factory;

	private Heap() {
		setOutcrop(Outcrop.HEAP);
		factory = new HeapFactory();
	}

	public static Heap getInstance()
	{
		if (instance == null)
		{
			synchronized (Heap.class)
			{
				if (instance == null)
				{
					instance = new Heap();
				}
			}
		}
		return instance;
	}


	@Override
	public String constructAndGetTableHeader()
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

		addToTemplate(strb.toString());
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
