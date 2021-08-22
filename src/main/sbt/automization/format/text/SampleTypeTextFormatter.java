package sbt.automization.format.text;

public final class SampleTypeTextFormatter extends TextFormatterImpl
{
	/**
	 * Method for specifying whether a sample is a single probe or could contain multiple.
	 *
	 * @param container a String of the sample container
	 * @return a String of the sample classification
	 */
	@Override
	public String format(final String container)
	{
		String sampleType;
		if ("-".equals(container))
		{
			sampleType = "EP";
		} else
		{
			sampleType = "MP";
		}
		return sampleType;
	}


	@Override
	public String format(String firstText, String secondText)
	{
		return null;
	}
}
