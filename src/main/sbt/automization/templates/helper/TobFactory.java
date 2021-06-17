package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.Util;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class TobFactory
{
	private static final String outcrop = "TOB";
	private static final String headerCellClass = "NormalHeader";
	private static final String normalCellClass = "NormalBold";
	
	public static String createIDRow(List<ExplorationSite> erkundungsstellen)
	{
		//Erkundungsstellen ID
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "110")
						.appendContent("Erkundungsstelle")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell htmlCell_ERK_ID = new HtmlCell.Builder()
					.appendAttribute("class", "NormalBold")
					.appendAttribute("width", "60")
					.appendContent(explorationSite.getInformation("ERK_ID"))
					.build();

			row.appendContent(htmlCell_ERK_ID.appendTag());
		}

		return row.appendTag();
	}

	public static String createAufschlussRow(List<ExplorationSite> erkundungsstellen)
	{
		//Erkundungsstellen Aufschlussart
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Aufschlussart")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_AUFSCHLUSS_TOB"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createMaterialRow(List<ExplorationSite> erkundungsstellen)
	{
		//Zonen Material 1 - Anzahl Schichten
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Material")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.presentTobLayers(explorationSite))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createKGVRow(List<ExplorationSite> explorationSites)
	{
		//Korngroeßenverteilungen
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Korngrößenverteilung,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Kornanteil < 0,063 mm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "SCHICHT_KORNGROESSENVERTEILUNG"))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();

	}

	public static String createGesamtDickeRow(List<ExplorationSite> explorationSites)
	{
		//Gesamtdicke Oberbau
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Gesamtdicke Oberbau,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			String gob_dicke = TextFormatUtil.formatSiteOutcropThickness(explorationSite, "GOB").replace(",",".");
			String tob_dicke = TextFormatUtil.formatSiteOutcropThickness(explorationSite, outcrop).replace(",",".");

			String doubleValue = String.valueOf(Math.round(Double.parseDouble(gob_dicke) + Double.parseDouble(tob_dicke)));
			String gesamtDicke = doubleValue.replace(".",",");

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(gesamtDicke)
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();

	}

	public static String createDickeRow(List<ExplorationSite> explorationSites)
	{
		//Gesamtdicke Oberbau
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Dicke,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			String tob_dicke = TextFormatUtil.formatSiteOutcropThickness(explorationSite, outcrop);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(tob_dicke)
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();

	}

	public static String createEvDynRow(List<ExplorationSite> explorationSites)
	{
		//if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "ERK_LP_EV")) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("E<sub>Vdyn</sub>,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("MN/m²")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();


		for (ExplorationSite explorationSite :
				explorationSites)
		{

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_LP_EV"))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createEvDyn85Row(List<ExplorationSite> explorationSites)
	{
		//if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "ERK_LP_EV15")) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("E<sub>Vdyn (-15%)</sub>,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("MN/m²")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();


		for (ExplorationSite explorationSite :
				explorationSites)
		{

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_LP_EV15"))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createEv2Row(List<ExplorationSite> explorationSites)
	{
		//if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "ERK_LP_EV2")) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("E<sub>V2</sub><sup>[42]</sup>,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("MN/m²")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();


		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(TextFormatUtil.formatErkLP(explorationSite))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createEvSollRow(List<ExplorationSite> explorationSites)
	{
		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "ERK_LP_EV2_SOLL")) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Soll Wert,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("E<sub>V2</sub>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_LP_EV2_SOLL"))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createChemieIDRow(List<ExplorationSite> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_ID = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Laborprobe")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_ID = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_ID"))
					.build();

			rowCHEMIE_ID.appendContent(htmlCell_CHEMIE_ID.appendTag());
		}

		return rowCHEMIE_ID.appendTag();

	}

	public static String createChemieMufvRow(List<ExplorationSite> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_MUFV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Abgrenzung")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("Gefährlichkeit,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Schreiben des MUFV<sup>[9]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_MUFV = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_MUFV"))
					.build();

			rowCHEMIE_MUFV.appendContent(htmlCell_CHEMIE_MUFV.appendTag());
		}

		return rowCHEMIE_MUFV.appendTag();
	}

	public static String createChemieLagaBoRow(List<ExplorationSite> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_LAGA_BO = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Zuordnung,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("LAGA Boden<sup>[2]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_LAGA_BO = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_LAGA_BO"))
					.build();

			rowCHEMIE_LAGA_BO.appendContent(htmlCell_CHEMIE_LAGA_BO.appendTag());
		}

		return rowCHEMIE_LAGA_BO.appendTag();
	}

	public static String createChemieLagaRcRow(List<ExplorationSite> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_LAGA_RC = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Zuordnung,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("LAGA Bauschutt<sup>[16]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_LAGA_RC = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_LAGA_RC"))
					.build();

			rowCHEMIE_LAGA_RC.appendContent(htmlCell_CHEMIE_LAGA_RC.appendTag());
		}

		return rowCHEMIE_LAGA_RC.appendTag();
	}

	public static String createChemieLagaRcOrientierungRow(List<ExplorationSite> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Orientierungswert,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("LAGA Bauschutt<sup>[16]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_LAGARC_ORIENTIERUNGSWERT"))
					.build();

			rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendContent(htmlCell_CHEMIE_LAGA_RC_ORIENTIERUNG.appendTag());
		}

		return rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendTag();

	}

	public static String createChemieTlGesteinRow(List<ExplorationSite> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_TL_GESTEIN = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Verwertungsklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("TL Gestein<sup>[15]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_TL_GESTEIN = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_TLGESTEIN"))
					.build();


			rowCHEMIE_TL_GESTEIN.appendContent(htmlCell_CHEMIE_TL_GESTEIN.appendTag());
		}

		return rowCHEMIE_TL_GESTEIN.appendTag();
	}

	public static String createChemieDepvRow(List<ExplorationSite> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_DEPV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Deponieklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DepV<sup>[7]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_DEPV = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_DEPV"))
					.build();


			rowCHEMIE_DEPV.appendContent(htmlCell_CHEMIE_DEPV.appendTag());
		}

		return rowCHEMIE_DEPV.appendTag();
	}

	public static String createChemieEntscheidungshilfeRow(List<ExplorationSite> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_ENTSCHEIDUNGSHILFE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Entscheidungshilfe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DepV<sup>[8]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : erkundungsstellen)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_ENTSCHEIDUNGSILFE = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_ENTSCHEIDUNGSHILFE"))
					.build();

			rowCHEMIE_ENTSCHEIDUNGSHILFE.appendContent(htmlCell_CHEMIE_ENTSCHEIDUNGSILFE.appendTag());
		}

		return rowCHEMIE_ENTSCHEIDUNGSHILFE.appendTag();
	}

	public static String createChemieAbfallSchluesselRow(List<ExplorationSite> explorationSites)
	{
		HtmlRow rowChemistryAVV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Abfallschlüssel,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("AVV<sup>[6]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			List<Layer> layersWithOutcrop = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_ABFALLSCHLUESSEL = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_ABFALLSCHLUESSEL"))
					.build();

			rowChemistryAVV.appendContent(htmlCell_CHEMIE_ABFALLSCHLUESSEL.appendTag());
		}

		return rowChemistryAVV.appendTag();
	}

	public static String createLegendeRow(List<ExplorationSite> erkundungsstellen)
	{
		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegende = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
						.build()
						.appendTag())
				.build();

		return rowLegende.appendTag();
	}
}
