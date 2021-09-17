package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class SizeRow extends RowConstructionStrategy
{
	public SizeRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Dicke,", formatUnit("cm")})
		});

		return row;
	}

	@Override
	String createCellFromProbe(DataTable table)
	{
		List<Sample> samples = table.getSamplesBy(SampleKey.OUTCROP, outcrop);
		String size = new SamplePrinter().printThickness(samples);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{size});

		return cell.appendTag();
	}

	@Override
	String createCellFromSample(DataTable table)
	{
		List<Sample> samples = table.getSamplesBy(SampleKey.OUTCROP, outcrop);
		String size = new SamplePrinter().printThickness(samples);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{size});

		return cell.appendTag();
	}
}
