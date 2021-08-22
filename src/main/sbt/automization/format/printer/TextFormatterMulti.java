package sbt.automization.format.printer;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.RuKKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.datatable.SampleFormatter;
import sbt.automization.format.text.ChemistryMarkupTextFormatter;
import sbt.automization.format.text.DepthTextFormatter;
import sbt.automization.format.text.LineBreakTextFormatter;
import sbt.automization.format.text.ProctorTextFormatter;
import sbt.automization.html.HtmlText;

import java.util.List;

/**
 * Class for formatting information of ExplorationSites and Layers.
 */
public final class TextFormatterMulti
{
	private TextFormatterMulti() {}

	/**
	 * Method to provide the standard html line break. May be moved to the html package in the future.
	 *
	 * @return a html line break
	 */
	public static String printLineBreak()
	{
		return "<br>";
	}

	/**
	 * TODO MOVE to ES
	 * Method calculates the thickness of a specific outcrop from an exploration site.
	 *
	 * @param samples an ExplorationSite object
	 * @return the thickness as String
	 */
	public static String printThicknessOfSamples(List<Sample> samples)
	{
		Double thicknessOfSamples = measureThicknessOfSamples(samples);

		String height = String.valueOf(thicknessOfSamples);
		return height.replace(".", ",");
	}

	public static Double measureThicknessOfSamples(List<Sample> samples)
	{
		double heightValue = 0.0;
		for (DataTable table : samples)
		{
			if (table.containsValueFor(SampleKey.THICKNESS))
			{
				heightValue = heightValue + Double.parseDouble(table.get(SampleKey.THICKNESS).replace(",", "."));
			}
		}
		return heightValue;
	}

	/**
	 * Method formats and strings together each layer from an exploration site that is related to an outcrop like "TOB".
	 *
	 * @param dataTable an ExplorationSite
	 * @return a html code as String containing all layers from an outcrop
	 */
	public static String formatOutcropLayers(final DataTable dataTable, final String outcrop)
	{
		StringBuilder formattedLayerMaterial = new StringBuilder();

		List<Sample> outcropLayerSamples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);
		int size = outcropLayerSamples.size();

		for (int i = 0 ; i < size ; i++)
		{
			Sample sample = outcropLayerSamples.get(i);

			formattedLayerMaterial.append(formatLayerAttributes(sample.get(SampleKey.TYPE),
					sample.get(SampleKey.ROUNDING_GRADATION),
					sample.get(SampleKey.GRANULATION)));

			formattedLayerMaterial.append(new DepthTextFormatter(true).format(sample.get(SampleKey.DEPTH_START), sample.get(SampleKey.DEPTH_END)));

			if (i + 1 < size)
			{
				formattedLayerMaterial.append(printCellTextDivider());
			}
		}

		return formattedLayerMaterial.toString();
	}

	/**
	 * Method formats Strings together in a specified manner.
	 * kind
	 * <p>
	 * rounding granulation
	 *
	 * @param kind        a String of a layer kind (SCHICHT_ART)
	 * @param rounding    a String of a layer rounding (SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT)
	 * @param granulation a String of a layer granulation (SCHICHT_KOERNUNG)
	 * @return a html code as String that formats the Attributes
	 */
	public static String formatLayerAttributes(final String kind, final String rounding, final String granulation)
	{
		StringBuilder stringBuilder = new StringBuilder();

		HtmlText attributes = new HtmlText.Builder()
				.appendAttribute("class", "Normal6")
				.appendContent(rounding)
				.appendContent(" ")
				.appendContent(granulation)
				.build();

		stringBuilder.append(new LineBreakTextFormatter().format(kind));
		stringBuilder.append(printLineEmpty());
		stringBuilder.append(attributes.appendTag());

		return stringBuilder.toString();
	}

	/**
	 * Method to provide a visual border between layer information.
	 *
	 * @return a String representing a line
	 */
	public static String printCellTextDivider()
	{
		StringBuilder stringBuilder = new StringBuilder();

		HtmlText textDivider = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("_________")
				.build();

		stringBuilder.append(textDivider.appendTag())
				.append(printLineEmpty());

		return stringBuilder.toString();
	}

	/**
	 * Method to provide a line without content.
	 *
	 * @return a html paragraph without content
	 */
	public static String printLineEmpty()
	{
		HtmlText emptyRow = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("&nbsp;")
				.build();

		return emptyRow.appendTag();
	}

	/**
	 * TODO chain layers together
	 *
	 * @param dataTable
	 * @param outcrop
	 * @param tag
	 * @return
	 */
	public static String printLayerInformationWithDepth(final DataTable dataTable, final String outcrop, final Key tag)
	{
		List<Sample> samples = SampleFormatter.combineSamplesOfOutcrop(dataTable, outcrop, tag);

		StringBuilder stringBuilder = new StringBuilder();

		int number = samples.size();

		for (int i = 0 ; i < number ; i++)
		{
			Sample sample = samples.get(i);

			String formattedTag;

			if (tag instanceof ChemistryKey)
			{
				formattedTag = new ChemistryMarkupTextFormatter().format(sample.getParameterValueBy(SampleKey.CHEMISTRY_ID, tag));
			} else if (tag instanceof RuKKey)
			{
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(sample.getParameterValueBy(SampleKey.RUK_ID, tag))
						.build().appendTag();
			} else if (tag == SampleKey.MOISTURE)
			{
				//TODO ERROR?
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(new ProctorTextFormatter().format(sample.get(SampleKey.MOISTURE)))
						.build().appendTag();
			} else
			{
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(sample.get(tag))
						.build().appendTag();
			}

			if (i > 0)
			{
				stringBuilder.append(printCellTextDivider());
			}

			stringBuilder.append(formattedTag);
			stringBuilder.append(printLineEmpty());
			stringBuilder.append(new DepthTextFormatter(true).format(sample.get(SampleKey.DEPTH_START),
					sample.get(SampleKey.DEPTH_END)));

		}

		return stringBuilder.toString();
	}

	public static String printRukLayers(final DataTable dataTable, final String outcrop)
	{
		List<Sample> samples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);

		StringBuilder stringBuilder = new StringBuilder();

		for (Sample sample : samples)
		{
			String rukValue = sample.getParameterValueBy(SampleKey.RUK_ID, RuKKey.VALUE);

			if (! "-".equals(rukValue) && ! "".equals(rukValue))
			{
				if (0 != stringBuilder.length())
				{
					stringBuilder.append(printCellTextDivider());
				}

				HtmlText layerKind = new HtmlText.Builder()
						.appendAttribute("class", "Normal6")
						.appendContent(sample.get(SampleKey.TYPE))
						.build();

				HtmlText rukText = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(rukValue)
						.build();

				stringBuilder.append(layerKind.appendTag())
						.append(rukText.appendTag());

			}
		}

		return stringBuilder.toString();
	}

}
