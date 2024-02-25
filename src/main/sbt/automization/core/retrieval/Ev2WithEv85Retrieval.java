package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.LpKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.text.LoadPlateTextFormatterExclusiveEv85Value;
import sbt.automization.core.format.text.StandardCellTextFormatter;

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
		String ev2 = sample.getParameterValueBy(SampleKey.LP_ID, LpKey.EV2);
		String ev85 = sample.getParameterValueBy(SampleKey.LP_ID, LpKey.EV85);

		return new LoadPlateTextFormatterExclusiveEv85Value().format(ev2, ev85);
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (Sample sample : probe.getSamples())
		{
			String ev2 = sample.getParameterValueBy(SampleKey.LP_ID, LpKey.EV2);
			String ev85 = sample.getParameterValueBy(SampleKey.LP_ID, LpKey.EV85);
			
			if ("".equals(ev2)) continue;
			if (stringBuilder.length() != 0) stringBuilder.append("<br>");
			
			String information = new LoadPlateTextFormatterExclusiveEv85Value().format(ev2, ev85);
			stringBuilder.append(information);
		}
		
		return new StandardCellTextFormatter().format(stringBuilder.toString());
	}
}
