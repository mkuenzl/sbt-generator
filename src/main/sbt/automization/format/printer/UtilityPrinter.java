package sbt.automization.format.printer;

import sbt.automization.data.DataTable;
import sbt.automization.html.HtmlText;

public final class UtilityPrinter implements TextPrinter
{
	/**
	 * Method to provide a visual border between layer information.
	 *
	 * @return a String representing a line
	 */
	public static String printCellTextDivider()
	{
		StringBuilder stringBuilder = new StringBuilder();

		HtmlText textDivider = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("_________")
				.build();

		stringBuilder.append(textDivider.appendTag())
				.append(printLineEmpty());

		return stringBuilder.toString();
	}

	/**
	 * Method to provide a line without content.
	 *
	 * @return a html paragraph without content
	 */
	public static String printLineEmpty()
	{
		HtmlText emptyRow = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("&nbsp;")
				.build();

		return emptyRow.appendTag();
	}

	/**
	 * Method to provide the standard html line break. May be moved to the html package in the future.
	 *
	 * @return a html line break
	 */
	public static String printLineBreak()
	{
		return "<br>";
	}

	@Override
	public String print(DataTable dataTable)
	{
		return null;
	}

	@Override
	public String print()
	{
		return null;
	}


}
