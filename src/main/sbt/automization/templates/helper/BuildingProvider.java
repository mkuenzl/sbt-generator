package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;

import java.util.List;

public class BuildingProvider extends RowProvider
{
	public BuildingProvider()
	{
		super(Outcrop.BUILDING.toString());
	}

	@Override
	public String createLegendRow(List<DataTable> dataTables)
	{
		return null;
	}
}
