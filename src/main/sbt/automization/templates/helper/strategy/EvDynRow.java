package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.LpKey;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class EvDynRow extends RowConstruction
{
	public EvDynRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	public EvDynRow()
	{
		super(LpKey.EV);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"E<sub>Vdyn</sub>,", formatUnit("MN/m²")})
		});

		return row;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		String parameterValue = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{parameterValue});

		return cell.appendTag();
	}

	@Override
	String createCellFrom(Sample sample)
	{
		String parameterValue = sample.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{parameterValue});

		return cell.appendTag();
	}
}
