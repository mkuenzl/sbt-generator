package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class UgFactory
{
	private static final String outcrop = "UG";
	private static final String headerCellClass = "NormalHeader";
	private static final String normalCellClass = "NormalBold";

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

	public static String createDickeRow(List<ExplorationSite> explorationSites)
	{
		//Erkundungsstellen Aufschlussart
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


		for (ExplorationSite explorationSite :
				explorationSites)
		{
			String outcropThickness = TextFormatUtil.formatSiteOutcropThickness(explorationSite, outcrop);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(outcropThickness)
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createGesamtDickeRow(List<ExplorationSite> explorationSites)
	{
		//Erkundungsstellen Aufschlussart
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Gesamtdicke,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("cm")
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
					.appendContent(String.valueOf(explorationSite.getThickness()).replace(".", ","))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createZielTiefeRow(List<ExplorationSite> explorationSites)
	{
		//ZIELTIEFE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Zieltiefe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//Wenn GesamtDicke gleich Zieltiefe, dann grün ansonsten rot da Zieltiefe nicht erreicht wurde

		for (ExplorationSite explorationSite : explorationSites)
		{
			String zieltiefe = explorationSite.getInformation("ERK_ZIELTIEFE");
			HtmlCell cell;
			if("-".equals(zieltiefe)){
				cell = new HtmlCell.Builder()
						.appendAttribute("class", normalCellClass)
						.appendAttribute("width", "60")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent(zieltiefe)
								.build().appendTag())
						.build();
			} else {
				double tiefe = Double.parseDouble(zieltiefe);

				String backgroundColor;
				String textColor;

				if (tiefe <= explorationSite.getThickness()) {
					backgroundColor = "#00FF00";
					textColor = "black";
				} else {
					backgroundColor = "red";
					textColor = "white";
				}

				cell = new HtmlCell.Builder()
						.appendAttribute("class", normalCellClass)
						.appendAttribute("width", "60")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"background-color: " + backgroundColor + ";font-weight: bold;\n\n" +
										"  color: " + textColor + "\">")
								.appendContent(zieltiefe)
								.appendContent("</span>")
								.build().appendTag())
						.build();

			}

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createAufschlussRow(List<ExplorationSite> explorationSites)
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

	public static String createWasserGehaltRow(List<ExplorationSite> explorationSites)
	{
		//WASSERGEHALT
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Wassergehalt,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("M.-%")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_WASSERGEHALT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createFeuchteZustandRow(List<ExplorationSite> explorationSites)
	{
		//FEUCHTEZUSTAND
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Feuchtezustand")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_FEUCHTIGKEIT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createKonsistenzRow(List<ExplorationSite> explorationSites)
	{
		//KONSISTENZ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Konsistenz")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_KONSISTENZ"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createProctordichteRow(List<ExplorationSite> explorationSites)
	{
		//PROCTORDICHTE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Proctordichte")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Mg/m³")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_WASSERPROCTOR"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createProtorDifferenzRow(List<ExplorationSite> explorationSites)
	{
		//DIFFERENCE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Differenz W<sub>n</sub> - W<sub>opt</sub>")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("M.-%")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_DIFFERENZ_WN_WOPT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createVerdichtungsfaehigkeitRow(List<ExplorationSite> explorationSites)
	{
		//VERDICHTUNGSFÄHIGKEIT
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Verdichtungsfähigkeit")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_VERDICHTUNGSFAEHIGKEIT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createTragPlanumRow(List<ExplorationSite> explorationSites)
	{
		//TRAGFAEHIGKEIT_PLANUM
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Tragfähigkeit")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("Planum")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Soll: E<sub>V2</sub> >= 45 MN/m²")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Ansatz Planum: FOK -60cm")
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
					.appendContent(explorationSite.getInformation("ERK_TRAG_PLANUM"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createTragSohleRow(List<ExplorationSite> explorationSites)
	{
		//TRAGFAEHIGKEIT_GRABENSOHLE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Tragfähigkeit")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("Grabensohle")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Ansatz Sohle")
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
					.appendContent(explorationSite.getInformation("ERK_TRAG_GRABENSOHLE"))
					.appendContent(TextFormatUtil.printLineEmpty())
					.appendContent(new HtmlText.Builder().appendAttribute("class", "Normal6")
							.appendContent("[T:")
							.appendContent(explorationSite.getInformation("ERK_SOHLE_TIEFE"))
							.appendContent("]").build().appendTag())
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
								.appendContent("DIN 18196<sup>[10]</sup>")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_ART"))
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
								.appendContent("DIN 18300<sup>[11]</sup>")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_BODENKLASSE"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createDIN19682Row(List<ExplorationSite> explorationSites)
	{
		//DIN19682
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Bodenarten-")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("hauptgruppe,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DIN 19682-2<sup>[13]</sup>")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_BODENART"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createDIN18300_09Row(List<ExplorationSite> explorationSites)
	{
		//DIN18300:2019-09
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Homogenbereich,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DIN 18300:2019-09<sup>[12]</sup>")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_HOMOGENBEREICH"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createZTVRow(List<ExplorationSite> explorationSites)
	{
		//ZTV-E
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Frostempfindlichkeits-")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("klasse,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("ZTV E<sup>[1]</sup>")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_FROSTEMPFINDLICHKEITSKLASSE"))
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_ID"))
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
								.appendContent("Schreiben des MUFV<sup>[9]</sup>")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_MUFV"))
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
						.appendContent("Zuordnungsklasse,")
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_LAGA_BO"))
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_LAGA_RC"))
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_LAGARC_ORIENTIERUNGSWERT"))
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_TLGESTEIN"))
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_DEPV"))
					.build();


			rowCHEMIE_DEPV.appendContent(htmlCell_CHEMIE_DEPV.appendTag());
		}

		return rowCHEMIE_DEPV.appendTag();
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_ENTSCHEIDUNGSHILFE"))
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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_ABFALLSCHLUESSEL"))
					.build();

			rowChemistryAVV.appendContent(htmlCell_CHEMIE_ABFALLSCHLUESSEL.appendTag());
		}

		return rowChemistryAVV.appendTag();
	}

	public static String createChemieRekuRow(List<ExplorationSite> explorationSites)
	{
		HtmlRow rowChemistryAVV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Rekultivierungsschicht,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Reku<sup>[7]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			List<Layer> layersWithOutcrop = explorationSite.getLayersWithOutcrop(outcrop);

			HtmlCell htmlCell_CHEMIE_REKU = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "CHEMIE_REKU"))
					.build();

			rowChemistryAVV.appendContent(htmlCell_CHEMIE_REKU.appendTag());
		}

		return rowChemistryAVV.appendTag();
	}

	public static String createLegendRow(List<ExplorationSite> explorationSites)
	{
		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegende = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendContent("Anmerkungen:")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm. ")
						.appendContent("Die Einstufung der Verdichtungsfähigkeit erfolgt unter Berücksichtigung der Bodenfeuchtigkeit und der Konsistenz\n" +
								"des Materials zum Erkundungszeitpunkt.")
						.build()
						.appendTag())
				.build();

		return rowLegende.appendTag();
	}
}
