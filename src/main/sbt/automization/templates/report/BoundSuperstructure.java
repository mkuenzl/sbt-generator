package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.ProbeCellStrategy;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.information.*;
import sbt.automization.templates.report.superstructure.CrossSectionWithPitchRows;
import sbt.automization.templates.report.superstructure.CrossSectionWithoutPitchRows;

import java.util.Collection;
import java.util.List;

public final class BoundSuperstructure extends Report
{
	private static BoundSuperstructure instance;
	private final RowProvider provider;

	private BoundSuperstructure()
	{
		super(Outcrop.GOB);
		provider = new RowProvider(Outcrop.GOB);
	}

	public static BoundSuperstructure getInstance()
	{
		if (instance == null)
		{
			synchronized (BoundSuperstructure.class)
			{
				if (instance == null)
				{
					instance = new BoundSuperstructure();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "GOB-Report";
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

		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}), new IdRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Aufbruch"}), new SuperstructureExposureRow()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		CrossSectionWithPitchRows crossSectionWithPitchRows = new CrossSectionWithPitchRows();
		crossSectionWithPitchRows.constructTemplate(dataTables);
		addToTable(crossSectionWithPitchRows.getTemplate());

		CrossSectionWithoutPitchRows crossSectionWithoutPitchRows = new CrossSectionWithoutPitchRows();
		crossSectionWithoutPitchRows.constructTemplate(dataTables);
		addToTable(crossSectionWithoutPitchRows.getTemplate());
	}

	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Gesamtdicke Oberbau,"}, "cm"), new SizeTotalObRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Belastungsklasse,"}, "RStO<sup>[5]</sup>"), new LoadClassRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erweichungspunkt RuK<sup>[31]</sup>,"}, "°C"), new RuKCombinedRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Soll Einzelwert,"}, "RuK"), new RuKSingleValueRow()));
	}

	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis qualitativ"}), new PitchQualitativeRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis halbquantitativ"}), new PitchHalfQuantitativeRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis quantitativ"}), new PitchQuantitativeRow()));
	}

	@Override
	protected void addLegendRow(List<DataTable> dataTables)
	{

	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
