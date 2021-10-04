package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;
import sbt.automization.templates.helper.ProbeCellStrategy;
import sbt.automization.templates.helper.RowProvider;
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

		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}), new IdRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Aufschlussart"}), new GroundExposureRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Dicke,"}, "cm"), new SizeRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Gesamtdicke,"}, "cm"), new SizeTotalRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Zieltiefe,"}, "cm"), new TargetDepthRetrieval()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addLegendRow(dataTables);
	}

	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodengruppe,"}, "DIN 18196<sup>[22]</sup>"), new DIN18196Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodenklasse,"}, "DIN 18300<sup>[23]</sup>"), new DIN18300Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Bodenarten-", UtilityPrinter.printLineBreak(), "hauptgruppe,"}, "DIN 19682-2<sup>[24]</sup>"), new DIN19682Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Homogenbereich,"}, "DIN 18320:2019-09<sup>[34]</sup>"), new DIN18300_09Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Frostempfindlichkeits-", UtilityPrinter.printLineBreak(), "klasse,"}, "ZTV E<sup>[2]</sup>"), new ZTVRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Wassergehalt,"}, "M.-%"), new WaterContentRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Feuchtezustand"}), new MoistureRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Konsistenz"}), new ConsistencyRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Verdichtungsfähigkeit"}), new CompressibilityRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Tragfähigkeit Planum"}, "Soll: E<sub>V2</sub> >= 45 MN/m²".concat(UtilityPrinter.printLineBreak()).concat("Ansatz Planum: FOK -60cm")), new WearPlanumRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Tragfähigkeit Grabensohle"}, "Ansatz Sohle"), new WearSoleRetrieval()));
	}

	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		HtmlCell chemistryIdHeader = header.createCell(new String[]{"Laborprobe"});
		addToTable(provider.getRowWithDataCheck(chemistryIdHeader, new ChemistryIdRetrieval()));
		HtmlCell chemistryMufvHeader = header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"}, "Schreiben des MUFV<sup>[18]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryMufvHeader, new ChemistryMufvRetrieval()));
		HtmlCell chemistryLfsHeader = header.createCell(new String[]{"LFS"});
		addToTable(provider.getRowWithDataCheck(chemistryLfsHeader, new ChemistryLfsRetrieval()));
		HtmlCell chemistryLagaBoHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Boden<sup>[11]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaBoHeader, new ChemistryLagaBoRetrieval()));
		HtmlCell chemistryLagaRcHeader = header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Bauschutt<sup>[28]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRcHeader, new ChemistryLagaRcRetrieval()));
		HtmlCell chemistryLagaRc = header.createCell(new String[]{"Orientierungswert,"}, "LAGA Bauschutt<sup>[28]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryLagaRc, new ChemistryLagaRcOrientationRetrieval()));
		HtmlCell chemistryTlRockHeader = header.createCell(new String[]{"Verwertungsklasse,"}, "TL Gestein<sup>[27]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryTlRockHeader, new ChemistryTlRockRetrieval()));
		HtmlCell chemistryRekuHeader = header.createCell(new String[]{"Rekultivierung,"}, "Reku<sup>[7]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryRekuHeader, new ChemistryRekuRetrieval()));
		HtmlCell chemistryDepvHeader = header.createCell(new String[]{"Deponieklasse,"}, "DepV<sup>[15]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDepvHeader, new ChemistryDepvRetrieval()));
		HtmlCell chemistryDecisionHeader = header.createCell(new String[]{"Entscheidungshilfe,"}, "DepV<sup>[17]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryDecisionHeader, new ChemistryDecisionSupportRetrieval()));
		HtmlCell chemistryWasteKeyHeader = header.createCell(new String[]{"Abfallschlüssel,"}, "AVV<sup>[14]</sup>");
		addToTable(provider.getRowWithDataCheck(chemistryWasteKeyHeader, new ChemistryAvvRetrieval()));
	}

	@Override
	protected void addLegendRow(List<DataTable> dataTables)
	{
		StyleParameter styleParameter = getStyleParameter();
		double size = styleParameter.getHeaderCellWidthAsDouble() + dataTables.size() * styleParameter.getNormalCellWidthAsDouble();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegend = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", styleParameter.getLegendCellClass())
						.appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Anmerkungen:")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Für die angegebenen Tiefen [] gilt die Einheit cm. ")
						.appendContent("Die Einstufung der Verdichtungsfähigkeit erfolgt unter Berücksichtigung der Bodenfeuchtigkeit und der Konsistenz\n" +
								"des Materials zum Erkundungszeitpunkt.")
						.build()
						.appendTag())
				.build();

		addToTable(rowLegend.appendTag());
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
