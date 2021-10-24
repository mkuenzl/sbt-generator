package sbt.automization.core.format.text;

import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlText;
import sbt.automization.core.styles.BasicStyle;

public final class LoadClassTextFormatter extends AbstractTextFormatter
{
	/**
	 * Method used for pretty printing the cell loadClassText for "Belastungsklasse"
	 * Schema:
	 * Belastungsklasse
	 * Bk1/Bk2/keine
	 *
	 * @param loadClassText as
	 * @return formatted html code
	 */
	@Override
	public String format(final String loadClassText)
	{
		String formattedText;
		String replacedText = replaceIfEmpty(loadClassText);
		
		if ("keine".equals(replacedText) || "-".equals(replacedText))
		{
			formattedText = replacedText;
		} else
		{
			formattedText = "Bk" + replacedText;
		}
		
		return new HtmlText.Builder()
				.appendAttribute("class", BasicStyle.ROW.getStyleClass())
				.appendContent("Belastungsklasse")
				.appendContent(UtilityPrinter.printLineBreak())
				.appendContent(formattedText)
				.build()
				.appendTag();
	}
	
	@Deprecated
	@Override
	public String format(String firstText, String secondText)
	{
		return firstText + " " + secondText;
	}
}
