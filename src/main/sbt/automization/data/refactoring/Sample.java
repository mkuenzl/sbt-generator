package sbt.automization.data.refactoring;

import sbt.automization.data.refactoring.references.Reference;

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

	public Parameter getParameterBy(Reference referenceID)
	{
		for (Parameter par : parameters)
		{
			String value = this.get(referenceID);
			if (par.contains(value)) return par;
		}

		return null;
	}

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
