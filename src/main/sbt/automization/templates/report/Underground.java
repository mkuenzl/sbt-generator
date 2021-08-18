package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.UgProvider;

import java.util.Collection;
import java.util.List;

public final class Underground extends Report
{
	private static Underground instance;

	private final UgProvider provider;

	private Underground()
	{
		super(Outcrop.UG);
		provider = new UgProvider();
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

		addToTable(provider.createIDRow(dataTables));
		addToTable(provider.createGroundExposureRow(dataTables));
		addToTable(provider.createSizeRow(dataTables));
		addToTable(provider.createTotalSizeRow(dataTables));
		addToTable(provider.createTargetDepthRow(dataTables));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addToTable(provider.createLegendRow(dataTables));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.createDIN18196Row(dataTables));
		addToTable(provider.createDIN18300Row(dataTables));
		addToTable(provider.createDIN19682Row(dataTables));
		addToTable(provider.createDIN18300_09Row(dataTables));
		addToTable(provider.createZTVRow(dataTables));
		addToTable(provider.createWaterContentRow(dataTables));
		addToTable(provider.createMoistureRow(dataTables));
		addToTable(provider.createConsistencyRow(dataTables));
		addToTable(provider.createCompressibilityRow(dataTables));
		addToTable(provider.createWearPlanumRow(dataTables));
		addToTable(provider.createWearSoleRow(dataTables));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.createChemieIDRow(dataTables));
		addToTable(provider.createChemieMufvRow(dataTables));
		addToTable(provider.createChemieLagaBoRow(dataTables));
		addToTable(provider.createChemieLagaRcRow(dataTables));
		addToTable(provider.createChemieLagaRcOrientationRow(dataTables));
		addToTable(provider.createChemieTlRockRow(dataTables));
		addToTable(provider.createREKUROW(dataTables));
		addToTable(provider.createChemieDepvRow(dataTables));
		addToTable(provider.createChemieDecisionSupportRow(dataTables));
		addToTable(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
