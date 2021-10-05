package sbt.automization.templates.helper.information;

import sbt.automization.data.Outcrop;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;

import java.util.List;

public class SizeTotalTmhbRetrieval extends DatatableInformationRetrieval
{
	public SizeTotalTmhbRetrieval()
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