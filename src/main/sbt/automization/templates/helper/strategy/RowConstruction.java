package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
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
			String cell = createCellFrom((Probe) probe);
			row.appendContent(cell);
		}
	}

	abstract HtmlRow createRow();

	abstract String createCellFrom(Probe probe);

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
				String cell = createCellFrom(sample);
				row.appendContent(cell);
			}
		}
	}

	abstract String createCellFrom(Sample sample);

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
}
