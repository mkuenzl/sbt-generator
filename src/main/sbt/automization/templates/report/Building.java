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
import sbt.automization.templates.helper.*;
import sbt.automization.templates.helper.rows.LegendWithBuildingInformationRow;
import sbt.automization.templates.helper.information.*;
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
		this.provider = new RowProvider(Outcrop.BUILDING, getStyleParameter());
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
		provider.setCellStrategy(new CombinedSampleCellStrategy());

		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new IdRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Bauteil"}), new ComponentRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Material"}), new MaterialBuildingRow()));

		constructEnvironmentTechnicalFeatures(dataTables);
		//addToTable(this.provider.getRowWithSamples(new LegendWithBuildingInformationRow()));
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
		provider.setCellStrategy(new SampleCellStrategy());
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryIdRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new SuspectedPollutantRow()));

		provider.setCellStrategy(new CombinedSampleByChemistryCellStrategy());
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryPak()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryPcbRow())); // PCB
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryAsbestosRow())); // ASBEST

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryBtexRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryPhenolRow())); // KMF
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryKmfRow())); // KMF
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistrySulfateRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryIcpScreeningRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryEoxRow()));

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaBoRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaRc()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryTlRockRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryMufvClassificationRow())); // Einstufung
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new ChemistryMufvParameterRow())); // Parameter
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new WasteKeyMaterialRow()));  // Material
		addInformationHeader(dataTables);
		provider.setCellStrategy(new CombinedSampleCellStrategy());
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new WasteKeyMixRow()));  // gemischt
	}
}
