package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class TargetDepthRow extends RowConstruction
{
	public TargetDepthRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	public TargetDepthRow()
	{
		super(ProbeKey.TARGET_DEPTH);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Zieltiefe,", formatUnit("cm")})
		});

		return row;
	}

	@Override
	HtmlCell createCellFrom(Probe probe)
	{
		String targetDepth = probe.get(ProbeKey.TARGET_DEPTH);
		HtmlCell cell;

		if ("".equals(targetDepth))
		{
			cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
					styleParameter.getNormalCellClass(),
					styleParameter.getNormalCellWidth(),
					new String[]{""});
		} else
		{
			double depth = Double.parseDouble(targetDepth);

			String backgroundColor;
			String textColor;

			double thickness = new SamplePrinter().measureThickness(probe.getSamples()); //dataTable.getThickness() TODO

			if (depth <= thickness)
			{
				backgroundColor = "#00FF00";
				textColor = "black";
			} else
			{
				backgroundColor = "red";
				textColor = "white";
			}

			cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
					styleParameter.getNormalCellClass(),
					styleParameter.getNormalCellWidth(),
					new String[]{"<span style=\"background-color: " + backgroundColor + ";font-weight: bold;\n\n" +
							"  color: " + textColor + "\">", targetDepth, "</span>"});


//			cell = new HtmlCell.Builder()
//					.appendAttribute("class", styleParameter.getNormalCellClass())
//					.appendAttribute("width", styleParameter.getNormalCellWidth())
//					.appendContent(new HtmlText.Builder()
//							.appendAttribute("class", "Normal")
//							.appendContent("<span style=\"background-color: " + backgroundColor + ";font-weight: bold;\n\n" +
//									"  color: " + textColor + "\">")
//							.appendContent(targetDepth)
//							.appendContent("</span>")
//							.build().appendTag())
//					.build();

		}
		return cell;
	}

	@Override
	HtmlCell createCellFrom(Sample sample)
	{
		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{sample.get(key)});

		return cell;
	}
}