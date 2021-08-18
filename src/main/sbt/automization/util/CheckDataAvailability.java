package sbt.automization.util;

import sbt.automization.data.DataTable;
import sbt.automization.data.Parameter;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.references.RefSample;
import sbt.automization.data.references.Reference;

import java.util.ArrayList;
import java.util.List;

public final class CheckDataAvailability
{
	private CheckDataAvailability() {}

	public static boolean thereExistsAnTableWithData(List<DataTable> dataTables, String outcrop, Reference reference)
	{
		for (DataTable dataTable : dataTables)
		{
			if (dataTable.containsValueFor(reference)) return true;
			if (thereExistsAnParameterWithData(dataTable, reference)) return true;
			if (thereExistsAnSampleWithData(dataTable, outcrop, reference)) return true;
		}
		return false;
	}

	private static boolean thereExistsAnParameterWithData(DataTable dataTable, Reference reference)
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
			if (parameter.containsValueFor(reference)) return true;
		}

		return false;
	}

	public static boolean thereExistsAnSampleWithData(DataTable dataTable, String outcrop, Reference reference)
	{
		List<Sample> samples;

		if (dataTable instanceof Probe)
		{
			if (! "".equals(outcrop))
			{
				samples = ((Probe) dataTable).getSamplesBy(RefSample.OUTCROP, outcrop);
			} else
			{
				samples = ((Probe) dataTable).getSamples();
			}

			for (Sample sample : samples)
			{
				if (sample.containsValueFor(reference)) return true;
				if (thereExistsAnParameterWithData(sample, reference)) return true;
			}
		}
		return false;
	}
}
