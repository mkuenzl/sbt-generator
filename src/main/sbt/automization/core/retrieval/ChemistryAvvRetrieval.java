package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ChemistryKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

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
