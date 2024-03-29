package sbt.automization.core.html;

import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.format.text.TextFormatter;

import java.util.Locale;

public class HtmlFactory
{
	private HtmlFactory()
	{
	}
	
	public static String createCellAsString(String classID, int width, int height, int rowspan, int colspan, String[] content)
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
	
	public static String createCellAsString(String classID, int width, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
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
	
	public static String createCellAsString(String classID, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(String classID, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(String classID, int width, String[] content)
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
	
	public static String createCellAsString(String classID, int width, String align, String[] content)
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
	
	public static String createCellAsString(String classID, String style, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell.appendTag();
	}
	
	public static HtmlCell createCell(String classID, String style, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell;
	}
	
	public static HtmlCell createCell(String classID, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell;
	}
	
	public static String createCellAsString(String classID, String style, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(TextFormatter textFormatter, String classID, int width, int height, int rowspan, int colspan, String[] content)
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
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(TextFormatter textFormatter, String classID, int width, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(TextFormatter textFormatter, String classID, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(TextFormatter textFormatter, String classID, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(TextFormatter textFormatter, String classID, int width, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(TextFormatter textFormatter, String classID, int width, String align, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.appendAttribute("align", align)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell.appendTag();
	}
	
	public static String createCellAsString(TextFormatter textFormatter, String classID, String style, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell.appendTag();
	}
	
	public static HtmlCell createCell(TextFormatter textFormatter, String classID, String style, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell;
	}
	
	public static HtmlCell createCell(TextFormatter textFormatter, String classID, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell;
	}
	
	public static String createCellAsString(TextFormatter textFormatter, String classID, String style, int rowspan, int colspan, String[] content)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(textFormatter.format(line));
		}
		
		return cell.appendTag();
	}
	
	public static HtmlTableHeader createHeader(String classID, int width, int height, int rowspan, int colspan, String align, String[] content)
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
		
		return cell;
		
	}
	
	public static HtmlTableHeader createHeader(String classID, int width, int height, int rowspan, int colspan, String[] content)
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
		
		return cell;
	}
	
	public static HtmlTableHeader createHeader(String classID, int rowspan, int colspan, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell;
	}
	
	public static HtmlTableHeader createHeader(String classID, int width, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("width", String.valueOf(width))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell;
	}
	
	public static HtmlTableHeader createHeader(String classID, String style, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell;
	}
	
	public static HtmlTableHeader createHeader(String classID, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell;
	}
	
	public static HtmlTableHeader createHeader(String classID, String style, int rowspan, int colspan, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell;
	}
	
	public static HtmlTableHeader createHeader(String classID, int width, int rowspan, int colspan, String[] content)
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
		
		return cell;
	}
	
	public static String createHeaderAsString(String classID, int width, int height, int rowspan, int colspan, String align, String[] content)
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
	
	public static String createHeaderAsString(String classID, int width, int height, int rowspan, int colspan, String[] content)
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
	
	public static String createHeaderAsString(String classID, int rowspan, int colspan, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell.appendTag();
	}
	
	public static String createHeaderAsString(String classID, int width, String[] content)
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
	
	public static String createHeaderAsString(String classID, String style, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell.appendTag();
	}
	
	public static String createHeaderAsString(String classID, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell.appendTag();
	}
	
	public static String createHeaderAsString(String classID, String style, int rowspan, int colspan, String[] content)
	{
		HtmlTableHeader cell = new HtmlTableHeader.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.appendAttribute("rowspan", String.valueOf(rowspan))
				.appendAttribute("colspan", String.valueOf(colspan))
				.build();
		
		for (String line : content)
		{
			cell.appendContent(line);
		}
		
		return cell.appendTag();
	}
	
	public static String createHeaderAsString(String classID, int width, int rowspan, int colspan, String[] content)
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
	
	public static String createRowAsString(String classID, int height, String[] content)
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
	
	public static String createRowAsString(int height, String[] content)
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
	
	public static String createRowAsString(String[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.build();
		
		for (String line : content)
		{
			row.appendContent(line);
		}
		
		return row.appendTag();
	}
	
	public static String createRowAsString(String classID, String[] content)
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
	
	public static String createRowAsString(String classID, String style, String[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", classID)
				.appendAttribute("style", style)
				.build();
		
		for (String line : content)
		{
			row.appendContent(line);
		}
		
		return row.appendTag();
	}
	
	public static HtmlRow createRow(String classID)
	{
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", classID)
				.build();
		
		return row;
	}
	
	public static HtmlRow createRow(String classID, String[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", classID)
				.build();
		
		for (String line : content)
		{
			row.appendContent(line);
		}
		
		return row;
	}
	
	public static HtmlRow createRow(HtmlCell[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.build();
		
		for (HtmlCell cell : content)
		{
			row.appendContent(cell.appendTag());
		}
		
		return row;
	}
	
	public static HtmlRow createRow(String classID, HtmlCell[] content)
	{
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", classID)
				.build();
		
		for (HtmlCell cell : content)
		{
			row.appendContent(cell.appendTag());
		}
		
		return row;
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
				htmlCell.appendAttribute("class", "ChemistryWhite");
				htmlCell.appendContent("FREI");
				break;
			case "JA":
				htmlCell.appendAttribute("class", "ChemistryBlack");
				htmlCell.appendContent("PECH");
				break;
			default:
				htmlCell.appendAttribute("class", "NormalBold");
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
	public static String createChemistryCellAsString(final String classification)
	{
		HtmlCell chemistryCell = createChemistryCell(classification);
		
		return chemistryCell.appendTag();
	}
	
	public static HtmlCell createChemistryCell(final String classification)
	{
		HtmlCell htmlCell;
		
		if ("".equals(classification) || null == classification) return new HtmlCell.Builder()
				.appendAttribute("class", "NormalBold")
				.appendContent("-")
				.build();
		
		switch (classification)
		{
			case "Z0":
			case "BM-0":
			case "BG-0":
			case "GS-0":
			case "GRS":
			case "SKG":
			case "SKA":
			case "SFA":
			case "BFA":
			case "HS":
			case "DK0":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryWhite")
						.appendContent(classification)
						.build();
				break;
			case "BM-0*":
			case "BG-0*":
			case "Z0*":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryBlue")
						.appendContent(classification)
						.build();
				break;
			case "BM-F0*":
			case "BG-F0*":
			case "RC-1":
			case "HOS-1":
			case "GS-1":
			case "SWS-1":
			case "CUM-1":
			case "HMVA-1":
			case "Z1":
			case "Z1.1":
			case "RC1":
			case "DK I":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryGreen")
						.appendContent(classification)
						.build();
				break;
			case "BM-F1":
			case "BG-F1":
			case "RC-2":
			case "HOS-2":
			case "GS-2":
			case "SWS-2":
			case "CUM-2":
			case "HMVA-2":
			case "Z1.2":
			case "RC2":
			case "DK II":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryYellow")
						.appendContent(classification)
						.build();
				break;
			case "BG-F2":
			case "BM-F2":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryOrange")
						.appendContent(classification)
						.build();
				break;
			case "BM-F3":
			case "BG-F3":
			case "RC-3":
			case "GS-3":
			case "Z2":
			case "RC3":
			case "DK III":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryRed")
						.appendContent(classification)
						.build();
				break;
			case ">BM-F3":
			case ">BG-F3":
			case ">Z2":
			case ">DK III":
			case ">RC3":
			case "gefährlich":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryBlack")
						.appendContent(classification)
						.build();
				break;
			case "nachweisbar":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryBlack")
						.appendContent("nachweis-")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("bar")
						.build();
				break;
			case "nicht gefährlich":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryWhite")
						.appendContent("nicht")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("gefährlich")
						.build();
				break;
			case "keine Auffälligkeit":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryWhite")
						.appendContent("keine")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Auffälligkeit")
						.build();
				break;
			case "nicht nachweisbar":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryWhite")
						.appendContent("nicht")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("nachweis-")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("bar")
						.build();
				break;
			case "nicht eingehalten":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryBlack")
						.appendContent("nicht")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("eingehalten")
						.build();
				break;
			case "eingehalten":
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "ChemistryWhite")
						.appendContent("eingehalten")
						.build();
				break;
			default:
				htmlCell = new HtmlCell.Builder()
						.appendAttribute("class", "NormalBold")
						.appendContent(classification)
						.build();
				break;
		}
		
		return htmlCell;
	}
}
