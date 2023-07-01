package sbt.automization.core.templates.appendix;

import sbt.automization.core.Project;
import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Parameter;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.RuKKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RingAndBall extends Appendix
{
	private static RingAndBall instance;
	
	private RingAndBall()
	{
		super();
	}
	
	public static RingAndBall getInstance()
	{
		if (instance == null)
		{
			synchronized (RingAndBall.class)
			{
				if (instance == null)
				{
					instance = new RingAndBall();
				}
			}
		}
		return instance;
	}
	
	@Override
	protected String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString("NormalTableHeader", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.5cm", 2, 1,
						new String[]{"Versuch"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.5cm", 2, 1,
						new String[]{"Erk. St.", UtilityPrinter.printLineBreak(), "Nr."}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:2.5cm", 2, 1,
						new String[]{"Probenart"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:5cm", 2, 1,
						new String[]{"Prüfschicht"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", 1, 3,
						new String[]{"Prüftiefe"}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:2.5cm",
						new String[]{"Erw. RuK", "<div>[31]</div>"}),
		});
		
		String secondRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits", 1, 3,
						new String[]{"cm"}),
				HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
						new String[]{"°C"}),
		});
		
		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow);
		
		return stringBuilder.toString();
	}
	
	@Override
	public String getExportFileName()
	{
		return "RUK-Anlage";
	}
	
	
	@Override
	public void constructTemplate(Project project)
	{
		List<DataTable> projectParameter = new ArrayList<>(project.getParameters());
		constructTemplate(projectParameter);
	}
	
	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		createTableWithHeader();
		List<Parameter> parameterList = retrieveRuKParameter(dataTables);
		sort(parameterList);
		
		for (Parameter parameter : parameterList)
		{
			for (Sample parameterSample : parameter.getSamples())
			{
				addAndResetTableOnPageBreak();
				
				String row = HtmlFactory.createRowAsString("NormalThin8", new String[]{
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{parameter.get(RuKKey.ID)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{parameterSample.getProbe().get(ProbeKey.ID)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{parameter.get(RuKKey.SAMPLE)}),
						HtmlFactory.createCellAsString("Normal",
								new String[]{parameterSample.get(SampleKey.TYPE), " ",
										parameterSample.get(SampleKey.GRANULATION)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "width:1cm",
								new String[]{parameterSample.get(SampleKey.DEPTH_START)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "width:1cm",
								new String[]{"-"}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "width:1cm",
								new String[]{parameterSample.get(SampleKey.DEPTH_END)}),
						HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
								new String[]{parameter.get(RuKKey.VALUE)})
				});
				
				table.appendContent(row);
			}
		}
		
		addToTemplate(table.appendTag());
	}
	
	private List<Parameter> retrieveRuKParameter(List<DataTable> parameterList){
		
		List<Parameter> parameters = new ArrayList<>();
		
		for (DataTable dataTable : parameterList)
		{
			Parameter parameter = (Parameter) dataTable;
			
			if (parameter.containsReference(RuKKey.ID))
			{
				parameters.add(parameter);
			}
		}
		return parameters;
	}
	
	
	
	private static void sort(List<Parameter> list) {
		Collections.sort(list, new Comparator<Parameter>() {
			@Override
			public int compare(Parameter param1, Parameter param2) {
				int id1 = extractFirstNumber(param1.get(RuKKey.ID));
				int id2 = extractFirstNumber(param2.get(RuKKey.ID));
				return Integer.compare(id1, id2);
			}
		});
	}
	
	public static int extractFirstNumber(String str) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(str);
		
		if (matcher.find()) {
			String numberStr = matcher.group();
			return Integer.parseInt(numberStr);
		}
		
		return -1;  // Return a default value if no number is found
	}
	
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
