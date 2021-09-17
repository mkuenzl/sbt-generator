package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.Key;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class WearPlanumRow extends RowConstructionStrategy
{
	public WearPlanumRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Tragfähigkeit", UtilityPrinter.printLineBreak(), "Planum", formatUnit("Soll: E<sub>V2</sub> >= 45 MN/m²".concat(UtilityPrinter.printLineBreak()).concat("Ansatz Planum: FOK -60cm"))})
		});

		return row;
	}

	@Override
	String createCellFromProbe(DataTable table)
	{
		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{table.get(key)});

		return cell.appendTag();
	}

	@Override
	String createCellFromSample(DataTable table)
	{
		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{table.get(key)});

		return cell.appendTag();
	}
}
