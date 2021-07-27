package sbt.automization.data.refactoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class Probe extends DataTableImpl
{
	private final Collection<Sample> samples = new ArrayList<>();
	private final Collection<Parameter> parameter = new ArrayList<>();

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
}
