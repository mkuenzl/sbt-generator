package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class AreaRetrieval extends DatatableInformationRetrieval
{
	public AreaRetrieval()
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