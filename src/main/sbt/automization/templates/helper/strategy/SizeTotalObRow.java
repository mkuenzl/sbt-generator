package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class SizeTotalObRow extends RowConstruction
{
	public SizeTotalObRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	public SizeTotalObRow()
	{
		super(SampleKey.ID);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Gesamtdicke geb.", UtilityPrinter.printLineBreak(), "Oberbau,", formatUnit("cm")})
		});

		return row;
	}

	@Override
	HtmlCell createCellFrom(Probe probe)
	{
		List<Sample> samples = probe.getSamplesBy(SampleKey.OUTCROP, new String[]{outcrop,
				Outcrop.CONCRETE.toString(),
				Outcrop.COATING.toString(),
				Outcrop.TMHB.toString(),
				Outcrop.SEAL.toString()});

		String size = new SamplePrinter().printThickness(samples);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{size});

		return cell;
	}

	@Override
	HtmlCell createCellFrom(Sample sample)
	{
		List<Sample> samples = sample.getSamplesBy(SampleKey.OUTCROP, outcrop);
		String size = new SamplePrinter().printThickness(samples);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{size});

		return cell;
	}
}