package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.RuKKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.RuKPrinter;

public class RuKCombinedRetrieval extends DatatableInformationRetrieval
{
	public RuKCombinedRetrieval()
	{
		super(SampleKey.RUK_ID);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Erweichungspunkt",
//								UtilityPrinter.printLineBreak(),
//								"RuK<sup>[31]</sup>,",
//								formatUnit("°C")})
//		});
//
//		return row;
//	}
	
	
	@Override
	String retrieveFrom(Sample sample)
	{
		String information = sample.getParameterValueBy(SampleKey.RUK_ID, RuKKey.VALUE);
		
		return information;
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new RuKPrinter().printRukLayers(probe, outcrop.toString());
		
		return information;
	}
}