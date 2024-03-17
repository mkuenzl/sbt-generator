package sbt.automization.core.templates.report;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.html.HtmlRow;
import sbt.automization.core.styles.StyleParameter;
import sbt.automization.core.templates.construction.RowFactory;
import sbt.automization.core.retrieval.*;
import sbt.automization.core.templates.construction.strategies.CellPerProbe;

import java.util.Collection;
import java.util.List;

public final class TopsoilReport
		extends AbstractReport
{
	private static TopsoilReport instance;
	private final RowFactory provider;
	
	private TopsoilReport()
	{
		super(Outcrop.OH);
		provider = new RowFactory(Outcrop.OH);
	}
	
	public static TopsoilReport getInstance()
	{
		if (instance == null)
		{
			synchronized (TopsoilReport.class)
			{
				if (instance == null)
				{
					instance = new TopsoilReport();
				}
			}
		}
		return instance;
	}
	
	@Override
	public String getExportFileName()
	{
		return "OH-Report";
	}
	
	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		Collection<List<DataTable>> tablesSplitIntoPortions = splitIntoPortionPerPage(dataTables);
		for (List<DataTable> portion : tablesSplitIntoPortions)
		{
			buildTable(portion);
			addTable();
			addPageBreak();
		}
	}
	
	private void buildTable(List<DataTable> dataTables)
	{
		createTable();
		provider.setDataTables(dataTables);
		provider.setCellStrategy(new CellPerProbe());
		
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}), new IdRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Aufschlussart"}), new GroundExposureRetrieval()));
		
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
		addLegendRow(dataTables);
	}
	
	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
		
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodengruppe,"}, "DIN 18196<sup>[22]</sup>"), new DIN18196Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodengruppe,"}, "DIN 18915<sup>[37]</sup>"), new DIN18915Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Homogenbereich,"}, "DIN 18320:2019-09<sup>[34]</sup>"), new DIN18300_09Retrieval()));

	}
	
	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);
		
		HtmlCell chemistryIdHeader = header.createCell(new String[]{"Laborprobe"});
		addToTable(provider.getRowWithDataCheck(chemistryIdHeader, new ChemistryIdRetrieval()));
		// updated 01.07.2023
		HtmlCell chemistryMufvHeader = header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"},
				"Schreiben des MUFV<sup>[18]</sup>"  + UtilityPrinter.printLineBreak() + "bis 31.07.2023");
		addToTable(provider.getRowWithDataCheck(chemistryMufvHeader, new ChemistryMufvRetrieval()));
		// added 01.07.2023
		HtmlCell chemistryMkuemHeader = header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"},
				"Schreiben des MKUEM<sup>[18]</sup>" + UtilityPrinter.printLineBreak() + "ab 01.08.2023");
		addToTable(provider.getRowWithDataCheck(chemistryMkuemHeader, new ChemistryMkuemRetrieval()));
		HtmlCell chemistryLfsHeader = header.createCell(new String[]{"Vollzugshinweise,"}, "LFS");
		addToTable(provider.getRowWithDataCheck(chemistryLfsHeader, new ChemistryLfsRetrieval()));
		// added 01.07.2023
		HtmlCell chemistryEbvSoilHeader = header.createCell(new String[]{"Materialklasse,"}, "EBV Boden<sup>[50]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryEbvSoilHeader, new ChemistryEbvSoil()));
		HtmlCell chemistryEbvConstructionWasteHeader = header.createCell(new String[]{"Materialklasse,"}, "EBV " +
				"Bauschutt<sup>[50]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryEbvConstructionWasteHeader, new ChemistryEbvConstructionWaste()));
		HtmlCell chemistryEbvMonitoringValue = header.createCell(new String[]{"Überwachungswert,"}, "EBV Bauschutt" +
				"<sup>[50]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryEbvMonitoringValue, new ChemistryEbvMonitoringValue()));
		
		HtmlCell chemistryLagaBoHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Boden<sup>[11]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaBoHeader, new ChemistryLagaBoRetrieval()));
		HtmlCell chemistryLagaRcHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Bauschutt<sup>[28]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRcHeader, new ChemistryLagaRcRetrieval()));
		HtmlCell chemistryLagaRc = header.createCell(new String[]{"Orientierungswert,"}, "LAGA Bauschutt<sup>[28]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRc, new ChemistryLagaRcOrientationRetrieval()));
		HtmlCell chemistryTlRockHeader = header.createCell(new String[]{"Verwertungsklasse,"}, "TL Gestein<sup>[27]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryTlRockHeader, new ChemistryTlRockRetrieval()));
		HtmlCell chemistryRekuHeader = header.createCell(new String[]{"Rekultivierung,"}, "Reku<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryRekuHeader, new ChemistryRekuRetrieval()));
		HtmlCell chemistryDepvHeader = header.createCell(new String[]{"Deponieklasse,"}, "DepV<sup>[15]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDepvHeader, new ChemistryDepvRetrieval()));
		HtmlCell chemistryDecisionHeader = header.createCell(new String[]{"Entscheidungshilfe,"}, "DepV<sup>[17]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDecisionHeader, new ChemistryDecisionSupportRetrieval()));
		HtmlCell chemistryWasteKeyHeader = header.createCell(new String[]{"Abfallschlüssel,"}, "AVV<sup>[14]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryWasteKeyHeader, new ChemistryAvvRetrieval()));
	}
	
	@Override
	protected void addLegendRow(List<DataTable> dataTables)
	{
		StyleParameter styleParameter = getStyleParameter();
		double size = styleParameter.getHeaderCellWidthAsDouble() + dataTables.size() * styleParameter.getNormalCellWidthAsDouble();
		
		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegend = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", styleParameter.getLegendCellClass())
						.appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Anmerkungen:")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Für die angegebenen Tiefen [] gilt die Einheit cm.")
						.build()
						.appendTag())
				.build();
		
		addToTable(rowLegend.appendTag());
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
