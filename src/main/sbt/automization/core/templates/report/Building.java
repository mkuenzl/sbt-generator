package sbt.automization.core.templates.report;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.format.text.StandardCellTextFormatter;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.html.HtmlRow;
import sbt.automization.core.styles.BuildingStyle;
import sbt.automization.core.styles.ReportStyle;
import sbt.automization.core.styles.StyleParameter;
import sbt.automization.core.styles.StyleParameterBuilder;
import sbt.automization.core.templates.construction.RowFactory;
import sbt.automization.core.retrieval.*;
import sbt.automization.core.templates.construction.strategies.CellPerSample;
import sbt.automization.core.templates.construction.strategies.CellPerSampleCombined;
import sbt.automization.core.templates.construction.strategies.CellPerSampleCombinedChemistry;
import sbt.automization.core.util.DatatableFilter;
import sbt.automization.core.util.ListSeparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Building extends Report
{
	private static Building instance;
	private final RowFactory provider;
	
	private Building()
	{
		super(Outcrop.BUILDING);
		this.provider = new RowFactory(Outcrop.BUILDING, getStyleParameter());
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
	
	@Override
	public String getExportFileName()
	{
		return "GEBÄUDE-Report";
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
		provider.setCellStrategy(new CellPerSampleCombined());
		provider.setStyleParameter(getStyleParameterHeader());
		addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}), new IdRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Bauteil"}), new ComponentRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Material"}), new MaterialBuildingRetrieval()));
		
		provider.setStyleParameter(getStyleParameter());
		
		constructEnvironmentTechnicalFeatures(dataTables);
		addLegendRow(dataTables);
	}
	
	@Override
	protected StyleParameter getStyleParameterHeader()
	{
		return new StyleParameterBuilder()
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("4")
				.setNormalCellClass("NormalBoldHeader")
				.setNormalCellWidth("2.5")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}
	
	@Override
	protected Collection<List<DataTable>> splitIntoPortionPerPage(List<DataTable> tables)
	{
		List<DataTable> probesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(tables, outcrop);
		
		Collection<List<DataTable>> portions = ListSeparator.separateProbesBySizeOfSamples(probesWhichIncludeOutcrop, 12);
		
		return portions;
	}
	
	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
	
	}
	
	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		provider.setCellStrategy(new CellPerSample());
		addToTable(provider.getRow(header.createCell(new String[]{"Laborprobe"}), new ChemistryIdRetrieval()));
		addToTable(provider.getRow(header.createCell(new String[]{"Schadstoffverdacht"}), new SuspectedPollutantRetrieval()));
		
		provider.setCellStrategy(new CellPerSampleCombinedChemistry());
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"PAK,"}, "mg/kg"), new ChemistryPakRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"PCB,"}, "mg/kg"), new ChemistryPcbRetrieval())); // PCB
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Asbest,"}, "Nachweisgrenze"), new ChemistryAsbestosRetrieval())); // ASBEST
		
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"BTEX,"}, "mg/kg"), new ChemistryBtexRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Phenole,"}, "mg/l"), new ChemistryPhenolRetrieval())); // PAtonal
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"KMF,"}, "Nachweisgrenze"), new ChemistryKmfRetrieval())); // KMF
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Sulfat,"}, "mg/kg"), new ChemistrySulfateRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"ICP Screening,"}, "mg/kg"), new ChemistryIcpScreeningRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"EOX,"}, "mg/kg"), new ChemistryEoxRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"HBCD,"}, "mg/kg"), new ChemistryHBCDRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"FCKW,"}, "mg/kg"), new ChemistryFCKWRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"MKW (C10 - C22),"}, "mg/kg"), new ChemistryMKWC22Retrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"MKW (C10 - C40),"}, "mg/kg"), new ChemistryMKWC40Retrieval()));
		
		// added 01.07.2023
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Materialklasse,"}, "EBV Boden<sup" +
				">[4]</sup>"),	new ChemistryEbvSoil()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Materialklasse,"}, "EBV " +
				"Bauschutt<sup>[4]</sup>"), new ChemistryEbvConstructionWaste()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Überwachungswert,"}, "EBV " +
				"Bauschutt<sup>[4]</sup>"), new ChemistryEbvMonitoringValue()));
		
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Boden<sup>[4]</sup>"), new ChemistryLagaBoRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Zuordnungsklasse,"}, "LAGA Bauschutt<sup>[15]</sup>"), new ChemistryLagaRcRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Orientierungswert,"}, "LAGA Bauschutt<sup>[15]</sup>"), new ChemistryLagaRcOrientationRetrieval()));
		addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Verwertungsklasse,"}, "TL Gestein<sup>[14]</sup>"), new ChemistryTlRockRetrieval()));
		
		addToTable(provider.getRow(header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"}, "Einstufung (bis 31" +
						".07.2023)"),
				new ChemistryMufvRetrieval())); // Einstufung
		
		addToTable(provider.getRow(header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"}, "Einstufung (ab 01" +
				".08.2023)"),	new ChemistryMkuemRetrieval())); // Einstufung
		
		addToTable(provider.getRow(header.createCell(new String[]{"Abgrenzung Gefährlichkeit,"}, "Parameter"), new ChemistryMufvParameterRetrieval())); // Parameter
		addToTable(provider.getRow(header.createCell(new String[]{"Abfallschlüssel<sup>1,2</sup>"}, "AVV<sup>[7]</sup>, materialspezifisch"), new WasteKeyMaterialRetrieval()));  // Material
		addInformationHeader(dataTables);
		provider.setCellStrategy(new CellPerSampleCombined());
		addToTable(provider.getRow(header.createCell(new String[]{"Abfallschlüssel<sup>1,2</sup>"}, "AVV<sup>[7]</sup>, mehrschichtig / Gemisch"), new WasteKeyMixRetrieval()));  // gemischt
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
	
	@Override
	protected void addLegendRow(List<DataTable> dataTables)
	{
		int amountOfSamples = 0;
		
		List<String> additionalFootnotes = new ArrayList<>();
		
		for (DataTable probe : dataTables)
		{
			List<Sample> samples = probe.getSamples();
			amountOfSamples += samples.size();
			
			for (Sample sample : samples)
			{
				String footnote = sample.get(SampleKey.MATERIAL_COMPARISON);
				String explorationSite = sample.get(SampleKey.PROBE_ID);
				
				if (!"".equals(footnote))
				{
					additionalFootnotes.add(explorationSite.concat(",").concat(footnote));
				}
			}
		}
		
		StyleParameter styleParameter = getStyleParameter();
		
		double size = styleParameter.getHeaderCellWidthAsDouble() + amountOfSamples * styleParameter.getNormalCellWidthAsDouble();
		
		//Umwelttechnische Merkmale Trennzeile
		HtmlCell content = new HtmlCell.Builder()
				.appendAttribute("class", styleParameter.getLegendCellClass())
				.appendAttribute("colspan", String.valueOf(1 + amountOfSamples))
				.appendAttribute("width", String.valueOf(size))
				.appendContent("Anmerkungen:")
				.appendContent(UtilityPrinter.printLineBreak())
				.appendContent("1) Die abschließende Zuordnung zu einem Abfallschlüssel hängt u. a. von der " +
						"Zusammensetzung der abzufahrenden, separierten Abfälle und von den Annahmebedingungen " +
						"und der Abfalleinstufung der vorgesehenen Entsorgungseinrichtung ab. ")
				.appendContent(UtilityPrinter.printLineBreak())
				.appendContent("2) AVV 17 09 04: Nicht gefährliche und nicht getrennte Bauteile können i.d.R. unter" +
						" dem vorgenannten Abfallschlüssel, gemischte Bau- und Abbruchabfälle zusammen entsorgt werden.")
				.build();
		
		int number = 3;
		for (String additionalFootnote : additionalFootnotes)
		{
			String[] splitter = additionalFootnote.split(",");
			String footnote;
			if (splitter.length == 3)
			{
				footnote = String.format("%d) An der Erk-St %s: Einstufung aufgrund des an der Erk.-St. %s ermittelten Untersuchungsergebnisses der Probe %s unter " +
						"Zugrundelegung der Vergleichbarkeit der vorhandenen Materialien.", number, splitter[0], splitter[1], splitter[2]);
			} else
			{
				footnote = String.format("%d) Falsches Format der Excel Eingabe!", number);
			}
			number++;
			content.appendContent(UtilityPrinter.printLineBreak());
			content.appendContent(footnote);
		}
		
		HtmlRow rowLegend = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(content.appendTag())
				.build();
		
		addToTable(rowLegend.appendTag());
	}
	
	@Override
	protected StyleParameter getStyleParameter()
	{
		return new StyleParameterBuilder()
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("4")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("2.5")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
