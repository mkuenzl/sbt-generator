package sbt.automization.format;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class TextFormatUtil
{
	public static String formatBelastungsklasse(final Erkundungsstelle erkundungsstelle)
	{
		String s1 = new HtmlText.Builder().appendAttribute("class", "Normal")
				.appendContent("Belastungsklasse")
				.build()
				.appendTag();
		String s2 = new HtmlText.Builder().appendAttribute("class", "Normal")
				.appendContent("Bk" + erkundungsstelle.getInformation("ERK_BELASTUNGSKLASSE"))
				.build()
				.appendTag();

		return s1 + s2;
	}


	public static String formatSchichtProctor(final Schicht schicht)
	{
        String schicht_feuchtigkeit = schicht.getInformation("SCHICHT_FEUCHTIGKEIT");
        if ("".equals(schicht_feuchtigkeit))
		{
			return "-";
		} else
		{
			return schicht_feuchtigkeit.concat(" W<sub>Pr</sub>");
		}
	}

	public static String formatErkAufschlussDicke(final Erkundungsstelle erkundungsstelle, String aufschluss)
	{

		double height = 0.0;
		List<Schicht> schichtList = erkundungsstelle.getSchichtAufschluss(aufschluss);
		for (Schicht schicht : schichtList)
		{

			height = height + Double.parseDouble(schicht.getInformation("SCHICHT_DICKE").replace(",", "."));
		}
		return String.valueOf(height);
	}

	public static String formatSchichtProbePN(final Schicht schicht)
	{
		String probenart;
		if ("".equals(schicht.getInformation("SCHICHT_BEHAELTNIS")))
		{
			probenart = "EP";
		} else
		{
			probenart = "MP";
		}
		return probenart;
	}

	public static String formatErkFootnotes(final Erkundungsstelle erkundungsstelle)
	{

		int footnoteCounter = 1;
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(String.valueOf(footnoteCounter++))
				.appendContent(".) ")
				.appendContent("Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m")
				.build()
				.appendTag());

		if ("#".equals(erkundungsstelle.getInformation("ERK_LEITFADEN_AUSBAUASPHALT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Bewertung unter Berücksichtigung der Angaben im Leitfaden Ausbauasphalt")
					.build()
					.appendTag());
		}

		if ("#".equals(erkundungsstelle.getInformation("ERK_TEILWEISE_VERFESTIGT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("teilweise verfestigt")
					.build()
					.appendTag());
		}

		if ("#".equals(erkundungsstelle.getInformation("ERK_UEBERSCHREITUNG_ORIENT")))
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

		if ("#".equals(erkundungsstelle.getInformation("ERK_RAMMHINDERNIS")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Rammhindernis; keine tiefere Entnahme möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(erkundungsstelle.getInformation("ERK_KABELTRASSE")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Kabeltrasse; keine tiefere Entnahme möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(erkundungsstelle.getInformation("ERK_FREMDBESTANDTEILE")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("mit mineralischen Fremdbestandteilen < 10 V.-%")
					.build()
					.appendTag());
		}

		if ("#".equals(erkundungsstelle.getInformation("ERK_GUENSTIGE_EINSTUFUNG")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Ggf. günstigere Einstufung nach Rücksprache mit der Behörde möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(erkundungsstelle.getInformation("ERK_VERNACHLAESSIGUNG_LEITFAEHIGKEIT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Einstufung unter Vernachlässigung des Parameters elektrische Leitfähigkeit")
					.build()
					.appendTag());
		}

        String erk_variable_footnote1 = erkundungsstelle.getInformation("ERK_VARIABLE_FOOTNOTE1");
        if (erk_variable_footnote1 != null && !erk_variable_footnote1.equals("#") && !erk_variable_footnote1.equals("-"))
        {
            stringBuilder.append(new HtmlText.Builder()
                    .appendAttribute("class", "Normal")
                    .appendContent(String.valueOf(footnoteCounter++))
                    .appendContent(".) ")
                    .appendContent(erk_variable_footnote1)
                    .build()
                    .appendTag());
        }

		String erk_variable_footnote2 = erkundungsstelle.getInformation("ERK_VARIABLE_FOOTNOTE2");
		if (erk_variable_footnote2 != null && !erk_variable_footnote2.equals("#") && !erk_variable_footnote2.equals("-"))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent(erk_variable_footnote2)
					.build()
					.appendTag());
		}

		String erk_variable_footnote3 = erkundungsstelle.getInformation("ERK_VARIABLE_FOOTNOTE3");
		if (erk_variable_footnote3 != null && !erk_variable_footnote3.equals("#") && !erk_variable_footnote3.equals("-"))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent(erk_variable_footnote3)
					.build()
					.appendTag());
		}


		if (! "".equals(erkundungsstelle.getInformation("ERK_LP")))
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


	public static String formatSchichtTiefe(final Schicht schicht)
	{
		HtmlText htmlText = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("")
				.appendContent(schicht.getInformation("SCHICHT_TIEFE_START"))
				.appendContent("-")
				.appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
				.appendContent("")
				.build();

		return htmlText.appendTag();
	}

	/**
	 * Expects a valid Bodengruppe and formats the String for representation in the ERK_Anlage
	 *
	 * @param schichtArt a valid Bodengruppe as String either with [] or without
	 * @return a formated String of the long text and short text of a Bodengruppe
	 */
	public static String formatSchichtBodenGruppe(final String schichtArt)
	{
		boolean isFillUp;
		String kind;
		String kindText;

		if (schichtArt == null)
		{
			return "-";
		}

		if (schichtArt.contains("-"))
		{
			return schichtArt;
		}

		if (schichtArt.contains("["))
		{
			kind = schichtArt.replaceAll("[\\[\\]]", "");
			isFillUp = true;
		} else
		{
			kind = schichtArt;
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
				kindText = "Leicht plastische Schluffe";
				break;
			case "UM":
				kindText = "Mittelplastische Schluffe";
				break;
			case "UA":
				kindText = "Ausgeprägt plastische Schluffe";
				break;
			case "TL":
				kindText = "Leicht plastische Tone";
				break;
			case "TM":
				kindText = "Mittelplastische Tone";
				break;
			case "TA":
				kindText = "Ausgeprägt plastische Tone";
				break;
			case "OU":
				kindText = "Organogene Schluffe";
				break;
			case "OT":
				//schicht_art = "Organogene Tone";
				//break;
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

	public static String presentSchichtenToB(final Erkundungsstelle erkundungsstelle)
	{
		StringBuilder formatedSchichtenMaterial = new StringBuilder();

		List<Schicht> tob = erkundungsstelle.getSchichtAufschluss("TOB");


		int size = tob.size();
		for (int i = 0 ; i < size ; i++)
		{
			Schicht schicht = tob.get(i);

			formatedSchichtenMaterial.append(NameFormatUtil.formatArt(schicht.getInformation("SCHICHT_ART")));

			formatedSchichtenMaterial.append(printLineBreak());

			HtmlText text2 = new HtmlText.Builder()
					.appendAttribute("class", "Normal6")
					.appendContent(schicht.getInformation("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT"))
					.appendContent(" ")
					.appendContent(schicht.getInformation("SCHICHT_KOERNUNG"))
					.build();

			HtmlText text3 = new HtmlText.Builder()
					.appendAttribute("class", "Normal6")
					.appendContent("[T:")
					.appendContent(schicht.getInformation("SCHICHT_TIEFE_START"))
					.appendContent("-")
					.appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
					.appendContent("]")
					.build();

			formatedSchichtenMaterial.append(text2.appendTag());
			formatedSchichtenMaterial.append(text3.appendTag());

			if (i + 1 < size)
			{
				formatedSchichtenMaterial.append(printCellTextDivider());
			}
		}

		return formatedSchichtenMaterial.toString();
	}

	public static String printLineBreak()
	{
		HtmlText lineBreak = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("&nbsp;")
				.build();

		return lineBreak.appendTag();
	}

	public static String printCellTextDivider()
	{
		StringBuilder strb = new StringBuilder();
		HtmlText textDivider = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("_________")
				.build();

		strb.append(textDivider.appendTag())
				.append(printLineBreak());

		return strb.toString();
	}

	public static String printSchichtInformation(final Erkundungsstelle erkundungsstelle, final String aufschluss, final String tag)
	{
		List<Schicht> schichten = erkundungsstelle.getSchichtAufschluss(aufschluss);

		StringBuilder stringBuilder = new StringBuilder();

		int number = schichten.size();

		for (int i = 0; i < number; i++)
		{
			Schicht schicht = schichten.get(i);

			String formatedTag;

			//TODO
			if (tag.contains("CHEMIE"))
			{
				formatedTag = printChemieMarkup(schicht.getInformation(tag));
			} else
			{
				formatedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(schicht.getInformation(tag))
						.build().appendTag();
			}


				if (i > 0)
				{
					stringBuilder.append(printCellTextDivider());
				}

				stringBuilder.append(formatedTag);


				if (number > 1)
				{

					HtmlText text2 = new HtmlText.Builder()
							.appendAttribute("class", "Normal6")
							.appendContent("[T:")
							.appendContent(schicht.getInformation("SCHICHT_TIEFE_START"))
							.appendContent("-")
							.appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
							.appendContent("]")
							.build();

					stringBuilder.append(printLineBreak());
					stringBuilder.append(text2.appendTag());
				}


			}

		return stringBuilder.toString();
	}

	public static String printSchichtRUK(final Erkundungsstelle erkundungsstelle, final String aufschluss)
	{
		List<Schicht> tob = erkundungsstelle.getSchichtAufschluss(aufschluss);

		StringBuilder stringBuilder = new StringBuilder();

		for (Schicht schicht : tob)
		{
			String ruk = schicht.getInformation("SCHICHT_RUK");

			if (!"-".equals(ruk) && !"".equals(ruk))
			{
				if (0 != stringBuilder.length())
				{
					stringBuilder.append(printCellTextDivider());
				}

				HtmlText text1 = new HtmlText.Builder()
						.appendAttribute("class", "Normal6")
						.appendContent(schicht.getInformation("SCHICHT_ART"))
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

	public static String printChemieMarkup(final String data)
	{
		StringBuilder stringBuilder = new StringBuilder();

		switch (data)
		{
			case "Z0":
			case "DK0":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: white;font-weight: bold;\n\n" +
								"  color: black\">")
						.appendContent(data)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z0*":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: #00FFFF;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent(data)
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
						.appendContent(data)
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
						.appendContent(data)
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
						.appendContent(data)
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
						.appendContent(data)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "nicht gefährlich":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: white;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent("nicht")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"font-weight: bold\";>")
								.appendContent("gefährlich")
								.appendContent("</span>")
								.build().appendTag())
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "nicht eingehalten":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"font-weight: bold\";>")
						.appendContent("nicht")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"font-weight: bold\";>")
								.appendContent("eingehalten")
								.appendContent("</span>")
								.build().appendTag())
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "eingehalten":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"font-weight: bold\";>")
						.appendContent(data)
						.appendContent("</span>")
						.build().appendTag());
			default:
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(data)
						.build().appendTag());
				break;
		}

		return stringBuilder.toString();
	}
}
