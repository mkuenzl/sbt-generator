package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;

public class PitchQualitativeRetrieval extends DatatableInformationRetrieval
{
	public PitchQualitativeRetrieval()
	{
		super(ProbeKey.PITCH_QUALITATIVE);
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
		String information = probe.get(informationKey);
		
		return information;
	}
}