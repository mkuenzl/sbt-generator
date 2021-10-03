package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.LpKey;
import sbt.automization.data.key.ProbeKey;

public class EvMinimumBorderRow extends DatatableInformationRetrieval
{
	public EvMinimumBorderRow()
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
		String information = probe.getParameterValueBy(ProbeKey.LP_ID, informationKey);

		return information;
	}
}