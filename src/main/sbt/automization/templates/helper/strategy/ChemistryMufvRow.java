package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class ChemistryMufvRow extends RowConstruction
{
	public ChemistryMufvRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	public ChemistryMufvRow()
	{
		super(ChemistryKey.MUFV);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Abgrenzung", UtilityPrinter.printLineBreak(), "Gefährlichkeit,", formatUnit("Schreiben des MUFV<sup>[18]</sup>")})
		});

		return row;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		String mufv = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{mufv});

		return cell.appendTag();
	}

	@Override
	String createCellFrom(Sample sample)
	{
		String cell = HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, key));

		return cell;
	}
}