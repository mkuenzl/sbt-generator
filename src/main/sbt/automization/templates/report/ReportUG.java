package sbt.automization.templates.report;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.appendix.AppendixTemplate;
import sbt.automization.templates.helper.UgFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportUG extends ReportTemplate
{
	private static ReportUG instance;
	private final UgFactory factory;

	private ReportUG()
	{
		layerKind = "UG";
		factory = new UgFactory();
	}

	public static ReportUG getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportUG.class)
			{
				if (instance == null)
				{
					instance = new ReportUG();
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
	public void constructTable(List<ExplorationSite> sites)
	{
		StringBuilder strb = new StringBuilder();

		for (List<ExplorationSite> portion : divideExplorationSites(sites))
		{
			//Sort Data nach UG
			HtmlTable reportTable = new HtmlTable.Builder()
					.appendAttribute("class", "MsoNormalTable")
					.appendAttribute("border", "1")
					.appendAttribute("style", AppendixTemplate.HTML_BASIC_TABLE_STYLE)
					.appendAttribute("cellspacing", "0")
					.appendAttribute("cellpadding", "0")
					.build();

			reportTable.appendContent(factory.createIDRow(portion));
			reportTable.appendContent(factory.createAufschlussRow(portion));
			reportTable.appendContent(factory.createSizeRow(portion));
			reportTable.appendContent(factory.createTotalSizeRow(portion));
			reportTable.appendContent(factory.createTargetDepthRow(portion));
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
		return "Bericht-UG";
	}

	@Override
	String buildTechnicalFeatures(List<ExplorationSite> sites)
	{
		StringBuilder techBuilder = new StringBuilder();

		//Technische Merkmale Trennzeile
		HtmlRow rowTECHMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + sites.size()))
						.appendContent("Technische Merkmale")
						.build()
						.appendTag())
				.build();

		techBuilder.append(rowTECHMERKMALE.appendTag())
				.append(factory.createDIN18196Row(sites))
				.append(factory.createDIN18300Row(sites))
				.append(factory.createDIN19682Row(sites))
				.append(factory.createDIN18300_09Row(sites))
				.append(factory.createZTVRow(sites))
				.append(factory.createWaterContentRow(sites))
				.append(factory.createMoistureRow(sites))
				.append(factory.createConsistencyRow(sites))
				.append(factory.createCompressibilityRow(sites))
				.append(factory.createWearPlanumRow(sites))
				.append(factory.createWearSoleRow(sites));

		return techBuilder.toString();
	}

	@Override
	String buildEnvironmentTechnicalFeatures(List<ExplorationSite> sites)
	{
		StringBuilder umweltTechBuilder = new StringBuilder();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowUMWELTMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + sites.size()))
						.appendContent("Umwelttechnische Merkmale")
						.build()
						.appendTag())
				.build();

		umweltTechBuilder.append(rowUMWELTMERKMALE.appendTag())
				.append(factory.createChemieIDRow(sites))
				.append(factory.createChemieMufvRow(sites))
				.append(factory.createChemieLagaBoRow(sites))
				.append(factory.createChemieLagaRcRow(sites))
				.append(factory.createChemieLagaRcOrientationRow(sites))
				.append(factory.createChemieTlRockRow(sites))
				.append(factory.createREKUROW(sites))
				.append(factory.createChemieDepvRow(sites))
				.append(factory.createChemieDecisionSupportRow(sites))
				.append(factory.createAVVRow(sites));

		return umweltTechBuilder.toString();
	}

	@Override
	HtmlTable constructAndGetTableObject()
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", AppendixTemplate.HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();

		return table;
	}

}
