package sbt.automization.data;

import sbt.automization.data.key.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Probe extends DataTableImpl
{
	private final List<Sample> samples = new ArrayList<>();
	private final List<Parameter> parameters = new ArrayList<>();

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
		this.parameters.add(parameter);
	}

	public void addListOfParameter(List<Parameter> parameter)
	{
		this.parameters.addAll(parameter);
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

		if (! Objects.equals(this.getParameters(), otherTable.getParameters()))
		{
			return false;
		}

		return true;
	}

	@Override
	public List<Sample> getSamples()
	{
		return samples;
	}

	public List<Parameter> getParameters()
	{
		return parameters;
	}

	@Override
	public Parameter getParameterBy(final Key key)
	{
		for (Parameter par : parameters)
		{
			String value = this.get(key);
			if (par.contains(value)) return par;
		}

		return null;
	}

	@Override
	public String getParameterValueBy(Key parameterID, Key valueID)
	{
		for (Parameter par : parameters)
		{
			String value = this.get(parameterID);
			if (par.contains(value))
			{
				return par.get(valueID);
			}
		}

		return "";
	}

	@Override
	public List<Sample> getSamplesBy(final Key key, final String value)
	{
		List<Sample> samplesWithValue = new ArrayList<>();

		for (Sample sample : this.samples)
		{
			String valueToCompare = sample.get(key);
			if (value.equals(valueToCompare))
			{
				samplesWithValue.add(sample);
			}
		}
		return samplesWithValue;
	}

	@Override
	public List<Sample> getSamplesBy(final Key key, final String[] values)
	{
		List<Sample> samplesWithValue = new ArrayList<>();

		for (Sample sample : this.samples)
		{
			String valueToCompare = sample.get(key);
			for (String value : values)
			{
				if (value.equals(valueToCompare))
				{
					samplesWithValue.add(sample);
				}
			}
		}
		return samplesWithValue;
	}

	@Override
	public boolean hasSampleWith(final Key key, final String value)
	{
		for (Sample sample : this.samples)
		{
			String valueToCompare = sample.get(key);
			if (value.equals(valueToCompare))
			{
				return true;
			}
		}
		return false;
	}
}