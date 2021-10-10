package sbt.automization.core.templates.helper.information;

import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.format.printer.SamplePrinter;

public class TargetDepthRetrieval extends DatatableInformationRetrieval
{
	public TargetDepthRetrieval()
	{
		super(ProbeKey.TARGET_DEPTH);
	}

//	@Override
//	HtmlRow createRow()
//	{
//		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
//				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
//						new String[]{"Zieltiefe,", formatUnit("cm")})
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
		String targetDepth = probe.get(ProbeKey.TARGET_DEPTH);

		if ("".equals(targetDepth))
		{
			return targetDepth;
		} else
		{
			double depth = Double.parseDouble(targetDepth);

			String backgroundColor;
			String textColor;

			double thickness = new SamplePrinter().measureThickness(probe.getSamples());

			if (depth <= thickness)
			{
				backgroundColor = "#00FF00";
				textColor = "black";
			} else
			{
				backgroundColor = "red";
				textColor = "white";
			}

			return "<span style=\"background-color: " + backgroundColor + ";font-weight: bold;\n\n" +
					"  color: " + textColor + "\">" + targetDepth + "</span>";
		}
	}
}