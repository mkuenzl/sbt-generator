package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.html.HtmlCell;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class CombinedSampleCellStrategy extends CellStrategy
{
	public CombinedSampleCellStrategy(StyleParameter styleParameter)
	{
		super(styleParameter);
	}

	public CombinedSampleCellStrategy()
	{
		super();
	}

	@Override
	void createCells(List<DataTable> probes)
	{
		for (DataTable probe : probes)
		{
			HtmlCell lastCell = null;
			int columnSpan = 1;

			for (Sample sample : probe.getSamples())
			{
				HtmlCell cell = createCell(sample);

				//case 1 sample
				if (null == lastCell)
				{
					lastCell = cell;
					continue;
				}

				if (compareContent(lastCell, cell))
				{
					lastCell = cell;
					columnSpan++;
				} else
				{
					lastCell.appendAttribute("colspan", String.valueOf(columnSpan));
					cells.add(new HtmlCell(lastCell));
					columnSpan = 1;
					lastCell = cell;
				}
			}
			lastCell.appendAttribute("colspan", String.valueOf(columnSpan));

			cells.add(new HtmlCell(lastCell));
		}
	}

	private boolean compareContent(HtmlCell first, HtmlCell second)
	{
		String firstContent = first.getContent();
		String secondContent = second.getContent();

		return firstContent.equals(secondContent) && ! "-".equals(firstContent);
	}
}
