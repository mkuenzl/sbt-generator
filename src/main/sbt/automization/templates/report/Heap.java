package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
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
		addToTable(provider.createOutcropRow(dataTables));
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
		addToTable(provider.createLegendRow(dataTables));
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
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.createChemieIDRow(dataTables));
		addToTable(provider.createChemieMufvRow(dataTables));
		addToTable(provider.createChemieLagaBoRow(dataTables));
		addToTable(provider.createChemieLagaRcRow(dataTables));
		addToTable(provider.createChemieLagaRcOrientationRow(dataTables));
		addToTable(provider.createChemieTlRockRow(dataTables));
		addToTable(provider.createREKUROW(dataTables));
		addToTable(provider.createChemieDepvRow(dataTables));
		addToTable(provider.createChemieAVVRow(dataTables));
		addToTable(provider.createChemieDecisionSupportRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
