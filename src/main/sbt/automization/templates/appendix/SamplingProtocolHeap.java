package sbt.automization.templates.appendix;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.datatable.SampleFormatter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.format.text.LineBreakTextFormatter;
import sbt.automization.format.text.SampleTypeTextFormatter;
import sbt.automization.html.HtmlFactory;

import java.util.List;

public final class SamplingProtocolHeap extends Appendix
{
	private static SamplingProtocolHeap instance;

	private SamplingProtocolHeap() {}

	public static SamplingProtocolHeap getInstance()
	{
		if (instance == null)
		{
			synchronized (SamplingProtocolHeap.class)
			{
				if (instance == null)
				{
					instance = new SamplingProtocolHeap();
				}
			}
		}
		return instance;
	}

	@Override
	protected String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:40px", 2, 1,
						new String[]{"Probe", UtilityPrinter.printLineBreak(), "Nr."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:40px", 2, 1,
						new String[]{"Art"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:105px",
						new String[]{"Behältnis", UtilityPrinter.printLineBreak(), "Vol."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Haufwerk", UtilityPrinter.printLineBreak(), "Vol."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:100px", 2, 1,
						new String[]{"Abfallart"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:40px", 1, 1,
						new String[]{"Korn-", UtilityPrinter.printLineBreak(),"größe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:75px", 2, 1,
						new String[]{"Farbe", UtilityPrinter.printLineBreak(), "Geruch", UtilityPrinter.printLineBreak(), "Bodenart"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:55px", 2, 1,
						new String[]{"Herkunft"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:55px", 2, 1,
						new String[]{"Proben-", UtilityPrinter.printLineBreak(),"lokalität"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:55px", 2, 1,
						new String[]{"Notiz"})
		});

		String secondRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"l"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"m³"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"mm"})
		});

		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow);

		return stringBuilder.toString();
	}

	@Override
	public String getExportFileName()
	{
		return "HAUFWERK-PN-Anlage";
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		buildTable(dataTables);
		addTable();
	}

	private void buildTable(List<DataTable> dataTables)
	{
		createTable();

		for (DataTable dataTable : dataTables)
		{
			SampleFormatter sampleFormatter = new SampleFormatter(dataTable, Outcrop.HEAP.toString());
			List<Sample> heapSamples = sampleFormatter.createHeapSamples();

			for (Sample sample : heapSamples)
			{
				addAndResetTableOnPageBreak();

				String row = HtmlFactory.createRowAsString("Normal", new String[]{
						HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
								new String[]{"P".concat(String.valueOf(++ lines))}),
						HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
								new String[]{new SampleTypeTextFormatter().format(sample.get(SampleKey.CONTAINER))}),
						HtmlFactory.createCellAsString(textFormatter,"Normal",
								new String[]{sample.get(SampleKey.CONTAINER)}),
						HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
								new String[]{sample.get(SampleKey.VOLUME)}),
						HtmlFactory.createCellAsString(textFormatter,"Normal",
								new String[]{new LineBreakTextFormatter().format(sample.get(SampleKey.WASTE_TYPE))}),
						HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
								new String[]{sample.get(SampleKey.GRANULATION)}),
						HtmlFactory.createCellAsString(textFormatter,"Normal", "left",
								new String[]{sample.get(SampleKey.COLOR), UtilityPrinter.printLineBreak(),
										sample.get(SampleKey.SMELL), UtilityPrinter.printLineBreak(),
										sample.get(SampleKey.SOIL_TYPE)}),
						HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
								new String[]{"-"}),
						HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
								new String[]{dataTable.get(ProbeKey.ID)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{new LineBreakTextFormatter().breakPerWord(sample.get(SampleKey.NOTE))})
				});

				linesPerPage++;

				addToTable(row);
			}
		}
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
