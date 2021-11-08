package sbt.automization.core.format.text;

import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlText;

public final class WaterContentFormatter extends AbstractTextFormatter
{
	@Override
	public String format(String text)
	{
		if (text.contains(","))
		{
			HtmlText htmlText = new HtmlText();
			String[] split = text.split(",");
			for (String value : split)
			{
				htmlText.appendContent(value);
				htmlText.appendContent(UtilityPrinter.printLineEmptyThin());
			}
			return htmlText.appendTag();
		}
		
		if ("".equals(text)) return "-";
		
		return text;
	}
	
	@Override
	public String format(String firstText, String secondText)
	{
		return null;
	}
}
