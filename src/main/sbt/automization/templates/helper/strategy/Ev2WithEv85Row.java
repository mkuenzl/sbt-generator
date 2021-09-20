package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.LpKey;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.format.text.LoadPlateTextFormatter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class Ev2WithEv85Row extends RowConstruction
{
	public Ev2WithEv85Row(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	public Ev2WithEv85Row()
	{
		super(LpKey.EV2);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"E<sub>V2</sub><sup>[41]</sup>,", formatUnit("MN/m²")})
		});

		return row;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		String ev2 = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV2);
		String ev85 = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85);


		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{new LoadPlateTextFormatter().format(ev2, ev85)});

		return cell.appendTag();
	}

	@Override
	String createCellFrom(Sample sample)
	{
		String ev2 = sample.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV2);
		String ev85 = sample.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{new LoadPlateTextFormatter().format(ev2, ev85)});

		return cell.appendTag();
	}
}
