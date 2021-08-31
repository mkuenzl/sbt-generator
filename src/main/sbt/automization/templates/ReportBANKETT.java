package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.BankettFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportBANKETT extends AReportTable
{
	private static ReportBANKETT instance;
	private final BankettFactory factory;

	private ReportBANKETT()
	{
		layerKind = "BANKETT";
		factory = new BankettFactory();

	}

	public static ReportBANKETT getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportBANKETT.class)
			{
				if (instance == null)
				{
					instance = new ReportBANKETT();
				}
			}
		}
		return instance;
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

			tableBericht.appendContent(factory.createIDRow(portion));
			tableBericht.appendContent(factory.createAufschlussRow(portion));
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
				.append(factory.createDIN18196Row(explorationSites))
				.append(factory.createDIN18915Row(explorationSites))
				.append(factory.createDIN18320Row(explorationSites));

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
				.append(factory.createChemieLagaBoRow(explorationSites))
				.append(factory.createChemieDepvRow(explorationSites))
				.append(factory.createChemieDecisionSupportRow(explorationSites))
				.append(factory.createAVVRow(explorationSites));

		return umweltTechBuilder.toString();
	}

	@Override
	HtmlTable constructAndGetTableObject()
	{
		return new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();
	}

	@Override
	String constructAndGetTableHeader()
	{
		return null;
	}

	@Override
	public void constructTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht-BANKETT";
	}
}
