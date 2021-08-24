package sbt.automization.templates.appendix;


import sbt.automization.data.DataTable;
import sbt.automization.data.Parameter;
import sbt.automization.data.Probe;
import sbt.automization.data.key.LpKey;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.format.text.LoadPlateTextFormatter;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlTable;

import java.util.List;

public final class LoadPlate extends Appendix
{
	private static LoadPlate instance;

	private LoadPlate() {
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
	protected String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow(2, new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:55px", 3, 1,
						new String[]{"Versuch", UtilityPrinter.printLineBreak(), "Nr."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:65px", 3, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:185px", 3, 1,
						new String[]{"Lage der Messstelle"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:160px", 1, 4,
						new String[]{"Setzung"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:45px", 2, 1,
						new String[]{"E<sub>Vdyn</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:45px", 2, 1,
						new String[]{"E<sub>Vdyn</sub>", UtilityPrinter.printLineBreak(), "<sub>(-15%)</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:45px", 2, 1,
						new String[]{"E<sub>V2</sub>", "<div>[41]</div>"})
		});

		String secondRow = HtmlFactory.createRow(new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:40px",
						new String[]{"S<sub>1</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:40px",
						new String[]{"S<sub>2</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:40px",
						new String[]{"S<sub>3</sub>"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:40px",
						new String[]{"x̅"})
		});

		String thirdRow = HtmlFactory.createRow(new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"mm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"mm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"mm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"mm"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"MN/m²"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"MN/m²"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"MN/m²"})
		});


		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow);

		return stringBuilder.toString();
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		createTable();

		for (DataTable dataTable : dataTables)
		{
			if (dataTable instanceof Probe)
			{
				Probe probe = (Probe) dataTable;

				Parameter parameter = probe.getParameterBy(ProbeKey.LP_ID);

				if (parameter != null)
				{
					String formattedEV2 = new LoadPlateTextFormatter().format(parameter.get(LpKey.EV2),
							parameter.get(LpKey.EV85));

					String row = HtmlFactory.createRow("Normal", new String[]{
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{dataTable.get(ProbeKey.LP_ID)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{dataTable.get(ProbeKey.ID)}),
							HtmlFactory.createCellAsString("Normal",
									new String[]{dataTable.get(ProbeKey.LOCATION)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{parameter.get(LpKey.VALUE_1)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{parameter.get(LpKey.VALUE_2)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{parameter.get(LpKey.VALUE_3)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{parameter.get(LpKey.MEAN)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{parameter.get(LpKey.EV)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{parameter.get(LpKey.EV85)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{formattedEV2}),
					});

					this.table.appendContent(row);
				}
			}
		}
		addToTemplate(this.table.appendTag());
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Anlage-LP";
	}
}
