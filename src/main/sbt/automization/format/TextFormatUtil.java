package sbt.automization.format;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class TextFormatUtil
{
	public static String formatLoadClass(final ExplorationSite explorationSite)
	{
		String s1 = new HtmlText.Builder().appendAttribute("class", "Normal")
				.appendContent("Belastungsklasse")
				.build()
				.appendTag();

		String erk_belastungsklasse = explorationSite.getInformation("ERK_BELASTUNGSKLASSE");
		String content;
		if ("keine".equals(erk_belastungsklasse) || "-".equals(erk_belastungsklasse))
		{
			content = erk_belastungsklasse;
		} else
		{
			content = "Bk" + erk_belastungsklasse;
		}

		String s2 = new HtmlText.Builder().appendAttribute("class", "Normal")
				.appendContent(content)
				.build()
				.appendTag();

		return s1 + s2;
	}

	public static String formatLayerProctor(final Layer layer)
	{
		String schicht_feuchtigkeit = layer.getInformation("SCHICHT_FEUCHTIGKEIT");
		if ("-".equals(schicht_feuchtigkeit))
		{
			return "-";
		} else
		{
			return schicht_feuchtigkeit.concat(" W<sub>Pr</sub>");
		}
	}

	public static String formatErkLP(final ExplorationSite explorationSite)
	{
		StringBuilder strb = new StringBuilder();

		String ev2 = explorationSite.getInformation("ERK_LP_EV2");

		if ("< 80".equals(ev2))
		{
			String erk_lp_ev15 = explorationSite.getInformation("ERK_LP_EV15").replace(",", ".");
			String replace = erk_lp_ev15.replace("~ ", "");
			double ev = Double.parseDouble(replace);
			String range = "";

			if (ev >= 10 && ev < 20)
			{
				range = "[30 - 40]";
			}
			if (ev >= 20 && ev < 30)
			{
				range = "[40 - 50]";
			}
			if (ev >= 30 && ev < 40)
			{
				range = "[50 - 60]";
			}
			if (ev >= 40 && ev < 45)
			{
				range = "[60 - 80]";
			}

			strb.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(ev2)
					.build()
					.appendTag())
					.append(new HtmlText.Builder()
							.appendAttribute("class", "Normal6")
							.appendContent("&nbsp;")
							.build()
							.appendTag())
					.append(new HtmlText.Builder()
							.appendAttribute("class", "Normal6")
							.appendContent(range)
							.build().appendTag());
		} else
		{
			strb.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(ev2)
					.build()
					.appendTag());
		}

		return strb.toString();
	}

	public static String formatSiteOutcropThickness(final ExplorationSite explorationSite, String outcrop)
	{
		double height = 0.0;
		List<Layer> layerList = explorationSite.getLayersWithOutcrop(outcrop);
		for (Layer layer : layerList)
		{

			height = height + Double.parseDouble(layer.getInformation("SCHICHT_DICKE").replace(",", "."));
		}
		String h = String.valueOf(height);
		return h.replace(".", ",");
	}

	public static String formatLayerSampleType(final Layer layer)
	{
		String probenart;
		if ("-".equals(layer.getInformation("SCHICHT_BEHAELTNIS")))
		{
			probenart = "EP";
		} else
		{
			probenart = "MP";
		}
		return probenart;
	}

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
				.appendContent(TextFormatUtil.printEmptyRow())
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
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Prüfergebnisse unter Berücksichtigung einer ca. 15 % Reduzierung aufgrund der Einspannung durch den ")
					.build()
					.appendTag())
					.append(new HtmlText.Builder()
							.appendAttribute("class", "Normal")
							.appendContent("gebundenen Oberbau")
							.build()
							.appendTag());

		}
		return stringBuilder.toString();
	}

	public static String printLineBreak()
	{
		return "<br>";
	}

	public static String printEmptyRow()
	{
		HtmlText lineBreak = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("&nbsp;")
				.build();

		return lineBreak.appendTag();
	}

	public static String formatLayerDepth(final Layer layer)
	{
		HtmlText htmlText = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("")
				.appendContent(layer.getInformation("SCHICHT_TIEFE_START"))
				.appendContent("-")
				.appendContent(layer.getInformation("SCHICHT_TIEFE_ENDE"))
				.appendContent("")
				.build();

		return htmlText.appendTag();
	}

	/**
	 * Expects a valid Bodengruppe and formats the String for representation in the ERK_Anlage
	 *
	 * @param layerKind a valid Bodengruppe as String either with [] or without
	 * @return a formated String of the long text and short text of a Bodengruppe
	 */
	public static String formatLayerSoilGroup(final String layerKind)
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
				kindText = "Invalid Bodengruppe";
				break;
		}

		if (isFillUp)
		{
			return kindText + " " + "[" + kind + "]";
		}

		return kindText + " " + kind;
	}

	public static String presentTobLayers(final ExplorationSite explorationSite)
	{
		StringBuilder formatedSchichtenMaterial = new StringBuilder();

		List<Layer> tob = explorationSite.getLayersWithOutcrop("TOB");


		int size = tob.size();
		for (int i = 0 ; i < size ; i++)
		{
			Layer layer = tob.get(i);

			formatedSchichtenMaterial.append(NameFormatUtil.formatArt(layer.getInformation("SCHICHT_ART")));

			formatedSchichtenMaterial.append(printEmptyRow());

			HtmlText text2 = new HtmlText.Builder()
					.appendAttribute("class", "Normal6")
					.appendContent(layer.getInformation("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT"))
					.appendContent(" ")
					.appendContent(layer.getInformation("SCHICHT_KOERNUNG"))
					.build();

			HtmlText text3 = new HtmlText.Builder()
					.appendAttribute("class", "Normal6")
					.appendContent("[T:")
					.appendContent(layer.getInformation("SCHICHT_TIEFE_START"))
					.appendContent("-")
					.appendContent(layer.getInformation("SCHICHT_TIEFE_ENDE"))
					.appendContent("]")
					.build();

			formatedSchichtenMaterial.append(text2.appendTag());
			formatedSchichtenMaterial.append(printFormattedLayerDepth(layer));

			if (i + 1 < size)
			{
				formatedSchichtenMaterial.append(printCellTextDivider());
			}
		}

		return formatedSchichtenMaterial.toString();
	}

	public static String printFormattedLayerDepth(final Layer layer)
	{
		String tiefe = "[T: " + layer.getInformation("SCHICHT_TIEFE_START") + " - " + layer.getInformation("SCHICHT_TIEFE_ENDE") + "]";

		HtmlText formatedTiefe = new HtmlText.Builder()
				.appendAttribute("class", "Normal6")
				.appendContent(tiefe)
				.build();

		return formatedTiefe.appendTag();
	}

	public static String printCellTextDivider()
	{
		StringBuilder strb = new StringBuilder();
		HtmlText textDivider = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("_________")
				.build();

		strb.append(textDivider.appendTag())
				.append(printEmptyRow());

		return strb.toString();
	}

	public static String printLayerKindWithGranulation(final Layer layer)
	{
		String format = "";

		String schicht_art = layer.getInformation("SCHICHT_ART");

		String schicht_koernung = layer.getInformation("SCHICHT_KOERNUNG");
		if ("-".equals(schicht_koernung)) schicht_koernung = "";

		format = schicht_art.concat(" ").concat(schicht_koernung);

		return format;
	}

	public static String printLayerInformation(final ExplorationSite explorationSite, final String outcrop, final String tag)
	{
		List<Layer> schichten = explorationSite.getLayersWithOutcrop(outcrop);

		StringBuilder stringBuilder = new StringBuilder();

		int number = schichten.size();

		for (int i = 0 ; i < number ; i++)
		{
			Layer layer = schichten.get(i);

			String formatedTag;

			//TODO
			if (tag.contains("CHEMIE"))
			{
				formatedTag = printChemistryMarkup(layer.getInformation(tag));
			} else
			{
				formatedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(layer.getInformation(tag))
						.build().appendTag();
			}


			if (i > 0)
			{
				stringBuilder.append(printCellTextDivider());
			}

			stringBuilder.append(formatedTag);


//				if (number > 1)
//				{

			HtmlText formatedTiefe = new HtmlText.Builder()
					.appendAttribute("class", "Normal6")
					.appendContent("[T:")
					.appendContent(layer.getInformation("SCHICHT_TIEFE_START"))
					.appendContent("-")
					.appendContent(layer.getInformation("SCHICHT_TIEFE_ENDE"))
					.appendContent("]")
					.build();

			stringBuilder.append(printEmptyRow());
			stringBuilder.append(printFormattedLayerDepth(layer));
//				}


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

	public static String printRukLayers(final ExplorationSite explorationSite, final String outcrop)
	{
		List<Layer> tob = explorationSite.getLayersWithOutcrop(outcrop);

		StringBuilder stringBuilder = new StringBuilder();

		for (Layer layer : tob)
		{
			String ruk = layer.getInformation("SCHICHT_RUK");

			if (! "-".equals(ruk) && ! "".equals(ruk))
			{
				if (0 != stringBuilder.length())
				{
					stringBuilder.append(printCellTextDivider());
				}

				HtmlText text1 = new HtmlText.Builder()
						.appendAttribute("class", "Normal6")
						.appendContent(layer.getInformation("SCHICHT_ART"))
						.build();

				HtmlText text2 = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(ruk)
						.build();

				stringBuilder.append(text1.appendTag())
						.append(text2.appendTag());

			}
		}

		return stringBuilder.toString();
	}
}
