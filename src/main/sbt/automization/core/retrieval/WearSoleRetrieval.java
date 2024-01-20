package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class WearSoleRetrieval extends DatatableInformationRetrieval
{
	public WearSoleRetrieval()
	{
		super(ProbeKey.WEAR_TRENCH_BOTTOM);
	}

	@Override
	String retrieveFrom(Sample sample)
	{
		Probe probe = sample.getProbe();
		return probe.get(informationKey);
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{


		return new SamplePrinter().printAttributeOfSamplesWithDepth(probe,
																	outcrop.toString(),
																	informationKey);
	}
}
