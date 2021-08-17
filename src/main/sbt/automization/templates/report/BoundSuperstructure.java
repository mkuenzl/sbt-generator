package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.ObProvider;

import java.util.Collection;
import java.util.List;

public final class BoundSuperstructure extends Report
{
	private static BoundSuperstructure instance;
	private final ObProvider provider;

	private BoundSuperstructure()
	{
		setOutcrop(Outcrop.GOB);
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
	public String constructAndGetTableHeader()
	{
		return null;
	}

	@Override
	void buildTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetTechnicalHeader(dataTables);
		table.appendContent(provider.createSizeOBRow(dataTables));
		table.appendContent(provider.createLoadClassRow(dataTables));
		table.appendContent(provider.createRukRow(dataTables));
		table.appendContent(provider.createRukSingleValueRow(dataTables));
	}

	@Override
	void buildEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetEnvironmentTechnicalHeader(dataTables);
		table.appendContent(provider.createPechQualitativRow(dataTables));
		table.appendContent(provider.createPechHalbQuantitativRow(dataTables));
		table.appendContent(provider.createPechQuantitativRow(dataTables));
	}

	@Override
	public String getExportFileName()
	{
		return "GOB-Report";
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
		table.appendContent(provider.createPechQuerschnittRows(dataTables, false));
		table.appendContent(provider.createPechQuerschnittRows(dataTables, true));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
