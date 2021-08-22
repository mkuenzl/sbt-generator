package sbt.automization.format.text;

public abstract class TextFormatterImpl implements TextFormatter
{
	public TextFormatterImpl(){}

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
