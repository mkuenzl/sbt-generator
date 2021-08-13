package sbt.automization.templates.report;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.appendix.AppendixTemplate;
import sbt.automization.templates.helper.ObFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ReportGOB extends ReportTemplate
{
	private static ReportGOB instance;
	private final ObFactory factory;

	private ReportGOB()
	{
		layerKind = "GOB";
		factory = new ObFactory();
	}

	public static ReportGOB getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportGOB.class)
			{
				if (instance == null)
				{
					instance = new ReportGOB();
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
	public void constructTable(final List<ExplorationSite> sites)
	{
		StringBuilder strb = new StringBuilder();

		for (List<ExplorationSite> portion : divideExplorationSites(sites))
		{
			//Sort Data nach GOB
			HtmlTable reportTable = new HtmlTable.Builder()
					.appendAttribute("class", "MsoNormalTable")
					.appendAttribute("border", "1")
					.appendAttribute("style", AppendixTemplate.HTML_BASIC_TABLE_STYLE)
					.appendAttribute("cellspacing", "0")
					.appendAttribute("cellpadding", "0")
					.build();


			reportTable.appendContent(factory.createIDRow(portion));

			reportTable.appendContent(factory.createAufschlussRow(portion));

			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));

			//TODO pech, no pech, pech by depth
			reportTable.appendContent(factory.createPechQuerschnittRows(portion, false));
			reportTable.appendContent(factory.createPechQuerschnittRows(portion, true));


			strb.append(reportTable.appendTag());
			strb.append("<br>");
		}
		setTable(strb.toString());
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
				.append(factory.createSizeOBRow(explorationSites))
				.append(factory.createLoadClassRow(explorationSites))
				.append(factory.createRukRow(explorationSites))
				.append(factory.createRukEinzelWertRow(explorationSites));

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
				.append(factory.createPechQualitativRow(explorationSites))
				.append(factory.createPechHalbQuantitativRow(explorationSites))
				.append(factory.createPechQuantitativRow(explorationSites));

		return umweltTechBuilder.toString();
	}

	@Override
	public void constructTable(final ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht-GOB";
	}

	@Override
	HtmlTable constructAndGetTableObject()
	{
		return new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", AppendixTemplate.HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();
	}

}
