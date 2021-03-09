package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

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
					.appendAttribute("width", "60")
					.appendContent(erkundungsstelle.getInformation("ERK_ID"))
					.build();

			rowERK_ID.appendContent(htmlCell_ERK_ID.appendTag());

			HtmlCell htmlCell_ERK_AUFSCHLUSS = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
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

//		//Zonen Dicke 1 - Anzahl Schichten
//		HtmlRow rowSCHICHTE_DICKE = new HtmlRow.Builder()
//				.appendAttribute("class", "Normal")
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", "Normal")
//						.appendAttribute("width", "100")
//						.appendContent("Dicke Zonen")
//						.build()
//						.appendTag())
//				.build();

		//Zonen Material 1 - Anzahl Schichten
		HtmlRow rowSCHICHTEN_MATERIAL = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Material")
						.build()
						.appendTag())
				.build();

		//Korngroeßenverteilungen
		HtmlRow rowSCHICHTEN_KGV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Korngrößenverteilung Kornanteil < 0,0063 mm")
						.build()
						.appendTag())
				.build();

		//Gesamtdicke / frostsicherer Oberbau
		HtmlRow rowERK_GESAMTDICKE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Gesamtdicke / frostsicherer Oberbau")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			HtmlCell htmlCell_SCHICHTEN_MATERIAL = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.presentSchichtenToB(erkundungsstelle))
					.build();

			rowSCHICHTEN_MATERIAL.appendContent(htmlCell_SCHICHTEN_MATERIAL.appendTag());

			HtmlCell htmlCell_SCHICHTEN_KGV = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent("dummy")
					.build();

			rowSCHICHTEN_KGV.appendContent(htmlCell_SCHICHTEN_KGV.appendTag());

			HtmlCell htmlCell_ERK_GESAMTDICKE = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent("dummy")
					.build();

			rowERK_GESAMTDICKE.appendContent(htmlCell_ERK_GESAMTDICKE.appendTag());
		}

		techBuilder.append(rowTECHMERKMALE.appendTag())
				.append(buildTechnischeMerkmaleEV(erkundungsstellen))
//				.append(rowSCHICHTE_DICKE.appendTag())
				.append(rowSCHICHTEN_MATERIAL.appendTag())
				.append(rowSCHICHTEN_KGV.appendTag())
				.append(rowERK_GESAMTDICKE.appendTag());

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

	private String buildTechnischeMerkmaleEV(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder evBuilder = new StringBuilder();

		HtmlRow rowERK_EVDYN = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("EVDYN")
						.build()
						.appendTag())
				.build();

		HtmlRow rowERK_EVDYN15 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("EVDYN15")
						.build()
						.appendTag())
				.build();

		HtmlRow rowERK_EV2 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("EV2")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle :
				erkundungsstellen)
		{
			HtmlCell htmlCell_ERK_EVDYN = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(erkundungsstelle.getInformation("ERK_LP_EV"))
					.build();

			rowERK_EVDYN.appendContent(htmlCell_ERK_EVDYN.appendTag());

			HtmlCell htmlCell_ERK_EVDYN15 = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(erkundungsstelle.getInformation("ERK_LP_EV15"))
					.build();

			rowERK_EVDYN15.appendContent(htmlCell_ERK_EVDYN15.appendTag());

			HtmlCell htmlCell_ERK_EV2 = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(erkundungsstelle.getInformation("ERK_LP_EV2"))
					.build();

			rowERK_EV2.appendContent(htmlCell_ERK_EV2.appendTag());
		}

		evBuilder.append(rowERK_EVDYN.appendTag())
				.append(rowERK_EVDYN15.appendTag())
				.append(rowERK_EV2.appendTag());

		return evBuilder.toString();
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
