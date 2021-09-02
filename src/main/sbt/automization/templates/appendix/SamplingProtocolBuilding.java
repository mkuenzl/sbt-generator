package sbt.automization.templates.appendix;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.format.text.DepthTextFormatter;
import sbt.automization.html.HtmlFactory;
import sbt.automization.styles.BuildingStyle;
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

	@Override
	protected String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN.getStyleClass(), new String[]{
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:2cm",
						new String[]{"Aufbau"}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:3.6cm",
						new String[]{"Material"}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:2cm",
						new String[]{"Farbe"}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:1.5cm",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:1.5cm",
						new String[]{"Probe"}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:1.5cm",
						new String[]{"Chemie"}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:3cm",
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

		for (Sample sample : dataTable.getSamplesBy(SampleKey.OUTCROP, Outcrop.BUILDING.toString()))
		{
			String row = HtmlFactory.createRowAsString("Normal", new String[]{
					HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "font-size:10.0pt",
							new String[]{"Querschnitt", UtilityPrinter.printLineBreak(), String.valueOf(++ linesPerPage)}),
					HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
							new String[]{sample.get(SampleKey.MATERIAL)}),
					HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "font-size:10.0pt",
							new String[]{sample.get(SampleKey.COLOR)}),
					HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
							new String[]{new DepthTextFormatter(false).format(sample.get(SampleKey.DEPTH_START), sample.get(SampleKey.DEPTH_END))}),
					HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "font-size:10.0pt",
							new String[]{"P".concat(String.valueOf(++ lines))}),
					HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "font-size:10.0pt",
							new String[]{sample.get(SampleKey.CHEMISTRY_ID)}),
					HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "font-size:10.0pt",
							new String[]{sample.get(SampleKey.SUSPECTED_POLLUTANT)})
			});
			addToTable(row);
		}

		linesPerPage = 0;

		addTotalDepthRow(dataTable);
		addToTemplate(table.appendTag());

		addPageBreak();
		addPageBreak();
	}

	private void createHeadTable(DataTable probe)
	{
		String firstRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createHeader(BuildingStyle.HEAD.getStyleClass(), BuildingStyle.HEAD.getStyle(), 1, 4,
						new String[]{"Probenahmeprotokoll"})
		});

		String secondRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN.getStyleClass(), new String[]{
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:4cm",
						new String[]{"Datum"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(), "width:4cm",
						new String[]{probe.get(ProbeKey.DATE)}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(), "width:4cm",
						new String[]{"Probe-Nr."}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(), "width:4cm",
						new String[]{probe.get(ProbeKey.PROBE_NUMBER)}),
		});

		String thirdRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN.getStyleClass(), new String[]{
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Erkundungsstelle"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.NUMBER)}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Gebäude"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.BUILDING)}),
		});

		String fourthRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN.getStyleClass(), new String[]{
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Bauteil"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.COMPONENT)}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Etage"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.FLOOR)}),
		});

		String fifthRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN.getStyleClass(), new String[]{
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Probencharakter"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.CHARACTER)}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Raum"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.ROOM)}),
		});

		String sixthRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN.getStyleClass(), new String[]{
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Geruch"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.SMELL)}),
				HtmlFactory.createHeader(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Entnahme"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
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
		addPageBreak();
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}

	private void addTotalDepthRow(DataTable dataTable)
	{
		String depthRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString(textFormatter, "NormalBold", "font-size:10.0pt", 1, 3,
						new String[]{"Gesamttiefe von Bauteiloberfläche (BOF):"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(), 1, 4,
						new String[]{new SamplePrinter().printThickness(dataTable.getSamples())})
		});

		addToTable(depthRow);
	}
}
