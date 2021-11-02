package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class MaterialBuildingRetrieval extends DatatableInformationRetrieval
{
	public MaterialBuildingRetrieval()
	{
		super(SampleKey.MATERIAL);
	}
	
	@Override
	String retrieveFrom(Sample sample)
	{
		return sample.get(informationKey);
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String material = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop.toString(), informationKey);
		
		return material;
	}
}

