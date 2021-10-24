package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class DIN19682Retrieval extends DatatableInformationRetrieval
{
	public DIN19682Retrieval()
	{
		super(SampleKey.SOIL_TYPE);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Bodenarten-", UtilityPrinter.printLineBreak(), "hauptgruppe,", formatUnit("DIN 19682-2<sup>[24]</sup>")})
//		});
//
//		return row;
//	}
	
	@Override
	String retrieveFrom(Sample sample)
	{
		String information = new SamplePrinter().printAttributeOfDatatable(sample, informationKey);
		
		return information;
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop.toString(), informationKey);
		
		return information;
	}
}