package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.ObProvider;

import java.util.Collection;
import java.util.List;

public final class BoundSuperstructure extends Report
{
	private static BoundSuperstructure instance;
	private final ObProvider provider;

	private BoundSuperstructure()
	{
		super(Outcrop.GOB);
		provider = new ObProvider();
	}

	public static BoundSuperstructure getInstance()
	{
		if (instance == null)
		{
			synchronized (BoundSuperstructure.class)
			{
				if (instance == null)
				{
					instance = new BoundSuperstructure();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "GOB-Report";
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

		addToTable(provider.createPechQuerschnittRows(dataTables, false));
		addToTable(provider.createPechQuerschnittRows(dataTables, true));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.createSizeOBRow(dataTables));
		addToTable(provider.createLoadClassRow(dataTables));
		addToTable(provider.createRukRow(dataTables));
		addToTable(provider.createRukSingleValueRow(dataTables));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.createPechQualitativRow(dataTables));
		addToTable(provider.createPechHalbQuantitativRow(dataTables));
		addToTable(provider.createPechQuantitativRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
