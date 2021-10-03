package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.ProbeCellStrategy;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.information.*;

import java.util.Collection;
import java.util.List;

public final class Gap extends Report
{
	private static Gap instance;
	private final RowProvider provider;

	private Gap()
	{
		super(Outcrop.GAP);
		provider = new RowProvider(Outcrop.GAP);
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
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryIdRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryMufvRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLfsRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaBoRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaRc()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryTlRockRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryRekuRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryDepvRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryDecisionSupport()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryAvvRow()));
	}

	@Override
	public String getExportFileName()
	{
		return "FUGE-Report";
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
		provider.setDataTables(dataTables);
		provider.setCellStrategy(new ProbeCellStrategy());

		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new IdRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new SuperstructureExposureRow()));

		constructEnvironmentTechnicalFeatures(dataTables);
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
