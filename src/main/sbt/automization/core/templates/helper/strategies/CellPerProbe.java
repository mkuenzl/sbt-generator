package sbt.automization.core.templates.helper.strategies;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.styles.StyleParameter;

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
