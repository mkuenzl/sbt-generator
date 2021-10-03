package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.UtilityPrinter;

public class HeapExposureRow extends DatatableInformationRetrieval
{
	public HeapExposureRow()
	{
		super(SampleKey.ID);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Aufschlussart"})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		return "Haufwerks-".concat(UtilityPrinter.printLineBreak()).concat("beprobung");
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		return "Haufwerks-".concat(UtilityPrinter.printLineBreak()).concat("beprobung");
	}
}