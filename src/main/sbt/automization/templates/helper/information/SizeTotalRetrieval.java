package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;

public class SizeTotalRetrieval extends DatatableInformationRetrieval
{
	public SizeTotalRetrieval()
	{
		super(SampleKey.ID);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Gesamtdicke,", formatUnit("cm")})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		String information = new SamplePrinter().printThickness(sample.getSamples());

		return information;
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printThickness(probe.getSamples());

		return information;
	}
}