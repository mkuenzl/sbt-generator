package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class Topsoil extends Report
{
	private static Topsoil instance;
	private final RowProvider rowProvider;

	private Topsoil()
	{
		super(Outcrop.OH);
		rowProvider = new RowProvider(Outcrop.OH);
	}

	public static Topsoil getInstance()
	{
		if (instance == null)
		{
			synchronized (Topsoil.class)
			{
				if (instance == null)
				{
					instance = new Topsoil();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "OH-Report";
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
