package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class UgFactory extends ARowFactory
{
	public UgFactory()
	{
		super("UG");
	}

	public String createTotalSizeRow(List<ExplorationSite> explorationSites)
	{
		//Erkundungsstellen Aufschlussart
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Gesamtdicke,")
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
					.appendContent(String.valueOf(explorationSite.getThickness()).replace(".", ","))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createTargetDepthRow(List<ExplorationSite> explorationSites)
	{
		//ZIELTIEFE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Zieltiefe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//Wenn GesamtDicke gleich Zieltiefe, dann grün ansonsten rot da Zieltiefe nicht erreicht wurde

		for (ExplorationSite explorationSite : explorationSites)
		{
			String targetDepth = explorationSite.getInformation(InformationTag.SITE_TARGET_DEPTH);
			HtmlCell cell;

			if("-".equals(targetDepth)){
				cell = new HtmlCell.Builder()
						.appendAttribute("class", normalCellClass)
						.appendAttribute("width", normalCellWidth)
						.appendContent(targetDepth)
						.build();
			} else {
				double depth = Double.parseDouble(targetDepth);

				String backgroundColor;
				String textColor;

				if (depth <= explorationSite.getThickness()) {
					backgroundColor = "#00FF00";
					textColor = "black";
				} else {
					backgroundColor = "red";
					textColor = "white";
				}

				cell = new HtmlCell.Builder()
						.appendAttribute("class", normalCellClass)
						.appendAttribute("width", normalCellWidth)
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"background-color: " + backgroundColor + ";font-weight: bold;\n\n" +
										"  color: " + textColor + "\">")
								.appendContent(targetDepth)
								.appendContent("</span>")
								.build().appendTag())
						.build();

			}

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
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
					.appendContent(explorationSite.getInformation(InformationTag.SITE_OUTCROP_UG_OH_BA))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	@Override
	public String createLegendRow(List<ExplorationSite> explorationSites)
	{
		int size = Integer.valueOf(headerCellWidth) + explorationSites.size()* Integer.valueOf(normalCellWidth);

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegende = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendAttribute("width", String.valueOf(size))
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
