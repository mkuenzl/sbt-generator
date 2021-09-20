package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class WearSoleRow extends RowConstruction
{
	public WearSoleRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	public WearSoleRow()
	{
		super(ProbeKey.WEAR_TRENCH_BOTTOM);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Tragfähigkeit", UtilityPrinter.printLineBreak(), "Grabensohle", formatUnit("Ansatz Sohle")})
		});

		return row;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		String formattedCellText = new HtmlText.Builder().appendAttribute("class", styleParameter.getUnitCellClass())
				.appendContent("[T:")
				.appendContent(probe.get(ProbeKey.SOLE_DEPTH))
				.appendContent("]").build().appendTag();

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{probe.get(key), UtilityPrinter.printLineEmpty(), formattedCellText});

		return cell.appendTag();
	}

	@Override
	String createCellFrom(Sample sample)
	{
		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{sample.get(key)});

		return cell.appendTag();
	}
}
