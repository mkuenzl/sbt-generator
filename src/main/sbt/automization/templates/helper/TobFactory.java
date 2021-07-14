package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class TobFactory extends ARowFactory
{
	public TobFactory()
	{
		super("TOB");
	}

	public String createOutcropRow(List<ExplorationSite> explorationSites)
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
					.appendContent(explorationSite.getInformation(InformationTag.SITE_OUTCROP_TOB))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createMaterialRow(List<ExplorationSite> explorationSites)
	{
		//Zonen Material 1 - Anzahl Schichten
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Material")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.formatOutcropLayers(explorationSite, outcrop))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createTotalSizeRow(List<ExplorationSite> explorationSites)
	{   //TODO ERROR Dicken werden falsch berechnet!
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
			String gobSize = TextFormatUtil.formatSiteOutcropThickness(explorationSite, "GOB").replace(",",".");
			String tobSize = TextFormatUtil.formatSiteOutcropThickness(explorationSite, outcrop).replace(",",".");

			String doubleValue = String.valueOf(Math.round(Double.parseDouble(gobSize) + Double.parseDouble(tobSize)));
			String totalSize = doubleValue.replace(".",",");

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(totalSize)
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
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Anmerkungen:")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm. ")
						.appendContent("Gem. a. G. = Gemisch aus Gesteinskörnungen, NS = Naturstein, LS = Lavaschlacke, HO = Hochofenschlacke,")
						.appendContent("RC = Rezyklierte Gesteinskörnung, BK = Brechkorn, RK = Rundkorn, sg = stetig gestuft, ug = unstetig gestuft")
						.build()
						.appendTag())
				.build();

		return rowLegende.appendTag();
	}
}
