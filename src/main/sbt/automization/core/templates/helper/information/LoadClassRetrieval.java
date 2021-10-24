package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;

public class LoadClassRetrieval extends DatatableInformationRetrieval
{
	public LoadClassRetrieval()
	{
		super(ProbeKey.LOAD_CLASS);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Belastungsklasse,", formatUnit("RStO<sup>[5]</sup>")})
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
