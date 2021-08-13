package sbt.automization.templates.appendix;


import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Parameter;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.references.ReferenceParameterLP;
import sbt.automization.data.refactoring.references.ReferenceProbe;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class AppendixLP extends AppendixTemplate
{
	private static AppendixLP instance;

	private AppendixLP() {}

	public static AppendixLP getInstance()
	{
		if (instance == null)
		{
			synchronized (AppendixLP.class)
			{
				if (instance == null)
				{
					instance = new AppendixLP();
				}
			}
		}
		return instance;
	}

	@Override
	public void constructTable(final List<ExplorationSite> explorationSites)
	{
		HtmlTable table = constructAndGetTableObject();

		setTable(table.appendTag());
	}

	@Override
	String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow(2, new String[]{
				HtmlFactory.createHeader("NormalTableHeader", 55, 3, 1,
						new String[]{"Versuch", TextFormatUtil.printLineBreak(), "Nr."}),
				HtmlFactory.createHeader("NormalTableHeader", 65, 3, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", 185, 3, 1,
						new String[]{"Lage der Messstelle"}),
				HtmlFactory.createHeader("NormalTableHeader", 160, 1, 4,
						new String[]{"Setzung"}),
				HtmlFactory.createHeader("NormalTableHeader", 45, 2, 1,
						new String[]{"E<sub>Vdyn</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", 45, 2, 1,
						new String[]{"E<sub>Vdyn</sub>", TextFormatUtil.printLineBreak(), "<sub>(-15%)</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", 45, 2, 1,
						new String[]{"E<sub>V2</sub>", "<div>[41]</div>"})
		});

		String secondRow = HtmlFactory.createRow(new String[]{
				HtmlFactory.createHeader("NormalTableHeader", 40,
						new String[]{"S<sub>1</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", 40,
						new String[]{"S<sub>2</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", 40,
						new String[]{"S<sub>3</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", 40,
						new String[]{"x̅"})
		});

		String thirdRow = HtmlFactory.createRow(new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits", 40,
						new String[]{"mm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 40,
						new String[]{"mm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 40,
						new String[]{"mm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 40,
						new String[]{"mm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 45,
						new String[]{"MN/m²"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 45,
						new String[]{"MN/m²"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 45,
						new String[]{"MN/m²"})
		});


		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);

		return stringBuilder.toString();
	}

	@Override
	HtmlTable constructAndGetTableObject()
	{
		return new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();
	}

	@Override
	public void constructTemplate(List<DataTable> tables)
	{
		HtmlTable table = constructAndGetTableObject();

		for (DataTable dataTable : tables)
		{
			if (dataTable instanceof Probe)
			{
				Probe probe = (Probe) dataTable;

				Parameter parameter = probe.getParameterBy(ReferenceProbe.LP_ID);

				if (parameter != null)
				{
					String formattedEV2 = TextFormatUtil.formatLP(parameter.get(ReferenceParameterLP.EV2),
							parameter.get(ReferenceParameterLP.EV85));

					String row = HtmlFactory.createRow("Normal", new String[]{
							HtmlFactory.createCell("Normal", 55, "center",
									new String[]{dataTable.get(ReferenceProbe.LP_ID)}),
							HtmlFactory.createCell("Normal", 65, "center",
									new String[]{dataTable.get(ReferenceProbe.ID)}),
							HtmlFactory.createCell("Normal", 185, "left",
									new String[]{dataTable.get(ReferenceProbe.LOCATION)}),
							HtmlFactory.createCell("Normal", 40, "center",
									new String[]{parameter.get(ReferenceParameterLP.VALUE_1)}),
							HtmlFactory.createCell("Normal", 40, "center",
									new String[]{parameter.get(ReferenceParameterLP.VALUE_2)}),
							HtmlFactory.createCell("Normal", 40, "center",
									new String[]{parameter.get(ReferenceParameterLP.VALUE_3)}),
							HtmlFactory.createCell("Normal", 40, "center",
									new String[]{parameter.get(ReferenceParameterLP.MEAN)}),
							HtmlFactory.createCell("Normal", 45, "center",
									new String[]{parameter.get(ReferenceParameterLP.EV)}),
							HtmlFactory.createCell("Normal", 45, "center",
									new String[]{parameter.get(ReferenceParameterLP.EV85)}),
							HtmlFactory.createCell("Normal", 45, "center",
									new String[]{formattedEV2}),
					});

					table.appendContent(row);
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
		return "Anlage-LP";
	}
}
