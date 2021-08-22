package sbt.automization.format.text;

import sbt.automization.format.printer.TextFormatterMulti;
import sbt.automization.html.HtmlText;

public final class LineBreakTextFormatter extends TextFormatterImpl
{
	/**
	 * Formats different names for better visualisation in each template, replacement for automatic line breaks.
	 * (Gem. a. G. (NS))
	 *
	 * @param name layer kind
	 * @return formatted String with line breaks
	 */
	@Override
	public String format(final String name)
	{
		String formattedName = null;
		String[] words = name.split(" ");

		switch (words.length)
		{
			case 3:
				return buildOneRowText(words);
			case 4:
				return buildTwoRowText(words);
			default:
				return name;
		}
	}

	@Override
	public String format(String firstText, String secondText)
	{
		return null;
	}

	private String buildOneRowText(String[] words)
	{
		HtmlText htmlText = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(words[0].concat(" ").concat(words[1]).concat(" ").concat(words[2]))
				.build();

		return htmlText.appendTag();
	}

	private String buildTwoRowText(String[] words)
	{
		HtmlText htmlText = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(words[0].concat(" ").concat(words[1]).concat(" ").concat(words[2]))
				.appendContent(TextFormatterMulti.printLineBreak())
				.appendContent(words[3])
				.build();

		return htmlText.appendTag();
	}
}
