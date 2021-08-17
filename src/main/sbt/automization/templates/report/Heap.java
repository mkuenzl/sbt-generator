package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.HeapProvider;

import java.util.Collection;
import java.util.List;

public final class Heap extends Report
{
	private static Heap instance;
	private final HeapProvider provider;

	private Heap() {
		setOutcrop(Outcrop.HEAP);
		provider = new HeapProvider();
	}

	@Override
	public String constructAndGetTableHeader()
	{
		return "";
	}

	@Override
	void buildTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetTechnicalHeader(dataTables);
		table.appendContent(provider.createMaterialRow(dataTables));
		table.appendContent(provider.createDIN18300Row(dataTables));
		table.appendContent(provider.createDIN18196Row(dataTables));
	}

	@Override
	void buildEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetEnvironmentTechnicalHeader(dataTables);
		table.appendContent(provider.createChemieIDRow(dataTables));
		table.appendContent(provider.createChemieMufvRow(dataTables));
		table.appendContent(provider.createChemieLagaBoRow(dataTables));
		table.appendContent(provider.createChemieLagaRcRow(dataTables));
		table.appendContent(provider.createChemieLagaRcOrientationRow(dataTables));
		table.appendContent(provider.createChemieTlRockRow(dataTables));
		table.appendContent(provider.createREKUROW(dataTables));
		table.appendContent(provider.createChemieDepvRow(dataTables));
		table.appendContent(provider.createChemieAVVRow(dataTables));
		table.appendContent(provider.createChemieDecisionSupportRow(dataTables));
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
		Collection<List<DataTable>> tablesSplitIntoPortions = splitGroupOf(dataTables);
		for (List<DataTable> portion : tablesSplitIntoPortions)
		{
			buildTable(portion);
			addToTemplate(table.appendTag());
			addToTemplate("<br>");
		}
	}

	private void buildTable(List<DataTable> dataTables)
	{
		table = constructAndGetTableObject();

		table.appendContent(provider.createIDRow(dataTables));
		table.appendContent(provider.createOutcropRow(dataTables));
		buildTechnicalFeatures(dataTables);
		buildEnvironmentTechnicalFeatures(dataTables);
		table.appendContent(provider.createLegendRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
