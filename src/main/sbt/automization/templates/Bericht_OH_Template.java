package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

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

		//Erkundungsstellen ID
		HtmlRow rowERK_ID = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Erkundungsstelle")
						.build()
						.appendTag())
				.build();

		//Erkundungsstellen Aufschlussart
		HtmlRow rowERK_AUFSCHLUSS = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Aufschlussart")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle :
				erkundungsstellen)
		{
			HtmlCell htmlCell_ERK_ID = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(erkundungsstelle.getInformation("ERK_ID"))
					.build();

			rowERK_ID.appendContent(htmlCell_ERK_ID.appendTag());

			HtmlCell htmlCell_ERK_AUFSCHLUSS = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(erkundungsstelle.getInformation("ERK_AUFSCHLUSS_TOB"))
					.build();

			rowERK_AUFSCHLUSS.appendContent(htmlCell_ERK_AUFSCHLUSS.appendTag());
		}


		tableBericht.appendContent(rowERK_ID.appendTag());
		tableBericht.appendContent(rowERK_AUFSCHLUSS.appendTag());
		tableBericht.appendContent(buildTechnischeMerkmale(erkundungsstellen));
		tableBericht.appendContent(buildUmweltTechnischeMerkmale(erkundungsstellen));

		setHtmlTable(tableBericht.appendTag());
	}

	private String buildUmweltTechnischeMerkmale(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder umweltTechBuilder = new StringBuilder();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowUMWELTMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Umwelttechnische Merkmale")
						.build()
						.appendTag())
				.build();

		HtmlRow rowCHEMIE_ID = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Laborprobe")
						.build()
						.appendTag())
				.build();

		HtmlRow rowCHEMIE_LAGA_BO = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("LAGA Boden, Zuordnung")
						.build()
						.appendTag())
				.build();

		HtmlRow rowCHEMIE_DEPV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("DepV, Deponieverordnungsklasse")
						.build()
						.appendTag())
				.build();

		HtmlRow rowCHEMIE_ENTSCHEIDUNGSHILFE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Entscheidungshilfe, DepV")
						.build()
						.appendTag())
				.build();

		HtmlRow rowCHEMIE_AVV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("AVV, Abfallschlüssel")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{

		}

		umweltTechBuilder.append(rowUMWELTMERKMALE.appendTag())
				.append(rowCHEMIE_ID.appendTag())
				.append(rowCHEMIE_LAGA_BO.appendTag())
				.append(rowCHEMIE_DEPV.appendTag())
				.append(rowCHEMIE_ENTSCHEIDUNGSHILFE.appendTag())
				.append(rowCHEMIE_AVV.appendTag());

		return umweltTechBuilder.toString();
	}

	private String buildTechnischeMerkmale(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder techBuilder = new StringBuilder();

		//Technische Merkmale Trennzeile
		HtmlRow rowTECHMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Technische Merkmale")
						.build()
						.appendTag())
				.build();

		techBuilder.append(rowTECHMERKMALE.appendTag())
			.append(buildTechnischeMerkmaleDIN(erkundungsstellen));

		return techBuilder.toString();
	}

	private String buildTechnischeMerkmaleDIN(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder stringBuilder = new StringBuilder();

		//DIN18196
		HtmlRow rowERK_DIN18196 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("DIN 18196")
						.build()
						.appendTag())
				.build();

		//DIN19682
		HtmlRow rowERK_DIN19682 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("DIN 19682")
						.build()
						.appendTag())
				.build();

		//DIN18320:2019-09
		HtmlRow rowERK_DIN18320 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("DIN 18320:2019-09")
						.build()
						.appendTag())
				.build();

		stringBuilder.append(rowERK_DIN18196.appendTag())
				.append(rowERK_DIN18196.appendTag())
				.append(rowERK_DIN19682.appendTag())
				.append(rowERK_DIN18320.appendTag());


		return stringBuilder.toString();
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
