package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.data.Outcrop;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;

import java.util.List;

public final class TobProvider extends RowProvider
{
	public TobProvider()
	{
		super("TOB");
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
					.appendContent(new SamplePrinter(Outcrop.TOB).printSamplesMaterials(dataTable))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createTotalSizeRow(List<DataTable> dataTables)
	{
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
			List<Sample> samplesByGoB = ((Probe) dataTable).getSamplesBy(SampleKey.OUTCROP,
					new String[]{Outcrop.GOB.toString(),
							Outcrop.TMHB.toString(),
							Outcrop.SEAL.toString(),
							Outcrop.GAP.toString(),
							Outcrop.COATING.toString(),
							Outcrop.CONCRETE.toString()});

			List<Sample> samplesByOutcrop = ((Probe) dataTable).getSamplesBy(SampleKey.OUTCROP, outcrop);

			SamplePrinter samplePrinter = new SamplePrinter();
			Double gobSize = samplePrinter.measureThickness(samplesByGoB);
			Double tobSize = samplePrinter.measureThickness(samplesByOutcrop);

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
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", legendCellClass)
						.appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Anmerkungen:")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Für die angegebenen Tiefen [] gilt die Einheit cm. ")
						.appendContent("Gem. a. G. = Gemisch aus Gesteinskörnungen, NS = Naturstein, LS = Lavaschlacke, HO = Hochofenschlacke,")
						.appendContent("RC = Rezyklierte Gesteinskörnung, BK = Brechkorn, RK = Rundkorn, sg = stetig gestuft, ug = unstetig gestuft")
						.build()
						.appendTag())
				.build();

		return rowLegende.appendTag();
	}
}
