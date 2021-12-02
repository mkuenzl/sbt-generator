package sbt.automization.core.templates.appendix;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.html.HtmlRow;
import sbt.automization.core.styles.BuildingStyle;
import sbt.automization.core.util.DatatableFilter;

import java.util.List;

public final class SamplingProtocolBuilding extends Appendix
{
	private static SamplingProtocolBuilding instance;
	
	private SamplingProtocolBuilding()
	{
	}
	
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
		String firstRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN_8.getStyleClass(), new String[]{
				HtmlFactory.createHeaderAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:1cm",
						new String[]{"QS"}),
				HtmlFactory.createHeaderAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:4.5cm",
						new String[]{"Material"}),
				HtmlFactory.createHeaderAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:2.5cm",
						new String[]{"Farbe"}),
				HtmlFactory.createHeaderAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:1.5cm",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeaderAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:1.5cm",
						new String[]{"Probe"}),
				HtmlFactory.createHeaderAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:1.5cm",
						new String[]{"Chemie"}),
				HtmlFactory.createHeaderAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:3.5cm",
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
		buildHeadTable(dataTable);
		
		createTableWithHeader();
		
		for (Sample sample : dataTable.getSamplesBy(SampleKey.OUTCROP, Outcrop.BUILDING.toString()))
		{
			String row = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN_8.getStyleClass(), new String[]{
					HtmlFactory.createCellAsString(textFormatter, "Normal", "font-size:10.0pt",
							new String[]{String.valueOf(++linesPerPage)}),
					HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
							new String[]{sample.get(SampleKey.MATERIAL)}),
					HtmlFactory.createCellAsString(textFormatter, "Normal", "font-size:10.0pt",
							new String[]{printSampleColor(sample)}),
					HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
							new String[]{printSampleDepth(sample)}),
					HtmlFactory.createCellAsString(textFormatter, "Normal", "font-size:10.0pt",
							new String[]{printSampleNumber(sample)}),
					HtmlFactory.createCellAsString(textFormatter, "Normal", "font-size:10.0pt",
							new String[]{sample.get(SampleKey.CHEMISTRY_ID)}),
					HtmlFactory.createCellAsString(textFormatter, "Normal", "font-size:10.0pt",
							new String[]{sample.get(SampleKey.SUSPECTED_POLLUTANT)})
			});
			addToTable(row);
		}
		
		linesPerPage = 0;
		
		addTotalDepthRow(dataTable);
		addToTemplate(table.appendTag());
		
		buildPhotoTable();
		
		addPageBreak();
	}
	
	private String printSampleDepth(Sample sample)
	{
		String depthStart = sample.get(SampleKey.DEPTH_START);
		String depthEnd = sample.get(SampleKey.DEPTH_END);
		
		if (depthStart.equals(depthEnd)) return "";
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String beginningSymbol = "0".equals(depthStart) ? "±" : "-";
		
		stringBuilder.append(beginningSymbol)
				.append(" ")
				.append(formatDepthValue(depthStart))
				.append(" ")
				.append("bis")
				.append(UtilityPrinter.printLineBreak())
				.append("-")
				.append(" ")
				.append(formatDepthValue(depthEnd))
				.append(" ")
				.append("cm");
		
		return stringBuilder.toString();
	}
	
	private String formatDepthValue(String value)
	{
		if (value.contains(",")) return value;
		else return value.concat(",0");
	}
	
	private String printSampleColor(Sample sample)
	{
		String colorsString = sample.get(SampleKey.COLOR);
		
		String[] colors = colorsString.split("(,|/)");
		
		if (colors.length == 1)
		{
			return colors[0];
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (String color : colors)
		{
			stringBuilder.append(color)
					.append(UtilityPrinter.printLineBreak());
		}
		
		return stringBuilder.toString();
	}
	
	
	private String printSampleNumber(Sample sample)
	{
		String extraction = sample.get(SampleKey.EXTRACTION);
		
		if ("JA".equalsIgnoreCase(extraction)) return "P".concat(String.valueOf(++lines));
		
		return "";
	}
	
	
	private void buildHeadTable(DataTable probe)
	{
		String firstRow = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createHeaderAsString(BuildingStyle.HEAD.getStyleClass(), BuildingStyle.HEAD.getStyle(), 1, 4,
						new String[]{"Probenahmeprotokoll"})
		});
		
		String secondRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN_6.getStyleClass(), new String[]{
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:4.0cm",
						new String[]{"Datum"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(), "width:4.0cm",
						new String[]{probe.get(ProbeKey.DATE)}),
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(), "width:4.0cm",
						new String[]{"Probe-Nr."}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(), "width:4.0cm",
						new String[]{probe.get(ProbeKey.PROBE_NUMBER)}),
		});
		
		String thirdRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN_6.getStyleClass(), new String[]{
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Erkundungsstelle"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.NUMBER)}),
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Gebäude"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.BUILDING)}),
		});
		
		String fourthRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN_6.getStyleClass(), new String[]{
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Bauteil"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.COMPONENT)}),
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Etage"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.FLOOR)}),
		});
		
		String fifthRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN_6.getStyleClass(), new String[]{
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Probencharakter"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.CHARACTER)}),
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Raum"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.ROOM)}),
		});
		
		String sixthRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN_6.getStyleClass(), new String[]{
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(),
						new String[]{"Geruch"}),
				HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(),
						new String[]{probe.get(ProbeKey.SMELL)}),
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(),
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
	}
	
	private void buildPhotoTable()
	{
		createTable();
		
		String firstRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_THIN_8.getStyleClass(), new String[]{
				HtmlFactory.createHeaderAsString(BuildingStyle.HEAD.getStyleClass(), BuildingStyle.HEAD.getStyle(), 1, 3,
						new String[]{"Fotodokumentation"})
		});
		addToTable(firstRow);
		
		String secondRow = HtmlFactory.createRowAsString(BuildingStyle.ROW_PHOTO.getStyleClass(), BuildingStyle.ROW_PHOTO.getStyle(), new String[]{
				HtmlFactory.createCellAsString(BuildingStyle.CELL_PHOTO.getStyleClass(), BuildingStyle.CELL_PHOTO.getStyle(),
						new String[]{""}),
				HtmlFactory.createCellAsString(BuildingStyle.CELL_PHOTO.getStyleClass(), BuildingStyle.CELL_PHOTO.getStyle(),
						new String[]{""}),
				HtmlFactory.createCellAsString(BuildingStyle.CELL_PHOTO.getStyleClass(), BuildingStyle.CELL_PHOTO.getStyle(),
						new String[]{""}),
		});
		addToTable(secondRow);
		
		addToTemplate(table.appendTag());
	}
	
	private void addTotalDepthRow(DataTable dataTable)
	{
		String thickness = new SamplePrinter().printThickness(dataTable.getSamples());
		
		HtmlRow depthRow = HtmlFactory.createRow(BuildingStyle.ROW_THIN_8.getStyleClass(), new String[]{
				HtmlFactory.createCellAsString(BuildingStyle.HEADER_CELL.getStyleClass(), 1, 3,
						new String[]{"Gesamttiefe von Bauteiloberfläche (BOF):"})});
		
		String printedThickness;
		if ("0".equals(thickness))
		{
			printedThickness = "-";
		} else
		{
			printedThickness = formatDepthValue(thickness).concat(" cm");
		}
		
		depthRow.appendContent(HtmlFactory.createCellAsString(textFormatter, BuildingStyle.CELL.getStyleClass(), 1, 4,
				new String[]{printedThickness}));
		
		addToTable(depthRow.appendTag());
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
