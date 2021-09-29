package sbt.automization.templates.appendix;

import sbt.automization.data.DataTable;
import sbt.automization.data.Parameter;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.RuKKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.html.HtmlFactory;

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
				HtmlFactory.createHeader("NormalTableHeader", "width:75px", 2, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:75px", 2, 1,
						new String[]{"Versuch Nr."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:95px", 2, 1,
						new String[]{"Probenart"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:165px", 2, 1,
						new String[]{"Prüfschicht"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:100px", 1, 3,
						new String[]{"Prüftiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:95px",
						new String[]{"Erw. RuK", "<div>[31]</div>"}),
		});

		String secondRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits", 1, 3,
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"°C"}),
		});

		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow);

		return stringBuilder.toString();
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
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
										new String[]{sample.get(SampleKey.DEPTH_START)}),
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
										new String[]{"-"}),
								HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
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

	@Override
	public String getExportFileName()
	{
		return "RUK-Anlage";
	}
}
