package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.ProbeCellStrategy;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.rows.LegendDepthAndClassificationRow;
import sbt.automization.templates.helper.information.*;

import java.util.Collection;
import java.util.List;

public final class Underground extends Report
{
	private static Underground instance;

	private final RowProvider provider;

	private Underground()
	{
		super(Outcrop.UG);
		provider = new RowProvider(Outcrop.UG);
	}

	public static Underground getInstance()
	{
		if (instance == null)
		{
			synchronized (Underground.class)
			{
				if (instance == null)
				{
					instance = new Underground();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "UG-Report";
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
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new SizeRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new SizeTotalRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new TargetDepthRow()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		//addToTable(provider.getRowWithProbes(new LegendDepthAndClassificationRow()));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN18196Row()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN18300Row()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN19682Row()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new DIN18300_09Row()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ZTVRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new WaterContentRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new MoistureRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ConsistencyRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new CompressibilityRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new WearPlanumRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new WearSoleRow()));
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
	public void constructTemplate(DataTable dataTable)
	{

	}
}
