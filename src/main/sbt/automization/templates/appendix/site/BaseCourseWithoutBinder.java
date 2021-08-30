package sbt.automization.templates.appendix.site;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.LpKey;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.data.Outcrop;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.templates.appendix.Appendix;
import sbt.automization.html.HtmlFactory;

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
		createTable();

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
		String row = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString("Normal",
						new String[]{sample.get(SampleKey.TYPE),
								UtilityPrinter.printLineBreak(),
								sample.get(SampleKey.GRANULATION),
								sample.get(SampleKey.ROUNDING_GRADATION)}),
				HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
						new String[]{sample.get(SampleKey.THICKNESS)}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(SampleKey.DEPTH_END)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.MUFV)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_BO)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_RC)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.TL_ROCK_STRATUM)),
				HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
						new String[]{printEV()}),
				HtmlFactory.createCellAsString(textFormatter,"NormalCenter",
						new String[]{sample.get(SampleKey.GRAIN_SIZE_DISTRIBUTION)})
		});

		return row;
	}

	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:125px;text-align:left",
						new String[]{"Tragschicht ohne", UtilityPrinter.printLineBreak(), "Bindemittel"}),
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});

		String secondRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 2, 1,
						new String[]{"Art der Schicht"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Dicke", "<div>[7]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"MUFV", "<div>[18]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"LAGA BO", "<div>[11]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"LAGA RC", "<div>[28]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"TL Ge.", "<div>[27]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"E<sub>V2</sub>",
								UtilityPrinter.printLineBreak(),
								"E<sub>Vdyn</sub>",
								UtilityPrinter.printLineBreak(),
								"<sub>(-15%)</sub>",
								"<div>[41]</div>"
						}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"KGV", "<div>[25]</div>"})
		});

		String thirdRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"MN/m²"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"M.-%"})
		});

		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);

		return stringBuilder.toString();
	}

	private String printEV()
	{
		if (probe.containsValueFor(ProbeKey.LP_ID) && ! alreadyPrintedLP)
		{
			String formattedEV = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV2)
					.concat(UtilityPrinter.printLineBreak())
					.concat(probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85));
			alreadyPrintedLP = true;

			return formattedEV;
		}
		return "-";
	}
}
