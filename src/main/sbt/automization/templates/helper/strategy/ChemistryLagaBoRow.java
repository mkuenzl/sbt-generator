package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.Key;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class ChemistryLagaBoRow extends RowConstructionStrategy
{
	public ChemistryLagaBoRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Zuordnungsklasse,", formatUnit("LAGA Boden<sup>[11]</sup>")})
		});

		return row;
	}

	@Override
	String createCellFromProbe(DataTable table)
	{
		String lagaBo = new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{lagaBo});

		return cell.appendTag();
	}

	@Override
	String createCellFromSample(DataTable table)
	{
		String cell = HtmlFactory.createChemistryCell(table.get(key));

		return cell;
	}
}