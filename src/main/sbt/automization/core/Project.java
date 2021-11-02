package sbt.automization.core;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Parameter;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;

import java.util.ArrayList;
import java.util.List;

public class Project
{
	private List<Probe> probes;
	private List<Sample> samples;
	private List<Parameter> parameters;
	
	public Project(){
		this.probes = new ArrayList<>();
		this.samples = new ArrayList<>();
		this.parameters = new ArrayList<>();
	}
	
	public List<Probe> getProbes()
	{
		return probes;
	}
	
	public void setProbes(List<Probe> probes)
	{
		this.probes = probes;
	}
	
	public List<Sample> getSamples()
	{
		return samples;
	}
	
	public void setSamples(List<Sample> samples)
	{
		this.samples = samples;
	}
	
	public List<Parameter> getParameters()
	{
		return parameters;
	}
	
	public void setParameters(List<Parameter> parameters)
	{
		this.parameters = parameters;
	}
}
