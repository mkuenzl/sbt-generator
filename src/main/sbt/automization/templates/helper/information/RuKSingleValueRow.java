package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;

public class RuKSingleValueRow extends DatatableInformationRetrieval
{
	public RuKSingleValueRow()
	{
		super(SampleKey.RUK_ID);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Soll Einzelwert,",
//								formatUnit("RuK")})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		return "77";
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		return "77";
	}
}