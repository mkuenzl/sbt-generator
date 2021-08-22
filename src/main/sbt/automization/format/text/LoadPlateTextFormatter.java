package sbt.automization.format.text;

import sbt.automization.html.HtmlText;

public final class LoadPlateTextFormatter extends TextFormatterImpl
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

		if ("< 80".equals(ev2) && ! "-".equals(ev85Percent))
		{
			String ev85PercentInformation = ev85Percent.replace(",", ".");
			String replace = ev85PercentInformation.replace("~ ", "");

			double ev85PercentValue = Double.parseDouble(replace);

			String range = "";

			if (ev85PercentValue >= 10 && ev85PercentValue < 20) range = "[30 - 40]";
			if (ev85PercentValue >= 20 && ev85PercentValue < 30) range = "[40 - 50]";
			if (ev85PercentValue >= 30 && ev85PercentValue < 40) range = "[50 - 60]";
			if (ev85PercentValue >= 40 && ev85PercentValue < 45) range = "[60 - 80]";

			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(ev2)
					//.appendContent(printLineBreak())
					.appendContent(new HtmlText.Builder()
							.appendAttribute("class", "Normal6")
							.appendContent(range)
							.build().appendTag())
					.build()
					.appendTag());
		} else
		{
			stringBuilder.append(new HtmlText.Builder()
					.appendAttribute("class", "Normal")
					.appendContent(ev2)
					.build()
					.appendTag());
		}

		return stringBuilder.toString();
	}
}
