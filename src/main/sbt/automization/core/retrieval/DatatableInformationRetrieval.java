package sbt.automization.core.retrieval;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.Key;

public abstract class DatatableInformationRetrieval implements InformationRetrievalStrategy
{
	protected Key informationKey;
	protected Outcrop outcrop;
	
	public DatatableInformationRetrieval(Key informationKey)
	{
		this.informationKey = informationKey;
	}
	
	@Override
	public String retrieve(DataTable dataTable)
	{
		if (dataTable instanceof Sample)
		{
			return retrieveFrom((Sample) dataTable);
		}
		if (dataTable instanceof Probe)
		{
			return retrieveFrom((Probe) dataTable);
		}
		return "";
	}
	
	abstract String retrieveFrom(Sample sample);
	
	abstract String retrieveFrom(Probe probe);
	
	@Override
	public Key getInformationKey()
	{
		return informationKey;
	}
	
	@Override
	public void setOutcrop(Outcrop outcrop)
	{
		this.outcrop = outcrop;
	}
}
