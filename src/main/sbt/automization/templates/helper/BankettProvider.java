package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;

import java.util.List;

public final class BankettProvider extends RowProvider
{
	public BankettProvider()
	{
		super("BANKETT");
	}

	@Override
	public String createLegendRow(List<DataTable> dataTables)
	{
		int size = Integer.valueOf(headerCellWidth) + dataTables.size()* Integer.valueOf(normalCellWidth);

		return null;
	}
}
