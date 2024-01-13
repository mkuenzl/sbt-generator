package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class DIN18196Retrieval
        extends DatatableInformationRetrieval
{
    public DIN18196Retrieval()
    {
        super(SampleKey.TYPE);
    }

    @Override
    String retrieveFrom(Sample sample)
    {
        String information = new SamplePrinter().printAttributeOfDatatable(sample, informationKey);
        if ("<p class=Normal >\nGem. a. G. (NS)\n</p>".equals(information))
        {
			information = "<p class=Normal >\n Gem. a. G.<br>(NS)\n </p>";
        }
        return information;
    }

    @Override
    String retrieveFrom(Probe probe)
    {
        String information = new SamplePrinter().printAttributeOfSamplesWithDepth(probe,
                                                                                  outcrop.toString(),
                                                                                  informationKey);

        return information;
    }
}