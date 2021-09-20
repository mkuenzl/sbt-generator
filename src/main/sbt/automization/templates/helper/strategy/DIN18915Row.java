package sbt.automization.templates.helper.strategy;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;


public class DIN18915Row extends RowConstruction
{
	public DIN18915Row()
	{
		super(SampleKey.SOIL_CLASS);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Bodengruppe,", formatUnit("DIN 18915<sup>[37]</sup>")})
		});

		return row;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		String din = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{din});

		return cell.appendTag();
	}

	@Override
	String createCellFrom(Sample sample)
	{
		String cell = HtmlFactory.createChemistryCell(sample.get(key));

		return cell;
	}
}