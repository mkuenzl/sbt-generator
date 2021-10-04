package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;


public class DIN18915Retrieval extends DatatableInformationRetrieval
{
	public DIN18915Retrieval()
	{
		super(SampleKey.SOIL_CLASS);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Bodengruppe,", formatUnit("DIN 18915<sup>[37]</sup>")})
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