package sbt.automization.format.text;

public final class SoilGroupTextFormatter extends TextFormatterImpl
{
	/**
	 * Expects a valid soil group and formats the String for representation in the appendix ExplorationSite
	 *
	 * @param layerKind a valid soil group as String either with [] or without
	 * @return a formatted String of the long text and short text of a soil group
	 */
	public String format(final String layerKind)
	{
		boolean isFillUp;
		String kind;
		String kindText;

		if (layerKind == null)
		{
			return "-";
		}

		if (layerKind.contains("-") || ("").equals(layerKind))
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

	@Override
	public String format(String firstText, String secondText)
	{
		return null;
	}
}
