package sbt.automization.format;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.RefRuK;
import sbt.automization.data.refactoring.references.RefSample;
import sbt.automization.util.html.HtmlText;

import java.util.List;

/**
 * Class for formatting information of ExplorationSites and Layers.
 */
public final class TextFormatUtil
{
	private TextFormatUtil() {}

	/**
	 * Method used for pretty printing the cell text for "Belastungsklasse"
	 * Schema:
	 * Belastungsklasse
	 * Bk1/Bk2/keine
	 *
	 * @param loadClass as
	 * @return formatted html code
	 */
	public static String formatLoadClass(final String loadClass)
	{
		String content;

		if ("keine".equals(loadClass) || "-".equals(loadClass))
		{
			content = loadClass;
		} else
		{
			content = "Bk" + loadClass;
		}

		return new HtmlText.Builder().appendAttribute("class", "Normal")
				.appendContent("Belastungsklasse")
				.appendContent(printLineBreak())
				.appendContent(content)
				.build()
				.appendTag();
	}

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
	 * Method creates a formatted html text of the Ev2 value. When Ev2 is smaller 80 and Ev85 is in between a certain
	 * range this range will be shown also.
	 *
	 * @param ev2         the Ev2 value from an LP experiment as String
	 * @param ev85Percent the Ev85 value from an LP experiment as String
	 * @return a String of the formatted html code
	 */
	public static String formatLP(final String ev2, final String ev85Percent)
	{
		StringBuilder stringBuilder = new StringBuilder();

		if ("< 80".equals(ev2) && ! "-".equals(ev85Percent))
		{
			String ev85PercentInformation = ev85Percent.replace(",", ".");
			String replace = ev85PercentInformation.replace("~ ", "");

			double ev85PercentValue = Double.parseDouble(replace);

			String range = "";

			if (ev85PercentValue >= 10 && ev85PercentValue < 20) range = "[30 - 40]";
			if (ev85PercentValue >= 20 && ev85PercentValue < 30) range = "[40 - 50]";
			if (ev85PercentValue >= 30 && ev85PercentValue < 40) range = "[50 - 60]";
			if (ev85PercentValue >= 40 && ev85PercentValue < 45) range = "[60 - 80]";

			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(ev2)
					//.appendContent(printLineBreak())
					.appendContent(new HtmlText.Builder()
							.appendAttribute("class", "Normal6")
							.appendContent(range)
							.build().appendTag())
					.build()
					.appendTag());
		} else
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(ev2)
					.build()
					.appendTag());
		}

		return stringBuilder.toString();
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
			heightValue = heightValue + Double.parseDouble(table.get(RefSample.THICKNESS).replace(",", "."));
		}
		return heightValue;
	}

	/**
	 * Method for specifying whether a sample is a single probe or could contain multiple.
	 *
	 * @param container a String of the sample container
	 * @return a String of the sample classification
	 */
	public static String formatSampleType(final String container)
	{
		String sampleType;
		if ("-".equals(container))
		{
			sampleType = "EP";
		} else
		{
			sampleType = "MP";
		}
		return sampleType;
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
	 * Method to format a range between two depths as html text.
	 *
	 * @param startDepth a String representing the start depth
	 * @param endDepth   a String representing the end depth
	 * @return a html text representing the range
	 */
	public static String formatDepth(final String startDepth, final String endDepth)
	{
		HtmlText htmlText = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("")
				.appendContent(startDepth)
				.appendContent("-")
				.appendContent(endDepth)
				.appendContent("")
				.build();

		return htmlText.appendTag();
	}

	/**
	 * Expects a valid soil group and formats the String for representation in the appendix ExplorationSite
	 *
	 * @param layerKind a valid soil group as String either with [] or without
	 * @return a formatted String of the long text and short text of a soil group
	 */
	public static String formatSoilGroup(final String layerKind)
	{
		boolean isFillUp;
		String kind;
		String kindText;

		if (layerKind == null)
		{
			return "-";
		}

		if (layerKind.contains("-"))
		{
			return layerKind;
		}

		if (layerKind.contains("["))
		{
			kind = layerKind.replaceAll("[\\[\\]]", "");
			isFillUp = true;
		} else
		{
			kind = layerKind;
			isFillUp = false;
		}

		switch (kind)
		{
			case "GE":
				kindText = "Engestufte Kiese";
				break;
			case "GW":
				kindText = "Weitgestufte Kies-Sand-Gemische";
				break;
			case "GI":
				kindText = "Intermittierend gestufte Kies-Sand-Gemische";
				break;
			case "SE":
				kindText = "Enggestufte Sande";
				break;
			case "SW":
				kindText = "Weitgestufte Sand-Kies-Gemische";
				break;
			case "SI":
				kindText = "Intermittierend gestufte Sand-Kies-Gemische";
				break;
			case "GU":
			case "GU*":
				kindText = "Kies-Schluff-Gemisch";
				break;
			case "GT":
			case "GT*":
				kindText = "Kies-Ton-Gemisch";
				break;
			case "SU":
			case "SU*":
				kindText = "Sand-Schluff-Gemisch";
				break;
			case "ST":
			case "ST*":
				kindText = "Sand-Ton-Gemisch";
				break;
			case "UL":
				kindText = "Leicht plast. Schluffe";
				break;
			case "UM":
				kindText = "Mittel plast. Schluffe";
				break;
			case "UA":
				kindText = "Ausgeprägt plastische Schluffe";
				break;
			case "TL":
				kindText = "Leicht plast. Ton";
				break;
			case "TM":
				kindText = "Mittel plast. Ton";
				break;
			case "TA":
				kindText = "Ausgeprägt plast. Ton";
				break;
			case "OU":
				kindText = "Organogene Schluffe";
				break;
			case "OT":
			case "OH":
				kindText = "Oberboden";
				break;
			case "OK":
				kindText = "Grob bis gemischtkörnige Böden mit kalkigen, kieseligen Bildungen";
				break;
			case "HN":
				kindText = "Nicht bis mäßig zersetzte Torfe";
				break;
			case "HZ":
				kindText = "Zersetzte Torfe";
				break;
			case "F":
				kindText = "Mudden";
				break;
			case "Bankettandeckung":
				kindText = "Bankettandeckung";
				break;
			default:
				kindText = "";
				break;
		}

		if (isFillUp)
		{
			return kindText + " " + "[" + kind + "]";
		}

		if ("".equals(kindText))
			return kind;

		return kindText + " " + kind;
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

		List<Sample> outcropLayerSamples = ((Probe) dataTable).getSamplesBy(RefSample.OUTCROP, outcrop);
		int size = outcropLayerSamples.size();

		for (int i = 0 ; i < size ; i++)
		{
			Sample sample = outcropLayerSamples.get(i);

			formattedLayerMaterial.append(formatLayerAttributes(sample.get(RefSample.TYPE),
					sample.get(RefSample.ROUNDING_GRADATION),
					sample.get(RefSample.GRANULATION)));

			formattedLayerMaterial.append(formatDepthSpecified(sample.get(RefSample.DEPTH_START), sample.get(RefSample.DEPTH_END)));

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

		stringBuilder.append(NameFormatUtil.formatLayerKind(kind));
		stringBuilder.append(printLineEmpty());
		stringBuilder.append(attributes.appendTag());

		return stringBuilder.toString();
	}


	public static String formatDepthSpecified(final String startDepth, final String endDepth)
	{
		String depth = "[T: " + startDepth + " - " + endDepth + "]";

		HtmlText formattedDepth = new HtmlText.Builder()
				.appendAttribute("class", "Normal6")
				.appendContent(depth)
				.build();

		return formattedDepth.appendTag();
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


	public static String formatKindAndGranulation(final String kind, String granulation)
	{
		String format = "";

		if ("-".equals(granulation)) granulation = "";

		format = kind.concat(" ").concat(granulation);

		return format;
	}

	/**
	 * TODO chain layers together
	 *
	 * @param dataTable
	 * @param outcrop
	 * @param tag
	 * @return
	 */
	public static String printLayerInformationWithDepth(final DataTable dataTable, final String outcrop, final String tag)
	{
		//TODO List<LayerSample> layerSamples = LayerFormatUtil.combineLayers(dataTable, outcrop, tag);

		if (!(dataTable instanceof Probe)) return "";

		List<Sample> samples = ((Probe) dataTable).getSamplesBy(RefSample.OUTCROP, outcrop);

		StringBuilder stringBuilder = new StringBuilder();

		int number = samples.size();

		for (int i = 0 ; i < number ; i++)
		{
			Sample sample = samples.get(i);

			String formattedTag;

			if (tag.contains("CHEMIE"))
			{
				formattedTag = printChemistryMarkup(sample.get(tag));
			} else if (tag.contains("FEUCHTIGKEIT"))
			{
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(TextFormatUtil.formatProctor(sample.get(RefSample.WATER_PROCTOR)))
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
			stringBuilder.append(formatDepthSpecified(sample.get(RefSample.DEPTH_START),
					sample.get(RefSample.DEPTH_END)));

		}

		return stringBuilder.toString();
	}

	public static String printLayerInformationWithDepth(final DataTable dataTable, final String outcrop, final ReferenceKey tag)
	{
		return printLayerInformationWithDepth(dataTable, outcrop, tag.getIdentifier());
	}

	public static String printChemistryMarkup(final String classification)
	{
		StringBuilder stringBuilder = new StringBuilder();

		switch (classification)
		{
			case "Z0":
			case "DK0":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: white;font-weight: bold;\n\n" +
								"  color: black\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z0*":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: #00FFFF;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z1":
			case "Z1.1":
			case "RC1":
			case "DK1":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: #00FF00;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z1.2":
			case "RC2":
			case "DK2":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: yellow;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z2":
			case "RC3":
			case "DK3":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: red;font-weight: bold;\n" +
								"  color: white\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case ">Z2":
			case ">DK3":
			case "gefährlich":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: black;font-weight: bold;\n" +
								"  color: white\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "nicht gefährlich":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: white;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent("nicht")
						.appendContent("</span>")
						.build().appendTag())
						.append(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"font-weight: bold\";>")
								.appendContent("gefährlich")
								.appendContent("</span>")
								.build().appendTag());
				break;
			case "nicht eingehalten":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"font-weight: bold\";>")
						.appendContent("nicht")
						.appendContent("</span>")
						.build().appendTag())
						.append(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"font-weight: bold\";>")
								.appendContent("eingehalten")
								.appendContent("</span>")
								.build().appendTag());
				break;
			case "eingehalten":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"font-weight: bold\";>")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			default:
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(classification)
						.build().appendTag());
				break;
		}

		return stringBuilder.toString();
	}

	public static String formatProctor(final String moisture)
	{
		if ("-".equals(moisture))
		{
			return "-";
		} else
		{
			return moisture.concat(" W<sub>Pr</sub>");
		}
	}

	public static String printRukLayers(final DataTable dataTable, final String outcrop)
	{
		List<Sample> samples = ((Probe) dataTable).getSamplesBy(RefSample.OUTCROP, outcrop);

		StringBuilder stringBuilder = new StringBuilder();

		for (Sample sample : samples)
		{
			String rukValue = sample.getParameterValueBy(RefSample.RUK_ID, RefRuK.VALUE);

			if (! "-".equals(rukValue) && ! "".equals(rukValue))
			{
				if (0 != stringBuilder.length())
				{
					stringBuilder.append(printCellTextDivider());
				}

				HtmlText layerKind = new HtmlText.Builder()
						.appendAttribute("class", "Normal6")
						.appendContent(sample.get(RefSample.TYPE))
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
