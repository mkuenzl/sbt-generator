package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.SamplePrinter;
import sbt.automization.core.format.printer.UtilityPrinter;

public class WasteKeyMaterialRetrieval extends DatatableInformationRetrieval
{
	public WasteKeyMaterialRetrieval()
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
		
		String footnote = sample.get(SampleKey.MATERIAL_COMPARISON);
		
		if (avvParameter.contains("("))
		{
			String[] split = avvParameter.split("[(]");
			avvParameter = split[0] + UtilityPrinter.printLineBreak() + "(" + split[1];
		}
		
		if (!"".equals(footnote) && !"".equals(avvParameter)) return "<em>".concat(avvParameter).concat("</em>");
		
		return avvParameter;
	}
	
	@Override
	String retrieveFrom(Probe probe)
	{
		String information = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop.toString(), informationKey);
		
		return information;
	}
}