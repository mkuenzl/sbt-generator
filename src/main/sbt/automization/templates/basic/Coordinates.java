package sbt.automization.templates.basic;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.html.HtmlFactory;

import java.util.Arrays;
import java.util.List;

public final class Coordinates extends TableTemplate
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
		return "KOORDINATEN-Tabelle";
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		createTable();
		addTableHeader();

		for (DataTable dataTable : dataTables)
		{
			addAndResetTableOnPageBreak();

			addRows(dataTable);

			linesPerPage++;
		}

		addTable();
	}

	@Override
	void addTableHeader()
	{
		String firstRow = HtmlFactory.createRowAsString("NormalThinHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:75px", 2, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", 1, 4,
						new String[]{"UTM"})
		});

		addToTable(firstRow);

		String secondRow = HtmlFactory.createRowAsString("NormalThinHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", "width:75px",
						new String[]{"Zone"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:75px",
						new String[]{"Ostwert"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:75px",
						new String[]{"Nordwert"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:305px",
						new String[]{""})
		});

		addToTable(secondRow);
	}

	private void addRows(DataTable dataTable)
	{
		String firstRow = HtmlFactory.createRowAsString("NormalThin5", new String[]{
				HtmlFactory.createCellAsString("NormalCenter", 2, 1,
						new String[]{dataTable.get(ProbeKey.ID)}),
				HtmlFactory.createCellAsString("Normal", 1, 4,
						new String[]{dataTable.get(ProbeKey.LOCATION)})
		});

		addToTable(firstRow);

		List<String> coordinateSplit = splitCoordinate(dataTable.get(ProbeKey.COORDINATES));

		String secondRow = HtmlFactory.createRowAsString("NormalThin5", new String[]{
				HtmlFactory.createCellAsString("Normal",
						new String[]{coordinateSplit.get(0)}),
				HtmlFactory.createCellAsString("Normal",
						new String[]{coordinateSplit.get(1)}),
				HtmlFactory.createCellAsString("Normal",
						new String[]{coordinateSplit.get(2)}),
				HtmlFactory.createCellAsString("Normal",
						new String[]{""})
		});

		addToTable(secondRow);
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

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
