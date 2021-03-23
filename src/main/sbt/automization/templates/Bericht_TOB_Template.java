package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.helper.Bericht_TOB_Factory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class Bericht_TOB_Template extends AHtmlTemplate
{
	private static Bericht_TOB_Template instance;

	private Bericht_TOB_Template() {}

	public static Bericht_TOB_Template getInstance()
	{
		if (instance == null)
		{
			synchronized (Bericht_TOB_Template.class)
			{
				if (instance == null)
				{
					instance = new Bericht_TOB_Template();
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
	public void buildHtmlTable(List<Erkundungsstelle> erkundungsstellen)
	{
		//Sort Data nach TOB
		HtmlTable tableBericht = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		tableBericht.appendContent(Bericht_TOB_Factory.createIDRow(erkundungsstellen));
		tableBericht.appendContent(Bericht_TOB_Factory.createAufschlussRow(erkundungsstellen));

		tableBericht.appendContent(buildTechnischeMerkmale(erkundungsstellen));
		tableBericht.appendContent(buildUmweltTechnischeMerkmale(erkundungsstellen));

		tableBericht.appendContent(Bericht_TOB_Factory.createLegendeRow(erkundungsstellen));

		setHtmlTable(tableBericht.appendTag());
	}

	private String buildTechnischeMerkmale(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder techBuilder = new StringBuilder();

		//Technische Merkmale Trennzeile
		HtmlRow rowTECHMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Technische Merkmale")
						.build()
						.appendTag())
				.build();

		techBuilder.append(rowTECHMERKMALE.appendTag())
				.append(Bericht_TOB_Factory.createEvDynRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createEvDyn85Row(erkundungsstellen))
				.append(Bericht_TOB_Factory.createEv2Row(erkundungsstellen))
				.append(Bericht_TOB_Factory.createEvSollRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createMaterialRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createDickeRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createKGVRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createGesamtDickeRow(erkundungsstellen));

		return techBuilder.toString();
	}

	private String buildUmweltTechnischeMerkmale(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder umweltTechBuilder = new StringBuilder();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowUMWELTMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
 						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Umwelttechnische Merkmale")
						.build()
						.appendTag())
				.build();

		umweltTechBuilder.append(rowUMWELTMERKMALE.appendTag())
				.append(Bericht_TOB_Factory.createChemieIDRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createChemieMufvRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createChemieLagaBoRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createChemieLagaRcRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createChemieLagaRcOrientierungRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createChemieTlGesteinRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createChemieDepvRow(erkundungsstellen))
				.append(Bericht_TOB_Factory.createChemieEntscheidungshilfeRow(erkundungsstellen));

		return umweltTechBuilder.toString();
	}

	@Override
	public void buildHtmlTable(Erkundungsstelle data)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_TOB_Table.html";
	}
}
