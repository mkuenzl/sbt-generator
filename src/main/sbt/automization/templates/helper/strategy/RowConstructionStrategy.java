package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.LpKey;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;
import sbt.automization.styles.ReportStyle;
import sbt.automization.styles.StyleParameter;
import sbt.automization.util.CheckDataAvailability;

import java.util.List;

public abstract class RowConstructionStrategy
{
	private final List<DataTable> probes;
	protected String outcrop;
	protected final Key key;
	protected StyleParameter styleParameter;
	private HtmlRow row;

	public RowConstructionStrategy(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		this.probes = probes;
		this.outcrop = outcrop;
		this.key = key;
		this.styleParameter = styleParameter;
	}

	public RowConstructionStrategy(List<DataTable> probes, String outcrop, Key key)
	{
		this.probes = probes;
		this.outcrop = outcrop;
		this.key = key;
	}

	public RowConstructionStrategy(List<DataTable> probes, Key key)
	{
		this.probes = probes;
		this.outcrop = null;
		this.key = key;
	}

	public String buildWithProbes()
	{
		if (! CheckDataAvailability.thereExistsAnTableWithData(probes, outcrop, key)) return "";

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
			String cell = createCellFromProbe(probe);
			row.appendContent(cell);
		}
	}

	abstract HtmlRow createRow();

	abstract String createCellFromProbe(DataTable table);

	public String buildWithSamples()
	{
		if (! CheckDataAvailability.thereExistsAnTableWithData(probes, outcrop, key)) return "";

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
				String cell;
				if (key instanceof ProbeKey || key instanceof LpKey)
				{
					cell = createCellFromSample(probe);
				} else
				{
					cell = createCellFromSample(sample);
				}
				row.appendContent(cell);
			}
		}
	}

	abstract String createCellFromSample(DataTable table);

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
}
