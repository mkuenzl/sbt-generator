package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Parameter;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.ReferenceParameterRuK;
import sbt.automization.data.refactoring.references.ReferenceProbe;
import sbt.automization.data.refactoring.references.ReferenceSample;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class AppendixRUK extends AppendixTemplate
{
	private static AppendixRUK instance;

	private AppendixRUK() {}

	public static AppendixRUK getInstance()
	{
		if (instance == null)
		{
			synchronized (AppendixRUK.class)
			{
				if (instance == null)
				{
					instance = new AppendixRUK();
				}
			}
		}
		return instance;
	}

	@Override
	public void constructTable(final List<ExplorationSite> sites)
	{
		HtmlTable table = constructAndGetTableObject();
		setTable(table.appendTag());
	}

	@Override
	String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", 75, 2, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", 75, 2, 1,
						new String[]{"Versuch Nr."}),
				HtmlFactory.createHeader("NormalTableHeader", 95, 2, 1,
						new String[]{"Probenart"}),
				HtmlFactory.createHeader("NormalTableHeader", 165, 2, 1,
						new String[]{"Prüfschicht"}),
				HtmlFactory.createHeader("NormalTableHeader", 100, 1, 3,
						new String[]{"Prüftiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", 95, 1, 1,
						new String[]{"Erw. RuK", "<div>[31]</div>"}),
		});

		String secondRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits", 100, 1, 3,
						new String[]{"cm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 95, 1, 1,
						new String[]{"°C"}),
		});

		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow);

		return stringBuilder.toString();
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
	public void constructTemplate(List<DataTable> tables)
	{
		HtmlTable table = constructAndGetTableObject();

		//TODO ADD PAGE BREAK

		for (DataTable dataTable : tables)
		{
			if (dataTable instanceof Probe)
			{
				Probe probe = (Probe) dataTable;

				for (Sample sample : probe.getSamples())
				{
					Parameter parameter = sample.getParameterBy(ReferenceSample.RUK_ID);

					if (parameter != null)
					{
						String row = HtmlFactory.createRow("Normal", new String[]{
								HtmlFactory.createCell("Normal", "center",
										new String[]{probe.get(ReferenceProbe.ID)}),
								HtmlFactory.createCell("Normal", "center",
										new String[]{parameter.get(ReferenceParameterRuK.ID)}),
								HtmlFactory.createCell("Normal", "center",
										new String[]{parameter.get(ReferenceParameterRuK.SAMPLE)}),
								HtmlFactory.createCell("Normal", "left",
										new String[]{sample.get(ReferenceSample.TYPE), " ",
												sample.get(ReferenceSample.GRANULATION)}),
								HtmlFactory.createCell("Normal", 35, "center",
										new String[]{sample.get(ReferenceSample.DEPTH_START)}),
								HtmlFactory.createCell("Normal", 30, "center",
										new String[]{"-"}),
								HtmlFactory.createCell("Normal", 35, "center",
										new String[]{sample.get(ReferenceSample.DEPTH_END)}),
								HtmlFactory.createCell("Normal", "center",
										new String[]{parameter.get(ReferenceParameterRuK.VALUE)})
						});

						table.appendContent(row);
					}
				}
			}
		}
		setTable(table.appendTag());

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
