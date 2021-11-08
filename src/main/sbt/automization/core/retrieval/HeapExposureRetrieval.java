package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;

public class HeapExposureRetrieval extends DatatableInformationRetrieval
{
	public HeapExposureRetrieval()
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