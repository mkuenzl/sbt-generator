package sbt.automization.templates.helper.strategy;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;

public class MaterialHeapRow extends RowConstruction
{
	public MaterialHeapRow()
	{
		super(SampleKey.WASTE_TYPE);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Material"})
		});

		return row;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{new SamplePrinter().printAttributeOfSamples(probe, outcrop, key)});

		return cell.appendTag();
	}

	@Override
	String createCellFrom(Sample sample)
	{
		Probe probe = sample.getProbe();

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{sample.get(key)});

		return cell.appendTag();
	}
}