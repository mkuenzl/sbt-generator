package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;

public class PitchHalfQuantitativeRetrieval extends DatatableInformationRetrieval
{
	public PitchHalfQuantitativeRetrieval()
	{
		super(ProbeKey.PITCH_HALF_QUANTITATIVE);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Pechnachweis",
//								UtilityPrinter.printLineBreak(),
//								"halbquantitativ"})
//		});
//
//		return row;
//	}
	
	@Override
	String retrieveFrom(Sample sample)
	{
		Probe probe = sample.getProbe();
		
		return probe.get(informationKey);
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String information = probe.get(informationKey);
		
		return information;
	}
}