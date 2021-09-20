package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class Gap extends Report
{
	private static Gap instance;
	private final RowProvider rowProvider;

	private Gap()
	{
		super(Outcrop.GAP);
		rowProvider = new RowProvider(Outcrop.GAP);
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
		addToTable(rowProvider.getRowWithProbes(new ChemistryIdRow()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryMufvRow()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryLagaRc()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryLagaRcOrientation()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryTlRockRow()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryDepvRow()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryAvvRow()));
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
		rowProvider.setDataTables(dataTables);

		addToTable(rowProvider.getRowWithProbes(new IdRow()));
		addToTable(rowProvider.getRowWithProbes(new SuperstructureExposureRow()));

		constructEnvironmentTechnicalFeatures(dataTables);
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
