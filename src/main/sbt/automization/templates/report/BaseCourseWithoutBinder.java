package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.TobFactory;

import java.util.List;

public final class BaseCourseWithoutBinder extends Report
{
	private static BaseCourseWithoutBinder instance;
	private final TobFactory factory;

	private BaseCourseWithoutBinder()
	{
		setOutcrop(Outcrop.TOB);
		factory = new TobFactory();
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
