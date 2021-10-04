package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.ReportStyle;
import sbt.automization.templates.helper.*;
import sbt.automization.templates.helper.information.*;

import java.util.Collection;
import java.util.List;

public final class Heap extends Report
{
	private static Heap instance;
	private final RowProvider provider;

	private Heap()
	{
		super(Outcrop.HEAP);
		provider = new RowProvider(Outcrop.HEAP);
	}

	public static Heap getInstance()
	{
		if (instance == null)
		{
			synchronized (Heap.class)
			{
				if (instance == null)
				{
					instance = new Heap();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "HAUFWERK-Report";
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
		provider.setCellStrategy(new SampleCellStrategy());

		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}),new IdRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Bereich"}),new AreaRow()));
		addToTable(provider.getRow(header.createCell(new String[]{"Probenahmeverfahren"}),new HeapExposureRow()));
		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);
		//addToTable(provider.getRowWithSamples(new LegendWithDepthRow()));
	}

	@Override
	void addTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = 1;

		for (DataTable dataTable : dataTables)
		{
			colspan += dataTable.getSamples().size();
		}

		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Technische Merkmale"})
		});

		addToTable(row.appendTag());
	}

	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRow(header.createCell(new String[]{"Material"}),new MaterialHeapRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodenklasse,"}, "DIN 18300<sup>[11]</sup>"),new DIN18300Row()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodengruppe,"}, "DIN 18196<sup>[10]</sup>"),new DIN18196Row()));
	}

	@Override
	void addEnvironmentTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = 1;

		for (DataTable dataTable : dataTables)
		{
			colspan += dataTable.getSamples().size();
		}
		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Umwelttechnische Merkmale"})
		});

		addToTable(row.appendTag());
	}

	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		provider.setCellStrategy(new SampleCellStrategy());
		HtmlCell chemistryIdHeader = header.createCell(new String[]{"Laborprobe"});
		addToTable(provider.getRowWithDataCheck(chemistryIdHeader,new ChemistryIdRow()));
		HtmlCell chemistryPakHeader = header.createCell(new String[]{"PAK,"}, "mg/kg");
		addToTable(provider.getRowWithDataCheck(chemistryPakHeader,new ChemistryPak()));
		HtmlCell chemistryMufvHeader = header.createCell(new String[]{"Abgrenzung", UtilityPrinter.printLineBreak(), "Gefährlichkeit,"}, "Schreiben des MUFV<sup>[9]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryMufvHeader,new ChemistryMufvRow()));
		HtmlCell chemistryLfsHeader = header.createCell(new String[]{"LFS"});
		addToTable(provider.getRowWithDataCheck(chemistryLfsHeader,new ChemistryLfsRow()));
		HtmlCell chemistryLagaBoHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Boden<sup>[2]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaBoHeader,new ChemistryLagaBoRow()));
		HtmlCell chemistryLagaRcHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Bauschutt<sup>[16]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRcHeader,new ChemistryLagaRc()));
		HtmlCell chemistryLagaRc = header.createCell(new String[]{"Orientierungswert,"}, "LAGA Bauschutt<sup>[16]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRc,new ChemistryLagaRcOrientation()));
		HtmlCell chemistryTlRockHeader = header.createCell(new String[]{"Verwertungsklasse,"}, "TL Gestein<sup>[15]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryTlRockHeader,new ChemistryTlRockRow()));
		HtmlCell chemistryRekuHeader = header.createCell(new String[]{"Rekultivierung,"}, "Reku<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryRekuHeader,new ChemistryRekuRow()));
		HtmlCell chemistryDepvHeader = header.createCell(new String[]{"Deponieklasse,"}, "DepV<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDepvHeader,new ChemistryDepvRow()));
		HtmlCell chemistryDecisionHeader = header.createCell(new String[]{"Entscheidungshilfe,"}, "DepV<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDecisionHeader,new ChemistryDecisionSupport()));
		HtmlCell chemistryRuvaHeader = header.createCell(new String[]{"Verwertungsklasse,"}, "RuVa-StB<sup>[1]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryRuvaHeader,new ChemistryRuvaRow()));
		HtmlCell chemistryWasteKeyHeader = header.createCell(new String[]{"Abfallschlüssel,"}, "AVV<sup>[6]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryWasteKeyHeader,new ChemistryAvvRow()));
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