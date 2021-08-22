package sbt.automization.format.text;

public final class ProctorTextFormatter extends TextFormatterImpl
{
	@Override
	public String format(final String moisture)
	{
		if ("-".equals(moisture))
		{
			return "-";
		} else
		{
			return moisture.concat(" W<sub>Pr</sub>");
		}
	}

	@Override
	public String format(String firstText, String secondText)
	{
		return null;
	}
}
