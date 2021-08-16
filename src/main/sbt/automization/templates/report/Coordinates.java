package sbt.automization.templates.report;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

import java.util.Arrays;
import java.util.List;

public final class Coordinates extends Report
{
	private static Coordinates instance;

	private Coordinates() {}

	public static Coordinates getInstance()
	{
		if (instance == null)
		{
			synchronized (Coordinates.class)
			{
				if (instance == null)
				{
					instance = new Coordinates();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "Bericht-KOORDINATEN";
	}

	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:75px", 2, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", 1, 4,
						new String[]{"UTM"})
		});

		String secondRow = HtmlFactory.createRow("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:75px",
						new String[]{"Zone"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:75px",
						new String[]{"Ostwert"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:75px",
						new String[]{"Nordwert"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:305px",
						new String[]{""})
		});

		StringBuilder strb = new StringBuilder()
				.append(firstRow)
				.append(secondRow);

		return strb.toString();
	}

	@Override
	String buildTechnicalFeatures(List<DataTable> dataTables)
	{
		return null;
	}

	@Override
	String buildEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		return null;
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		HtmlTable table = constructAndGetTableObject();
		table.appendContent(constructAndGetTableHeader());

		for (DataTable dataTable : dataTables)
		{
			String rows = createRows(dataTable);
			table.appendContent(rows);
		}

		addToTemplate(table.appendTag());
	}

	private String createRows(DataTable dataTable)
	{
		StringBuilder strb = new StringBuilder();

		String firstRow = HtmlFactory.createRow("NormalThin", new String[]{
				HtmlFactory.createCellAsString("NormalCenter", 2, 1,
						new String[]{dataTable.get(RefProbe.ID)}),
				HtmlFactory.createCellAsString("Normal", 1, 4,
						new String[]{dataTable.get(RefProbe.LOCATION)})
		});

		List<String> coordinateSplit = splitCoordinate(dataTable.get(RefProbe.COORDINATES));

		String secondRow = HtmlFactory.createRow("NormalThin", new String[]{
				HtmlFactory.createCellAsString("Normal",
						new String[]{coordinateSplit.get(0)}),
				HtmlFactory.createCellAsString("Normal",
						new String[]{coordinateSplit.get(1)}),
				HtmlFactory.createCellAsString("Normal",
						new String[]{coordinateSplit.get(2)}),
				HtmlFactory.createCellAsString("Normal",
						new String[]{""})
		});

		strb.append(firstRow)
				.append(secondRow);

		return strb.toString();
	}

	@Override
	public void constructTemplate(sbt.automization.data.refactoring.DataTable dataTable)
	{

	}

	private List<String> splitCoordinate(String coordinates)
	{
		List<String> coordinateList;

		String[] split = coordinates.split("(\\s)+");

		if (split.length == 3)
		{
			coordinateList = Arrays.asList(split);
		} else
		{
			coordinateList = Arrays.asList("-", "-", "-");
		}

		return coordinateList;
	}
}
