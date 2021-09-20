package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class Underground extends Report
{
	private static Underground instance;

	private final RowProvider provider;

	private Underground()
	{
		super(Outcrop.UG);
		provider = new RowProvider(Outcrop.UG);
	}

	public static Underground getInstance()
	{
		if (instance == null)
		{
			synchronized (Underground.class)
			{
				if (instance == null)
				{
					instance = new Underground();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "UG-Report";
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
		addToTable(provider.getRowWithProbes(new GroundExposureRow()));
		addToTable(provider.getRowWithProbes(new SizeRow()));
		addToTable(provider.getRowWithProbes(new SizeTotalRow()));
		addToTable(provider.getRowWithProbes(new TargetDepthRow()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addToTable(provider.getRowWithProbes(new LegendDepthAndClassificationRow()));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRowWithProbes(new DIN18196Row()));
		addToTable(provider.getRowWithProbes(new DIN18300Row()));
		addToTable(provider.getRowWithProbes(new DIN19682Row()));
		addToTable(provider.getRowWithProbes(new DIN18300_09Row()));
		addToTable(provider.getRowWithProbes(new ZTVRow()));
		addToTable(provider.getRowWithProbes(new WaterContentRow()));
		addToTable(provider.getRowWithProbes(new MoistureRow()));
		addToTable(provider.getRowWithProbes(new ConsistencyRow()));
		addToTable(provider.getRowWithProbes(new CompressibilityRow()));
		addToTable(provider.getRowWithProbes(new WearPlanumRow()));
		addToTable(provider.getRowWithProbes(new WearSoleRow()));
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
		addToTable(provider.getRowWithProbes(new ChemistryRekuRow()));
		addToTable(provider.getRowWithProbes(new ChemistryDepvRow()));
		addToTable(provider.getRowWithProbes(new ChemistryDecisionSupport()));
		addToTable(provider.getRowWithProbes(new ChemistryAvvRow()));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
