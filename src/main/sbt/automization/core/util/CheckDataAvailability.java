package sbt.automization.core.util;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Parameter;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.Key;
import sbt.automization.core.data.key.SampleKey;

import java.util.ArrayList;
import java.util.List;

public final class CheckDataAvailability
{
	private CheckDataAvailability() {}

	public static boolean thereExistsAnTableWithData(List<DataTable> dataTables, String outcrop, Key key)
	{
		for (DataTable dataTable : dataTables)
		{
			if (dataTable.containsValueFor(key)) return true;
			if (thereExistsAnParameterWithData(dataTable, key)) return true;
			if (thereExistsAnSampleWithData(dataTable, outcrop, key)) return true;
		}
		return false;
	}

	private static boolean thereExistsAnParameterWithData(DataTable dataTable, Key key)
	{
		List<Parameter> parameters = new ArrayList<>();

		if (dataTable instanceof Probe)
		{
			List<Parameter> parameterList = ((Probe) dataTable).getParameters();
			parameters.addAll(parameterList);
		}
		if (dataTable instanceof Sample)
		{
			List<Parameter> parameterList = ((Sample) dataTable).getParameters();
			parameters.addAll(parameterList);
		}

		for (Parameter parameter : parameters)
		{
			if (parameter.containsValueFor(key)) return true;
		}

		return false;
	}

	public static boolean thereExistsAnSampleWithData(DataTable dataTable, String outcrop, Key key)
	{
		List<Sample> samples;

		if (dataTable instanceof Probe)
		{
			if (! "".equals(outcrop))
			{
				samples = ((Probe) dataTable).getSamplesBy(SampleKey.OUTCROP, outcrop);
			} else
			{
				samples = dataTable.getSamples();
			}

			for (Sample sample : samples)
			{
				if (sample.containsValueFor(key)) return true;
				if (thereExistsAnParameterWithData(sample, key)) return true;
			}
		}
		return false;
	}
}
