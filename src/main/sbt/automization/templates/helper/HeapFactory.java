package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;

import java.util.List;

public final class HeapFactory extends ARowFactory
{
	public HeapFactory(){super("HAUFWERK");}

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
					.appendContent(explorationSite.getInformation(ReferenceKey.SITE_OUTCROP_UG_OH_BA))
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
					.appendContent(explorationSite.getInformation(ReferenceKey.SITE_HEAP_MATERIAL))
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
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
						.build()
						.appendTag())
				.build();

		return row.appendTag();
	}
}
