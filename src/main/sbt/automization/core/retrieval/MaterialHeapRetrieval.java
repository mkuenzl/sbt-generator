package sbt.automization.core.retrieval;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;
import sbt.automization.core.format.printer.UtilityPrinter;

public class MaterialHeapRetrieval
        extends DatatableInformationRetrieval
{
    public MaterialHeapRetrieval()
    {
        super(SampleKey.WASTE_TYPE);
    }

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Material"})
//		});
//
//		return row;
//	}

    @Override
    String retrieveFrom(Sample sample)
    {
        final String actualValue = sample.get(informationKey);
        String formattedValue = actualValue;
        if ("bit. Befestigung".equals(actualValue))
        {
            formattedValue = "bit." + UtilityPrinter.printLineBreak() + "Befestigung";
        }
        else if ("Gem. a. G. (NS)".equals(actualValue))
        {
			formattedValue = "Gem. a. G."+UtilityPrinter.printLineBreak() + "(NS)";
        }
        return formattedValue;
    }

    @Override
    String retrieveFrom(Probe probe)
    {
        String attributeOfSamples = new SamplePrinter().printAttributeOfSamples(probe,
                                                                                outcrop.toString(),
                                                                                informationKey);
        return attributeOfSamples;
    }
}