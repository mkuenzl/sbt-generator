package sbt.automization.core.templates.appendix.site;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ChemistryKey;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.RuKKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.format.text.KindAndGranulationTextFormatter;
import sbt.automization.core.format.text.LoadClassTextFormatter;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.templates.appendix.Appendix;

import java.util.List;

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
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.MUFV)),
				HtmlFactory.createPitchCell(sample.get(SampleKey.PITCH)),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_RC)),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.TL_ROCK_STRATUM)),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.PAK)}),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{sample.getParameterValueBy(SampleKey.RUK_ID, RuKKey.VALUE)})
		});
		
		return row;
	}
	
	private String createLoadClassRow()
	{
		String row = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalCenter", 1, 5,
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
						new String[]{"MUFV", "<div>[18]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"PECH", "<div>[10]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"LAGA RC", "<div>[28]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"TL Ge.", "<div>[27]</div>"}),
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
