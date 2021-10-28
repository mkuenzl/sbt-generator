package sbt.automization.core.data;

import java.util.Map;

public final class Parameter extends DataTableImpl
{
	private Sample sample;
	
	public Parameter(Map<String, String> informationMap)
	{
		super(informationMap);
	}
	
	public Parameter()
	{
		super();
	}
	
	@Override
	public int compareTo(DataTable o)
	{
		return 0;
	}
	
	public Sample getSample()
	{
		return sample;
	}
	
	public void setSample(Sample sample)
	{
		this.sample = sample;
	}
}
