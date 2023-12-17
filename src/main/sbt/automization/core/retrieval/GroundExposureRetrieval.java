package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.format.printer.UtilityPrinter;

public class GroundExposureRetrieval
        extends DatatableInformationRetrieval
{
    public GroundExposureRetrieval()
    {
        super(ProbeKey.OUTCROP_UG_OH_BA);
    }

    @Override
    String retrieveFrom(Sample sample)
    {
        Probe probe = sample.getProbe();

        return probe.get(informationKey);
    }

    @Override
    String retrieveFrom(Probe probe)
    {
        final String value = probe.get(informationKey);
        if ("Kleinrammbohrung".equalsIgnoreCase(value))
        {

            return "Kleinramm" + UtilityPrinter.printLineBreakWithHyphen() + "bohrung";
        }
        if ("Baggerschurf".equalsIgnoreCase(value))
        {
            return "Bagger" + UtilityPrinter.printLineBreakWithHyphen() + "schurf";
        }

        return probe.get(informationKey);
    }
}