package sbt.automization.core.templates.helper;

import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.html.HtmlTableHeader;
import sbt.automization.core.html.HtmlText;
import sbt.automization.core.styles.StyleParameter;

import java.util.Arrays;
import java.util.List;

public final class HeaderFactory
{
	private final StyleParameter style;
	
	public HeaderFactory(StyleParameter style)
	{
		this.style = style;
	}
	
	public HtmlCell createCell(String[] text)
	{
		HtmlCell cell = HtmlFactory.createCell(style.getHeaderCellClass(), style.getHeaderCellWidth(), text);
		
		return cell;
	}
	
	public HtmlTableHeader createHeader(String[] text)
	{
		HtmlTableHeader headerCell = HtmlFactory.createHeader(style.getHeaderCellClass(), style.getHeaderCellWidth(), text);
		
		return headerCell;
	}
	
	public HtmlCell createCell(String[] text, String unit)
	{
		String[] formattedTexts = new String[text.length + 1];
		for (int i = 0; i < text.length; i++)
		{
			formattedTexts[i] = text[i];
		}
		formattedTexts[text.length] = formatUnit(unit);
		
		HtmlCell cell = HtmlFactory.createCell(style.getHeaderCellClass(), style.getHeaderCellWidth(), formattedTexts);
		
		return cell;
	}
	
	protected String formatUnit(String text)
	{
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", style.getUnitCellClass())
				.appendContent(text)
				.build()
				.appendTag();
		
		return formattedUnitText;
	}
	
	public HtmlTableHeader createHeader(String[] text, String unit)
	{
		List<String> strings = Arrays.asList(text);
		strings.add(formatUnit(unit));
		String[] formattedTexts = strings.toArray(new String[0]);
		
		HtmlTableHeader headerCell = HtmlFactory.createHeader(style.getHeaderCellClass(), style.getHeaderCellWidth(), text);
		
		return headerCell;
	}
}
