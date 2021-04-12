package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.templates.helper.Bericht_BETON_Factory;
import sbt.automization.templates.helper.Bericht_FUGE_Factory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;
import java.util.stream.Collectors;

public class Bericht_FUGE_Template extends AHtmlTemplate{
	private static Bericht_FUGE_Template instance;

	private Bericht_FUGE_Template() {}

	public static Bericht_FUGE_Template getInstance()
	{
		if (instance == null)
		{
			synchronized (Bericht_FUGE_Template.class)
			{
				if (instance == null)
				{
					instance = new Bericht_FUGE_Template();
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
				.filter(e -> e.getSchichtAufschluss("FUGE").size() > 0)
				.collect(Collectors.toList());

		tableBericht.appendContent(Bericht_FUGE_Factory.createIDRow(templateErkundungsstellen));
		tableBericht.appendContent(Bericht_FUGE_Factory.createAufschlussRow(templateErkundungsstellen));

		tableBericht.appendContent(buildUmweltTechnischeMerkmale(templateErkundungsstellen));

		//tableBericht.appendContent(Bericht_FUGE_Factory.createLegendeRow(templateErkundungsstellen));

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
				.append(Bericht_FUGE_Factory.createChemieIDRow(erkundungsstellen))
				.append(Bericht_FUGE_Factory.createChemieMufvRow(erkundungsstellen))
				.append(Bericht_FUGE_Factory.createAVVRow(erkundungsstellen));

		return umweltTechBuilder.toString();
	}

	@Override
	public void buildHtmlTable(Erkundungsstelle data)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_FUGE_Table.html";
	}
}
