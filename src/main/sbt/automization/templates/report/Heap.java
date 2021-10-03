package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.ReportStyle;
import sbt.automization.templates.helper.*;
import sbt.automization.templates.helper.information.*;

import java.util.Collection;
import java.util.List;

public final class Heap extends Report
{
	private static Heap instance;
	private final RowProvider provider;

	private Heap()
	{
		super(Outcrop.HEAP);
		provider = new RowProvider(Outcrop.HEAP);
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
		provider.setCellStrategy(new SampleCellStrategy());

		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new IdRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new AreaRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new HeapExposureRow()));
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
		//addToTable(provider.getRowWithSamples(new LegendWithDepthRow()));
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
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new MaterialHeapRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN18300Row()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN18196Row()));
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
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);
		provider.setCellStrategy(new SampleCellStrategy());

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryIdRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryPak()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryMufvRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaBoRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaRc()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryTlRockRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryRekuRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryDepvRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryDecisionSupport()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryRuvaRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryAvvRow()));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
