package sbt.automization.templates.appendix.site;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.references.Chemistry;
import sbt.automization.data.refactoring.references.RuK;
import sbt.automization.data.refactoring.references.Probe;
import sbt.automization.data.refactoring.references.Sample;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.appendix.Appendix;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class BoundSuperstructure extends Appendix
{
	private String outcrop = "";
	private sbt.automization.data.refactoring.Probe probe;

	@Override
	public void constructTable(final List<ExplorationSite> sites)
	{
	}

	@Override
	public void constructTable(final ExplorationSite site)
	{
	}	private void setOutcrop(DataTable dataTable)
	{
		outcrop = dataTable.get(Probe.OUTCROP_GOB);
	}

	@Override
	public String getExportFileName()
	{
		return null;
	}	@Override
	public void constructTemplate(DataTable dataTable)
	{
		setOutcrop(dataTable);
		HtmlTable table = constructAndGetTableObject();

		if (dataTable instanceof sbt.automization.data.refactoring.Probe)
		{
			this.probe = (sbt.automization.data.refactoring.Probe) dataTable;
			List<sbt.automization.data.refactoring.Sample> samplesOfOutcrop = probe.getSamplesBy(Sample.OUTCROP,
					new String[]{Outcrop.GOB.toString(),
							Outcrop.TMHB.toString(),
							Outcrop.CONCRETE.toString(),
							Outcrop.SEAL.toString(),
							Outcrop.COATING.toString()
					});

			for (sbt.automization.data.refactoring.Sample sample : samplesOfOutcrop)
			{
				String row = createRow(sample);
				table.appendContent(row);
			}

			String loadClassRow = createLoadClassRow();
			table.appendContent(loadClassRow);
		}

		addToTemplate(table.appendTag());
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{

	}	private String createRow(sbt.automization.data.refactoring.Sample sample)
	{
		String row = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("Normal",
						new String[]{TextFormatUtil.formatKindAndGranulation(sample.get(Sample.TYPE),
								sample.get(Sample.GRANULATION))}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.THICKNESS)}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.DEPTH_END)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.MUFV)),
				HtmlFactory.createPitchCell(sample.get(Sample.PITCH)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.LAGA_RC)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.TL_ROCK_STRATUM)),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.PAK)}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.getParameterValueBy(Sample.RUK_ID, RuK.VALUE)})
		});

		return row;
	}

	private String createLoadClassRow()
	{
		String row = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("NormalCenter", 1, 5,
						new String[]{""}),
				HtmlFactory.createCell("NormalCenter", 1, 2,
						new String[]{TextFormatUtil.formatLoadClass(probe.get(Probe.LOAD_CLASS))}),
				HtmlFactory.createCell("NormalCenter", 1, 2,
						new String[]{"RStO<sup>[5]</sup>",
								TextFormatUtil.printLineBreak(),
								probe.get(Probe.LOAD_CLASS_BOARD)})
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
