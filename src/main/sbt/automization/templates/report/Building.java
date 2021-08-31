package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.BuildingProvider;

import java.util.Collection;
import java.util.List;

public final class Building extends Report
{
	private static Building instance;
	private final BuildingProvider provider;

	private Building()
	{
		super(Outcrop.BUILDING);
		provider = new BuildingProvider();
	}

	public static Building getInstance()
	{
		if (instance == null)
		{
			synchronized (Building.class)
			{
				if (instance == null)
				{
					instance = new Building();
				}
			}
		}
		return instance;
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
		constructEnvironmentTechnicalFeatures(dataTables);
		//addToTable(provider.createLegendRow(dataTables));
	}

	@Override
	public String getExportFileName()
	{
		return "GEBÄUDE-Report";
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{

	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addToTable(provider.createChemieIDRow(dataTables));
		//addToTable(schadstoffverdacht);
		addToTable(provider.createChemistryPak(dataTables));
		addToTable(provider.createChemistryPak(dataTables)); // PCB
		addToTable(provider.createChemistryPak(dataTables)); // ASBEST
		addToTable(provider.createChemistryPak(dataTables)); // KMF
		addToTable(provider.createChemieLagaBoRow(dataTables));
		addToTable(provider.createChemieLagaRcRow(dataTables));
		addToTable(provider.createChemieLagaRcOrientationRow(dataTables));
		addToTable(provider.createChemieTlRockRow(dataTables));
		addToTable(provider.createChemieMufvRow(dataTables)); // Einstufung
		addToTable(provider.createChemieMufvRow(dataTables)); // Parameter
		addToTable(provider.createChemieAVVRow(dataTables));  // Material
		addToTable(provider.createChemieAVVRow(dataTables));  // gemischt
	}
}
