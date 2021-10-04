package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;

public class MaterialBuildingRetrieval extends DatatableInformationRetrieval
{
	public MaterialBuildingRetrieval()
	{
		super(SampleKey.MATERIAL);
	}

	@Override
	String retrieveFrom(Sample sample)
	{
		return sample.get(informationKey);
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		String material = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop.toString(), informationKey);

		return material;
	}
}

