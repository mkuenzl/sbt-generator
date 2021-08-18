package sbt.automization.templates.appendix.site;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.references.RefChemistry;
import sbt.automization.data.references.RefProbe;
import sbt.automization.data.references.RefRuK;
import sbt.automization.data.references.RefSample;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.appendix.Appendix;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlTable;

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
		HtmlTable table = constructAndGetTableObject();

		if (dataTable instanceof Probe)
		{
			this.probe = (Probe) dataTable;
			List<Sample> samplesOfOutcrop = probe.getSamplesBy(RefSample.OUTCROP,
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
		outcrop = dataTable.get(RefProbe.OUTCROP_GOB);
	}

	private String createRow(Sample sample)
	{
		String row = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCellAsString("Normal",
						new String[]{TextFormatUtil.formatKindAndGranulation(sample.get(RefSample.TYPE),
								sample.get(RefSample.GRANULATION))}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(RefSample.THICKNESS)}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(RefSample.DEPTH_END)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(RefSample.CHEMISTRY_ID, RefChemistry.MUFV)),
				HtmlFactory.createPitchCell(sample.get(RefSample.PITCH)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(RefSample.CHEMISTRY_ID, RefChemistry.LAGA_RC)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(RefSample.CHEMISTRY_ID, RefChemistry.TL_ROCK_STRATUM)),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(RefSample.PAK)}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.getParameterValueBy(RefSample.RUK_ID, RefRuK.VALUE)})
		});

		return row;
	}

	private String createLoadClassRow()
	{
		String row = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalCenter", 1, 5,
						new String[]{""}),
				HtmlFactory.createCellAsString("NormalCenter", 1, 2,
						new String[]{TextFormatUtil.formatLoadClass(probe.get(RefProbe.LOAD_CLASS))}),
				HtmlFactory.createCellAsString("NormalCenter", 1, 2,
						new String[]{"RStO<sup>[5]</sup>",
								TextFormatUtil.printLineBreak(),
								probe.get(RefProbe.LOAD_CLASS_BOARD)})
		});

		return row;
	}


	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:125;text-align:left", 1, 1,
						new String[]{"Gebundener Oberbau"}),
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});

		String secondRow = HtmlFactory.createRow("NormalHeader", new String[]{
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

		String thirdRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
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

	@Override
	public HtmlTable constructAndGetTableObject()
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();

		return table;
	}
}
