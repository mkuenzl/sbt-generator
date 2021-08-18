package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.ConcreteProvider;

import java.util.Collection;
import java.util.List;

public final class Concrete extends Report
{

	private static Concrete instance;
	private final ConcreteProvider provider;

	private Concrete()
	{
		super(Outcrop.CONCRETE);
		provider = new ConcreteProvider();
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

		addToTable(provider.createIDRow(dataTables));
		addToTable(provider.createSuperstructureExposureRow(dataTables));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addToTable(provider.createLegendRow(dataTables));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.createMaterialRow(dataTables));
		addToTable(provider.createCompressiveStrengthRow(dataTables));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.createChemieIDRow(dataTables));
		addToTable(provider.createChemieMufvRow(dataTables));
		addToTable(provider.createChemieLagaRcRow(dataTables));
		addToTable(provider.createChemieLagaRcOrientationRow(dataTables));
		addToTable(provider.createChemieTlRockRow(dataTables));
		addToTable(provider.createChemieDepvRow(dataTables));
		addToTable(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
