package sbt.automization.data;

import sbt.automization.data.references.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Sample extends DataTableImpl
{
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

	@Override
	public int compareTo(DataTable o)
	{
		return 0;
	}

	@Override
	public Parameter getParameterBy(Reference referenceID)
	{
		for (Parameter par : parameters)
		{
			String value = this.get(referenceID);
			if (par.contains(value)) return par;
		}

		return null;
	}

	@Override
	public String getParameterValueBy(Reference parameterID, Reference valueID)
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
}
