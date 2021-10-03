package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.LpKey;
import sbt.automization.data.key.ProbeKey;

public class EvDynRow extends DatatableInformationRetrieval
{
	public EvDynRow()
	{
		super(LpKey.EV);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"E<sub>Vdyn</sub>,", formatUnit("MN/m²")})
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
		String parameterValue = probe.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV);

		return parameterValue;
	}
}
