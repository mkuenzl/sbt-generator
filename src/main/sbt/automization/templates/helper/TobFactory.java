package sbt.automization.templates.helper;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.data.refactoring.references.RefSample;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.Outcrop;
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
					.appendContent(dataTable.get(RefProbe.OUTCROP_TOB))
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
					.appendContent(TextFormatUtil.formatOutcropLayers(dataTable, outcrop))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createTotalSizeRow(List<DataTable> dataTables)
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

		for (DataTable dataTable : dataTables)
		{
			List<Sample> samplesByGoB = ((Probe) dataTable).getSamplesBy(RefSample.OUTCROP,
					new String[]{Outcrop.GOB.toString(),
							Outcrop.TMHB.toString(),
							Outcrop.SEAL.toString(),
							Outcrop.GAP.toString(),
							Outcrop.COATING.toString(),
							Outcrop.CONCRETE.toString()});

			List<Sample> samplesByOutcrop = ((Probe) dataTable).getSamplesBy(RefSample.OUTCROP, outcrop);

			Double gobSize = TextFormatUtil.measureThicknessOfSamples(samplesByGoB);
			Double tobSize = TextFormatUtil.measureThicknessOfSamples(samplesByOutcrop);

			String doubleValue = String.valueOf(Math.round(gobSize + tobSize));
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
	public String createLegendRow(List<DataTable> dataTables)
	{
		int size = Integer.valueOf(headerCellWidth) + dataTables.size()* Integer.valueOf(normalCellWidth);

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegende = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
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
