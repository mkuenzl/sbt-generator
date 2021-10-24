package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.LpKey;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.format.text.LoadPlateTextFormatter;

public class Ev2WithEv85Retrieval extends DatatableInformationRetrieval
{
	public Ev2WithEv85Retrieval()
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
		Probe probe = sample.getProbe();
		
		return retrieveFrom(probe);
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String ev2 = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV2);
		String ev85 = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85);
		
		String information = new LoadPlateTextFormatter().format(ev2, ev85);
		
		return information;
	}
}
