package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;

public class WearPlanumRetrieval extends DatatableInformationRetrieval
{
	public WearPlanumRetrieval()
	{
		super(ProbeKey.WEAR_PLANUM);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Tragfähigkeit", UtilityPrinter.printLineBreak(), "Planum", formatUnit("Soll: E<sub>V2</sub> >= 45 MN/m²".concat(UtilityPrinter.printLineBreak()).concat("Ansatz Planum: FOK -60cm"))})
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
		return probe.get(informationKey);
	}
}
