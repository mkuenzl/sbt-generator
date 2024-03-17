package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ChemistryKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class ChemistryMufvRetrieval extends DatatableInformationRetrieval
{
	public ChemistryMufvRetrieval()
	{
		super(ChemistryKey.MUFV);
	}

	
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