package sbt.automization.data.refactoring;

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
}
