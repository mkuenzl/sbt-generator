package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.ReferenceParameterChemistry;
import sbt.automization.data.refactoring.references.ReferenceParameterLP;
import sbt.automization.data.refactoring.references.ReferenceProbe;
import sbt.automization.data.refactoring.references.ReferenceSample;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.Outcrop;
import sbt.automization.util.html.*;

import java.util.List;

final class AppendixSiteTOB extends AppendixTemplate
{
	private String outcrop = "";
	private Probe probe;
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
	String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", 125, 1, 1, 1, "left",
						new String[]{"Tragschicht ohne", TextFormatUtil.printLineBreak(), "Bindemittel"}),
				HtmlFactory.createHeader("NormalTableHeader", 480, 1, 1, 8, "left",
						new String[]{"Aufschlussverfahren:",outcrop}),
		});

		String secondRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", 125, 1, 2, 1, "left",
						new String[]{"Art der Schicht"}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
						new String[]{"Dicke", "<div>[7]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 2, 1,
						new String[]{"MUFV", "<div>[18]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 2, 1,
						new String[]{"LAGA BO", "<div>[11]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 2, 1,
						new String[]{"LAGA RC", "<div>[28]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 2, 1,
						new String[]{"TL Ge.", "<div>[27]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
						new String[]{"E<sub>V2</sub>",
								TextFormatUtil.printLineBreak(),
								"E<sub>Vdyn</sub>",
								TextFormatUtil.printLineBreak(),
								"<sub>(-15%)</sub>",
								"<div>[41]</div>"
						}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
						new String[]{"KGV", "<div>[25]</div>"})
		});

		String thirdRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
						new String[]{"MN/m²"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
						new String[]{"M.-%"})
		});

		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);

		return stringBuilder.toString();
	}

	private void setOutcrop(DataTable dataTable)
	{
		outcrop = dataTable.get(ReferenceProbe.OUTCROP_TOB);
	}

	@Override
	HtmlTable constructAndGetTableObject()
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
			List<Sample> samplesOfOutcrop = probe.getSamplesBy(ReferenceSample.OUTCROP, Outcrop.TOB.toString());

			for (Sample sample : samplesOfOutcrop)
			{
				String row = createRow(sample);
				table.appendContent(row);
			}
		}

		addToTemplate(table.appendTag());
	}

	private String printEV()
	{
		if (probe.containsValueFor(ReferenceProbe.LP_ID) && !alreadyPrintedLP)
		{
			String formattedEV = probe.getParameterValueBy(ReferenceProbe.LP_ID, ReferenceParameterLP.EV2)
							.concat(TextFormatUtil.printLineBreak())
									.concat(probe.getParameterValueBy(ReferenceProbe.LP_ID, ReferenceParameterLP.EV85));
			alreadyPrintedLP = true;

			return formattedEV;
		}
		return "-";
	}

	private String createRow(Sample sample)
	{
		String row = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("Normal", 125, 1, 1, 1,
						new String[]{sample.get(ReferenceSample.TYPE),
								TextFormatUtil.printLineBreak(),
								sample.get(ReferenceSample.GRANULATION),
								sample.get(ReferenceSample.ROUNDING_GRADATION)}),
				HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
						new String[]{sample.get(ReferenceSample.THICKNESS)}),
				HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
						new String[]{sample.get(ReferenceSample.DEPTH_END)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.MUFV)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.LAGA_BO)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.LAGA_RC)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.TL_ROCK_STRATUM)),
				HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
						new String[]{printEV()}),
				HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
						new String[]{sample.get(ReferenceSample.GRAIN_SIZE_DISTRIBUTION)})
		});

		return row;
	}
}
