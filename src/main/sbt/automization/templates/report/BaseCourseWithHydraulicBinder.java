package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.TmhbProvider;

import java.util.Collection;
import java.util.List;

public final class BaseCourseWithHydraulicBinder extends Report
{
	private static BaseCourseWithHydraulicBinder instance;
	private final TmhbProvider provider;

	private BaseCourseWithHydraulicBinder()
	{
		super(Outcrop.TMHB);
		provider = new TmhbProvider();
	}

	public static BaseCourseWithHydraulicBinder getInstance()
	{
		if (instance == null)
		{
			synchronized (BaseCourseWithHydraulicBinder.class)
			{
				if (instance == null)
				{
					instance = new BaseCourseWithHydraulicBinder();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "TMHB-Report";
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
		addToTable(provider.createBaseCourseExposureRow(dataTables));
		addToTable(provider.createTotalSizeRow(dataTables));
		addToTable(provider.createLoadClassRow(dataTables));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addToTable(provider.createLegendRow(dataTables));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
		addToTable(provider.createSizeRow(dataTables));
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
