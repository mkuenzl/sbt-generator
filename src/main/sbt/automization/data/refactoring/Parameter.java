package sbt.automization.data.refactoring;

import java.util.Map;

public final class Parameter extends DataTableImpl
{
	public Parameter(Map<String, String> informationMap) {
		super(informationMap);
	}

	public Parameter() {
		super();
	}

	@Override
	public int compareTo(DataTable o)
	{
		return 0;
	}
}
