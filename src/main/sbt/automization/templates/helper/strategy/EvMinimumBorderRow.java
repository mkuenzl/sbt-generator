package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class EvMinimumBorderRow extends RowConstructionStrategy
{
	public EvMinimumBorderRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Soll Wert,", formatUnit("E<sub>V2</sub>")})
		});

		return row;
	}

	@Override
	String createCellFromProbe(DataTable table)
	{
		String minBorder = table.getParameterValueBy(ProbeKey.LP_ID, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{minBorder});

		return cell.appendTag();
	}

	@Override
	String createCellFromSample(DataTable table)
	{
		String minBorder = table.getParameterValueBy(ProbeKey.LP_ID, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{minBorder});

		return cell.appendTag();
	}
}