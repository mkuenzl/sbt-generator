package sbt.automization.core.templates.appendix.site08;

import sbt.automization.core.data.*;
import sbt.automization.core.data.key.ChemistryKey;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.RuKKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.format.text.KindAndGranulationTextFormatter;
import sbt.automization.core.format.text.LoadClassTextFormatter;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.templates.appendix.Appendix;

import java.util.Comparator;
import java.util.List;

/**
 * Repräsentiert den <b>Gebundenen Oberbau</b>
 */
public final class BoundSuperstructure extends Appendix
{
	private String outcrop = "";
	private Probe probe;
	
	@Override
	public String getExportFileName()
	{
		return null;
	}
	
	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
	
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
		setOutcrop(dataTable);
		createTableWithHeader();
		
		if (dataTable instanceof Probe)
		{
			this.probe = (Probe) dataTable;
			List<Sample> samplesOfOutcrop = probe.getSamplesBy(SampleKey.OUTCROP,
					new String[]{Outcrop.GOB.toString(),
							Outcrop.TMHB.toString(),
							Outcrop.CONCRETE.toString(),
							Outcrop.SEAL.toString(),
							Outcrop.COATING.toString()
					});
			
			samplesOfOutcrop.sort(Comparator.comparing(a -> Integer.parseInt(a.get(SampleKey.NUMBER))));
			
			for (Sample sample : samplesOfOutcrop)
			{
				String row = createRow(sample);
				table.appendContent(row);
			}
			
			String loadClassRow = createLoadClassRow();
			table.appendContent(loadClassRow);
		}
		
		addToTemplate(table.appendTag());
	}
	
	private void setOutcrop(DataTable dataTable)
	{
		outcrop = dataTable.get(ProbeKey.OUTCROP_GOB);
	}
	
	private String createRow(Sample sample)
	{
		String row = HtmlFactory.createRowAsString("NormalThin8", new String[]{
				HtmlFactory.createCellAsString(textFormatter, "Normal",
						new String[]{new KindAndGranulationTextFormatter().format(sample.get(SampleKey.TYPE),
								sample.get(SampleKey.GRANULATION))}),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
						new String[]{sample.get(SampleKey.THICKNESS)}),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
						new String[]{sample.get(SampleKey.DEPTH_END)}),
				HtmlFactory.createChemistryCellAsString(formatParameterValue(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID,
						ChemistryKey.MKUEM))),
				HtmlFactory.createPitchCell(sample.get(SampleKey.PITCH)),
				HtmlFactory.createChemistryCellAsString(formatParameterValue(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID,
						ChemistryKey.EBV_CONSTRUCTION_WASTE))),
				HtmlFactory.createChemistryCellAsString(formatParameterValue(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID,
						ChemistryKey.EBV_MONITORING_VALUE))),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.PAK)}),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{sample.getParameterValueBy(SampleKey.RUK_ID, RuKKey.VALUE)})
		});
		
		return row;
	}

	private String formatParameterValue(String parameterValue)
	{
		return ParameterValueToFormat.getFormattedValue(parameterValue);
	}
	
	private String createLoadClassRow()
	{
		String row = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeaderCenter", 1, 5,
						new String[]{""}),
				HtmlFactory.createCellAsString("NormalCenter", 1, 2,
						new String[]{new LoadClassTextFormatter().format(probe.get(ProbeKey.LOAD_CLASS))}),
				HtmlFactory.createCellAsString("NormalCenter", 1, 2,
						new String[]{"RStO<sup>[5]</sup>",
								UtilityPrinter.printLineBreak(),
								probe.get(ProbeKey.LOAD_CLASS_BOARD)})
		});
		
		return row;
	}
	
	
	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:3.2cm;text-align:left", 1, 1,
						new String[]{"Gebundener Oberbau"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});
		
		String secondRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader", "text-align:left", 2, 1,
						new String[]{"Art der Schicht"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"Dicke"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",2, 1,
						new String[]{"MKUEM", "<div>[18]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"PECH", "<div>[10]</div>"}),
				
				
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"EBV RC", "<div>[50]</div>"}),
				
				
				// EBV ÜBERWACHUNGSWERTE
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"EBV RC" + UtilityPrinter.printLineBreak() + "Überw.-" + UtilityPrinter.printLineBreak() + "werte", "<div>[50]</div>"}),
				
				
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"PAK"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"RuK", "<div>[31]</div>"})
		});
		
		String thirdRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"mg/kg"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"°C"})
		});
		
		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);
		
		return stringBuilder.toString();
	}
}
