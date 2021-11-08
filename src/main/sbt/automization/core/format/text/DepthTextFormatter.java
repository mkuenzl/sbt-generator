package sbt.automization.core.format.text;

import sbt.automization.core.html.HtmlText;

public final class DepthTextFormatter extends AbstractTextFormatter
{
	private final boolean specified;
	
	public DepthTextFormatter()
	{
		this.specified = false;
	}
	
	public DepthTextFormatter(boolean specified)
	{
		this.specified = specified;
	}
	
	@Override
	public String format(String text)
	{
		return text;
	}
	
	/**
	 * Method to format a range between two depths as html text.
	 *
	 * @param startDepth a String representing the start depth
	 * @param endDepth   a String representing the end depth
	 * @return a html text representing the range
	 */
	@Override
	public String format(final String startDepth, final String endDepth)
	{
		if (specified)
		{
			return formatSpecified(startDepth, endDepth);
		} else
		{
			return formatNonSpecified(startDepth, endDepth);
		}
	}
	
	private String formatSpecified(final String startDepth, final String endDepth)
	{
		String depth = "[T: " + startDepth + " - " + endDepth + "]";
		
		HtmlText formattedDepth = new HtmlText.Builder()
				.appendAttribute("class", "Normal5")
				.appendContent(depth)
				.build();
		
		return formattedDepth.appendTag();
	}
	
	private String formatNonSpecified(final String startDepth, final String endDepth)
	{
		HtmlText htmlText = new HtmlText.Builder()
				.appendAttribute("class", "Normal")
				.appendContent("")
				.appendContent(startDepth)
				.appendContent("-")
				.appendContent(endDepth)
				.appendContent("")
				.build();
		
		return htmlText.appendTag();
	}
}