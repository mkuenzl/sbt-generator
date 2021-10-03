package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.CombinedSampleCellStrategy;
import sbt.automization.templates.helper.ProbeCellStrategy;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.information.*;

import java.util.Collection;
import java.util.List;

public final class Banquet extends Report
{
	private static Banquet instance;
	private final RowProvider provider;

	private Banquet()
	{
		super(Outcrop.BANQUET);
		provider = new RowProvider(Outcrop.BANQUET);
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
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN18196Row()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN18915Row()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN18320Row()));
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
		return "BANKETT-Report";
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
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new GroundExposureRow()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
