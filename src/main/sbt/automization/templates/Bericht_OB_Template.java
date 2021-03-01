package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

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
					.appendContent(erkundungsstelle.getInformation("ERK_AUFSCHLUSS_OB"))
					.build();

			rowERK_AUFSCHLUSS.appendContent(htmlCell_ERK_AUFSCHLUSS.appendTag());
		}

		buildTechnischeMerkmale(erkundungsstellen);



		tableBericht.appendContent(rowERK_ID.appendTag());
		tableBericht.appendContent(rowERK_AUFSCHLUSS.appendTag());
		tableBericht.appendContent(buildTechnischeMerkmale(erkundungsstellen));
		tableBericht.appendContent(buildUmweltTechnischeMerkmale(erkundungsstellen));
		tableBericht.appendContent(buildQuerschnittRows(erkundungsstellen, false));
		tableBericht.appendContent(buildQuerschnittRows(erkundungsstellen, true));

		setHtmlTable(tableBericht.appendTag());
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

		//Gesamtdicke Oberbau
		HtmlRow rowERK_DICKE_OBERBAU = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Gesamtdicke geb. Oberbau cm")
						.build()
						.appendTag())
				.build();

		//RSTO Belastungsklasse
		HtmlRow rowERK_BELASTUNGSKLASSE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("RStO, Belastungsklasse")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle :
				erkundungsstellen)
		{
			HtmlCell htmlCell_ERK_DICKE = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(TextFormatUtil.formatErkAufschlussDicke(erkundungsstelle, "GOB"))
					.build();

			rowERK_DICKE_OBERBAU.appendContent(htmlCell_ERK_DICKE.appendTag());

			HtmlCell htmlCell_ERK_BELASTUNGSKLASSE = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(erkundungsstelle.getInformation("ERK_BELASTUNGSKLASSE"))
					.build();

			rowERK_BELASTUNGSKLASSE.appendContent(htmlCell_ERK_BELASTUNGSKLASSE.appendTag());
		}

		//RUK
		HtmlRow rowERK_RUK = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Erweichungspunkt RuK °C")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle :
				erkundungsstellen)
		{
			HtmlCell htmlCell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent("")
					.build();

			rowERK_RUK.appendContent(htmlCell.appendTag());
		}

		techBuilder.append(rowTECHMERKMALE.appendTag())
				.append(rowERK_DICKE_OBERBAU.appendTag())
				.append(rowERK_BELASTUNGSKLASSE.appendTag())
				.append(rowERK_RUK.appendTag());

		return techBuilder.toString();
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

	private String buildUmweltTechnischeMerkmale(final List<Erkundungsstelle> erkundungsstellen)
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

		//Pechnachweis qualitativ
		HtmlRow rowERK_PECH_QUALITATIV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Pechnachweiß qualitativ")
						.build()
						.appendTag())
				.build();

		//Pechnachweis quantitativ
		HtmlRow rowERK_PECH_QUANTITATIV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Pechnachweiß quantitativ")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle :
				erkundungsstellen)
		{
			HtmlCell htmlCell_PECHQUAL = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(erkundungsstelle.getInformation("ERK_PECH_QUALITATIV"))
					.build();

			rowERK_PECH_QUALITATIV.appendContent(htmlCell_PECHQUAL.appendTag());

			HtmlCell htmlCell_PECHQUAN = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(erkundungsstelle.getInformation("ERK_PECH_QUANTITATIV"))
					.build();

			rowERK_PECH_QUANTITATIV.appendContent(htmlCell_PECHQUAN.appendTag());
		}

		umweltTechBuilder.append(rowUMWELTMERKMALE.appendTag())
				.append(rowERK_PECH_QUALITATIV.appendTag())
				.append(rowERK_PECH_QUANTITATIV.appendTag());

		return umweltTechBuilder.toString();
	}

	private String buildQuerschnittRows(final List<Erkundungsstelle> erkundungsstellen, boolean pech)
	{
		StringBuilder querschnittBuilder = new StringBuilder();

		String querschnitt;
		String dicke;
		String mufv;
		String ruva;
		String avv;

		if (pech)
		{
			querschnitt = "Pechhaltiger Querschnitt";
			dicke = "7";
			mufv = "gefährlich";
			ruva = "B";
			avv = "17 03 01*";
		} else
		{
			querschnitt = "Pechfreier Querschnitt";
			dicke = "7";
			mufv = "nicht gefährlich";
			ruva = "A";
			avv = "17 03 02";
		}

		//Pechfreier Querschnitt Trennzeile
		HtmlRow rowPECH_QUERSCHNITT = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent(querschnitt)
						.build()
						.appendTag())
				.build();

		//Dicke pechfreier Querschnitt
		HtmlRow rowERK_DICKE_PECH = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Dicke cm")
						.build()
						.appendTag())
				.build();

		//MUFV Querschnitt
		HtmlRow rowERK_MUFV_PECH = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Schreiben des MUFV")
						.build()
						.appendTag())
				.build();

		//RUVA Querschnitt
		HtmlRow rowERK_RUVA_PECH = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("RUVA Verwertungsklasse")
						.build()
						.appendTag())
				.build();

		//AVV Querschnitt
		HtmlRow rowERK_AVV_PECH = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("AVV Abfallschlüssel")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle :
				erkundungsstellen)
		{
			if (erkundungsstelle.getSchichtAufschluss("GOB") != null)
			{
				//TODO
			}

			HtmlCell htmlCell_Dicke = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(dicke)
					.build();

			rowERK_DICKE_PECH.appendContent(htmlCell_Dicke.appendTag());

			HtmlCell htmlCell_MUFV = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(mufv)
					.build();

			rowERK_MUFV_PECH.appendContent(htmlCell_MUFV.appendTag());

			HtmlCell htmlCell_RUVA = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(ruva)
					.build();

			rowERK_RUVA_PECH.appendContent(htmlCell_RUVA.appendTag());

			HtmlCell htmlCell_AVV = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(avv)
					.build();

			rowERK_AVV_PECH.appendContent(htmlCell_AVV.appendTag());
		}

		querschnittBuilder.append(rowPECH_QUERSCHNITT.appendTag())
				.append(rowERK_DICKE_PECH.appendTag())
				.append(rowERK_MUFV_PECH.appendTag())
				.append(rowERK_RUVA_PECH.appendTag())
				.append(rowERK_AVV_PECH.appendTag());

		return querschnittBuilder.toString();
	}
}
