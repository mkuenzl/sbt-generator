package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class Concrete extends Report
{

	private static Concrete instance;
	private final RowProvider rowProvider;

	private Concrete()
	{
		super(Outcrop.CONCRETE);
		rowProvider = new RowProvider(Outcrop.CONCRETE);
	}

	public static Concrete getInstance()
	{
		if (instance == null)
		{
			synchronized (Concrete.class)
			{
				if (instance == null)
				{
					instance = new Concrete();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "BETON-Report";
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

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addToTable(rowProvider.getRowWithProbes(new LegendWithDepthRow()));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(rowProvider.getRowWithProbes(new MaterialRow()));
		addToTable(rowProvider.getRowWithProbes(new CompressiveStrengthRow()));
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
	public void constructTemplate(DataTable dataTable)
	{

	}
}
