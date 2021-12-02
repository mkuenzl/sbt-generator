package sbt.automization.core.templates.appendix.site;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ChemistryKey;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.text.ProctorTextFormatter;
import sbt.automization.core.format.text.SoilGroupTextFormatter;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.templates.appendix.Appendix;

import java.util.List;

public final class Banquet extends Appendix
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
		createTableWithHeader();
		
		if (dataTable instanceof Probe)
		{
			Probe probe = (Probe) dataTable;
			List<Sample> samplesOfOutcrop = probe.getSamplesBy(SampleKey.OUTCROP, Outcrop.BANQUET.toString());
			
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
		String row = HtmlFactory.createRowAsString("NormalThin8", new String[]{
				HtmlFactory.createCellAsString(textFormatter, "Normal",
						new String[]{new SoilGroupTextFormatter().format(sample.get(SampleKey.TYPE))}),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
						new String[]{sample.get(SampleKey.THICKNESS)}),
				HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
						new String[]{sample.get(SampleKey.DEPTH_END)}),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.MUFV)),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_BO)),
				HtmlFactory.createChemistryCellAsString(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, ChemistryKey.LAGA_RC)),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{sample.get(SampleKey.WATER_CONTENT)}),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{new ProctorTextFormatter().format(sample.get(SampleKey.MOISTURE))}),
				HtmlFactory.createCellAsString(textFormatter, "NormalBold",
						new String[]{"-"})
		});
		
		return row;
	}
	
	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:3.2cm;text-align:left",
						new String[]{"Bankett"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "text-align:left", 1, 8,
						new String[]{"Aufschlussverfahren:", outcrop}),
		});
		
		String secondRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader", "text-align:left", 2, 1,
						new String[]{"Bodengruppe"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"Dicke"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"MUFV", "<div>[18]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"LAGA BO", "<div>[11]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm", 2, 1,
						new String[]{"LAGA RC", "<div>[28]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"WG", "<div>[19]</div>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"W<sub>Pr</sub>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.6cm",
						new String[]{"Proctor", "<div>[20]</div>"})
		});
		
		String thirdRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"cm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"M.-%"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"M.-%"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"Mg/m³"})
		});
		
		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);
		
		return stringBuilder.toString();
	}
}
