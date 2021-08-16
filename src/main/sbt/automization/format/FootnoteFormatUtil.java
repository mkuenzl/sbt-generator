package sbt.automization.format;

import sbt.automization.data.DataTableOld;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.data.refactoring.references.Reference;
import sbt.automization.util.html.HtmlText;

import java.util.ArrayList;
import java.util.List;

//TODO implement footnotes in excel and references
public final class FootnoteFormatUtil
{
	private static int counter = 1;

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
	 * @param dataTable an ExplorationSite object
	 * @return a String of html code representing a list of footnotes
	 */
	public static String formatSiteFootnotes(final DataTableOld dataTable)
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

		if ("#".equals(dataTable.getInformation("ERK_LEITFADEN_AUSBAUASPHALT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Bewertung unter Berücksichtigung der Angaben im Leitfaden Ausbauasphalt")
					.build()
					.appendTag());
		}

		if ("#".equals(dataTable.getInformation("ERK_TEILWEISE_VERFESTIGT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("teilweise verfestigt")
					.build()
					.appendTag());
		}

		if ("#".equals(dataTable.getInformation("ERK_UEBERSCHREITUNG_ORIENT")))
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

		if ("#".equals(dataTable.getInformation("ERK_RAMMHINDERNIS")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Rammhindernis; keine tiefere Entnahme möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(dataTable.getInformation("ERK_KABELTRASSE")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Kabeltrasse; keine tiefere Entnahme möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(dataTable.getInformation("ERK_FREMDBESTANDTEILE")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("mit mineralischen Fremdbestandteilen < 10 V.-%")
					.build()
					.appendTag());
		}

		if ("#".equals(dataTable.getInformation("ERK_GUENSTIGE_EINSTUFUNG")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Ggf. günstigere Einstufung nach Rücksprache mit der Behörde möglich")
					.build()
					.appendTag());
		}

		if ("#".equals(dataTable.getInformation("ERK_VERNACHLAESSIGUNG_LEITFAEHIGKEIT")))
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(String.valueOf(footnoteCounter++))
					.appendContent(".) ")
					.appendContent("Einstufung unter Vernachlässigung des Parameters elektrische Leitfähigkeit")
					.build()
					.appendTag());
		}

		String erk_variable_footnote1 = dataTable.getInformation("ERK_VARIABLE_FOOTNOTE1");
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

		String erk_variable_footnote2 = dataTable.getInformation("ERK_VARIABLE_FOOTNOTE2");
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

		String erk_variable_footnote3 = dataTable.getInformation("ERK_VARIABLE_FOOTNOTE3");
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


		if (! "-".equals(dataTable.getInformation("ERK_LP")))
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

	private static List<RefProbe> getFootnoteReferences()
	{
		List<RefProbe> references = new ArrayList<>(){{
			add(RefProbe.LP_ID);
			//TODO
		}};

		return references;
	}

	private static boolean checkExistenceOfFootnote(sbt.automization.data.refactoring.DataTable table, Reference reference)
	{
		String footnote = table.get(reference);
		return (footnote != null && ! footnote.equals("#") && ! footnote.equals("-") && ! footnote.equals(""));
	}

	public static String printFootnotes(sbt.automization.data.refactoring.DataTable table)
	{
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(formatInformationFootnote())
				.append(printFootnoteWithText(counter++, "Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m"));

		for (RefProbe footnoteReference : getFootnoteReferences())
		{
			if (checkExistenceOfFootnote(table, footnoteReference))
			{
				stringBuilder.append(printFootnoteForReference(table, footnoteReference));
			}
		}
		return stringBuilder.toString();
	}

	private static String printFootnoteForReference(sbt.automization.data.refactoring.DataTable table, RefProbe footnoteReference)
	{
		switch (footnoteReference)
		{
			case LP_ID:
				return printFootnoteWithText(counter++, new String[]{
						"Prüfergebnisse unter Berücksichtigung einer ca. 15 % Reduzierung aufgrund der Einspannung durch den ",
						TextFormatUtil.printLineBreak(),
						"gebundenen Oberbau"});
			default:
				throw new IllegalStateException("Unexpected value: " + footnoteReference);
		}
	}

	private static HtmlText createFootnote(int id)
	{
		return new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(String.valueOf(id))
				.appendContent(".) ")
				.build();
	}

	private static String printFootnoteWithText(int id, String line)
	{
		HtmlText footnote = createFootnote(id);
		footnote.appendContent(line);

		return footnote.appendTag();
	}

	private static String printFootnoteWithText(int id, String[] lines)
	{
		HtmlText footnote = createFootnote(id);

		for (String line : lines)
		{
			footnote.appendContent(line);
		}

		return footnote.appendTag();
	}
}
