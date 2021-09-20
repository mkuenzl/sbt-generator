package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;

import java.util.List;

public class WasteKeyMaterialRow extends RowConstruction
{
	public WasteKeyMaterialRow(List<DataTable> probes, String outcrop, Key key, StyleParameter styleParameter)
	{
		super(probes, outcrop, key, styleParameter);
	}

	public WasteKeyMaterialRow()
	{
		super(SampleKey.WASTE_KEY_MATERIAL);
	}

	@Override
	HtmlRow createRow()
	{
		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Abfallschlüssel", formatUnit("AVV<sup>[14]</sup>")})
		});

		return row;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		String attributesOfSamples = new SamplePrinter().printAttributeOfSamplesWithDepth(probe, outcrop, key);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{attributesOfSamples});

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