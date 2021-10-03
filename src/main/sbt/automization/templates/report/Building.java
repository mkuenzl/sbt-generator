package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.BuildingStyle;
import sbt.automization.styles.ReportStyle;
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
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("5")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("2.5")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}

	private StyleParameter getStyleParameterHeader()
	{
		return new StyleParameterBuilder()
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("5")
				.setNormalCellClass("NormalBoldHeader")
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

	void addInformationHeader(List<DataTable> dataTables)
	{
		int colspan = 1;

		for (DataTable dataTable : dataTables)
		{
			int size = dataTable.getSamples().size();
			colspan += size;
		}

		HtmlRow row = HtmlFactory.createRow(BuildingStyle.ROW_THIN_8.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Hinweise zur Einstufung in Abhängigkeit des Rückbauverfahrens (informativ)"})
		});

		addToTable(row.appendTag());
	}

	private void buildTable(List<DataTable> dataTables)
	{
		createTable();
		provider.setDataTables(dataTables);

		RowProvider headerProvider = new RowProvider(Outcrop.BUILDING, getStyleParameterHeader());
		headerProvider.setDataTables(dataTables);
		addToTable(headerProvider.getRowWithSamplesCombined(new IdRow()));
		addToTable(headerProvider.getRowWithSamplesCombined(new ComponentRow()));
		addToTable(headerProvider.getRowWithSamplesCombined(new MaterialBuildingRow()));

		constructEnvironmentTechnicalFeatures(dataTables);
		addToTable(this.provider.getRowWithSamples(new LegendWithBuildingInformationRow()));
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
		addToTable(provider.getRowWithSamplesWithoutDataCheck(new ChemistryIdRow()));
		addToTable(provider.getRowWithSamplesWithoutDataCheck(new SuspectedPollutantRow()));
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryPak()));
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryPcbRow())); // PCB
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryAsbestosRow())); // ASBEST

		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryBtexRow()));
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryPhenolRow())); // KMF
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryKmfRow())); // KMF
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistrySulfateRow()));
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryIcpScreeningRow()));
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryEoxRow()));

		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryLagaBoRow()));
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryLagaRc()));
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithChemistrySamplesCombined(new ChemistryTlRockRow()));
		addToTable(provider.getRowWithChemistrySamplesCombinedWithoutDataCheck(new ChemistryMufvClassificationRow())); // Einstufung
		addToTable(provider.getRowWithChemistrySamplesCombinedWithoutDataCheck(new ChemistryMufvParameterRow())); // Parameter
		addToTable(provider.getRowWithChemistrySamplesCombinedWithoutDataCheck(new WasteKeyMaterialRow()));  // Material
		addInformationHeader(dataTables);
		addToTable(provider.getRowWithSamplesCombinedWithoutDataCheck(new WasteKeyMixRow()));  // gemischt
	}
}
