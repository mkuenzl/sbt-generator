package sbt.automization.core.util;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.key.SampleKey;

import java.util.List;
import java.util.stream.Collectors;

public final class DatatableFilter
{
	private DatatableFilter() {}

	public static List<DataTable> getProbesWhichIncludeOutcrop(List<DataTable> tables, Outcrop outcrop)
	{
		List<DataTable> probesWithOutcrop = tables.stream()
				.filter(table -> table instanceof Probe)
				.filter(table -> table.hasSampleWith(SampleKey.OUTCROP, outcrop.toString()))
				.collect(Collectors.toList());

		return probesWithOutcrop;
	}
}
