package sbt.automization.core.format.text;

public interface TextFormatter
{
	String format(String text);
	
	String format(String firstText, String secondText);
}
