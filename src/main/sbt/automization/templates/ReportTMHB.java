package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.TmhbFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportTMHB extends AReportTemplate
{
	private static ReportTMHB instance;

	private ReportTMHB()
	{
		layerId = "TMHB";
	}

	public static ReportTMHB getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportTMHB.class)
			{
				if (instance == null)
				{
					instance = new ReportTMHB();
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

			reportTable.appendContent(TmhbFactory.createIDRow(portion));
			reportTable.appendContent(TmhbFactory.createAufschlussRow(portion));
			reportTable.appendContent(TmhbFactory.createGesamtDickeRow(portion));
			reportTable.appendContent(TmhbFactory.createBelastungklasseRow(portion));

			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));

			reportTable.appendContent(TmhbFactory.createLegendeRow(portion));

			strb.append(reportTable.appendTag());
		}
		setHtmlTable(strb.toString());

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
				.append(TmhbFactory.createDickeRow(explorationSites))
				.append(TmhbFactory.createDruckfestigkeitRow(explorationSites));

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
				.append(TmhbFactory.createChemieIDRow(explorationSites))
				.append(TmhbFactory.createChemieMufvRow(explorationSites))
				.append(TmhbFactory.createChemieLagaRcRow(explorationSites))
				.append(TmhbFactory.createChemieLagaRcOrientierungRow(explorationSites))
				.append(TmhbFactory.createChemieTlGesteinRow(explorationSites))
				.append(TmhbFactory.createChemieDepvRow(explorationSites))
				.append(TmhbFactory.createAVVRow(explorationSites));

		return umweltTechBuilder.toString();
	}

	@Override
	public void buildHtmlTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_TMHB_Table.html";
	}


}
