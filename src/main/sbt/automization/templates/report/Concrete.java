package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.templates.helper.ProbeCellStrategy;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.rows.LegendWithDepthRow;
import sbt.automization.templates.helper.information.*;

import java.util.Collection;
import java.util.List;

public final class Concrete extends Report
{

	private static Concrete instance;
	private final RowProvider provider;

	private Concrete()
	{
		super(Outcrop.CONCRETE);
		provider = new RowProvider(Outcrop.CONCRETE);
	}

	public static Concrete getInstance()
	{
		if (instance == null)
		{
			synchronized (Concrete.class)
			{
				if (instance == null)
				{
					instance = new Concrete();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "BETON-Report";
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
		addToTable(provider.getRow(header.createCell(new String[]{"Aufschlussart"}),new SuperstructureExposureRow()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		//addToTable(provider.getRowWithProbes(new LegendWithDepthRow()));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Material"}),new MaterialRow()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Druckfestigkeit,"}, "N/mm²"),new CompressiveStrengthRow()));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		HtmlCell chemistryIdHeader = header.createCell(new String[]{"Laborprobe"});
		addToTable(provider.getRowWithDataCheck(chemistryIdHeader,new ChemistryIdRow()));
		HtmlCell chemistryMufvHeader = header.createCell(new String[]{"Abgrenzung", UtilityPrinter.printLineBreak(), "Gefährlichkeit,"}, "Schreiben des MUFV<sup>[18]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryMufvHeader,new ChemistryMufvRow()));
		HtmlCell chemistryLfsHeader = header.createCell(new String[]{"LFS"});
		addToTable(provider.getRowWithDataCheck(chemistryLfsHeader,new ChemistryLfsRow()));
		HtmlCell chemistryLagaBoHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Boden<sup>[11]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaBoHeader,new ChemistryLagaBoRow()));
		HtmlCell chemistryLagaRcHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Bauschutt<sup>[28]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRcHeader,new ChemistryLagaRc()));
		HtmlCell chemistryLagaRc = header.createCell(new String[]{"Orientierungswert,"}, "LAGA Bauschutt<sup>[28]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRc,new ChemistryLagaRcOrientation()));
		HtmlCell chemistryTlRockHeader = header.createCell(new String[]{"Verwertungsklasse,"}, "TL Gestein<sup>[27]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryTlRockHeader,new ChemistryTlRockRow()));
		HtmlCell chemistryRekuHeader = header.createCell(new String[]{"Rekultivierung,"}, "Reku<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryRekuHeader,new ChemistryRekuRow()));
		HtmlCell chemistryDepvHeader = header.createCell(new String[]{"Deponieklasse,"}, "DepV<sup>[15]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDepvHeader,new ChemistryDepvRow()));
		HtmlCell chemistryDecisionHeader = header.createCell(new String[]{"Entscheidungshilfe,"}, "DepV<sup>[17]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDecisionHeader,new ChemistryDecisionSupport()));
		HtmlCell chemistryWasteKeyHeader = header.createCell(new String[]{"Abfallschlüssel,"}, "AVV<sup>[14]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryWasteKeyHeader,new ChemistryAvvRow()));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
