package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.templates.helper.Bericht_OH_Factory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;
import java.util.stream.Collectors;

public final class Bericht_OH_Template extends AHtmlTemplate
{
	private static Bericht_OH_Template instance;

	private Bericht_OH_Template() {}

	public static Bericht_OH_Template getInstance()
	{
		if (instance == null)
		{
			synchronized (Bericht_OH_Template.class)
			{
				if (instance == null)
				{
					instance = new Bericht_OH_Template();
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
		//Sort Data nach OH
		HtmlTable tableBericht = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		List<Erkundungsstelle> templateErkundungsstellen = erkundungsstellen.stream()
				.filter(e -> e.getSchichtAufschluss("OH").size() > 0)
				.collect(Collectors.toList());

		tableBericht.appendContent(Bericht_OH_Factory.createIDRow(templateErkundungsstellen));
		tableBericht.appendContent(Bericht_OH_Factory.createAufschlussRow(templateErkundungsstellen));
		tableBericht.appendContent(buildTechnischeMerkmale(templateErkundungsstellen));
		tableBericht.appendContent(buildUmweltTechnischeMerkmale(templateErkundungsstellen));

		setHtmlTable(tableBericht.appendTag());
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
				.append(Bericht_OH_Factory.createChemieIDRow(erkundungsstellen))
				.append(Bericht_OH_Factory.createChemieLagaBoRow(erkundungsstellen))
				.append(Bericht_OH_Factory.createChemieDepvRow(erkundungsstellen))
				.append(Bericht_OH_Factory.createChemieEntscheidungshilfeRow(erkundungsstellen))
				.append(Bericht_OH_Factory.createChemieAbfallSchluesselRow(erkundungsstellen));

		return umweltTechBuilder.toString();
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
			.append(Bericht_OH_Factory.createDIN18196Row(erkundungsstellen))
			.append(Bericht_OH_Factory.createDIN18915Row(erkundungsstellen))
			.append(Bericht_OH_Factory.createDIN18320Row(erkundungsstellen));

		return techBuilder.toString();
	}

	@Override
	public void buildHtmlTable(Erkundungsstelle data)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_OH_Table.html";
	}
}
