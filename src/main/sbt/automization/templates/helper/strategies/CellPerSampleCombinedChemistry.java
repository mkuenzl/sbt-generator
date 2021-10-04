package sbt.automization.templates.helper.strategies;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.html.HtmlCell;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public final class CellPerSampleCombinedChemistry extends CellRow
{
	public CellPerSampleCombinedChemistry(StyleParameter styleParameter)
	{
		super(styleParameter);
	}

	public CellPerSampleCombinedChemistry()
	{
		super();
	}

	@Override
	void createCells(List<DataTable> probes)
	{
		for (DataTable probe : probes)
		{
			HtmlCell lastCell = null;
			String lastCellChemistryId = null;
			int columnSpan = 1;

			for (Sample sample : probe.getSamples())
			{
				String cellChemistryId = sample.get(SampleKey.CHEMISTRY_ID);
				HtmlCell cell = createCell(sample);

				//case 1 sample
				if (null == lastCell)
				{
					lastCell = cell;
					lastCellChemistryId = cellChemistryId;
					continue;
				}

				if (compareContent(lastCell, cell) && lastCellChemistryId.equals(cellChemistryId))
				{
					lastCell = cell;
					lastCellChemistryId = cellChemistryId;
					columnSpan++;
				} else
				{
					lastCell.appendAttribute("colspan", String.valueOf(columnSpan));
					cells.add(new HtmlCell(lastCell));

					columnSpan = 1;
					lastCell = cell;
					lastCellChemistryId = cellChemistryId;
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
