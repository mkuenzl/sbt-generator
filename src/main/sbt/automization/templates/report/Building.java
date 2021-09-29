package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.styles.StyleParameter;
import sbt.automization.styles.StyleParameterBuilder;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;
import sbt.automization.util.DatatableFilter;
import sbt.automization.util.Separator;

import java.util.Collection;
import java.util.List;

public final class Building extends Report
{
	private static Building instance;
	private final RowProvider provider;

	private Building()
	{
		super(Outcrop.BUILDING);
		provider = new RowProvider(Outcrop.BUILDING, getStyleParameter());

	}

	public static Building getInstance()
	{
		if (instance == null)
		{
			synchronized (Building.class)
			{
				if (instance == null)
				{
					instance = new Building();
				}
			}
		}
		return instance;
	}

	private StyleParameter getStyleParameter()
	{
		return new StyleParameterBuilder()
				.setRowClass("NormalThin9")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("5")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("2.5")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}

	@Override
	Collection<List<DataTable>> splitIntoPortionPerPage(List<DataTable> tables)
	{
		List<DataTable> probesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(tables, outcrop);

		Collection<List<DataTable>> portions = Separator.separateProbesBySizeOfSamples(probesWhichIncludeOutcrop, 12);

		return portions;
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

		addToTable(provider.getRowWithSamplesCombined(new IdRow()));
		addToTable(provider.getRowWithSamplesCombined(new ComponentRow()));
		addToTable(provider.getRowWithSamplesCombined(new MaterialBuildingRow()));

		constructEnvironmentTechnicalFeatures(dataTables);
		addToTable(provider.getRowWithSamples(new LegendWithBuildingInformationRow()));
	}

	@Override
	public String getExportFileName()
	{
		return "GEBÄUDE-Report";
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{

	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addToTable(provider.getRowWithSamplesCombined(new ChemistryIdRow()));
		addToTable(provider.getRowWithSamplesCombined(new SuspectedPollutantRow()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryPak()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryPcbRow())); // PCB
		addToTable(provider.getRowWithSamplesCombined(new ChemistryAsbestosRow())); // ASBEST

		addToTable(provider.getRowWithSamplesCombined(new ChemistryBtexRow()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryPhenolRow())); // KMF
		addToTable(provider.getRowWithSamplesCombined(new ChemistryKmfRow())); // KMF
		addToTable(provider.getRowWithSamplesCombined(new ChemistrySulfateRow()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryIcpScreeningRow()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryEoxRow()));

		addToTable(provider.getRowWithSamplesCombined(new ChemistryLagaBoRow()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryLagaRc()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryTlRockRow()));
		addToTable(provider.getRowWithSamplesCombined(new ChemistryMufvClassificationRow())); // Einstufung
		addToTable(provider.getRowWithSamplesCombined(new ChemistryMufvParameterRow())); // Parameter
		addToTable(provider.getRowWithSamplesCombined(new WasteKeyMaterialRow()));  // Material
		addToTable(provider.getRowWithSamplesCombined(new WasteKeyMixRow()));  // gemischt
	}
}
