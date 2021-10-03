package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.html.HtmlCell;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class ProbeCellStrategy extends CellStrategy
{
	public ProbeCellStrategy(StyleParameter styleParameter)
	{
		super(styleParameter);
	}

	public ProbeCellStrategy()
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
