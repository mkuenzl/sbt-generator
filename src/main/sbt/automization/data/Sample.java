package sbt.automization.data;

import sbt.automization.data.key.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Sample extends DataTableImpl
{
	private Probe probe;
	private final List<Parameter> parameters = new ArrayList<>();

	public Sample(Map<String, String> informationMap)
	{
		super(informationMap);
	}

	public Sample()
	{
		super();
	}

	public List<Parameter> getParameters()
	{
		return parameters;
	}

	public void addParameter(Parameter parameter)
	{
		this.parameters.add(parameter);
	}

	public void addListOfParameter(List<Parameter> parameter)
	{
		this.parameters.addAll(parameter);
	}

	public void setProbe(Probe probe)
	{
		this.probe = probe;
	}

	@Override
	public int compareTo(DataTable o)
	{
		return 0;
	}

	@Override
	public Parameter getParameterBy(Key keyID)
	{
		for (Parameter par : parameters)
		{
			String value = this.get(keyID);
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

	public Probe getProbe()
	{
		return this.probe;
	}
}
