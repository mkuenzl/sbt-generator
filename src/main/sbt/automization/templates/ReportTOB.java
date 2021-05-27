package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.TobFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportTOB extends AReportTemplate
{
	private static ReportTOB instance;

	private ReportTOB()
	{
		layerId = "TOB";
	}

	public static ReportTOB getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportTOB.class)
			{
				if (instance == null)
				{
					instance = new ReportTOB();
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
			//Sort Data nach TOB
			HtmlTable reportTable = new HtmlTable.Builder()
					.appendAttribute("class", "MsoNormalTable")
					.appendAttribute("border", "1")
					.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
					.appendAttribute("cellspacing", "0")
					.appendAttribute("cellpadding", "0")
					.build();

			reportTable.appendContent(TobFactory.createIDRow(portion));
			reportTable.appendContent(TobFactory.createAufschlussRow(portion));

			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));

			reportTable.appendContent(TobFactory.createLegendeRow(portion));

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
		return "Bericht_TOB_Table.html";
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
				.append(TobFactory.createEvDynRow(explorationSites))
				.append(TobFactory.createEvDyn85Row(explorationSites))
				.append(TobFactory.createEv2Row(explorationSites))
				.append(TobFactory.createEvSollRow(explorationSites))
				.append(TobFactory.createMaterialRow(explorationSites))
				.append(TobFactory.createDickeRow(explorationSites))
				.append(TobFactory.createKGVRow(explorationSites))
				.append(TobFactory.createGesamtDickeRow(explorationSites));

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
				.append(TobFactory.createChemieIDRow(explorationSites))
				.append(TobFactory.createChemieMufvRow(explorationSites))
				.append(TobFactory.createChemieLagaBoRow(explorationSites))
				.append(TobFactory.createChemieLagaRcRow(explorationSites))
				.append(TobFactory.createChemieLagaRcOrientierungRow(explorationSites))
				.append(TobFactory.createChemieTlGesteinRow(explorationSites))
				.append(TobFactory.createChemieDepvRow(explorationSites))
				.append(TobFactory.createChemieEntscheidungshilfeRow(explorationSites))
				.append(TobFactory.createChemieAbfallSchluesselRow(explorationSites));


		return umweltTechBuilder.toString();
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
}
