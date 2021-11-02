package sbt.automization.core.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Parameter extends DataTableImpl
{
	private List<Sample> samples;
	
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
	
	public List<Sample> getSamples()
	{
		return samples;
	}
	
	public void addSample(Sample sample)
	{
		if (this.samples == null)
			this.samples = new ArrayList<>();
		
		this.samples.add(sample);
	}
}
