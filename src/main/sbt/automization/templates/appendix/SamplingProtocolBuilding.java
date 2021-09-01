package sbt.automization.templates.appendix;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.html.HtmlFactory;
import sbt.automization.util.DatatableFilter;

import java.util.List;

public class SamplingProtocolBuilding extends Appendix
{
	private static SamplingProtocolBuilding instance;

	private SamplingProtocolBuilding() {}

	public static SamplingProtocolBuilding getInstance()
	{
		if (instance == null)
		{
			synchronized (SamplingProtocolBuilding.class)
			{
				if (instance == null)
				{
					instance = new SamplingProtocolBuilding();
				}
			}
		}
		return instance;
	}

	private void createHeadTable(DataTable probe)
	{
		String firstRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader", "text-align:center",1, 4,
						new String[]{"Probenahmeprotokoll"})
		});

		String secondRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader","width:150px",
						new String[]{"Datum"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal", "width:150px",
						new String[]{probe.get(ProbeKey.DATE)}),
				HtmlFactory.createCellAsString("NormalHeader", "width:150px",
						new String[]{"Probe-Nr."}),
				HtmlFactory.createCellAsString(textFormatter,"Normal", "width:150px",
						new String[]{probe.get(ProbeKey.PROBE_NUMBER)}),
		});

		String thirdRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Erkundungsstelle"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal",
						new String[]{probe.get(ProbeKey.NUMBER)}),
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Gebäude"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal",
						new String[]{probe.get(ProbeKey.BUILDING)}),
		});

		String fourthRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Bauteil"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal",
						new String[]{probe.get(ProbeKey.COMPONENT)}),
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Etage"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal",
						new String[]{probe.get(ProbeKey.FLOOR)}),
		});

		String fifthRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Probencharakter"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal",
						new String[]{probe.get(ProbeKey.CHARACTER)}),
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Raum"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal",
						new String[]{probe.get(ProbeKey.ROOM)}),
		});

		String sixthRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Geruch"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal",
						new String[]{probe.get(ProbeKey.SMELL)}),
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Entnahme"}),
				HtmlFactory.createCellAsString(textFormatter,"Normal",
						new String[]{probe.get(ProbeKey.EXTRACTION)}),
		});

		createTable();
		String content = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow)
				.append(fourthRow)
				.append(fifthRow)
				.append(sixthRow)
				.toString();
		addToTable(content);

		addToTemplate(table.appendTag());
	}


	@Override
	protected String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:80px",
						new String[]{"Aufbau"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:80px",
						new String[]{"Material"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:80px",
						new String[]{"Farbe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:80px",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:50px",
						new String[]{"Probe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:80px",
						new String[]{"Chemie"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:80px",
						new String[]{"Umfang"})
		});

		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow);

		return stringBuilder.toString();
	}

	@Override
	public String getExportFileName()
	{
		return "GEBÄUDE-PN-Anlage";
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		List<DataTable> probesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(dataTables, Outcrop.BUILDING);

		for (DataTable dataTable : probesWhichIncludeOutcrop)
		{
			buildTable(dataTable);
		}
	}

	private void buildTable(DataTable dataTable)
	{
		createHeadTable(dataTable);

		createTableWithHeader();

		for (Sample sample : dataTable.getSamplesBy(SampleKey.OUTCROP, Outcrop.HEAP.toString()))
		{
//				String row = HtmlFactory.createRowAsString("Normal", new String[]{
//						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
//								new String[]{"P".concat(String.valueOf(++ lines))}),
//						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
//								new String[]{new SampleTypeTextFormatter().format(sample.get(SampleKey.CONTAINER))}),
//						HtmlFactory.createCellAsString(textFormatter, "Normal",
//								new String[]{sample.get(SampleKey.CONTAINER)}),
//						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
//								new String[]{sample.get(SampleKey.VOLUME)}),
//						HtmlFactory.createCellAsString(textFormatter, "Normal",
//								new String[]{new LineBreakTextFormatter().format(sample.get(SampleKey.WASTE_TYPE))}),
//						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
//								new String[]{sample.get(SampleKey.GRANULATION)}),
//						HtmlFactory.createCellAsString(textFormatter, "Normal", "left",
//								new String[]{sample.get(SampleKey.COLOR), UtilityPrinter.printLineBreak(),
//										sample.get(SampleKey.SMELL), UtilityPrinter.printLineBreak(),
//										sample.get(SampleKey.SOIL_TYPE)}),
//						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
//								new String[]{"-"}),
//						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
//								new String[]{dataTable.get(ProbeKey.ID)}),
//						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
//								new String[]{new LineBreakTextFormatter().breakPerWord(sample.get(SampleKey.NOTE))})
//				});
//				addToTable(row);
		}
		addPageBreak();

		addToTemplate(table.appendTag());

		addPageBreak();
		addPageBreak();
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
