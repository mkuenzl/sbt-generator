package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class TmhbFactory extends ARowFactory
{
	public TmhbFactory() {super("TMHB");}

	@Override
	public String createLegendRow(List<ExplorationSite> explorationSites)
	{
		int size = Integer.valueOf(headerCellWidth) + explorationSites.size()* Integer.valueOf(normalCellWidth);

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegende = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Anmerkungen:")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
						.build()
						.appendTag())
				.build();

		return rowLegende.appendTag();
	}

	public String createAufschlussRow(List<ExplorationSite> explorationSites)
	{
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
					.appendContent(explorationSite.getInformation(InformationTag.SITE_OUTCROP_TOB))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createTotalSizeRow(List<ExplorationSite> explorationSites)
	{
		//Gesamtdicke Oberbau
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Gesamtdicke Oberbau,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			Double gobSize = explorationSite.getOutcropThickness("GOB");
			Double tobSize = explorationSite.getOutcropThickness(outcrop);

			String doubleValue = String.valueOf(Math.round(gobSize + tobSize));
			String totalSize = doubleValue.replace(".", ",");

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(totalSize)
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();

	}
}
