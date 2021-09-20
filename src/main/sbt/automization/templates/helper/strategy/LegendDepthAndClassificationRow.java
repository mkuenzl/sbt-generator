package sbt.automization.templates.helper.strategy;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlRow;

public class LegendDepthAndClassificationRow extends RowConstruction
{
	public LegendDepthAndClassificationRow()
	{
		super(null);
	}

	@Override
	public String buildWithProbes()
	{
		int size = styleParameter.getHeaderCellWidthAsInt() + this.probes.size() * styleParameter.getNormalCellWidthAsInt();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowLegend = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", styleParameter.getLegendCellClass())
						.appendAttribute("colspan", String.valueOf(1 + this.probes.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Anmerkungen:")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Für die angegebenen Tiefen [] gilt die Einheit cm. ")
						.appendContent("Die Einstufung der Verdichtungsfähigkeit erfolgt unter Berücksichtigung der Bodenfeuchtigkeit und der Konsistenz\n" +
								"des Materials zum Erkundungszeitpunkt.")
						.build()
						.appendTag())
				.build();

		return rowLegend.appendTag();
	}

	@Override
	public String buildWithSamples()
	{
		return "";
	}

	@Override
	HtmlRow createRow()
	{
		return null;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		return null;
	}

	@Override
	String createCellFrom(Sample sample)
	{
		return null;
	}
}
