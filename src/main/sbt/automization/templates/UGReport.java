package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.helper.UgFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class UGReport extends AReportTemplate
{
	private static UGReport instance;

	private UGReport()
	{
		layerId = "UG";
	}

	public static UGReport getInstance()
	{
		if (instance == null)
		{
			synchronized (UGReport.class)
			{
				if (instance == null)
				{
					instance = new UGReport();
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
			//Sort Data nach UG
			HtmlTable reportTable = new HtmlTable.Builder()
					.appendAttribute("class", "MsoNormalTable")
					.appendAttribute("border", "1")
					.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
					.appendAttribute("cellspacing", "0")
					.appendAttribute("cellpadding", "0")
					.build();

			reportTable.appendContent(UgFactory.createIDRow(portion));
			reportTable.appendContent(UgFactory.createAufschlussRow(portion));
			reportTable.appendContent(UgFactory.createDickeRow(portion));
			reportTable.appendContent(UgFactory.createGesamtDickeRow(portion));
			reportTable.appendContent(UgFactory.createZielTiefeRow(portion));
			reportTable.appendContent(buildTechnicalFeatures(portion));
			reportTable.appendContent(buildEnvironmentTechnicalFeatures(portion));
			reportTable.appendContent(UgFactory.createLegendeRow(sites));

			strb.append(reportTable.appendTag());
		}

		setHtmlTable(strb.toString());

	}

	@Override
	public void buildHtmlTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_UG_Table.html";
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
				.append(UgFactory.createDIN18196Row(sites))
				.append(UgFactory.createDIN18300Row(sites))
				.append(UgFactory.createDIN19682Row(sites))
				.append(UgFactory.createDIN18300_09Row(sites))
				.append(UgFactory.createZTVRow(sites))
				.append(UgFactory.createWasserGehaltRow(sites))
				.append(UgFactory.createFeuchteZustandRow(sites))
				.append(UgFactory.createKonsistenzRow(sites))
				.append(UgFactory.createVerdichtungsfaehigkeitRow(sites))
				.append(UgFactory.createTragPlanumRow(sites))
				.append(UgFactory.createTragSohleRow(sites));

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
				.append(UgFactory.createChemieIDRow(sites))
				.append(UgFactory.createChemieMufvRow(sites))
				.append(UgFactory.createChemieLagaBoRow(sites))
				.append(UgFactory.createChemieLagaRcRow(sites))
				.append(UgFactory.createChemieLagaRcOrientierungRow(sites))
				.append(UgFactory.createChemieTlGesteinRow(sites))
				.append(UgFactory.createChemieDepvRow(sites))
				.append(UgFactory.createChemieEntscheidungshilfeRow(sites));

		return umweltTechBuilder.toString();
	}

}
