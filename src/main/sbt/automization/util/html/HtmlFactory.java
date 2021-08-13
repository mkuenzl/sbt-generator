package sbt.automization.util.html;

public class HtmlFactory
{
	private HtmlFactory(){}

	public static String createCell(String classID, int width, int height, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("height", String.valueOf(height))
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createCell(String classID, int width, int height, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("height", String.valueOf(height))
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createCell(String classID, int width, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createCell(String classID, int width, String align, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("align", align)
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createCell(String classID, String align, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("align", align)
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createHeader(String classID, int width, int height, int rowspan, int colspan, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("height", String.valueOf(height))
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createHeader(String classID, int width, int height, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("height", String.valueOf(height))
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createHeader(String classID, int width, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createHeader(String classID, int width, int rowspan, int colspan, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();

		for (String line : content)
		{
			cell.appendContent(line);
		}

		return cell.appendTag();
	}

	public static String createRow(String classID, int height, String[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("height", String.valueOf(height))
				.build();

		for (String line : content)
		{
			row.appendContent(line);
		}

		return row.appendTag();
	}

	public static String createRow(int height, String[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("height", String.valueOf(height))
				.build();

		for (String line : content)
		{
			row.appendContent(line);
		}

		return row.appendTag();
	}

	public static String createRow(String[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.build();

		for (String line : content)
		{
			row.appendContent(line);
		}

		return row.appendTag();
	}

	public static String createRow(String classID, String[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", classID)
				.build();

		for (String line : content)
		{
			row.appendContent(line);
		}

		return row.appendTag();
	}
}
