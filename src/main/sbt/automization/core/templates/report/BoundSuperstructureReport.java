package sbt.automization.core.templates.report;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.retrieval.*;
import sbt.automization.core.templates.construction.RowFactory;
import sbt.automization.core.templates.construction.strategies.CellPerProbe;
import sbt.automization.core.templates.report.tableparts.CrossSectionWithPitchBefore08Report;
import sbt.automization.core.templates.report.tableparts.CrossSectionWithoutPitchBefore08Report;

import java.util.Collection;
import java.util.List;

/**
 * Represent the Table Data Structure for the "GOB-Report"
 */
public final class BoundSuperstructureReport
		extends AbstractReport
{
	private static BoundSuperstructureReport instance;
	private final RowFactory provider;
	
	private BoundSuperstructureReport()
	{
		super(Outcrop.GOB);
		provider = new RowFactory(Outcrop.GOB);
	}
	
	public static BoundSuperstructureReport getInstance()
	{
		if (instance == null)
		{
			synchronized (BoundSuperstructureReport.class)
			{
				if (instance == null)
				{
					instance = new BoundSuperstructureReport();
				}
			}
		}
		return instance;
	}
	
	@Override
	public String getExportFileName()
	{
		return "GOB-Report";
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
		addToTable(provider.getRow(header.createCell(new String[]{"Aufbruch"}), new SuperstructureExposureRetrieval()));
		
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		CrossSectionWithoutPitchBefore08Report crossSectionWithoutPitchBefore08Report = new CrossSectionWithoutPitchBefore08Report();
		crossSectionWithoutPitchBefore08Report.constructTemplate(dataTables);
		addToTable(crossSectionWithoutPitchBefore08Report.getTemplate());

		CrossSectionWithPitchBefore08Report crossSectionWithPitchBefore08Report = new CrossSectionWithPitchBefore08Report();
		crossSectionWithPitchBefore08Report.constructTemplate(dataTables);
		addToTable(crossSectionWithPitchBefore08Report.getTemplate());
	}
	
	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
		
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Gesamtdicke geb. Oberbau,"}, "cm"), new SizeTotalGOBRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Belastungsklasse,"}, "RStO<sup>[5]</sup>"), new LoadClassRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erweichungspunkt RuK<sup>[31]</sup>,"}, "°C"), new RuKCombinedRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Soll Einzelwert,"}, "RuK"), new RuKSingleValueRetrieval()));
	}
	
	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);
		
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis qualitativ"}), new PitchQualitativeRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis halbquantitativ"}), new PitchHalfQuantitativeRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis quantitativ"}), new PitchQuantitativeRetrieval()));
	}
	
	@Override
	protected void addLegendRow(List<DataTable> dataTables)
	{
	
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
