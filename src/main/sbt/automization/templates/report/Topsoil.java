package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.OhProvider;

import java.util.Collection;
import java.util.List;

public final class Topsoil extends Report
{
	private static Topsoil instance;
	private final OhProvider provider;

	private Topsoil()
	{
		setOutcrop(Outcrop.OH);
		provider = new OhProvider();
	}

	public static Topsoil getInstance()
	{
		if (instance == null)
		{
			synchronized (Topsoil.class)
			{
				if (instance == null)
				{
					instance = new Topsoil();
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
		table.appendContent(provider.createDIN18915Row(dataTables));
		table.appendContent(provider.createDIN18320Row(dataTables));
	}

	@Override
	void buildEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		constructAndGetEnvironmentTechnicalHeader(dataTables);
		table.appendContent(provider.createChemieIDRow(dataTables));
		table.appendContent(provider.createChemieLagaBoRow(dataTables));
		table.appendContent(provider.createChemieDepvRow(dataTables));
		table.appendContent(provider.createChemieDecisionSupportRow(dataTables));
		table.appendContent(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public String getExportFileName()
	{
		return "OH-Report";
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
		table.appendContent(provider.createGroundExposureRow(dataTables));
		buildTechnicalFeatures(dataTables);
		buildEnvironmentTechnicalFeatures(dataTables);
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
