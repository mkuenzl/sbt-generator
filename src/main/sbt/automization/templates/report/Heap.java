package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.ReportStyle;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

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

		addToTable(provider.getRowWithSamples(new IdRow()));
		addToTable(provider.getRowWithSamples(new AreaRow()));
		addToTable(provider.getRowWithSamples(new HeapExposureRow()));
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

		addToTable(provider.getRowWithSamples(new MaterialHeapRow()));
		addToTable(provider.getRowWithSamples(new DIN18300Row()));
		addToTable(provider.getRowWithSamples(new DIN18196Row()));
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

		addToTable(provider.getRowWithSamples(new ChemistryIdRow()));
		addToTable(provider.getRowWithSamples(new ChemistryPak()));
		addToTable(provider.getRowWithSamples(new ChemistryMufvRow()));
		addToTable(provider.getRowWithSamples(new ChemistryLagaBoRow()));
		addToTable(provider.getRowWithSamples(new ChemistryLagaRc()));
		addToTable(provider.getRowWithSamples(new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithSamples(new ChemistryTlRockRow()));
		addToTable(provider.getRowWithSamples(new ChemistryRekuRow()));
		addToTable(provider.getRowWithSamples(new ChemistryDepvRow()));
		addToTable(provider.getRowWithSamples(new ChemistryDecisionSupport()));
		addToTable(provider.getRowWithSamples(new ChemistryRuvaRow()));
		addToTable(provider.getRowWithSamples(new ChemistryAvvRow()));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
