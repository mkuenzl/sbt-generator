package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

import java.util.List;

public class SizeTotalOBRetrieval extends DatatableInformationRetrieval
{
	public SizeTotalOBRetrieval()
	{
		super(SampleKey.ID);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Gesamtdicke Oberbau,", formatUnit("cm")})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		List<Sample> samples = sample.getSamplesBy(SampleKey.OUTCROP, outcrop.toString());
		String information = new SamplePrinter().printThickness(samples);

		return information;
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		List<Sample> samples = probe.getSamplesBy(SampleKey.OUTCROP, new String[]{
				outcrop.toString(),
				Outcrop.CONCRETE.toString(),
				Outcrop.TOB.toString(),
				Outcrop.COATING.toString(),
				Outcrop.GOB.toString(),
				Outcrop.SEAL.toString()});

		String information = new SamplePrinter().printThickness(samples);

		return information;
	}
}