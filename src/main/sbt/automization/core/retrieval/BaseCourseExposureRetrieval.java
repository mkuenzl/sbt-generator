package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;

public class BaseCourseExposureRetrieval
        extends DatatableInformationRetrieval
{
    public BaseCourseExposureRetrieval()
    {
        super(ProbeKey.OUTCROP_TOB);
    }

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Aufschlussart"})
//		});
//
//		return row;
//	}

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
            return "Kleinramm-\nbohrung";
        }
        return probe.get(informationKey);
    }
}