package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.OhProvider;

import java.util.Collection;
import java.util.List;

public final class Topsoil extends Report
{
	private static Topsoil instance;
	private final OhProvider provider;

	private Topsoil()
	{
		super(Outcrop.OH);
		provider = new OhProvider();
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

		addToTable(provider.createIDRow(dataTables));
		addToTable(provider.createGroundExposureRow(dataTables));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.createDIN18196Row(dataTables));
		addToTable(provider.createDIN18915Row(dataTables));
		addToTable(provider.createDIN18320Row(dataTables));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.createChemistryIDRow(dataTables));
		addToTable(provider.createChemieLagaBoRow(dataTables));
		addToTable(provider.createChemieDepvRow(dataTables));
		addToTable(provider.createChemieDecisionSupportRow(dataTables));
		addToTable(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
