package sbt.automization.data.refactoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Probe extends DataTableImpl
{
	private final List<Sample> samples = new ArrayList<>();
	private final List<Parameter> parameter = new ArrayList<>();

	public Probe(Map<String, String> informationMap)
	{
		super(informationMap);
	}

	public Probe()
	{
		super();
	}

	@Override
	public int compareTo(DataTable o)
	{
		return 0;
	}

	public void addSample(Sample sample)
	{
		this.samples.add(sample);
	}

	public void addListOfSample(List<Sample> samples)
	{
		this.samples.addAll(samples);
	}

	public void addParameter(Parameter parameter)
	{
		this.parameter.add(parameter);
	}

	public void addListOfParameter(List<Parameter> parameter)
	{
		this.parameter.addAll(parameter);
	}

	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (obj.getClass() != this.getClass())
		{
			return false;
		}

		final Probe otherTable = (Probe) obj;

		if (! Objects.equals(this.getTable(), otherTable.getTable()))
		{
			return false;
		}

		if (! Objects.equals(this.getSamples(), otherTable.getSamples()))
		{
			return false;
		}

		if (! Objects.equals(this.getParameter(), otherTable.getParameter()))
		{
			return false;
		}

		return true;
	}

	public List<Sample> getSamples()
	{
		return samples;
	}

	public List<Parameter> getParameter()
	{
		return parameter;
	}
}
