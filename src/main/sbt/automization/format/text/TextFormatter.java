package sbt.automization.format.text;

public interface TextFormatter
{
	String format(String text);

	String format(String firstText, String secondText);
}
