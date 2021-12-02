package sbt.automization.core.templates.appendix;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Parameter;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.RuKKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlFactory;

import java.util.List;

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
						new String[]{"Erk. St."}),
				HtmlFactory.createHeaderAsString("NormalTableHeader", "width:1.5cm", 2, 1,
						new String[]{"Versuch", UtilityPrinter.printLineBreak(), "Nr."}),
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
	public void constructTemplate(List<DataTable> dataTables)
	{
		createTableWithHeader();
		
		for (DataTable dataTable : dataTables)
		{
			if (dataTable instanceof Probe)
			{
				Probe probe = (Probe) dataTable;
				
				for (Sample sample : probe.getSamples())
				{
					Parameter parameter = sample.getParameterBy(SampleKey.RUK_ID);
					
					if (parameter != null)
					{
						addAndResetTableOnPageBreak();
						
						String row = HtmlFactory.createRowAsString("NormalThin8", new String[]{
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
										new String[]{probe.get(ProbeKey.ID)}),
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
										new String[]{parameter.get(RuKKey.ID)}),
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
										new String[]{parameter.get(RuKKey.SAMPLE)}),
								HtmlFactory.createCellAsString("Normal",
										new String[]{sample.get(SampleKey.TYPE), " ",
												sample.get(SampleKey.GRANULATION)}),
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter","width:1cm",
										new String[]{sample.get(SampleKey.DEPTH_START)}),
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter","width:1cm",
										new String[]{"-"}),
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter","width:1cm",
										new String[]{sample.get(SampleKey.DEPTH_END)}),
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
										new String[]{parameter.get(RuKKey.VALUE)})
						});
						
						table.appendContent(row);
					}
				}
			}
		}
		addToTemplate(table.appendTag());
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
