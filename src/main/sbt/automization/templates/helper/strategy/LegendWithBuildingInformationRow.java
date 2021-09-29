package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlRow;

import java.util.ArrayList;
import java.util.List;

public class LegendWithBuildingInformationRow extends RowConstruction
{
	public LegendWithBuildingInformationRow()
	{
		super(null);
	}

	@Override
	public String buildWithProbes()
	{
		double size = styleParameter.getHeaderCellWidthAsDouble() + this.probes.size() * styleParameter.getNormalCellWidthAsDouble();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegend = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", styleParameter.getLegendCellClass())
						.appendAttribute("colspan", String.valueOf(1 + this.probes.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Anmerkungen:")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Die abschließende Zuordnung zu einem Abfallschlüssel hängt u. a. von der " +
								"Zusammensetzung der abzufahrenden, separierten Abfälle und von den Annahmebedingungen " +
								"und der Abfalleinstufung der vorgesehenen Entsorgungseinrichtung ab. ")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("AVV 17 09 04: Nicht gefährliche und nicht getrennte Bauteile können i.d.R. unter" +
								" dem vorgenannten Abfallschlüssel, gemischte Bau- und Abbruchabfälle zusammen entsorgt werden.")
						.build()
						.appendTag())
				.build();

		return rowLegend.appendTag();
	}

	@Override
	public String buildWithSamples()
	{
		int amountOfSamples = 0;

		List<String> additionalFootnotes = new ArrayList<>();

		for (DataTable probe : probes)
		{
			List<Sample> samples = probe.getSamples();
			amountOfSamples += samples.size();

			for (Sample sample : samples)
			{
				String footnote = sample.get(SampleKey.MATERIAL_COMPARISON);
				if (!"".equals(footnote))
				{
					additionalFootnotes.add(footnote);
				}
			}
		}
		//TODO

		double size = styleParameter.getHeaderCellWidthAsDouble() + amountOfSamples * styleParameter.getNormalCellWidthAsDouble();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegend = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", styleParameter.getLegendCellClass())
						.appendAttribute("colspan", String.valueOf(1 + amountOfSamples))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Anmerkungen:")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Die abschließende Zuordnung zu einem Abfallschlüssel hängt u. a. von der " +
								"Zusammensetzung der abzufahrenden, separierten Abfälle und von den Annahmebedingungen " +
								"und der Abfalleinstufung der vorgesehenen Entsorgungseinrichtung ab. ")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("AVV 17 09 04: Nicht gefährliche und nicht getrennte Bauteile können i.d.R. unter" +
								" dem vorgenannten Abfallschlüssel, gemischte Bau- und Abbruchabfälle zusammen entsorgt werden.")
						.build()
						.appendTag())
				.build();

		return rowLegend.appendTag();
	}

	@Override
	HtmlRow createRow()
	{
		return null;
	}

	@Override
	HtmlCell createCellFrom(Probe probe)
	{
		return null;
	}

	@Override
	HtmlCell createCellFrom(Sample sample)
	{
		return null;
	}
}
