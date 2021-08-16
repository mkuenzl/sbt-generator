package sbt.automization.templates.appendix.site;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.references.Chemistry;
import sbt.automization.data.refactoring.references.Probe;
import sbt.automization.data.refactoring.references.Sample;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.appendix.Appendix;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class Gap extends Appendix
{
	private String outcrop = "";

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
	}	private void setOutcrop(DataTable dataTable)
	{
		outcrop = dataTable.get(Probe.OUTCROP_UG_OH_BA);
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

		if (dataTable instanceof sbt.automization.data.refactoring.Probe)
		{
			sbt.automization.data.refactoring.Probe probe = (sbt.automization.data.refactoring.Probe) dataTable;
			List<sbt.automization.data.refactoring.Sample> samplesOfOutcrop = probe.getSamplesBy(Sample.OUTCROP, Outcrop.GAP.toString());

			for (sbt.automization.data.refactoring.Sample sample : samplesOfOutcrop)
			{
				String row = createRow(sample);
				table.appendContent(row);
			}
		}

		addToTemplate(table.appendTag());
	}



	private String createRow(sbt.automization.data.refactoring.Sample sample)
	{
		String row = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("Normal",
						new String[]{sample.get(Sample.TYPE)}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.THICKNESS)}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.DEPTH_END)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.MUFV)),
				HtmlFactory.createCell("NormalCenter", 1, 3,
						new String[]{""}),
				HtmlFactory.createCell("NormalCenter",
						new String[]{sample.get(Sample.PAK)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(Sample.CHEMISTRY_ID, Chemistry.ASBESTOS)),
		});

		return row;
	}


	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:125px;text-align:left",
						new String[]{"FUGE"}),
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});

		String secondRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 1, 2,
						new String[]{"Art der Schicht"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Dicke", "<div>[7]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"MUFV", "<div>[18]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 3,
						new String[]{""}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"PAK"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"Asbest"})
		});

		String thirdRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"mg/kg"})
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
