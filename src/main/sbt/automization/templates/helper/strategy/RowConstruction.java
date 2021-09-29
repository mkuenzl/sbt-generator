package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.SampleKey;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;
import sbt.automization.styles.StyleParameter;
import sbt.automization.util.CheckDataAvailability;

import java.util.List;

public abstract class RowConstruction implements RowStrategy
{
	protected String outcrop;
	protected StyleParameter styleParameter;
	protected Key key;
	protected List<DataTable> probes;

	private HtmlRow row;
	private boolean checkDataAvailability = true;

	public RowConstruction(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		this.probes = probes;
		this.outcrop = outcrop;
		this.key = key;
		this.styleParameter = styleParameter;
	}

	public RowConstruction(Key key)
	{
		this.key = key;
	}

	public String buildWithProbes()
	{
		if (checkDataAvailability && ! CheckDataAvailability.thereExistsAnTableWithData(probes, outcrop, key)) return "";

		initializeRow();
		createCellForEachProbe();
		return row.appendTag();
	}

	private void initializeRow()
	{
		this.row = createRow();
	}

	private void createCellForEachProbe()
	{
		for (DataTable probe : probes)
		{
			HtmlCell cell = createCellFrom((Probe) probe);
			row.appendContent(cell.appendTag());
		}
	}

	abstract HtmlRow createRow();

	abstract HtmlCell createCellFrom(Probe probe);

	public String buildWithSamples()
	{
		if (checkDataAvailability && ! CheckDataAvailability.thereExistsAnTableWithData(probes, outcrop, key)) return "";

		initializeRow();
		createCellForEachSample();
		return row.appendTag();
	}

	private void createCellForEachSample()
	{
		for (DataTable probe : probes)
		{
			for (Sample sample : probe.getSamples())
			{
				HtmlCell cell = createCellFrom(sample);
				row.appendContent(cell.appendTag());
			}
		}
	}

	abstract HtmlCell createCellFrom(Sample sample);

	public String buildWithSamplesCombined()
	{
		if (checkDataAvailability && ! CheckDataAvailability.thereExistsAnTableWithData(probes, outcrop, key)) return "";

		initializeRow();
		createCombinedCellsForSamples();
		return row.appendTag();
	}

	private void createCombinedCellsForSamples()
	{
		for (DataTable probe : probes)
		{
			HtmlCell lastCell = null;
			int columnSpan = 1;

			for (Sample sample : probe.getSamples())
			{
				HtmlCell cell = createCellFrom(sample);

				//case 1 sample
				if (null == lastCell)
				{
					lastCell = cell;
					continue;
				}

				if (checkCellContent(lastCell, cell))
				{
					lastCell = cell;
					columnSpan++;
				} else
				{
					lastCell.appendAttribute("colspan", String.valueOf(columnSpan));
					row.appendContent(lastCell.appendTag());
					columnSpan = 1;
					lastCell = cell;
				}
			}
			lastCell.appendAttribute("colspan", String.valueOf(columnSpan));
			row.appendContent(lastCell.appendTag());
		}
	}

	public String buildWithChemistrySamplesCombined()
	{
		if (checkDataAvailability && ! CheckDataAvailability.thereExistsAnTableWithData(probes, outcrop, key)) return "";

		initializeRow();
		createCombinedCellsForSameChemistrySamples();
		return row.appendTag();
	}

	private void createCombinedCellsForSameChemistrySamples()
	{
		for (DataTable probe : probes)
		{
			HtmlCell lastCell = null;
			String lastCellChemistryId = null;
			int columnSpan = 1;

			for (Sample sample : probe.getSamples())
			{
				String cellChemistryId = sample.get(SampleKey.CHEMISTRY_ID);
				HtmlCell cell = createCellFrom(sample);

				//case 1 sample
				if (null == lastCell)
				{
					lastCell = cell;
					lastCellChemistryId = cellChemistryId;
					continue;
				}

				if (checkCellContent(lastCell, cell) && lastCellChemistryId.equals(cellChemistryId))
				{
					lastCell = cell;
					lastCellChemistryId = cellChemistryId;
					columnSpan++;
				} else
				{
					lastCell.appendAttribute("colspan", String.valueOf(columnSpan));
					row.appendContent(lastCell.appendTag());
					columnSpan = 1;
					lastCell = cell;
					lastCellChemistryId = cellChemistryId;
				}
			}
			lastCell.appendAttribute("colspan", String.valueOf(columnSpan));
			row.appendContent(lastCell.appendTag());
		}
	}

	private boolean checkCellContent(HtmlCell first, HtmlCell second)
	{
		String firstContent = first.getContent();
		String secondContent = second.getContent();

		return firstContent.equals(secondContent) && ! "-".equals(firstContent);
	}

	protected String formatUnit(String text)
	{
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", styleParameter.getUnitCellClass())
				.appendContent(text)
				.build()
				.appendTag();

		return formattedUnitText;
	}

	public void setStyle(StyleParameter styleParameter)
	{
		this.styleParameter = styleParameter;
	}

	public void setOutcrop(String outcrop)
	{
		this.outcrop = outcrop;
	}

	public void setTables(List<DataTable> dataTables)
	{
		this.probes = dataTables;
	}

	public void setCheckData(boolean printAlways)
	{
		this.checkDataAvailability = printAlways;
	}
}
