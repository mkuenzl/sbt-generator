package sbt.automization.format.text;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.Key;
import sbt.automization.format.text.TextFormatter;
import sbt.automization.html.HtmlText;

import java.util.ArrayList;
import java.util.List;

public final class FootnoteFormatter
{
	private static int counter;

	private FootnoteFormatter() {}

	public static String printFootnotes(DataTable table)
	{
		StringBuilder stringBuilder = new StringBuilder();

		startCounter();

		stringBuilder.append(formatInformationFootnote())
				.append(printFootnoteWithText("Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m"));

		for (ProbeKey footnoteReference : getFootnoteReferences())
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

	private static String formatInformationFootnote()
	{
		return new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("Angaben:")
				.appendContent(TextFormatter.printLineBreak())
				.appendContent("KGV = Korngrößenverteilung, WG = Wassergehalt, LP = Plattendruckversuch, wPr = optimaler Wassergehalt")
				.appendContent(TextFormatter.printLineBreak())
				.appendContent("Gem. a. G. = Gemisch aus Gesteinskörnungen, NS = Naturstein, LS = Lavaschlacke, HO = Hochofenschlacke")
				.appendContent(TextFormatter.printLineBreak())
				.appendContent("RC = Rezyklierte Gesteinskörnung, BK = Brechkorn, RK = Rundkorn, sg = stetig gestuft, ug = unstetig gestuft")
				.appendContent(TextFormatter.printLineBreak())
				.appendContent(TextFormatter.printLineEmpty())
				.build()
				.appendTag();
	}

	private static String printFootnoteWithText(String line)
	{
		HtmlText footnote = createFootnote();
		footnote.appendContent(line);

		return footnote.appendTag();
	}

	private static List<ProbeKey> getFootnoteReferences()
	{
		List<ProbeKey> references = new ArrayList<>()
		{{
			add(ProbeKey.LP_ID);
			add(ProbeKey.FOOTNOTE_1);
			add(ProbeKey.FOOTNOTE_2);
			add(ProbeKey.FOOTNOTE_3);
			add(ProbeKey.FOOTNOTE_4);
			add(ProbeKey.FOOTNOTE_5);
			add(ProbeKey.FOOTNOTE_6);
			add(ProbeKey.FOOTNOTE_7);
			add(ProbeKey.FOOTNOTE_8);
			add(ProbeKey.FOOTNOTE_9);
			add(ProbeKey.FOOTNOTE_10);
		}};

		return references;
	}

	private static boolean checkExistenceOfFootnote(DataTable table, Key key)
	{
		String footnote = table.get(key);
		return (footnote != null && ! footnote.equals("#") && ! footnote.equals("-") && ! footnote.equals(""));
	}

	private static String printFootnoteForReference(DataTable table, ProbeKey footnoteReference)
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
		if (! table.containsValueFor(ProbeKey.LP_ID)) return "";

		return printFootnoteWithText(new String[]{
				"Prüfergebnisse unter Berücksichtigung einer ca. 15 % Reduzierung aufgrund der Einspannung durch den ",
				TextFormatter.printLineBreak(),
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
