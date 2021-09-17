package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.GapProvider;

import java.util.Collection;
import java.util.List;

public final class Gap extends Report
{
	private static Gap instance;
	private final GapProvider provider;

	private Gap()
	{
		super(Outcrop.GAP);
		provider = new GapProvider();
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
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);
		addToTable(provider.createChemistryIDRow(dataTables));
		addToTable(provider.createChemieMufvRow(dataTables));
		addToTable(provider.createChemieAVVRow(dataTables));
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

		addToTable(provider.createIDRow(dataTables));
		addToTable(provider.createSuperstructureExposureRow(dataTables));

		constructEnvironmentTechnicalFeatures(dataTables);
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
