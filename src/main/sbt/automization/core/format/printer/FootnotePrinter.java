package sbt.automization.core.format.printer;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.key.Key;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.html.HtmlText;

import java.util.ArrayList;
import java.util.List;

public final class FootnotePrinter implements TextPrinter
{
	private static int counter;
	
	public FootnotePrinter()
	{
	}
	
	public String print(DataTable table)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		startCounter();
		
		stringBuilder.append(formatInformationFootnote())
				.append(printFootnoteWithText("Messeinheit: Garmin eTrex 10, herstellerseitig angegebene Lagegenauigkeit ~ 3 m"));
		
		for (ProbeKey footnoteReference : getFootnoteReferences())
		{
			if (checkExistenceOfFootnote(table, footnoteReference))
			{
				stringBuilder.append(printFootnoteWithText(table.get(footnoteReference)));
			}
		}
		return stringBuilder.toString();
	}
	
	@Override
	public String print()
	{
		return null;
	}
	
	static void startCounter()
	{
		counter = 1;
	}
	
	private String formatInformationFootnote()
	{
		return new HtmlText.Builder()
				.appendAttribute("class", "Normal6")
				.appendContent("Angaben:")
				.appendContent(UtilityPrinter.printLineBreak())
				.appendContent("KGV = Korngrößenverteilung, WG = Wassergehalt, Ev = Plattendruckversuch, wPr = optimaler Wassergehalt, Gem. a. G. = Gemisch aus Gesteinskörnungen, NS = ")
				.appendContent(UtilityPrinter.printLineBreak())
				.appendContent("Naturstein, LS = Lavaschlacke, HO = Hochofenschlacke, RC = Rezyklierte Gesteinskörnung, BK = Brechkorn, RK = Rundkorn, sg = stetig gestuft, ug = unstetig gestuft")
				.appendContent(UtilityPrinter.printLineEmptyThin())
				.build()
				.appendTag();
	}
	
	private String printFootnoteWithText(String line)
	{
		HtmlText footnote = createFootnote();
		footnote.appendContent(line);
		
		return footnote.appendTag();
	}
	
	private HtmlText createFootnote()
	{
		return new HtmlText.Builder()
				.appendAttribute("class", "Normal6")
				.appendContent(String.valueOf(counter++))
				.appendContent(".) ")
				.build();
	}
	
	private List<ProbeKey> getFootnoteReferences()
	{
		List<ProbeKey> references = new ArrayList<>()
		{{
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
	
	private boolean checkExistenceOfFootnote(DataTable table, Key key)
	{
		String footnote = table.get(key);
		return (footnote != null && !footnote.equals("#") && !footnote.equals("-") && !footnote.equals(""));
	}
}
