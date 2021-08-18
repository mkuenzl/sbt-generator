package sbt.automization.templates.appendix;

import sbt.automization.data.Sample;
import sbt.automization.data.DataTable;
import sbt.automization.data.Parameter;
import sbt.automization.data.Probe;
import sbt.automization.data.references.RefProbe;
import sbt.automization.data.references.RefRuK;
import sbt.automization.data.references.RefSample;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlTable;

import java.util.List;

public final class RingAndBall extends Appendix
{
	private static RingAndBall instance;

	private RingAndBall() {
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
		String firstRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
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

		String secondRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
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
	public void constructTemplate(List<DataTable> dataTables)
	{
		HtmlTable table = constructAndGetTableObject();

		for (DataTable dataTable : dataTables)
		{
			if (dataTable instanceof Probe)
			{
				Probe probe = (Probe) dataTable;

				for (Sample sample : probe.getSamples())
				{
					Parameter parameter = sample.getParameterBy(RefSample.RUK_ID);

					if (parameter != null)
					{
						addAndResetTableOnPageBreak();

						String row = HtmlFactory.createRow("Normal", new String[]{
								HtmlFactory.createCellAsString("NormalCenter",
										new String[]{probe.get(RefProbe.ID)}),
								HtmlFactory.createCellAsString("NormalCenter",
										new String[]{parameter.get(RefRuK.ID)}),
								HtmlFactory.createCellAsString("NormalCenter",
										new String[]{parameter.get(RefRuK.SAMPLE)}),
								HtmlFactory.createCellAsString("Normal",
										new String[]{sample.get(RefSample.TYPE), " ",
												sample.get(RefSample.GRANULATION)}),
								HtmlFactory.createCellAsString("NormalCenter",
										new String[]{sample.get(RefSample.DEPTH_START)}),
								HtmlFactory.createCellAsString("NormalCenter",
										new String[]{"-"}),
								HtmlFactory.createCellAsString("NormalCenter",
										new String[]{sample.get(RefSample.DEPTH_END)}),
								HtmlFactory.createCellAsString("NormalCenter",
										new String[]{parameter.get(RefRuK.VALUE)})
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
		return "Anlage-RUK";
	}
}
