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
		setOutcrop(Outcrop.CONCRETE);
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
	public String constructAndGetTableHeader()
	{
		return null;
	}

	@Override
	void buildTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetTechnicalHeader(dataTables);
		table.appendContent(provider.createMaterialRow(dataTables));
		table.appendContent(provider.createCompressiveStrengthRow(dataTables));
	}

	@Override
	void buildEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetEnvironmentTechnicalHeader(dataTables);
		table.appendContent(provider.createChemieIDRow(dataTables));
		table.appendContent(provider.createChemieMufvRow(dataTables));
		table.appendContent(provider.createChemieLagaRcRow(dataTables));
		table.appendContent(provider.createChemieLagaRcOrientationRow(dataTables));
		table.appendContent(provider.createChemieTlRockRow(dataTables));
		table.appendContent(provider.createChemieDepvRow(dataTables));
		table.appendContent(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public String getExportFileName()
	{
		return "BETON-Report";
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
		table.appendContent(provider.createSuperstructureExposureRow(dataTables));
		buildTechnicalFeatures(dataTables);
		buildEnvironmentTechnicalFeatures(dataTables);
		table.appendContent(provider.createLegendRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
