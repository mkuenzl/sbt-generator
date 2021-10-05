package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.strategies.CellPerProbe;
import sbt.automization.templates.helper.RowFactory;
import sbt.automization.templates.helper.information.*;
import sbt.automization.templates.report.tableparts.CrossSectionWithPitch;
import sbt.automization.templates.report.tableparts.CrossSectionWithoutPitch;

import java.util.Collection;
import java.util.List;

public final class BoundSuperstructure extends Report
{
	private static BoundSuperstructure instance;
	private final RowFactory provider;

	private BoundSuperstructure()
	{
		super(Outcrop.GOB);
		provider = new RowFactory(Outcrop.GOB);
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
		provider.setCellStrategy(new CellPerProbe());

		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}), new IdRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Aufbruch"}), new SuperstructureExposureRetrieval()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		CrossSectionWithPitch crossSectionWithPitch = new CrossSectionWithPitch();
		crossSectionWithPitch.constructTemplate(dataTables);
		addToTable(crossSectionWithPitch.getTemplate());

		CrossSectionWithoutPitch crossSectionWithoutPitch = new CrossSectionWithoutPitch();
		crossSectionWithoutPitch.constructTemplate(dataTables);
		addToTable(crossSectionWithoutPitch.getTemplate());
	}

	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Gesamtdicke geb. Oberbau,"}, "cm"), new SizeTotalGOBRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Belastungsklasse,"}, "RStO<sup>[5]</sup>"), new LoadClassRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erweichungspunkt RuK<sup>[31]</sup>,"}, "°C"), new RuKCombinedRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Soll Einzelwert,"}, "RuK"), new RuKSingleValueRetrieval()));
	}

	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis qualitativ"}), new PitchQualitativeRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis halbquantitativ"}), new PitchHalfQuantitativeRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis quantitativ"}), new PitchQuantitativeRetrieval()));
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
