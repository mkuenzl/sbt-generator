package sbt.automization.core.templates.appendix.site;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ChemistryKey;
import sbt.automization.core.data.key.LpKey;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.templates.appendix.Appendix;

import java.util.List;

public final class BaseCourseWithoutBinder extends Appendix
{
	private String outcrop = "";
	private Probe probe;
	private boolean alreadyPrintedLP = false;
	
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
			List<Sample> samplesOfOutcrop = probe.getSamplesBy(SampleKey.OUTCROP, Outcrop.TOB.toString());
			
			for (Sample sample : samplesOfOutcrop)
			{
				String row = createRow(sample);
				table.appendContent(row);
			}
		}
		
		addToTemplate(table.appendTag());
	}
	
	private void setOutcrop(DataTable dataTable)
	{
		outcrop = dataTable.get(ProbeKey.OUTCROP_TOB);
	}
	
	private String createRow(Sample sample)
	{
		String row = HtmlFactory.createRowAsString("NormalThin8", new String[]{
				HtmlFactory.createCellAsString("Normal",
						new String[]{sample.get(SampleKey.TYPE),
								UtilityPrinter.printLineBreak(),
								sample.get(SampleKey.GRANULATION),
								sample.get(SampleKey.ROUNDING_GRADATION)}),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
						new String[]{sample.get(SampleKey.THICKNESS)}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(SampleKey.DEPTH_END)}),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.MUFV)),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_BO)),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_RC)),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.TL_ROCK_STRATUM)),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{printEV()}),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{sample.get(SampleKey.GRAIN_SIZE_DISTRIBUTION)})
		});
		
		return row;
	}
	
	private String printEV()
	{
		if (probe.containsValueFor(ProbeKey.LP_ID) && !alreadyPrintedLP)
		{
			String formattedEV = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV2)
					.concat(UtilityPrinter.printLineBreak())
					.concat(probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85));
			alreadyPrintedLP = true;
			
			return formattedEV;
		}
		return "-";
	}
	
	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:125px;text-align:left",
						new String[]{"Tragschicht ohne", UtilityPrinter.printLineBreak(), "Bindemittel"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});
		
		String secondRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader", "text-align:left", 2, 1,
						new String[]{"Art der Schicht"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px",
						new String[]{"Dicke", "<div>[7]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"MUFV", "<div>[18]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"LAGA BO", "<div>[11]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"LAGA RC", "<div>[28]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"TL Ge.", "<div>[27]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px",
						new String[]{"E<sub>V2</sub>",
								UtilityPrinter.printLineBreak(),
								"E<sub>Vdyn</sub>",
								UtilityPrinter.printLineBreak(),
								"<sub>(-15%)</sub>",
								"<div>[41]</div>"
						}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px",
						new String[]{"KGV", "<div>[25]</div>"})
		});
		
		String thirdRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"MN/m²"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"M.-%"})
		});
		
		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);
		
		return stringBuilder.toString();
	}
}
