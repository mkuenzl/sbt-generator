package sbt.automization.core.templates.report;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.html.HtmlRow;
import sbt.automization.core.styles.ReportStyle;
import sbt.automization.core.templates.construction.RowFactory;
import sbt.automization.core.retrieval.*;
import sbt.automization.core.templates.construction.strategies.CellPerSample;

import java.util.Collection;
import java.util.List;

public final class Heap extends Report
{
	private static Heap instance;
	private final RowFactory provider;
	
	private Heap()
	{
		super(Outcrop.HEAP);
		provider = new RowFactory(Outcrop.HEAP);
	}
	
	public static Heap getInstance()
	{
		if (instance == null)
		{
			synchronized (Heap.class)
			{
				if (instance == null)
				{
					instance = new Heap();
				}
			}
		}
		return instance;
	}
	
	@Override
	public String getExportFileName()
	{
		return "HAUFWERK-Report";
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
		provider.setCellStrategy(new CellPerSample());
		
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}), new IdRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Bereich"}), new AreaRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Probenahmeverfahren"}), new HeapExposureRetrieval()));
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
		//addToTable(provider.getRowWithSamples(new LegendWithDepthRow()));
	}
	
	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
		
		addToTable(provider.getRow(header.createCell(new String[]{"Material"}), new MaterialHeapRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodenklasse,"}, "DIN 18300<sup>[11]</sup>"), new DIN18300Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodengruppe,"}, "DIN 18196<sup>[10]</sup>"), new DIN18196Retrieval()));
	}
	
	@Override
	void addTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = 1;
		
		for (DataTable dataTable : dataTables)
		{
			colspan += dataTable.getSamples().size();
		}
		
		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Technische Merkmale"})
		});
		
		addToTable(row.appendTag());
	}
	
	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);
		
		provider.setCellStrategy(new CellPerSample());
		HtmlCell chemistryIdHeader = header.createCell(new String[]{"Laborprobe"});
		addToTable(provider.getRowWithDataCheck(chemistryIdHeader, new ChemistryIdRetrieval()));
		HtmlCell chemistryPakHeader = header.createCell(new String[]{"PAK,"}, "mg/kg");
		addToTable(provider.getRowWithDataCheck(chemistryPakHeader, new ChemistryPakRetrieval()));
		// updated 01.07.2023
		HtmlCell chemistryMufvHeader = header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"},
				"Schreiben des MUFV<sup>[18]</sup>"  + UtilityPrinter.printLineBreak() + "bis 31.07.2023");
		addToTable(provider.getRowWithDataCheck(chemistryMufvHeader, new ChemistryMufvRetrieval()));
		
		// added 01.07.2023
		HtmlCell chemistryMkuemHeader = header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"},
				"Schreiben des MKUEM<sup>[9]</sup>" + UtilityPrinter.printLineBreak() + "ab 01.08.2023");
		addToTable(provider.getRowWithDataCheck(chemistryMkuemHeader, new ChemistryMkuemRetrieval()));
		
		HtmlCell chemistryLfsHeader = header.createCell(new String[]{"Vollzugshinweise,"}, "LFS");
		addToTable(provider.getRowWithDataCheck(chemistryLfsHeader, new ChemistryLfsRetrieval()));
		
		// added 01.07.2023
		HtmlCell chemistryEbvSoilHeader = header.createCell(new String[]{"Materialklasse,"}, "EBV Boden<sup>[2]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryEbvSoilHeader, new ChemistryEbvSoil()));
		HtmlCell chemistryEbvConstructionWasteHeader = header.createCell(new String[]{"Materialklasse,"}, "EBV " +
				"Bauschutt<sup>[2]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryEbvConstructionWasteHeader, new ChemistryEbvConstructionWaste()));
		HtmlCell chemistryEbvMonitoringValue = header.createCell(new String[]{"Überwachungswert,"}, "EBV Bauschutt" +
				"<sup>[2]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryEbvMonitoringValue, new ChemistryEbvMonitoringValue()));
		
		
		HtmlCell chemistryLagaBoHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Boden<sup>[2]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaBoHeader, new ChemistryLagaBoRetrieval()));
		HtmlCell chemistryLagaRcHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Bauschutt<sup>[16]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRcHeader, new ChemistryLagaRcRetrieval()));
		HtmlCell chemistryLagaRc = header.createCell(new String[]{"Orientierungswert,"}, "LAGA Bauschutt<sup>[16]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRc, new ChemistryLagaRcOrientationRetrieval()));
		HtmlCell chemistryTlRockHeader = header.createCell(new String[]{"Verwertungsklasse,"}, "TL Gestein<sup>[15]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryTlRockHeader, new ChemistryTlRockRetrieval()));
		HtmlCell chemistryRekuHeader = header.createCell(new String[]{"Rekultivierung,"}, "Reku<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryRekuHeader, new ChemistryRekuRetrieval()));
		HtmlCell chemistryDepvHeader = header.createCell(new String[]{"Deponieklasse,"}, "DepV<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDepvHeader, new ChemistryDepvRetrieval()));
		HtmlCell chemistryDecisionHeader = header.createCell(new String[]{"Entscheidungshilfe,"}, "DepV<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDecisionHeader, new ChemistryDecisionSupportRetrieval()));
		HtmlCell chemistryRuvaHeader = header.createCell(new String[]{"Verwertungsklasse,"}, "RuVa-StB<sup>[1]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryRuvaHeader, new ChemistryRuvaRetrieval()));
		HtmlCell chemistryWasteKeyHeader = header.createCell(new String[]{"Abfallschlüssel,"}, "AVV<sup>[6]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryWasteKeyHeader, new ChemistryAvvRetrieval()));
	}
	
	@Override
	protected void addLegendRow(List<DataTable> dataTables)
	{
	
	}
	
	@Override
	void addEnvironmentTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = 1;
		
		for (DataTable dataTable : dataTables)
		{
			colspan += dataTable.getSamples().size();
		}
		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Umwelttechnische Merkmale"})
		});
		
		addToTable(row.appendTag());
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
