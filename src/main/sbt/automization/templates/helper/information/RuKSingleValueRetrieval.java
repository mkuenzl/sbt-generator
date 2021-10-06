package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.RuKKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.util.CheckDataAvailability;

public class RuKSingleValueRetrieval extends DatatableInformationRetrieval
{
	public RuKSingleValueRetrieval()
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
		if (!"".equals(sample.get(informationKey)))
		return "77";
		else return "";
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		if (CheckDataAvailability.thereExistsAnSampleWithData(probe, outcrop.toString(), informationKey))
		{
			return "77";
		}
		else
		{
			return "";
		}
	}
}