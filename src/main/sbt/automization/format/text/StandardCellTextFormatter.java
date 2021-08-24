package sbt.automization.format.text;

public final class StandardCellTextFormatter extends AbstractTextFormatter
{
	@Override
	public String format(String text)
	{
		return replaceIfEmpty(text);
	}

	@Override
	public String format(String firstText, String secondText)
	{
		return replaceIfEmpty(firstText).concat(" ").concat(replaceIfEmpty(secondText));
	}
}
