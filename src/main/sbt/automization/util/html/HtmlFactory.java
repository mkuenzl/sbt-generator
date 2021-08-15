package sbt.automization.util.html;

import sbt.automization.format.TextFormatUtil;

import java.util.Locale;

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

	public static String createHeader(String classID, int width, int height, int rowspan, int colspan, String align, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("height", String.valueOf(height))
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
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


	/**
	 * Creates different html cells based on the information about pitch.
	 *
	 * @param information a String that represents whether there is pitch (ja/nein/...)
	 * @return a new html cell object
	 */
	public static String createPitchCell(final String information)
	{
		HtmlCell htmlCell = new HtmlCell();

		switch (information.toUpperCase(Locale.ROOT))
		{
			case "NEIN":
				htmlCell.appendAttribute("class", "ChemieWhite");
				htmlCell.appendContent("FREI");
				break;
			case "JA":
				htmlCell.appendAttribute("class", "ChemieBlack");
				htmlCell.appendContent("PECH");
				break;
			default:
				htmlCell.appendAttribute("class", "NormalCenter");
				htmlCell.appendContent("-");
				break;
		}

		return htmlCell.appendTag();
	}

	/**
	 * Creates different kinds of cells based on the chemistry classification.
	 *
	 * @param classification a String of a chemistry classification
	 * @return a formatted html cell object for a specific chemistry classification
	 */
	public static String createChemistryCell(final String classification)
	{
		HtmlCell htmlCell;

		switch (classification)
		{
			case "Z0":
			case "DK0":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieWhite")
						.appendContent(classification)
						.build();
				break;
			case "Z0*":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieBlue")
						.appendContent(classification)
						.build();
				break;
			case "Z1":
			case "Z1.1":
			case "RC1":
			case "DK1":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieGreen")
						.appendContent(classification)
						.build();
				break;
			case "Z1.2":
			case "RC2":
			case "DK2":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieYellow")
						.appendContent(classification)
						.build();
				break;
			case "Z2":
			case "RC3":
			case "DK3":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieRed")
						.appendContent(classification)
						.build();
				break;
			case ">Z2":
			case ">DK3":
			case ">RC3":
			case "gefährlich":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieBlack")
						.appendContent(classification)
						.build();
				break;
			case "nachweisbar":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieBlack")
						.appendContent("nachweis-")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("bar")
						.build();
				break;
			case "nicht gefährlich":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieWhite")
						.appendContent("nicht")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("gefährlich")
						.build();
				break;
			case "nicht nachweisbar":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemieWhite")
						.appendContent("nicht")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("nachweis-")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("bar")
						.build();
				break;
			default:
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "NormalCenter")
						.appendContent("-")
						.build();
				break;
		}

		return htmlCell.appendTag();
	}
}
