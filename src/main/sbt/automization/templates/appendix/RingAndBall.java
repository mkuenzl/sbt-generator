package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Parameter;
import sbt.automization.data.refactoring.references.Probe;
import sbt.automization.data.refactoring.references.RuK;
import sbt.automization.data.refactoring.references.Sample;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

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
	public void constructTable(final List<ExplorationSite> sites)
	{
		HtmlTable table = constructAndGetTableObject();
		addToTemplate(table.appendTag());
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
			if (dataTable instanceof sbt.automization.data.refactoring.Probe)
			{
				sbt.automization.data.refactoring.Probe probe = (sbt.automization.data.refactoring.Probe) dataTable;

				for (sbt.automization.data.refactoring.Sample sample : probe.getSamples())
				{
					Parameter parameter = sample.getParameterBy(Sample.RUK_ID);

					if (parameter != null)
					{
						addAndResetTableOnPageBreak();

						String row = HtmlFactory.createRow("Normal", new String[]{
								HtmlFactory.createCell("NormalCenter",
										new String[]{probe.get(Probe.ID)}),
								HtmlFactory.createCell("NormalCenter",
										new String[]{parameter.get(RuK.ID)}),
								HtmlFactory.createCell("NormalCenter",
										new String[]{parameter.get(RuK.SAMPLE)}),
								HtmlFactory.createCell("Normal",
										new String[]{sample.get(Sample.TYPE), " ",
												sample.get(Sample.GRANULATION)}),
								HtmlFactory.createCell("NormalCenter",
										new String[]{sample.get(Sample.DEPTH_START)}),
								HtmlFactory.createCell("NormalCenter",
										new String[]{"-"}),
								HtmlFactory.createCell("NormalCenter",
										new String[]{sample.get(Sample.DEPTH_END)}),
								HtmlFactory.createCell("NormalCenter",
										new String[]{parameter.get(RuK.VALUE)})
						});

						table.appendContent(row);
					}
				}
			}
		}
		addToTemplate(table.appendTag());
	}

	@Override
	public void constructTable(final ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Anlage-RUK";
	}
}
