package sbt.automization.templates.helper.information;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;

public class WasteKeyMaterialRow extends DatatableInformationRetrieval
{
	public WasteKeyMaterialRow()
	{
		super(SampleKey.WASTE_KEY_MATERIAL);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Abfallschlüssel<sup>1,2</sup>", formatUnit("AVV<sup>[7]</sup>, materialspezifisch")})
//		});
//
//		return row;
//	}

	@Override
	String retrieveFrom(Sample sample)
	{
		String avvParameter = sample.get(informationKey);

		if (avvParameter.contains("("))
		{
			String[] split = avvParameter.split("[(]");
			avvParameter = split[0] + UtilityPrinter.printLineBreak() + "(" + split[1];
		}

		return avvParameter;
	}

	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop.toString(), informationKey);

		return information;
	}
}