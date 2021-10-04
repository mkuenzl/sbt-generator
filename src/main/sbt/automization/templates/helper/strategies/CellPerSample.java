package sbt.automization.templates.helper.strategies;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.html.HtmlCell;
import sbt.automization.styles.StyleParameter;

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
