package sbt.automization.templates.helper.information;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;

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
