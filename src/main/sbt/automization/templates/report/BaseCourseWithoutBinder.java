package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class BaseCourseWithoutBinder extends Report
{
	private static BaseCourseWithoutBinder instance;
	private final RowProvider provider;

	private BaseCourseWithoutBinder()
	{
		super(Outcrop.TOB);
		provider = new RowProvider(Outcrop.TOB);
	}

	public static BaseCourseWithoutBinder getInstance()
	{
		if (instance == null)
		{
			synchronized (BaseCourseWithoutBinder.class)
			{
				if (instance == null)
				{
					instance = new BaseCourseWithoutBinder();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "TOB-Report";
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

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addToTable(provider.getRowWithProbes(new LegendDepthAndAcronymRow()));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRowWithProbes(new EvDynRow()));
		addToTable(provider.getRowWithProbes(new EvDyn85Row()));
		addToTable(provider.getRowWithProbes(new Ev2WithEv85Row()));
		addToTable(provider.getRowWithProbes(new EvMinimumBorderRow()));
		addToTable(provider.getRowWithProbes(new MaterialTobRow()));
		addToTable(provider.getRowWithProbes(new SizeRow()));
		addToTable(provider.getRowWithProbes(new GrainSizeDistributionRow()));
		addToTable(provider.getRowWithProbes(new SizeTotalTobRow()));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.getRowWithProbes(new ChemistryIdRow()));
		addToTable(provider.getRowWithProbes(new ChemistryMufvRow()));
		addToTable(provider.getRowWithProbes(new ChemistryLagaBoRow()));
		addToTable(provider.getRowWithProbes(new ChemistryLagaRc()));
		addToTable(provider.getRowWithProbes(new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithProbes(new ChemistryTlRockRow()));
		addToTable(provider.getRowWithProbes(new ChemistryDepvRow()));
		addToTable(provider.getRowWithProbes(new ChemistryDecisionSupport()));
		addToTable(provider.getRowWithProbes(new ChemistryAvvRow()));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
