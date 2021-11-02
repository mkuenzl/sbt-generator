package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.LpKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class EvMinimumBorderRetrieval extends DatatableInformationRetrieval
{
	public EvMinimumBorderRetrieval()
	{
		super(LpKey.EV2_TARGET);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Soll Wert,", formatUnit("E<sub>V2</sub>")})
//		});
//
//		return row;
//	}
	
	@Override
	String retrieveFrom(Sample sample)
	{
		Probe probe = sample.getProbe();
		
		return retrieveFrom(probe);
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printParameterOfSamples(probe, outcrop.toString(), informationKey);
		
		return information;
	}
}