package sbt.automization.core.format.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTextFormatter extends AbstractTextFormatter
{
	@Override
	public String format(String text)
	{
		if(text == null || text.trim().isEmpty()) return replaceIfEmpty(text);
		
		return formatDate(text);
	}
	
	@Override
	public String format(String firstText, String secondText)
	{
		String firstFormattedText;
		String secondFormattedText;
		
		if(firstText == null || firstText.trim().isEmpty())
		{firstFormattedText =  replaceIfEmpty(firstText);}
		else {
			firstFormattedText =  formatDate(firstText);
		}
		
		if(secondText == null || secondText.trim().isEmpty())
		{secondFormattedText =  replaceIfEmpty(secondText);}
		else {
			secondFormattedText =  formatDate(secondText);
		}
		
		return firstFormattedText.concat(" ").concat(secondFormattedText);
	}
	
	private String formatDate(String text){
		Date date = null;
		try
		{
			date = new SimpleDateFormat("dd.MM.yyyy").parse(text);
			
		} catch (ParseException e)
		{
			System.out.println("Format DateException: " + e);
			e.printStackTrace();
		}

		return new SimpleDateFormat("dd.MM.yyyy").format(date);
	}
}


