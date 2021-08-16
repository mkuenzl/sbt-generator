package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.FugeFactory;

import java.util.List;

public final class Gap extends Report
{
	private static Gap instance;
	private final FugeFactory factory;

	private Gap()
	{
		setOutcrop(Outcrop.GAP);
		factory = new FugeFactory();
	}

	@Override
	public String constructAndGetTableHeader()
	{
		return null;
	}

	@Override
	String buildTechnicalFeatures(List<DataTable> dataTables)
	{
		return null;
	}

	@Override
	String buildEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		return null;
	}

	public static Gap getInstance()
	{
		if (instance == null)
		{
			synchronized (Gap.class)
			{
				if (instance == null)
				{
					instance = new Gap();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return null;
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{

	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
