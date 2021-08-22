package sbt.automization.format.text;

public final class KindAndGranulationTextFormatter extends TextFormatterImpl
{
	@Override
	public String format(String text)
	{
		return null;
	}

	@Override
	public String format(final String kind, String granulation)
	{
		String format = "";

		if ("-".equals(granulation)) granulation = "";

		format = kind.concat(" ").concat(granulation);

		return format;
	}
}
