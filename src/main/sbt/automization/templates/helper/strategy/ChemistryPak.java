package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class ChemistryPak extends RowConstructionStrategy
{
	public ChemistryPak(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"PAK,", formatUnit("mg/kg")})
		});

		return row;
	}

	@Override
	String createCellFromProbe(DataTable table)
	{
		String pak = new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{pak});

		return cell.appendTag();
	}

	@Override
	String createCellFromSample(DataTable table)
	{
		String pak = table.getParameterValueBy(SampleKey.CHEMISTRY_ID, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{pak});

		return cell.appendTag();
	}
}