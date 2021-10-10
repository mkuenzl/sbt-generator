package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlText;

public class WearSoleRetrieval extends DatatableInformationRetrieval
{
	public WearSoleRetrieval()
	{
		super(ProbeKey.WEAR_TRENCH_BOTTOM);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Tragfähigkeit", UtilityPrinter.printLineBreak(), "Grabensohle", formatUnit("Ansatz Sohle")})
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
		String formattedCellText = new HtmlText.Builder().appendAttribute("class", "Normal6")
				.appendContent("[T:")
				.appendContent(probe.get(ProbeKey.SOLE_DEPTH))
				.appendContent("]").build().appendTag();

		String information = probe.get(informationKey);

		if ("".equals(information)) return "";

		return information.concat(UtilityPrinter.printLineEmpty()).concat(formattedCellText);

	}
}
