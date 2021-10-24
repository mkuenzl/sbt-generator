package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.LpKey;
import sbt.automization.core.data.key.ProbeKey;

public class EvDyn85Retrieval extends DatatableInformationRetrieval
{
	public EvDyn85Retrieval()
	{
		super(LpKey.EV85);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"E<sub>Vdyn (-15%)</sub>,", formatUnit("MN/m²")})
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
		String parameterValue = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85);
		
		return parameterValue;
	}
}