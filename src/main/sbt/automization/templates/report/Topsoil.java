package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.OhFactory;

import java.util.List;

public final class Topsoil extends Report
{
	private static Topsoil instance;
	private final OhFactory factory;

	private Topsoil()
	{
		setOutcrop(Outcrop.OH);
		factory = new OhFactory();
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
