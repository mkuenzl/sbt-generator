package sbt.automization.templates.helper;

import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlTableHeader;
import sbt.automization.html.HtmlText;
import sbt.automization.styles.StyleParameter;

import java.util.Arrays;
import java.util.List;

public final class HeaderProvider
{
	private final StyleParameter style;

	public HeaderProvider(StyleParameter style)
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
		List<String> strings = Arrays.asList(text);
		strings.add(formatUnit(unit));
		String[] formattedTexts = strings.toArray(new String[0]);

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
