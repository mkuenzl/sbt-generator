package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.templates.helper.Bericht_TMHB_Factory;
import sbt.automization.templates.helper.Bericht_TOB_Factory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;
import java.util.stream.Collectors;

public class Bericht_TMHB_Template extends AHtmlTemplate{
	private static Bericht_TMHB_Template instance;

	private Bericht_TMHB_Template() {}

	public static Bericht_TMHB_Template getInstance()
	{
		if (instance == null)
		{
			synchronized (Bericht_TMHB_Template.class)
			{
				if (instance == null)
				{
					instance = new Bericht_TMHB_Template();
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

		List<Erkundungsstelle> templateErkundungsstellen = erkundungsstellen.stream()
				.filter(e -> e.getSchichtAufschluss("TMHB").size() > 0)
				.collect(Collectors.toList());

		tableBericht.appendContent(Bericht_TMHB_Factory.createIDRow(templateErkundungsstellen));
		tableBericht.appendContent(Bericht_TMHB_Factory.createAufschlussRow(templateErkundungsstellen));
		tableBericht.appendContent(Bericht_TMHB_Factory.createGesamtDickeRow(templateErkundungsstellen));
		tableBericht.appendContent(Bericht_TMHB_Factory.createBelastungklasseRow(templateErkundungsstellen));

		tableBericht.appendContent(buildTechnischeMerkmale(templateErkundungsstellen));
		tableBericht.appendContent(buildUmweltTechnischeMerkmale(templateErkundungsstellen));

		tableBericht.appendContent(Bericht_TMHB_Factory.createLegendeRow(templateErkundungsstellen));

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
				.append(Bericht_TMHB_Factory.createDickeRow(erkundungsstellen))
				.append(Bericht_TMHB_Factory.createDruckfestigkeitRow(erkundungsstellen));

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
				.append(Bericht_TMHB_Factory.createChemieIDRow(erkundungsstellen))
				.append(Bericht_TMHB_Factory.createChemieMufvRow(erkundungsstellen))
				.append(Bericht_TMHB_Factory.createChemieLagaRcRow(erkundungsstellen))
				.append(Bericht_TMHB_Factory.createChemieLagaRcOrientierungRow(erkundungsstellen))
				.append(Bericht_TMHB_Factory.createChemieTlGesteinRow(erkundungsstellen))
				.append(Bericht_TMHB_Factory.createChemieDepvRow(erkundungsstellen))
				.append(Bericht_TMHB_Factory.createAVVRow(erkundungsstellen));

		return umweltTechBuilder.toString();
	}

	@Override
	public void buildHtmlTable(Erkundungsstelle data)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_TMHB_Table.html";
	}
}
