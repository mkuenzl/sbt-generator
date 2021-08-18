package sbt.automization.format;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.data.refactoring.references.Reference;
import sbt.automization.util.html.HtmlText;

import java.util.ArrayList;
import java.util.List;

public final class FootnoteFormatUtil
{
	private static int counter;

	private FootnoteFormatUtil() {}

	public static String printFootnotes(DataTable table)
	{
		StringBuilder stringBuilder = new StringBuilder();

		startCounter();

		stringBuilder.append(formatInformationFootnote())
				.append(printFootnoteWithText("Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m"));

		for (RefProbe footnoteReference : getFootnoteReferences())
		{
			if (checkExistenceOfFootnote(table, footnoteReference))
			{
				stringBuilder.append(printFootnoteForReference(table, footnoteReference));
			}
		}
		return stringBuilder.toString();
	}

	static void startCounter()
	{
		counter = 1;
	}

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

	private static String printFootnoteWithText(String line)
	{
		HtmlText footnote = createFootnote();
		footnote.appendContent(line);

		return footnote.appendTag();
	}

	private static List<RefProbe> getFootnoteReferences()
	{
		List<RefProbe> references = new ArrayList<>()
		{{
			add(RefProbe.LP_ID);
			add(RefProbe.FOOTNOTE_1);
			add(RefProbe.FOOTNOTE_2);
			add(RefProbe.FOOTNOTE_3);
			add(RefProbe.FOOTNOTE_4);
			add(RefProbe.FOOTNOTE_5);
			add(RefProbe.FOOTNOTE_6);
			add(RefProbe.FOOTNOTE_7);
			add(RefProbe.FOOTNOTE_8);
			add(RefProbe.FOOTNOTE_9);
			add(RefProbe.FOOTNOTE_10);
		}};

		return references;
	}

	private static boolean checkExistenceOfFootnote(DataTable table, Reference reference)
	{
		String footnote = table.get(reference);
		return (footnote != null && ! footnote.equals("#") && ! footnote.equals("-") && ! footnote.equals(""));
	}

	private static String printFootnoteForReference(DataTable table, RefProbe footnoteReference)
	{
		switch (footnoteReference)
		{
			case LP_ID:
				return printFootnoteForLP(table);
			default:
				return printFootnoteWithText(table.get(footnoteReference));
		}
	}

	private static HtmlText createFootnote()
	{
		return new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(String.valueOf(counter++))
				.appendContent(".) ")
				.build();
	}

	private static String printFootnoteForLP(DataTable table)
	{
		if (! table.containsValueFor(RefProbe.LP_ID)) return "";

		return printFootnoteWithText(new String[]{
				"Prüfergebnisse unter Berücksichtigung einer ca. 15 % Reduzierung aufgrund der Einspannung durch den ",
				TextFormatUtil.printLineBreak(),
				"gebundenen Oberbau"});
	}

	private static String printFootnoteWithText(String[] lines)
	{
		HtmlText footnote = createFootnote();

		for (String line : lines)
		{
			footnote.appendContent(line);
		}

		return footnote.appendTag();
	}
}
