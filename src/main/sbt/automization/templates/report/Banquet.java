package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class Banquet extends Report
{
	private static Banquet instance;
	private final RowProvider rowProvider;

	private Banquet()
	{
		super(Outcrop.BANQUET);
		rowProvider = new RowProvider(Outcrop.BANQUET);
	}

	public static Banquet getInstance()
	{
		if (instance == null)
		{
			synchronized (Banquet.class)
			{
				if (instance == null)
				{
					instance = new Banquet();
				}
			}
		}
		return instance;
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
		addToTable(rowProvider.getRowWithProbes(new DIN18196Row()));
		addToTable(rowProvider.getRowWithProbes(new DIN18915Row()));
		addToTable(rowProvider.getRowWithProbes(new DIN18320Row()));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(rowProvider.getRowWithProbes(new ChemistryIdRow()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryLagaBoRow()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryDepvRow()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryDecisionSupport()));
		addToTable(rowProvider.getRowWithProbes(new ChemistryAvvRow()));
	}

	@Override
	public String getExportFileName()
	{
		return "BANKETT-Report";
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
		addToTable(rowProvider.getRowWithProbes(new GroundExposureRow()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
