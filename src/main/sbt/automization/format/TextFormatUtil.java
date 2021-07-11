package sbt.automization.format;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
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
	 * @param explorationSite expects a exploration site
	 * @return formatted html code
	 */
	public static String formatLoadClass(final ExplorationSite explorationSite)
	{
		String loadClass = explorationSite.getInformation("ERK_BELASTUNGSKLASSE");
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
	 * @param explorationSite an ExplorationSite object
	 * @param outcrop         a String characterizing a specific outcrop like GOB / TOB ...
	 * @return the thickness as String
	 */
	public static String formatSiteOutcropThickness(final ExplorationSite explorationSite, String outcrop)
	{
		double heightValue = 0.0;
		List<Layer> layersInOutcrop = explorationSite.getLayersWithOutcrop(outcrop);
		for (Layer layer : layersInOutcrop)
		{
			heightValue = heightValue + Double.parseDouble(layer.getInformation("SCHICHT_DICKE").replace(",", "."));
		}
		String height = String.valueOf(heightValue);
		return height.replace(".", ",");
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
	 * Method formats all footnotes related to an exploration site in the provided order.
	 *
	 * @param explorationSite an ExplorationSite object
	 * @return a String of html code representing a list of footnotes
	 */
	public static String formatSiteFootnotes(final ExplorationSite explorationSite)
	{
		int footnoteCounter = 1;
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("Angaben:")
				.appendContent(printLineBreak())
				.appendContent("KGV = Korngrößenverteilung, WG = Wassergehalt, LP = Plattendruckversuch, wPr = optimaler Wassergehalt")
				.appendContent(printLineBreak())
				.appendContent("Gem. a. G. = Gemisch aus Gesteinskörnungen, NS = Naturstein, LS = Lavaschlacke, HO = Hochofenschlacke")
				.appendContent(printLineBreak())
				.appendContent("RC = Rezyklierte Gesteinskörnung, BK = Brechkorn, RK = Rundkorn, sg = stetig gestuft, ug = unstetig gestuft")
				.appendContent(printLineBreak())
				.appendContent(TextFormatUtil.printLineEmpty())
				.build()
				.appendTag());

		stringBuilder.append(new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(String.valueOf(footnoteCounter++))
				.appendContent(".) ")
				.appendContent("Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m")
				.build()
				.appendTag());

		if ("#".equals(explorationSite.getInformation("ERK_LEITFADEN_AUSBAUASPHALT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Bewertung unter Berücksichtigung der Angaben im Leitfaden Ausbauasphalt")
					.build()
					.appendTag());
		}

		if ("#".equals(explorationSite.getInformation("ERK_TEILWEISE_VERFESTIGT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("teilweise verfestigt")
					.build()
					.appendTag());
		}

		if ("#".equals(explorationSite.getInformation("ERK_UEBERSCHREITUNG_ORIENT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Aufgrund der Überschreitung eines Orientierungswertes ist eine Aufbereitung (z. B. als RC-Gemisch) ggf. nicht möglich.")
					.build()
					.appendTag())
					.append(new HtmlText.Builder()
							.appendAttribute("class", "Normal")
							.appendContent("Absprache mit Behörde empfohlen")
							.build()
							.appendTag());

		}

		if ("#".equals(explorationSite.getInformation("ERK_RAMMHINDERNIS")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Rammhindernis; keine tiefere Entnahme möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(explorationSite.getInformation("ERK_KABELTRASSE")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Kabeltrasse; keine tiefere Entnahme möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(explorationSite.getInformation("ERK_FREMDBESTANDTEILE")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("mit mineralischen Fremdbestandteilen < 10 V.-%")
					.build()
					.appendTag());
		}

		if ("#".equals(explorationSite.getInformation("ERK_GUENSTIGE_EINSTUFUNG")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Ggf. günstigere Einstufung nach Rücksprache mit der Behörde möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(explorationSite.getInformation("ERK_VERNACHLAESSIGUNG_LEITFAEHIGKEIT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Einstufung unter Vernachlässigung des Parameters elektrische Leitfähigkeit")
					.build()
					.appendTag());
		}

		String erk_variable_footnote1 = explorationSite.getInformation("ERK_VARIABLE_FOOTNOTE1");
		if (erk_variable_footnote1 != null && ! erk_variable_footnote1.equals("#") && ! erk_variable_footnote1.equals("-"))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent(erk_variable_footnote1)
					.build()
					.appendTag());
		}

		String erk_variable_footnote2 = explorationSite.getInformation("ERK_VARIABLE_FOOTNOTE2");
		if (erk_variable_footnote2 != null && ! erk_variable_footnote2.equals("#") && ! erk_variable_footnote2.equals("-"))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent(erk_variable_footnote2)
					.build()
					.appendTag());
		}

		String erk_variable_footnote3 = explorationSite.getInformation("ERK_VARIABLE_FOOTNOTE3");
		if (erk_variable_footnote3 != null && ! erk_variable_footnote3.equals("#") && ! erk_variable_footnote3.equals("-"))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent(erk_variable_footnote3)
					.build()
					.appendTag());
		}


		if (! "-".equals(explorationSite.getInformation("ERK_LP")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter))
					.appendContent(".) ")
					.appendContent("Prüfergebnisse unter Berücksichtigung einer ca. 15 % Reduzierung aufgrund der Einspannung durch den ")
					.appendContent(printLineBreak())
					.appendContent("gebundenen Oberbau")
					.build()
					.appendTag());
		}
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
	 * @param explorationSite an ExplorationSite
	 * @return a html code as String containing all layers from an outcrop
	 */
	public static String formatOutcropLayers(final ExplorationSite explorationSite, final String outcrop)
	{
		StringBuilder formattedLayerMaterial = new StringBuilder();

		List<Layer> outcropLayers = explorationSite.getLayersWithOutcrop(outcrop);
		int size = outcropLayers.size();

		for (int i = 0 ; i < size ; i++)
		{
			Layer layer = outcropLayers.get(i);

			formattedLayerMaterial.append(formatLayerAttributes(layer.getInformation("SCHICHT_ART"),
					layer.getInformation("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT"),
					layer.getInformation("SCHICHT_KOERNUNG")));

			formattedLayerMaterial.append(formatDepthSpecified(layer.getInformation("SCHICHT_TIEFE_START"), layer.getInformation("SCHICHT_TIEFE_ENDE")));

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
<<<<<<< Updated upstream
	 * @param kind        a String of a layer kind (SCHICHT_ART)
	 * @param rounding    a String of a layer rounding (SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT)
	 * @param granulation a String of a layer granulation (SCHICHT_KOERNUNG)
=======
	 * @param kind a String of a layer kind (SCHICHT_ART)
	 * @param rounding a String of a layer rounding (SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT)
	 * @param granulation a String of a layer granulation (SCHICHT_KOERNUNGSGRAD)
>>>>>>> Stashed changes
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
	 * @param explorationSite
	 * @param outcrop
	 * @param tag
	 * @return
	 */
	public static String printLayerInformationWithDepth(final ExplorationSite explorationSite, final String outcrop, final String tag)
	{
		List<Layer> layers = explorationSite.getLayersWithOutcrop(outcrop);

		StringBuilder stringBuilder = new StringBuilder();

		int number = layers.size();

		for (int i = 0 ; i < number ; i++)
		{
			Layer layer = layers.get(i);

			String formattedTag;

			if (tag.contains("CHEMIE"))
			{
				formattedTag = printChemistryMarkup(layer.getInformation(tag));
			} else if (tag.contains("FEUCHTIGKEIT"))
			{
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(TextFormatUtil.formatLayerProctor(layer))
						.build().appendTag();
			} else
			{
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(layer.getInformation(tag))
						.build().appendTag();
			}

			if (i > 0)
			{
				stringBuilder.append(printCellTextDivider());
			}

			stringBuilder.append(formattedTag);
			stringBuilder.append(printLineEmpty());
			stringBuilder.append(formatDepthSpecified(layer.getInformation("SCHICHT_TIEFE_START"),
					layer.getInformation("SCHICHT_TIEFE_ENDE")));

		}

		return stringBuilder.toString();
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

	public static String formatLayerProctor(final Layer layer)
	{
		String feuchtigkeit = layer.getInformation("SCHICHT_FEUCHTIGKEIT");
		if ("-".equals(feuchtigkeit))
		{
			return "-";
		} else
		{
			return feuchtigkeit.concat(" W<sub>Pr</sub>");
		}
	}

	public static String printRukLayers(final ExplorationSite explorationSite, final String outcrop)
	{
		List<Layer> layersWithOutcrop = explorationSite.getLayersWithOutcrop(outcrop);

		StringBuilder stringBuilder = new StringBuilder();

		for (Layer layer : layersWithOutcrop)
		{
			String rukValue = layer.getInformation("SCHICHT_RUK");

			if (! "-".equals(rukValue) && ! "".equals(rukValue))
			{
				if (0 != stringBuilder.length())
				{
					stringBuilder.append(printCellTextDivider());
				}

				HtmlText layerKind = new HtmlText.Builder()
						.appendAttribute("class", "Normal6")
						.appendContent(layer.getInformation("SCHICHT_ART"))
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
