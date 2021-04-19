package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.ObFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class GOBReport extends AReportTemplate
{
	private static GOBReport instance;

	private GOBReport()
	{
		layerId = "GOB";
	}

	public static GOBReport getInstance()
	{
		if (instance == null)
		{
			synchronized (GOBReport.class)
			{
				if (instance == null)
				{
					instance = new GOBReport();
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
	public void buildHtmlTable(final List<ExplorationSite> sites)
	{
		StringBuilder strb = new StringBuilder();

		for (List<ExplorationSite> portion : divideExplorationSites(sites))
		{
			//Sort Data nach GOB
			HtmlTable reportTable = new HtmlTable.Builder()
					.appendAttribute("class", "MsoNormalTable")
					.appendAttribute("border", "1")
					.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
					.appendAttribute("cellspacing", "0")
					.appendAttribute("cellpadding", "0")
					.build();


			reportTable.appendContent(ObFactory.createIDRow(portion));
			reportTable.appendContent(ObFactory.createAufschlussRow(portion));

			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));

			//TODO pech, no pech, pech by depth
			reportTable.appendContent(ObFactory.createPechQuerschnittRows(portion, false));
			reportTable.appendContent(ObFactory.createPechQuerschnittRows(portion, true));

			strb.append(reportTable.appendTag());
		}
		setHtmlTable(strb.toString());
	}

	@Override
	String buildTechnicalFeatures(List<ExplorationSite> explorationSites)
	{
		StringBuilder techBuilder = new StringBuilder();

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
				.append(ObFactory.createDickeOberbauRow(explorationSites))
				.append(ObFactory.createBelastungsklasseRow(explorationSites))
				.append(ObFactory.createRukRow(explorationSites))
				.append(ObFactory.createRukEinzelWertRow(explorationSites));

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
				.append(ObFactory.createPechQualitativRow(explorationSites))
				.append(ObFactory.createPechHalbQuantitativRow(explorationSites))
				.append(ObFactory.createPechQuantitativRow(explorationSites));

		return umweltTechBuilder.toString();
	}

	@Override
	public void buildHtmlTable(final ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_GOB_Table.html";
	}

}
