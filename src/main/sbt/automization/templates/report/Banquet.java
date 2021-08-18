package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.BankettProvider;

import java.util.Collection;
import java.util.List;

public final class Banquet extends Report
{
	private static Banquet instance;
	private final BankettProvider provider;

	private Banquet()
	{
		super(Outcrop.BANQUET);
		provider = new BankettProvider();
	}

	public static Banquet getInstance()
	{
		if (instance == null)
		{
			synchronized (Banquet.class)
			{
				if (instance == null)
				{
					instance = new Banquet();
				}
			}
		}
		return instance;
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
		addToTable(provider.createDIN18196Row(dataTables));
		addToTable(provider.createDIN18915Row(dataTables));
		addToTable(provider.createDIN18320Row(dataTables));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);
		
		addToTable(provider.createChemieIDRow(dataTables));
		addToTable(provider.createChemieLagaBoRow(dataTables));
		addToTable(provider.createChemieDepvRow(dataTables));
		addToTable(provider.createChemieDecisionSupportRow(dataTables));
		addToTable(provider.createChemieAVVRow(dataTables));
	}

	@Override
	public String getExportFileName()
	{
		return "Bankett-Report";
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
		
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
