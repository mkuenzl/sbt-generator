package sbt.automization.core.templates.construction.strategies;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Sample;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.styles.StyleParameter;

import java.util.List;

public final class CellPerSampleCombined extends CellRow
{
	public CellPerSampleCombined(StyleParameter styleParameter)
	{
		super(styleParameter);
	}
	
	public CellPerSampleCombined()
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
		
		return firstContent.equals(secondContent) && !"-".equals(firstContent);
	}
}
