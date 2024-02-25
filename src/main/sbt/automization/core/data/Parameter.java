package sbt.automization.core.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Repräsentiert die <b>Ring und Kugel (RuK)-, Chemie- und Lastplattenversuch (LP)-</b>Parameter der <b>datenbank-template</b>,
 * die an einer oder mehreren Proben ({@link Sample}) hängen.
 */
public final class Parameter
        extends AbstractDataTable
{
    /**
     * Verknüpfte Proben ({@link Sample})
     */
    private List<Sample> samples;

    public Parameter(Map<String, String> informationMap)
    {
        super(informationMap);
    }

    public Parameter()
    {
        super();
    }

    @Override
    public int compareTo(DataTable o)
    {
        return 0;
    }

    public List<Sample> getSamples()
    {
        return samples;
    }

    public void addSample(Sample sample)
    {
        if (this.samples == null)
            this.samples = new ArrayList<>();

        this.samples.add(sample);
    }
}
