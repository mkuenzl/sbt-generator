package sbt.automization.data.refactoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class Examination extends DataTableImpl
{
	private final Collection<Probe> probes = new ArrayList<>();
	private final Collection<Parameter> parameter = new ArrayList<>();

	public Examination(Map<String, String> informationMap) {
		super(informationMap);
	}

	public Examination() {
		super();
	}

	@Override
	public int compareTo(DataTable o)
	{
		return 0;
	}
}
