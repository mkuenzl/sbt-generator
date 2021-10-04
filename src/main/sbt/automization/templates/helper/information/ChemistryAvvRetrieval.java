package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;

public class ChemistryAvvRetrieval extends DatatableInformationRetrieval
{
	public ChemistryAvvRetrieval()
	{
		super(ChemistryKey.WASTE_KEY);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Abfallschlüssel,", formatUnit("AVV<sup>[14]</sup>")})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		String information = sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, informationKey);

		return information;
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop.toString(), informationKey);

		return information;
	}
}
