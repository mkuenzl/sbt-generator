package sbt.automization.templates.appendix.site;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.text.SoilGroupTextFormatter;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.appendix.Appendix;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlTable;

import java.util.List;

public final class Underground extends Appendix
{
	private String outcrop = "";

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
			Probe probe = (Probe) dataTable;
			List<Sample> samplesOfOutcrop = probe.getSamplesBy(SampleKey.OUTCROP, Outcrop.UG.toString());

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
		outcrop = dataTable.get(ProbeKey.OUTCROP_UG_OH_BA);
	}

	private String createRow(Sample sample)
	{
		String row = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCellAsString("Normal",
						new String[]{new SoilGroupTextFormatter().format(sample.get(SampleKey.TYPE))}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(SampleKey.THICKNESS)}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(SampleKey.DEPTH_END)}),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.MUFV)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_BO)),
				HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_RC)),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(SampleKey.WATER_CONTENT)}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{sample.get(SampleKey.WATER_PROCTOR)}),
				HtmlFactory.createCellAsString("NormalCenter",
						new String[]{"-"})
		});

		return row;
	}

	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:125px;text-align:left",
						new String[]{"Untergrund / Unterbau"}),
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});

		String secondRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 2, 1,
						new String[]{"Bodengruppe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Dicke"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"MUFV", "<div>[18]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"LAGA BO", "<div>[11]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"LAGA RC", "<div>[28]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"WG", "<div>[19]</div>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"W<sub>Pr</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Proctor", "<div>[20]</div>"})
		});

		String thirdRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"M.-%"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"M.-%"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"Mg/m³"})
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
