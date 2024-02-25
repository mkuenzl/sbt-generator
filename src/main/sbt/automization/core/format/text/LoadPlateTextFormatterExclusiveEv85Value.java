package sbt.automization.core.format.text;

import sbt.automization.core.html.HtmlText;

public final class LoadPlateTextFormatterExclusiveEv85Value
		extends AbstractLoadPlateTextFormatter
{
	@Override
	public String format(String text)
	{
		return null;
	}
	
	/**
	 * Method creates a formatted html text of the Ev2 value. When Ev2 is smaller 80 and Ev85 is in between a certain
	 * range this range will be shown also.
	 *
	 * @param ev2         the Ev2 value from an LP experiment as String
	 * @param ev85Percent the Ev85 value from an LP experiment as String
	 * @return a String of the formatted html code
	 */
	@Override
	public String format(final String ev2, final String ev85Percent)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		if ("< 80".equals(ev2) && !"-".equals(ev85Percent))
		{
			String ev85PercentInformation = ev85Percent.replace(",", ".");
			String replace = ev85PercentInformation.replace("~ ", "");
			
			double ev85PercentValue = Double.parseDouble(replace);
			
			String range = formatRange(ev85PercentValue);

			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(ev2)
					.appendContent(new HtmlText.Builder()
							.appendAttribute("class", "Normal6")
							.appendContent(range)
							.build().appendTag())
					.build()
					.appendTag());
		} else
		{
			stringBuilder.append(ev2);
		}
		
		return stringBuilder.toString();
	}
}
