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
		setOutcrop(Outcrop.UG);
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
	public String constructAndGetTableHeader()
	{
		return null;
	}

	@Override
	void buildTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetTechnicalHeader(dataTables);
		table.appendContent(provider.createDIN18196Row(dataTables));
		table.appendContent(provider.createDIN18300Row(dataTables));
		table.appendContent(provider.createDIN19682Row(dataTables));
		table.appendContent(provider.createDIN18300_09Row(dataTables));
		table.appendContent(provider.createZTVRow(dataTables));
		table.appendContent(provider.createWaterContentRow(dataTables));
		table.appendContent(provider.createMoistureRow(dataTables));
		table.appendContent(provider.createConsistencyRow(dataTables));
		table.appendContent(provider.createCompressibilityRow(dataTables));
		table.appendContent(provider.createWearPlanumRow(dataTables));
		table.appendContent(provider.createWearSoleRow(dataTables));
	}

	@Override
	void buildEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetEnvironmentTechnicalHeader(dataTables);
		table.appendContent(provider.createChemieIDRow(dataTables));
		table.appendContent(provider.createChemieMufvRow(dataTables));
		table.appendContent(provider.createChemieLagaBoRow(dataTables));
		table.appendContent(provider.createChemieLagaRcRow(dataTables));
		table.appendContent(provider.createChemieLagaRcOrientationRow(dataTables));
		table.appendContent(provider.createChemieTlRockRow(dataTables));
		table.appendContent(provider.createREKUROW(dataTables));
		table.appendContent(provider.createChemieDepvRow(dataTables));
		table.appendContent(provider.createChemieDecisionSupportRow(dataTables));
		table.appendContent(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public String getExportFileName()
	{
		return "UG-Report";
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		//TODO EXTRACT
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
		table.appendContent(provider.createGroundExposureRow(dataTables));
		table.appendContent(provider.createSizeRow(dataTables));
		table.appendContent(provider.createTotalSizeRow(dataTables));
		table.appendContent(provider.createTargetDepthRow(dataTables));
		buildTechnicalFeatures(dataTables);
		buildEnvironmentTechnicalFeatures(dataTables);
		table.appendContent(provider.createLegendRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
