package sbt.automization.data.refactoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class Sample extends DataTableImpl
{
	private final Collection<Parameter> parameter = new ArrayList<>();

	public Sample(Map<String, String> informationMap)
	{
		super(informationMap);
	}

	public Sample()
	{
		super();
	}

	@Override
	public int compareTo(DataTable o)
	{
		return 0;
	}
}
