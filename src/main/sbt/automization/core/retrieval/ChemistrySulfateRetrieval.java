package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ChemistryKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class ChemistrySulfateRetrieval extends DatatableInformationRetrieval
{
	public ChemistrySulfateRetrieval()
	{
		super(ChemistryKey.SULFATE);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Sulfat,", formatUnit("mg/l")})
//		});
//
//		return row;
//	}
	
	@Override
	String retrieveFrom(Sample sample)
	{
		String parameter = sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, informationKey);
		
		return parameter;
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop.toString(), informationKey);
		
		return information;
	}
}