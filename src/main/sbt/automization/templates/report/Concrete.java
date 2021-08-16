package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.ConcreteFactory;

import java.util.List;

public final class Concrete extends Report
{

	private static Concrete instance;
	private final ConcreteFactory factory;

	private Concrete()
	{
		setOutcrop(Outcrop.CONCRETE);
		factory = new ConcreteFactory();
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

	public static Concrete getInstance()
	{
		if (instance == null)
		{
			synchronized (Concrete.class)
			{
				if (instance == null)
				{
					instance = new Concrete();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "Bericht-BETON";
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
