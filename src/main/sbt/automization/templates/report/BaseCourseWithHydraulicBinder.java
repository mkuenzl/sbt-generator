package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.TmhbFactory;

import java.util.List;

public final class BaseCourseWithHydraulicBinder extends Report
{
	private static BaseCourseWithHydraulicBinder instance;
	private final TmhbFactory factory;

	private BaseCourseWithHydraulicBinder()
	{
		setOutcrop(Outcrop.TMHB);
		factory = new TmhbFactory();
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
