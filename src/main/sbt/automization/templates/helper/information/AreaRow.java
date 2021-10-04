package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;

public class AreaRow extends DatatableInformationRetrieval
{
	public AreaRow()
	{
		super(SampleKey.NUMBER);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Bereich"})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		Probe probe = sample.getProbe();

		String concat = probe.get(ProbeKey.NUMBER).concat(".").concat(sample.get(SampleKey.NUMBER));

		return concat;
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		String component = new SamplePrinter().printAttributeOfSamples(probe, outcrop.toString(), informationKey);

		return component;
	}
}