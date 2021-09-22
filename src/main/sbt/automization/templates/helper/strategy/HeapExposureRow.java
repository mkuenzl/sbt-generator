package sbt.automization.templates.helper.strategy;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;

public class HeapExposureRow extends RowConstruction
{
	public HeapExposureRow()
	{
		super(SampleKey.ID);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Aufschlussart"})
		});

		return row;
	}

	@Override
	HtmlCell createCellFrom(Probe probe)
	{
		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{"Haufwerks-", UtilityPrinter.printLineBreak(), "beprobung"});

		return cell;
	}

	@Override
	HtmlCell createCellFrom(Sample sample)
	{
		Probe probe = sample.getProbe();

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{"Haufwerks-", UtilityPrinter.printLineBreak(), "beprobung"});

		return cell;
	}
}