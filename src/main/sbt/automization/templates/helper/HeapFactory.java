package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class HeapFactory
{
	private static final String outcrop = "HAUFWERK";
	private static final String headerCellClass = "NormalHeader";
	private static final String normalCellClass = "NormalBold";

	private HeapFactory(){}

	public static String createIDRow(List<ExplorationSite> explorationSites)
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
				explorationSites)
		{
			HtmlCell htmlCell_ERK_ID = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(explorationSite.getInformation("ERK_ID"))
					.build();

			row.appendContent(htmlCell_ERK_ID.appendTag());
		}

		return row.appendTag();
	}

	public static String createOutcropRow(List<ExplorationSite> explorationSites)
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
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_AUFSCHLUSS_UG_OH_BA"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createMaterialRow(List<ExplorationSite> explorationSites)
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

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(explorationSite.getInformation("ERK_HAUFWERK_MATERIAL"))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createDIN18196Row(List<ExplorationSite> explorationSites)
	{
		//DIN18196
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Bodengruppe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DIN 18196<sup>[22]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "SCHICHT_ART"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createDIN18300Row(List<ExplorationSite> explorationSites)
	{
		//DIN18300
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Bodenklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DIN 18300<sup>[23]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "SCHICHT_BODENKLASSE"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createChemieIDRow(List<ExplorationSite> explorationSites)
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

		for (ExplorationSite explorationSite : explorationSites)
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

	public static String createChemieMufvRow(List<ExplorationSite> explorationSites)
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
								.appendContent("Schreiben des MUFV<sup>[46]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
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

	public static String createChemieLagaBoRow(List<ExplorationSite> explorationSites)
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

		for (ExplorationSite explorationSite : explorationSites)
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

	public static String createChemieLagaRcRow(List<ExplorationSite> explorationSites)
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

		for (ExplorationSite explorationSite : explorationSites)
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

	public static String createChemieLagaRcOrientierungRow(List<ExplorationSite> explorationSites)
	{
		HtmlRow rowCHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Orientierung,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("LAGA Bauschutt<sup>[16]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
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

	public static String createChemieTlGesteinRow(List<ExplorationSite> explorationSites)
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

		for (ExplorationSite explorationSite : explorationSites)
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

	public static String createChemieDepvRow(List<ExplorationSite> explorationSites)
	{
		HtmlRow rowCHEMIE_DEPV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Deponieverordnungs-")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("klasse,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DepV<sup>[7]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
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

	public static String createAVVRow(List<ExplorationSite> explorationSites){
		//AVV
		HtmlRow rowERK_AVV = new HtmlRow.Builder()
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

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell htmlCell_AVV = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_ABFALLSCHLUESSEL"))
					.build();

			rowERK_AVV.appendContent(htmlCell_AVV.appendTag());
		}

		return rowERK_AVV.appendTag();
	}

	public static String createREKUROW(List<ExplorationSite> explorationSites)
	{
		HtmlRow rowERK_REKU = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Rekultivierung,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Reku<sup>[7]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell htmlCell_REKU = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(TextFormatUtil.printLayerInformation(explorationSite, outcrop, "CHEMIE_REKU"))
					.build();

			rowERK_REKU.appendContent(htmlCell_REKU.appendTag());
		}

		return rowERK_REKU.appendTag();
	}

	public static String createChemieEntscheidungshilfeRow(List<ExplorationSite> explorationSites)
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

		for (ExplorationSite explorationSite : explorationSites)
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

	public static String createLegendeRow(List<ExplorationSite> explorationSites)
	{
		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegende = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
						.build()
						.appendTag())
				.build();

		return rowLegende.appendTag();
	}
}
