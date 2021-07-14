package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.TmhbFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportTMHB extends AReportTable
{
	private static ReportTMHB instance;
	private final TmhbFactory factory;

	private ReportTMHB()
	{
		layerKind = "TMHB";
		factory = new TmhbFactory();
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

			reportTable.appendContent(factory.createIDRow(portion));
			reportTable.appendContent(factory.createAufschlussRow(portion));
			reportTable.appendContent(factory.createTotalSizeRow(portion));
			reportTable.appendContent(factory.createLoadClassRow(portion));

			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));

			reportTable.appendContent(factory.createLegendRow(portion));

			strb.append(reportTable.appendTag());
			strb.append("<br>");
		}
		setTable(strb.toString());

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
				.append(factory.createSizeRow(explorationSites))
				.append(factory.createCompressiveStrengthRow(explorationSites));

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
				.append(factory.createChemieIDRow(explorationSites))
				.append(factory.createChemieMufvRow(explorationSites))
				.append(factory.createChemieLagaRcRow(explorationSites))
				.append(factory.createChemieLagaRcOrientationRow(explorationSites))
				.append(factory.createChemieTlRockRow(explorationSites))
				.append(factory.createChemieDepvRow(explorationSites))
				.append(factory.createAVVRow(explorationSites));

		return umweltTechBuilder.toString();
	}

	@Override
	public void constructTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht-TMHB";
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
