package sbt.automization.core.format.text;

public abstract class AbstractTextFormatter implements TextFormatter
{
	public AbstractTextFormatter()
	{
	}
	
	protected String replaceIfEmpty(String text)
	{
		if ("".equals(text))
		{
			return "-";
		} else
		{
			return text;
		}
	}
}
