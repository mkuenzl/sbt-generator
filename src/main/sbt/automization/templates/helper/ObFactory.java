package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.data.Layer;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class ObFactory extends ARowFactory
{
	public ObFactory()
	{
		super("GOB");
	}

	@Override
	public String createLegendRow(List<ExplorationSite> explorationSites)
	{
		int size = Integer.valueOf(headerCellWidth) + explorationSites.size()* Integer.valueOf(normalCellWidth);

		return null;
	}

	public String createAufschlussRow(List<ExplorationSite> explorationSites)
	{
		//Erkundungsstellen Aufschlussart
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Aufschlussart")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(InformationTag.SITE_OUTCROP_OB))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createSizeOBRow(List<ExplorationSite> explorationSites)
	{
		//Gesamtdicke Oberbau
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Gesamtdicke geb.")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("Oberbau,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
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
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.formatSiteOutcropThickness(explorationSite, outcrop))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createRukRow(List<ExplorationSite> explorationSites)
	{
		//RUK
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Erweichungspunkt")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("RuK<sup>[34]</sup>,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("°C")
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
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printRukLayers(explorationSite, outcrop))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createRukEinzelWertRow(List<ExplorationSite> explorationSites)
	{
		//RUK EinzelWert
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Soll Einzelwert,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("RuK")
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
					.appendAttribute("width", normalCellWidth)
					.appendContent("77")
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createPechQualitativRow(List<ExplorationSite> explorationSites)
	{
		//Pechnachweis qualitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Pechnachweiß")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("qualitativ")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(InformationTag.SITE_PITCH_QUALITATIVE))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPechHalbQuantitativRow(List<ExplorationSite> explorationSites)
	{
		//Pechnachweis quantitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Pechnachweiß")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("halbquantitativ")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(InformationTag.SITE_PITCH_HALF_QUANTITATIVE))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPechQuantitativRow(List<ExplorationSite> explorationSites)
	{
		//Pechnachweis quantitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Pechnachweiß")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("quantitativ")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(InformationTag.SITE_PITCH_QUANTITATIVE))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPechQuerschnittRows(List<ExplorationSite> explorationSites, boolean pitch)
	{
		StringBuilder querschnittBuilder = new StringBuilder();

		boolean isThereDataToBuild = false;

		String querschnitt = pitch ? "Pechhaltiger Querschnitt" : "Pechfreier Querschnitt";
		String size = "-";
		String mufv = pitch ? "gefährlich" : "nicht gefährlich";
		String ruva = pitch ? "B" : "A";
		String avv = pitch ? "17 03 01*" : "17 03 02";

		//Pechfreier Querschnitt Trennzeile
		HtmlRow rowHeader = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendContent(querschnitt)
						.build()
						.appendTag())
				.build();

		//Dicke pechfreier Querschnitt
		HtmlRow rowPitchSize = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Dicke,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//MUFV Querschnitt
		HtmlRow rowPitchMufv = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Abgrenzung")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("Gefährlichkeit,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Schreiben des MUFV<sup>[46]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//RUVA Querschnitt
		HtmlRow rowPitchRuva = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Verwertungsklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("RuVA<sup>[22]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//AVV Querschnitt
		HtmlRow rowPitchAvv = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Abfallschlüssel,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("AVV<sup>[6]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			boolean empty = true;

			List<Layer> layers = explorationSite.getLayersWithOutcrop(outcrop);

			if (layers != null)
			{
				double d = 0;

				//TODO
				//Wie viele Schichten haben Pech und wie viele haben kein Pech
				//Dicke anpassen
				//Wenn eine Erkundungsstelle nicht keine pitch freien / haltigen Schichten hat, dann "-"

				for (Layer layer : layers)
				{
					//TODO CHANGE TO TRUE & FALSE PECH
					String layerSize = layer.getInformation(InformationTag.LAYER_THICKNESS);
					layerSize = layerSize.replace(",", ".");

					String layerPitch = layer.getInformation(InformationTag.LAYER_PITCH);

					if (pitch && "ja".equals(layerPitch))
					{
						//Zähle Dicke der pechhaltigen Schichten
						d += Double.parseDouble(layerSize);
					}
					if (! pitch && "nein".equals(layerPitch))
					{
						//Zähle Dicke der pechfreien Schichten
						d += Double.parseDouble(layerSize);
					}
				}

				if (d > 0)
				{
					size = String.valueOf(d).replace(".", ",");
					empty = false;
					isThereDataToBuild = true;
				}
			}

			if (empty)
			{
				size = "-";
				mufv = "-";
				ruva = "-";
				avv = "-";
			}

			//DICKE
			HtmlCell cellPitchSize = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(size)
					.build();

			rowPitchSize.appendContent(cellPitchSize.appendTag());

			//MUFV
			HtmlCell cellPitchMufv = HtmlCellFormatUtil.formatChemistry(mufv);

			rowPitchMufv.appendContent(cellPitchMufv.appendTag());

			//RUVA
			HtmlCell cellPitchRuva = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(ruva)
					.build();

			rowPitchRuva.appendContent(cellPitchRuva.appendTag());

			//AVV
			HtmlCell cellPitchAvv = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(avv)
					.build();

			rowPitchAvv.appendContent(cellPitchAvv.appendTag());
		}


		if (isThereDataToBuild)
		{
			querschnittBuilder.append(rowHeader.appendTag())
					.append(rowPitchSize.appendTag())
					.append(rowPitchMufv.appendTag())
					.append(rowPitchRuva.appendTag())
					.append(rowPitchAvv.appendTag());
		}

		return querschnittBuilder.toString();
	}
}
