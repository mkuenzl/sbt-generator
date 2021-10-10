package sbt.automization.core.templates.helper.strategies;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Sample;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.styles.StyleParameter;

import java.util.List;

public final class CellPerSample extends CellRow
{
	public CellPerSample(StyleParameter styleParameter)
	{
		super(styleParameter);
	}

	public CellPerSample()
	{
		super();
	}

	@Override
	void createCells(List<DataTable> probes)
	{
		for (DataTable probe : probes)
		{
			for (Sample sample : probe.getSamples())
			{
				HtmlCell cell = createCell(sample);
				cells.add(cell);
			}
		}
	}
}
