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

public final class BaseCourseWithoutBinder extends Report
{
	private static BaseCourseWithoutBinder instance;
	private final RowFactory provider;
	
	private BaseCourseWithoutBinder()
	{
		super(Outcrop.TOB);
		provider = new RowFactory(Outcrop.TOB);
	}
	
	public static BaseCourseWithoutBinder getInstance()
	{
		if (instance == null)
		{
			synchronized (BaseCourseWithoutBinder.class)
			{
				if (instance == null)
				{
					instance = new BaseCourseWithoutBinder();
				}
			}
		}
		return instance;
	}
	
	@Override
	public String getExportFileName()
	{
		return "TOB-Report";
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
		addToTable(provider.getRow(header.createCell(new String[]{"Aufbruch"}), new BaseCourseExposureRetrieval()));
		
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
		
		addLegendRow(dataTables);
	}
	
	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
		
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"E<sub>Vdyn</sub>,"}, "MN/m²"), new EvDynRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"E<sub>Vdyn (-15%)</sub>,"}, "MN/m²"), new EvDyn85Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"E<sub>V2</sub><sup>[41]</sup>,"}, "MN" +
				"/m²"), new Ev2WithEv85Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Soll Wert,"}, "E<sub>V2</sub>"), new EvMinimumBorderRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Dicke,"}, "cm"), new SizeRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Gesamtdicke Oberbau,"}, "cm"), new SizeTotalOBRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Material"}), new MaterialTobRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Korngrößenverteilung,"}, "Kornanteil < 0,063 mm"), new GrainSizeDistributionRetrieval()));
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
		HtmlCell chemistryMufv0823Header = header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"},
				"Schreiben des MKUEM<sup>[18]</sup>" + UtilityPrinter.printLineBreak() + "ab 01.08.2023");
		addToTable(provider.getRowWithDataCheck(chemistryMufv0823Header, new ChemistryMkuemRetrieval()));
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
						.appendContent("Für die angegebenen Tiefen [] gilt die Einheit cm. ")
						.appendContent("Gem. a. G. = Gemisch aus Gesteinskörnungen, NS = Naturstein, LS = Lavaschlacke, HO = Hochofenschlacke,")
						.appendContent("RC = Rezyklierte Gesteinskörnung, BK = Brechkorn, RK = Rundkorn, sg = stetig gestuft, ug = unstetig gestuft")
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
