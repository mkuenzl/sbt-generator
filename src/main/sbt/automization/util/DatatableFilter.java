package sbt.automization.util;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Probe;
import sbt.automization.data.key.SampleKey;

import java.util.List;
import java.util.stream.Collectors;

public class DatatableFilter
{
	private DatatableFilter(){}

	public static List<DataTable> getProbesWhichIncludeOutcrop(List<DataTable> tables, Outcrop outcrop)
	{
		List<DataTable> probesWithOutcrop = tables.stream()
				.filter(table -> table instanceof Probe)
				.filter(table -> table.hasSampleWith(SampleKey.OUTCROP, outcrop.toString()))
				.collect(Collectors.toList());

		return probesWithOutcrop;
	}
}
