package sbt.automization.format;

import sbt.automization.data.Schicht;
import sbt.automization.util.html.HtmlText;

public final class NameFormatUtil
{
	//Gem. a. G. (NS)

	/**
	 * Formats different names for better visualisation in each template, replacement for automatic line breaks.
	 * @param name
	 * @return
	 */
	public static String formatArt(final String name)
	{
		String formatedName = null;

		if (name.contains("Gem"))
		{
			//Gem. a. G. (NS)
			String[] split = name.split(" ");

			HtmlText text1 = new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(split[0].concat(" ").concat(split[1]).concat(" ").concat(split[2]))
					.appendContent(TextFormatUtil.printLineBreak())
					.appendContent(split[3])
					.build();

			formatedName = text1.appendTag();
		} else {
			return name;
		}
		return formatedName;
	}
}
