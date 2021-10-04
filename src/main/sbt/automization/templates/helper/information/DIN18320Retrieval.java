package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;

public class DIN18320Retrieval extends DatatableInformationRetrieval
{
	public DIN18320Retrieval()
	{
		super(SampleKey.HOMOGENEOUS_RANGE);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Homogenbereich,", formatUnit("DIN 18320:2019-09<sup>[36]</sup>")})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		String information = new SamplePrinter().printAttributeOfDatatable(sample, informationKey);

		return information;
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop.toString(), informationKey);

		return information;
	}

}