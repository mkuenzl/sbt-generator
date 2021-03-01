package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class Bericht_UG_Template extends AHtmlTemplate
{
	private static Bericht_UG_Template instance;

	private Bericht_UG_Template() {}

	public static Bericht_UG_Template getInstance()
	{
		if (instance == null)
		{
			synchronized (Bericht_UG_Template.class)
			{
				if (instance == null)
				{
					instance = new Bericht_UG_Template();
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

		tableBericht.appendContent(buildUntergrundErkundungsstelle(erkundungsstellen));
		tableBericht.appendContent(buildTechnischeMerkmale(erkundungsstellen));
		tableBericht.appendContent(buildUmweltTechnischeMerkmale(erkundungsstellen));

		setHtmlTable(tableBericht.appendTag());
	}

	@Override
	public void buildHtmlTable(Erkundungsstelle data)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_UG_Table.html";
	}

	private String buildUntergrundErkundungsstelle(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder ugERKBuilder = new StringBuilder();

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
		HtmlRow rowERK_DICKE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Dicke")
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

			HtmlCell htmlCell_ERK_DICKE = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent("dicke")
					.build();

			rowERK_DICKE.appendContent(htmlCell_ERK_DICKE.appendTag());
		}

		ugERKBuilder.append(rowERK_ID.appendTag())
				.append(rowERK_DICKE.appendTag());

		return ugERKBuilder.toString();
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

		//WASSERGEHALT
		HtmlRow rowERK_WASSERGEHALT = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Wassergehalt")
						.build()
						.appendTag())
				.build();

		//FEUCHTEZUSTAND
		HtmlRow rowERK_FEUCHTEZUSTAND = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Feuchtezustand")
						.build()
						.appendTag())
				.build();

		//KONSISTENZ
		HtmlRow rowERK_KONSISTENZ = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Konsistenz")
						.build()
						.appendTag())
				.build();

		//PROCTORDICHTE
		HtmlRow rowERK_PROCTORDICHTE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Proctordichte")
						.build()
						.appendTag())
				.build();

		//DIFFERENCE
		HtmlRow rowERK_DIFFERENCE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Difference")
						.build()
						.appendTag())
				.build();

		//VERDICHTUNGSFÄHIGKEIT
		HtmlRow rowERK_VERDICHTUNGSFÄHIGKEIT = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Verdichtungsfähigkeit")
						.build()
						.appendTag())
				.build();

		//TRAGFAEHIGKEIT_PLANUM
		HtmlRow rowERK_TRAGFAEHIGKEIT_PLANUM = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Tragfähigkeit Planum")
						.build()
						.appendTag())
				.build();

		//TRAGFAEHIGKEIT_GRABENSOHLE
		HtmlRow rowERK_TRAGFAEHIGKEIT_GRABENSOHLE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Tragfähigkeit Grabensohle")
						.build()
						.appendTag())
				.build();

		techBuilder.append(rowTECHMERKMALE.appendTag())
				.append(buildTechnischeMerkmaleDIN(erkundungsstellen))
				.append(rowERK_WASSERGEHALT.appendTag())
				.append(rowERK_FEUCHTEZUSTAND.appendTag())
				.append(rowERK_KONSISTENZ.appendTag())
				.append(rowERK_PROCTORDICHTE.appendTag())
				.append(rowERK_DIFFERENCE.appendTag())
				.append(rowERK_VERDICHTUNGSFÄHIGKEIT.appendTag())
				.append(rowERK_TRAGFAEHIGKEIT_PLANUM.appendTag())
				.append(rowERK_TRAGFAEHIGKEIT_GRABENSOHLE.appendTag());

		return techBuilder.toString();
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

		HtmlRow rowCHEMIE_MUFV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Schreiben des MUFV")
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

		HtmlRow rowCHEMIE_LAGA_RC = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("LAGA Bauschutt, Zuordnung")
						.build()
						.appendTag())
				.build();

		HtmlRow rowCHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("LAGA Bauschutt, Orientierung")
						.build()
						.appendTag())
				.build();

		HtmlRow rowCHEMIE_TL_GESTEIN = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("TL Gestein, Verwertungsklasse")
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

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{

		}

		umweltTechBuilder.append(rowUMWELTMERKMALE.appendTag())
				.append(rowCHEMIE_ID.appendTag())
				.append(rowCHEMIE_MUFV.appendTag())
				.append(rowCHEMIE_LAGA_BO.appendTag())
				.append(rowCHEMIE_LAGA_RC.appendTag())
				.append(rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendTag())
				.append(rowCHEMIE_TL_GESTEIN.appendTag())
				.append(rowCHEMIE_DEPV.appendTag())
				.append(rowCHEMIE_ENTSCHEIDUNGSHILFE.appendTag());

		return umweltTechBuilder.toString();
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

		//DIN18300
		HtmlRow rowERK_DIN18300 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("DIN 18300")
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

		//DIN18300:2019-09
		HtmlRow rowERK_DIN18300N = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("DIN 18300:2019-09")
						.build()
						.appendTag())
				.build();

		//ZTV-E
		HtmlRow rowERK_ZTV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("ZTV E")
						.build()
						.appendTag())
				.build();

		stringBuilder.append(rowERK_DIN18196.appendTag())
				.append(rowERK_DIN18300.appendTag())
				.append(rowERK_DIN19682.appendTag())
				.append(rowERK_DIN18300N.appendTag())
				.append(rowERK_ZTV.appendTag());

		return stringBuilder.toString();
	}
}
