package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class ObFactory
{
	private static final String aufschluss = "OB";
	private static final String normalCellClass = "NormalBold";
	private static final String headerCellClass = "NormalHeader";
	
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
					.appendAttribute("class", normalCellClass)
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
					.appendContent(explorationSite.getInformation("ERK_AUFSCHLUSS_OB"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createDickeOberbauRow(List<ExplorationSite> erkundungsstellen)
	{
		//Gesamtdicke Oberbau
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Gesamtdicke geb.")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("Oberbau,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(TextFormatUtil.formatSiteOutcropThickness(explorationSite, "GOB"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createBelastungsklasseRow(List<ExplorationSite> erkundungsstellen)
	{
		//RSTO Belastungsklasse
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Belastungsklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("RStO")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_BELASTUNGSKLASSE"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createRukRow(List<ExplorationSite> erkundungsstellen)
	{
		//RUK
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Erweichungspunkt")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("RuK,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("°C")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(TextFormatUtil.printRukLayers(explorationSite, "GOB"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createRukEinzelWertRow(List<ExplorationSite> erkundungsstellen)
	{
		//RUK EinzelWert
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Soll Einzelwert,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("RuK")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent("77")
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createPechQualitativRow(List<ExplorationSite> erkundungsstellen)
	{
		//Pechnachweis qualitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Pechnachweiß")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("qualitativ")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_PECH_QUALITATIV"))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createPechHalbQuantitativRow(List<ExplorationSite> erkundungsstellen)
	{
		//Pechnachweis quantitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Pechnachweiß")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("halb-quantitativ")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_PECH_HALBQUANTITATIV"))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createPechQuantitativRow(List<ExplorationSite> erkundungsstellen)
	{
		//Pechnachweis quantitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Pechnachweiß")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("quantitativ")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(explorationSite.getInformation("ERK_PECH_QUANTITATIV"))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public static String createPechQuerschnittRows(List<ExplorationSite> erkundungsstellen, boolean pech)
	{
		StringBuilder querschnittBuilder = new StringBuilder();

		boolean isThereDataToBuild = false;

		String querschnitt = pech ? "Pechhaltiger Querschnitt" : "Pechfreier Querschnitt";
		String dicke = "-";
		String mufv = pech ? "gefährlich" : "nicht gefährlich";
		String ruva = pech ? "B" : "A";
		String avv = pech ? "17 03 01*" : "17 03 02";

		//Pechfreier Querschnitt Trennzeile
		HtmlRow rowPECH_QUERSCHNITT = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent(querschnitt)
						.build()
						.appendTag())
				.build();

		//Dicke pechfreier Querschnitt
		HtmlRow rowERK_DICKE_PECH = new HtmlRow.Builder()
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

		//MUFV Querschnitt
		HtmlRow rowERK_MUFV_PECH = new HtmlRow.Builder()
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
								.appendContent("Schreiben des MUFV")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//RUVA Querschnitt
		HtmlRow rowERK_RUVA_PECH = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Verwertungsklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("RuVA")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//AVV Querschnitt
		HtmlRow rowERK_AVV_PECH = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Abfallschlüssel,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("AVV")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				erkundungsstellen)
		{
			boolean empty = true;

			List<Layer> gob = explorationSite.getLayersWithOutcrop("GOB");

			if (gob != null)
			{
				double d = 0;

				//TODO
				//Wie viele Schichten haben Pech und wie viele haben kein Pech
				//Dicke anpassen
				//Wenn eine Erkundungsstelle nicht keine pech freien / haltigen Schichten hat, dann "-"

				for (Layer layer : gob)
				{
					//TODO CHANGE TO TRUE & FALSE PECH
					String schicht_dicke = layer.getInformation("SCHICHT_DICKE");
					schicht_dicke = schicht_dicke.replace(",", ".");

					String schicht_pech = layer.getInformation("SCHICHT_PECH");

					if (pech && "ja".equals(schicht_pech))
					{
						//Zähle Dicke der pechhaltigen Schichten
						d += Double.parseDouble(schicht_dicke);
					}
					if (! pech && "nein".equals(schicht_pech))
					{
						//Zähle Dicke der pechfreien Schichten
						d += Double.parseDouble(schicht_dicke);
					}
				}

				if (d > 0)
				{
					dicke = String.valueOf(d).replace(".", ",");
					empty = false;
					isThereDataToBuild = true;
				}
			}

			if (empty)
			{
				dicke = "-";
				mufv = "-";
				ruva = "-";
				avv = "-";
			}

			//DICKE
			HtmlCell htmlCell_Dicke = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(dicke)
					.build();

			rowERK_DICKE_PECH.appendContent(htmlCell_Dicke.appendTag());

			//MUFV
			HtmlCell htmlCell_MUFV = HtmlCellFormatUtil.formatChemistry(mufv);

			rowERK_MUFV_PECH.appendContent(htmlCell_MUFV.appendTag());

			//RUVA
			HtmlCell htmlCell_RUVA = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(ruva)
					.build();

			rowERK_RUVA_PECH.appendContent(htmlCell_RUVA.appendTag());

			//AVV
			HtmlCell htmlCell_AVV = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", "50")
					.appendContent(avv)
					.build();

			rowERK_AVV_PECH.appendContent(htmlCell_AVV.appendTag());
		}


		if (isThereDataToBuild)
		{
			querschnittBuilder.append(rowPECH_QUERSCHNITT.appendTag())
					.append(rowERK_DICKE_PECH.appendTag())
					.append(rowERK_MUFV_PECH.appendTag())
					.append(rowERK_RUVA_PECH.appendTag())
					.append(rowERK_AVV_PECH.appendTag());
		}

		return querschnittBuilder.toString();
	}
}
