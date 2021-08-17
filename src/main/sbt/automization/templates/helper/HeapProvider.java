package sbt.automization.templates.helper;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.data.refactoring.references.RefSample;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;

import java.util.List;

public final class HeapProvider extends RowProvider
{
	public HeapProvider(){super("HAUFWERK");}

	public String createOutcropRow(List<DataTable> dataTables)
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

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(dataTable.get(RefProbe.OUTCROP_UG_OH_BA))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createMaterialRow(List<DataTable> dataTables)
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

		for (DataTable dataTable : dataTables)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(dataTable.get(RefSample.TYPE))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	@Override
	public String createLegendRow(List<DataTable> dataTables)
	{
		int size = Integer.valueOf(headerCellWidth) + dataTables.size()* Integer.valueOf(normalCellWidth);

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
						.build()
						.appendTag())
				.build();

		return row.appendTag();
	}
}
