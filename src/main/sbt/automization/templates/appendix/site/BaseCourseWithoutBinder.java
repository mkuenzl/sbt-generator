package sbt.automization.templates.appendix.site;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.references.Chemistry;
import sbt.automization.data.refactoring.references.LP;
import sbt.automization.data.refactoring.references.Probe;
import sbt.automization.data.refactoring.references.Sample;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.appendix.Appendix;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class BaseCourseWithoutBinder extends Appendix
{
	private String outcrop = "";
	private sbt.automization.data.refactoring.Probe probe;
	private boolean alreadyPrintedLP = false;

	@Override
	public void constructTable(final List<ExplorationSite> sites)
	{

	}

	@Override
	public void constructTable(final ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return null;
	}	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:125px;text-align:left",
						new String[]{"Tragschicht ohne", TextFormatUtil.printLineBreak(), "Bindemittel"}),
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});

		String secondRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
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
								TextFormatUtil.printLineBreak(),
								"E<sub>Vdyn</sub>",
								TextFormatUtil.printLineBreak(),
								"<sub>(-15%)</sub>",
								"<div>[41]</div>"
						}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"KGV", "<div>[25]</div>"})
		});

		String thirdRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
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

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{

	}	private void setOutcrop(DataTable dataTable)
	{
		outcrop = dataTable.get(Probe.OUTCROP_TOB);
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

	@Override
	public void constructTemplate(DataTable dataTable)
	{
		setOutcrop(dataTable);
		HtmlTable table = constructAndGetTableObject();

		if (dataTable instanceof sbt.automization.data.refactoring.Probe)
		{
			this.probe = (sbt.automization.data.refactoring.Probe) dataTable;
			List<sbt.automization.data.refactoring.Sample> samplesOfOutcrop = probe.getSamplesBy(Sample.OUTCROP, Outcrop.TOB.toString());

			for (sbt.automization.data.refactoring.Sample sample : samplesOfOutcrop)
			{
				String row = createRow(sample);
				table.appendContent(row);
			}
		}

		addToTemplate(table.appendTag());
	}

	private String printEV()
	{
		if (probe.containsValueFor(Probe.LP_ID) && ! alreadyPrintedLP)
		{
			String formattedEV = probe.getParameterValueBy(Probe.LP_ID, LP.EV2)
					.concat(TextFormatUtil.printLineBreak())
					.concat(probe.getParameterValueBy(Probe.LP_ID, LP.EV85));
			alreadyPrintedLP = true;

			return formattedEV;
		}
		return "-";
	}

	private String createRow(sbt.automization.data.refactoring.Sample sample)
	{
		String row = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("Normal",
						new String[]{sample.get(Sample.TYPE),
								TextFormatUtil.printLineBreak(),
								sample.get(Sample.GRANULATION),
								sample.get(Sample.ROUNDING_GRADATION)}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.THICKNESS)}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.DEPTH_END)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.MUFV)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.LAGA_BO)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.LAGA_RC)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.TL_ROCK_STRATUM)),
				HtmlFactory.createCell("NormalCenter",
						new String[]{printEV()}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.GRAIN_SIZE_DISTRIBUTION)})
		});

		return row;
	}
}
