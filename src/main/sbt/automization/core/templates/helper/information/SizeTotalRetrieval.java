package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class SizeTotalRetrieval extends DatatableInformationRetrieval
{
	public SizeTotalRetrieval()
	{
		super(SampleKey.ID);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Gesamtdicke,", formatUnit("cm")})
//		});
//
//		return row;
//	}
	
	@Override
	String retrieveFrom(Sample sample)
	{
		String information = new SamplePrinter().printThickness(sample.getSamples());
		
		return information;
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printThickness(probe.getSamples());
		
		return information;
	}
}