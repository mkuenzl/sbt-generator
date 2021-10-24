package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.LpKey;
import sbt.automization.core.data.key.ProbeKey;

public class Ev2Retrieval extends DatatableInformationRetrieval
{
	public Ev2Retrieval()
	{
		super(LpKey.EV2);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"E<sub>V2</sub><sup>[41]</sup>,", formatUnit("MN/m²")})
//		});
//
//		return row;
//	}
	
	@Override
	String retrieveFrom(Sample sample)
	{
		String parameterValue = sample.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV2);
		
		return parameterValue;
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String parameterValue = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV2);
		
		return parameterValue;
	}
}