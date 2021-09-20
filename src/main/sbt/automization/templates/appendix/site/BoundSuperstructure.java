package sbt.automization.templates.appendix.site;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.RuKKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.format.text.KindAndGranulationTextFormatter;
import sbt.automization.format.text.LoadClassTextFormatter;
import sbt.automization.html.HtmlFactory;
import sbt.automization.templates.appendix.Appendix;

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
		String row = HtmlFactory.createRowAsString("Normal", new String[]{
				HtmlFactory.createCellAsString(textFormatter, "Normal",
						new String[]{new KindAndGranulationTextFormatter().format(sample.get(SampleKey.TYPE),
								sample.get(SampleKey.GRANULATION))}),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
						new String[]{sample.get(SampleKey.THICKNESS)}),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
						new String[]{sample.get(SampleKey.DEPTH_END)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.MUFV)),
				HtmlFactory.createPitchCell(sample.get(SampleKey.PITCH)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_RC)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.TL_ROCK_STRATUM)),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
						new String[]{sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.PAK)}),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
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
		String firstRow = HtmlFactory.createRowAsString("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:125;text-align:left", 1, 1,
						new String[]{"Gebundener Oberbau"}),
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});

		String secondRow = HtmlFactory.createRowAsString("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 2, 1,
						new String[]{"Art der Schicht"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Dicke"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"MUFV", "<div>[18]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"PECH", "<div>[10]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"LAGA RC", "<div>[28]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"TL Ge.", "<div>[27]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"PAK"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"RuK", "<div>[31]</div>"})
		});

		String thirdRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"mg/kg"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"°C"})
		});

		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);

		return stringBuilder.toString();
	}
}
