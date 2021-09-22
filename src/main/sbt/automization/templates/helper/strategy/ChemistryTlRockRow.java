package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class ChemistryTlRockRow extends RowConstruction
{
	public ChemistryTlRockRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	public ChemistryTlRockRow()
	{
		super(ChemistryKey.TL_ROCK_STRATUM);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Verwertungsklasse,", formatUnit("TL Gestein<sup>[27]</sup>")})
		});

		return row;
	}

	@Override
	HtmlCell createCellFrom(Probe probe)
	{
		String tlRock = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{tlRock});

		return cell;
	}

	@Override
	HtmlCell createCellFrom(Sample sample)
	{
		HtmlCell cell = HtmlFactory.createChemistryCell(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, key));

		return cell;
	}
}
