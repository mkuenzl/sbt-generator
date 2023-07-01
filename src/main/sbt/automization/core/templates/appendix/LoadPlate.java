package sbt.automization.core.templates.appendix;


import sbt.automization.core.Project;
import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Parameter;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.LpKey;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.format.text.LoadPlateTextFormatter;
import sbt.automization.core.html.HtmlFactory;

import java.util.ArrayList;
import java.util.List;

import static sbt.automization.core.format.text.NumberTextFormatter.formatStringToOneDecimal;
import static sbt.automization.core.format.text.NumberTextFormatter.formatStringToTwoDecimals;

public final class LoadPlate extends Appendix
{
	private static LoadPlate instance;
	
	private LoadPlate()
	{
		super();
	}
	
	public static LoadPlate getInstance()
	{
		if (instance == null)
		{
			synchronized (LoadPlate.class)
			{
				if (instance == null)
				{
					instance = new LoadPlate();
				}
			}
		}
		return instance;
	}
	
	@Override
	public void constructTemplate(Project project)
	{
		List<DataTable> dataTables = new ArrayList<>(project.getParameters());
		constructTemplate(dataTables);
	}
	
	@Override
	protected String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString(2, new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.5cm", 3, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.5cm", 3, 1,
						new String[]{"Versuch", UtilityPrinter.printLineBreak(), "Nr."}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:6cm", 3, 1,
						new String[]{"Lage der Messstelle"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader",  1, 4,
						new String[]{"Setzung"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1cm", 2, 1,
						new String[]{"E<sub>Vdyn</sub>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1cm", 2, 1,
						new String[]{"E<sub>Vdyn</sub>", UtilityPrinter.printLineBreak(), "<sub>(-15%)</sub>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1cm", 2, 1,
						new String[]{"E<sub>V2</sub>", "<div>[41]</div>"})
		});
		
		String secondRow = HtmlFactory.createRowAsString(new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader","width:1cm",
						new String[]{"S<sub>1</sub>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader","width:1cm",
						new String[]{"S<sub>2</sub>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader","width:1cm",
						new String[]{"S<sub>3</sub>"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader","width:1cm",
						new String[]{"x̅"})
		});
		
		String thirdRow = HtmlFactory.createRowAsString(new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits","width:1cm",
						new String[]{"mm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits","width:1cm",
						new String[]{"mm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits","width:1cm",
						new String[]{"mm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits","width:1cm",
						new String[]{"mm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits","width:1cm",
						new String[]{"MN/m²"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits","width:1cm",
						new String[]{"MN/m²"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits","width:1cm",
						new String[]{"MN/m²"})
		});
		
		
		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);
		
		return stringBuilder.toString();
	}
	
	@Override
	public String getExportFileName()
	{
		return "LP-Anlage";
	}
	
	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		createTableWithHeader();
		
		for (DataTable dataTable : dataTables)
		{
			if (dataTable instanceof Parameter && dataTable.containsReference(LpKey.ID))
			{
				Parameter parameter = (Parameter) dataTable;
				
				String formattedEV2 = new LoadPlateTextFormatter().format(parameter.get(LpKey.EV2),
						parameter.get(LpKey.EV85));
				
				String row = HtmlFactory.createRowAsString("NormalThin8", new String[]{
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{getProbeIds(parameter)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{parameter.get(LpKey.ID)}),
						HtmlFactory.createCellAsString(textFormatter, "Normal",
								new String[]{getProbeLocations(parameter)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{formatStringToTwoDecimals(parameter.get(LpKey.VALUE_1))}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{formatStringToTwoDecimals(parameter.get(LpKey.VALUE_2))}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{formatStringToTwoDecimals(parameter.get(LpKey.VALUE_3))}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{formatStringToTwoDecimals(parameter.get(LpKey.MEAN))}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{formatStringToOneDecimal(parameter.get(LpKey.EV))}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{parameter.get(LpKey.EV85)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{formattedEV2}),
				});
				
				this.table.appendContent(row);
			}
		}
		addToTemplate(this.table.appendTag());
	}
	
	public String getProbeIds(Parameter parameter)
	{
		StringBuilder stringBuilder = new StringBuilder();
		List<Sample> samples = parameter.getSamples();
		List<Probe> probes = new ArrayList<>();
		for (Sample sample : samples)
		{
			probes.add(sample.getProbe());
		}
		for (Probe probe : probes)
		{
			stringBuilder.append(probe.get(ProbeKey.ID));
		}
		return stringBuilder.toString();
	}
	
	public String getProbeLocations(Parameter parameter)
	{
		StringBuilder stringBuilder = new StringBuilder();
		List<Sample> samples = parameter.getSamples();
		List<Probe> probes = new ArrayList<>();
		for (Sample sample : samples)
		{
			probes.add(sample.getProbe());
		}
		for (Probe probe : probes)
		{
			stringBuilder.append(probe.get(ProbeKey.LOCATION));
		}
		return stringBuilder.toString();
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
