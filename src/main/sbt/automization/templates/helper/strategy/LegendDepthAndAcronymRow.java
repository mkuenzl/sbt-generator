package sbt.automization.templates.helper.strategy;

import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlRow;

public class LegendDepthAndAcronymRow extends RowConstruction
{
	public LegendDepthAndAcronymRow()
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
						.appendContent("Gem. a. G. = Gemisch aus Gesteinskörnungen, NS = Naturstein, LS = Lavaschlacke, HO = Hochofenschlacke,")
						.appendContent("RC = Rezyklierte Gesteinskörnung, BK = Brechkorn, RK = Rundkorn, sg = stetig gestuft, ug = unstetig gestuft")
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
