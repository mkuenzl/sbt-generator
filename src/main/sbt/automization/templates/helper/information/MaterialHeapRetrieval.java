package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;

public class MaterialHeapRetrieval extends DatatableInformationRetrieval
{
	public MaterialHeapRetrieval()
	{
		super(SampleKey.WASTE_TYPE);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Material"})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		return sample.get(informationKey);
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		String attributeOfSamples = new SamplePrinter().printAttributeOfSamples(probe, outcrop.toString(), informationKey);
		return attributeOfSamples;
	}
}