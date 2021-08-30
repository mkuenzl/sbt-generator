package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.ReportStyle;
import sbt.automization.templates.helper.HeapProvider;

import java.util.Collection;
import java.util.List;

public final class Heap extends Report
{
	private static Heap instance;
	private final HeapProvider provider;

	private Heap()
	{
		super(Outcrop.HEAP);
		provider = new HeapProvider();
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

		addToTable(provider.createIDRow(dataTables));
		addToTable(provider.createAreaRow(dataTables));
		addToTable(provider.createOutcropRow(dataTables));
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
		//addToTable(provider.createLegendRow(dataTables));
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

		addToTable(provider.createMaterialRow(dataTables));
		addToTable(provider.createDIN18300Row(dataTables));
		addToTable(provider.createDIN18196Row(dataTables));
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

		addToTable(provider.createChemieIDRow(dataTables));
		addToTable(provider.createChemistryPak(dataTables));
		addToTable(provider.createChemieMufvRow(dataTables));
		addToTable(provider.createChemieLagaBoRow(dataTables));
		addToTable(provider.createChemieLagaRcRow(dataTables));
		addToTable(provider.createChemieLagaRcOrientationRow(dataTables));
		addToTable(provider.createChemieTlRockRow(dataTables));
		addToTable(provider.createChemistryReku(dataTables));
		addToTable(provider.createChemieDepvRow(dataTables));
		addToTable(provider.createChemieDecisionSupportRow(dataTables));
		addToTable(provider.createChemistryRuva(dataTables));
		addToTable(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
