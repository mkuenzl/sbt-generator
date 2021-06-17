package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.OhFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportOH extends AReportTemplate
{
	private static ReportOH instance;

	private ReportOH()
	{
		layerId = "OH";
	}

	public static ReportOH getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportOH.class)
			{
				if (instance == null)
				{
					instance = new ReportOH();
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
			//Sort Data nach OH
			HtmlTable tableBericht = new HtmlTable.Builder()
					.appendAttribute("class", "MsoNormalTable")
					.appendAttribute("border", "1")
					.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
					.appendAttribute("cellspacing", "0")
					.appendAttribute("cellpadding", "0")
					.build();

			tableBericht.appendContent(OhFactory.createIDRow(portion));
			tableBericht.appendContent(OhFactory.createAufschlussRow(portion));
			tableBericht.appendContent(buildTechnicalFeatures(portion));
			tableBericht.appendContent(buildEnvironmentTechnicalFeatures(portion));

			strb.append(tableBericht.appendTag());
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
				.append(OhFactory.createDIN18196Row(explorationSites))
				.append(OhFactory.createDIN18915Row(explorationSites))
				.append(OhFactory.createDIN18320Row(explorationSites));

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
				.append(OhFactory.createChemieIDRow(explorationSites))
				.append(OhFactory.createChemieLagaBoRow(explorationSites))
				.append(OhFactory.createChemieDepvRow(explorationSites))
				.append(OhFactory.createChemieEntscheidungshilfeRow(explorationSites))
				.append(OhFactory.createChemieAbfallSchluesselRow(explorationSites));

		return umweltTechBuilder.toString();
	}

	@Override
	public void constructTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht-OH.html";
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
