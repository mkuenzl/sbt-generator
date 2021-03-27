package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.templates.helper.Bericht_OB_Factory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlText;

import java.util.List;
import java.util.stream.Collectors;

public final class Bericht_OB_Template extends AHtmlTemplate
{
	private static Bericht_OB_Template instance;

	private Bericht_OB_Template() {}

	public static Bericht_OB_Template getInstance()
	{
		if (instance == null)
		{
			synchronized (Bericht_OB_Template.class)
			{
				if (instance == null)
				{
					instance = new Bericht_OB_Template();
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
	public void buildHtmlTable(final List<Erkundungsstelle> erkundungsstellen)
	{
		//Sort Data nach OB
		HtmlTable tableBericht = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		List<Erkundungsstelle> templateErkundungsstellen = erkundungsstellen.stream()
				.filter(e -> e.getSchichtAufschluss("GOB").size() > 0)
				.collect(Collectors.toList());

		tableBericht.appendContent(Bericht_OB_Factory.createIDRow(templateErkundungsstellen));
		tableBericht.appendContent(Bericht_OB_Factory.createAufschlussRow(templateErkundungsstellen));

		tableBericht.appendContent(buildTechnischeMerkmale(templateErkundungsstellen));
		tableBericht.appendContent(buildUmweltTechnischeMerkmale(templateErkundungsstellen));
		//TODO
		tableBericht.appendContent(Bericht_OB_Factory.createPechQuerschnittRows(templateErkundungsstellen, false));
		tableBericht.appendContent(Bericht_OB_Factory.createPechQuerschnittRows(templateErkundungsstellen, true));

		setHtmlTable(tableBericht.appendTag());
	}

	private String buildTechnischeMerkmale(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder techBuilder = new StringBuilder();

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
				.append(Bericht_OB_Factory.createDickeOberbauRow(erkundungsstellen))
				.append(Bericht_OB_Factory.createBelastungsklasseRow(erkundungsstellen))
				.append(Bericht_OB_Factory.createRukRow(erkundungsstellen))
				.append(Bericht_OB_Factory.createRukEinzelWertRow(erkundungsstellen));

		return techBuilder.toString();
	}

	private String buildUmweltTechnischeMerkmale(final List<Erkundungsstelle> erkundungsstellen)
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
				.append(Bericht_OB_Factory.createPechQualitativRow(erkundungsstellen))
				.append(Bericht_OB_Factory.createPechHalbQuantitativRow(erkundungsstellen))
				.append(Bericht_OB_Factory.createPechQuantitativRow(erkundungsstellen));

		return umweltTechBuilder.toString();
	}

	@Override
	public void buildHtmlTable(final Erkundungsstelle data)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_OB_Table.html";
	}
}
