package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.TobProvider;

import java.util.Collection;
import java.util.List;

public final class BaseCourseWithoutBinder extends Report
{
	private static BaseCourseWithoutBinder instance;
	private final TobProvider provider;

	private BaseCourseWithoutBinder()
	{
		setOutcrop(Outcrop.TOB);
		provider = new TobProvider();
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
	public String constructAndGetTableHeader()
	{
		return null;
	}

	@Override
	void buildTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetTechnicalHeader(dataTables);
		table.appendContent(provider.createEvDynRow(dataTables));
		table.appendContent(provider.createEvDyn85Row(dataTables));
		table.appendContent(provider.createEv2Row(dataTables));
		table.appendContent(provider.createEvMinimumBorderRow(dataTables));
		table.appendContent(provider.createMaterialRow(dataTables));
		table.appendContent(provider.createSizeRow(dataTables));
		table.appendContent(provider.createGrainSizeDistributionRow(dataTables));
		table.appendContent(provider.createTotalSizeRow(dataTables));
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
		table.appendContent(provider.createChemieDepvRow(dataTables));
		table.appendContent(provider.createChemieDecisionSupportRow(dataTables));
		table.appendContent(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public String getExportFileName()
	{
		return "TOB-Report";
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
		table.appendContent(provider.createOutcropRow(dataTables));
		buildTechnicalFeatures(dataTables);
		buildEnvironmentTechnicalFeatures(dataTables);
		table.appendContent(provider.createLegendRow(dataTables));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
