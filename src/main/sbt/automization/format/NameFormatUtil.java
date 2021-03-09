package sbt.automization.format;

import sbt.automization.data.Schicht;
import sbt.automization.util.html.HtmlText;

public class NameFormatUtil
{
	//Gem. a. G. (NS)
	public static String formatArt(final Schicht schicht)
	{
		String schicht_art = schicht.getInformation("SCHICHT_ART");

		String formatedArt = null;

		if (schicht_art.contains("Gem"))
		{
			//Gem. a. G. (NS)
			String[] split = schicht_art.split(" ");

			HtmlText text1 = new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(split[0].concat(" ").concat(split[1]).concat(" ").concat(split[2]))
					.build();
			HtmlText text2 = new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(split[3])
					.build();

			formatedArt = text1.appendTag().concat(text2.appendTag());

		}
		return formatedArt;
	}
}
