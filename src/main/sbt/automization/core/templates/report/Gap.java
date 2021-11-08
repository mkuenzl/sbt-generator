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

public final class Gap extends Report
{
	private static Gap instance;
	private final RowFactory provider;
	
	private Gap()
	{
		super(Outcrop.GAP);
		provider = new RowFactory(Outcrop.GAP);
	}
	
	public static Gap getInstance()
	{
		if (instance == null)
		{
			synchronized (Gap.class)
			{
				if (instance == null)
				{
					instance = new Gap();
				}
			}
		}
		return instance;
	}
	
	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
	}
	
	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);
		HtmlCell chemistryIdHeader = header.createCell(new String[]{"Laborprobe"});
		addToTable(provider.getRowWithDataCheck(chemistryIdHeader, new ChemistryIdRetrieval()));
		HtmlCell chemistryMufvHeader = header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"}, "Schreiben des MUFV<sup>[18]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryMufvHeader, new ChemistryMufvRetrieval()));
		HtmlCell chemistryLfsHeader = header.createCell(new String[]{"Vollzugshinweise,"}, "LFS");
		addToTable(provider.getRowWithDataCheck(chemistryLfsHeader, new ChemistryLfsRetrieval()));
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
	public String getExportFileName()
	{
		return "FUGE-Report";
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
		addToTable(provider.getRow(header.createCell(new String[]{"Aufschlussart"}), new SuperstructureExposureRetrieval()));
		
		constructEnvironmentTechnicalFeatures(dataTables);
		addLegendRow(dataTables);
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
