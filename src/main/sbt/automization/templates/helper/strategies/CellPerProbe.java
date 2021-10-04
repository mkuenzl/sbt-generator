package sbt.automization.templates.helper.strategies;

import sbt.automization.data.DataTable;
import sbt.automization.html.HtmlCell;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public final class CellPerProbe extends CellRow
{
	public CellPerProbe(StyleParameter styleParameter)
	{
		super(styleParameter);
	}

	public CellPerProbe()
	{
		super();
	}

	@Override
	void createCells(List<DataTable> probes)
	{
		for (DataTable probe : probes)
		{
			HtmlCell cell = createCell(probe);
			cells.add(cell);
		}
	}
}
