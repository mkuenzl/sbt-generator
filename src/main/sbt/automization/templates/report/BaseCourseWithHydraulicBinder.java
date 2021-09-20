package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class BaseCourseWithHydraulicBinder extends Report
{
	private static BaseCourseWithHydraulicBinder instance;
	private final RowProvider provider;

	private BaseCourseWithHydraulicBinder()
	{
		super(Outcrop.TMHB);
		provider = new RowProvider(Outcrop.TMHB);
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
		provider.setDataTables(dataTables);
		addToTable(provider.getRowWithProbes(new IdRow()));
		addToTable(provider.getRowWithProbes(new BaseCourseExposureRow()));
		addToTable(provider.getRowWithProbes(new SizeTotalTmhbRow()));
		addToTable(provider.getRowWithProbes(new LoadClassRow()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addToTable(provider.getRowWithProbes(new LegendWithDepthRow()));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRowWithProbes(new SizeRow()));
		addToTable(provider.getRowWithProbes(new CompressiveStrengthRow()));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.getRowWithProbes(new ChemistryIdRow()));
		addToTable(provider.getRowWithProbes(new ChemistryMufvRow()));
		addToTable(provider.getRowWithProbes(new ChemistryLagaRc()));
		addToTable(provider.getRowWithProbes(new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithProbes(new ChemistryTlRockRow()));
		addToTable(provider.getRowWithProbes(new ChemistryDepvRow()));
		addToTable(provider.getRowWithProbes(new ChemistryAvvRow()));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
