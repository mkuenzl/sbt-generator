package sbt.automization.format;

import sbt.automization.html.HtmlCell;

import java.util.Locale;

/**
 * Class that creates formatted html cell objects.
 */
public final class HtmlCellFormatUtil
{
	private HtmlCellFormatUtil(){}

	/**
	 * Creates different html cells based on the information about pitch.
	 *
	 * @param information a String that represents whether there is pitch (ja/nein/...)
	 * @return a new html cell object
	 */
	public static HtmlCell formatPitch(final String information)
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
				htmlCell.appendAttribute("class", "NormalErkundungsstelle");
				htmlCell.appendContent("-");
				break;
		}

		return htmlCell;
	}

	/**
	 * Creates different kinds of cells based on the chemistry classification.
	 *
	 * @param classification a String of a chemistry classification
	 * @return a formatted html cell object for a specific chemistry classification
	 */
	public static HtmlCell formatChemistry(final String classification)
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
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent("-")
						.build();
				break;
		}

		return htmlCell;
	}
}
