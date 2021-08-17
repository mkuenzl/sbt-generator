package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.helper.BankettProvider;
import sbt.automization.templates.styles.ReportStyle;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlRow;

import java.util.Collection;
import java.util.List;

public final class Banquet extends Report
{
	private static Banquet instance;
	private final BankettProvider provider;

	private Banquet()
	{
		setOutcrop(Outcrop.BANQUET);
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
		return "Bankett-Report";
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
