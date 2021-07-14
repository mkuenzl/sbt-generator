package sbt.automization.format;

import sbt.automization.data.ExplorationSite;
import sbt.automization.util.html.HtmlText;

public final class FootnoteFormatUtil
{
	private FootnoteFormatUtil(){}


	public static String formatInformationFootnote()
	{
		return new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("Angaben:")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("KGV = Korngrößenverteilung, WG = Wassergehalt, LP = Plattendruckversuch, wPr = optimaler Wassergehalt")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("Gem. a. G. = Gemisch aus Gesteinskörnungen, NS = Naturstein, LS = Lavaschlacke, HO = Hochofenschlacke")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("RC = Rezyklierte Gesteinskörnung, BK = Brechkorn, RK = Rundkorn, sg = stetig gestuft, ug = unstetig gestuft")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent(TextFormatUtil.printLineEmpty())
				.build()
				.appendTag();
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

		stringBuilder.append(formatInformationFootnote());

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
					.appendContent(TextFormatUtil.printLineBreak())
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
					.appendContent(TextFormatUtil.printLineBreak())
					.appendContent("gebundenen Oberbau")
					.build()
					.appendTag());
		}
		return stringBuilder.toString();
	}
}
