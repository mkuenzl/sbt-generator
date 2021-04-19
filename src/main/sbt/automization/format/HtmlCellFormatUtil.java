package sbt.automization.format;

import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlText;

public final class HtmlCellFormatUtil
{

    public static HtmlCell formatPitch(final String data)
    {
        HtmlCell htmlCell = new HtmlCell();

        switch (data)
        {
            case "nein":
                htmlCell.appendAttribute("class", "ChemieWhite");
                htmlCell.appendContent("FREI");
                break;
            case "ja":
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

    public static HtmlCell formatChemistry(final String data)
    {
        HtmlCell htmlCell;

        switch (data)
        {
            case "Z0":
            case "DK0":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieWhite")
                        .appendContent(data)
                        .build();
                break;
            case "Z0*":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieBlue")
                        .appendContent(data)
                        .build();
                break;
            case "Z1":
            case "Z1.1":
            case "RC1":
            case "DK1":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieGreen")
                        .appendContent(data)
                        .build();
                break;
            case "Z1.2":
            case "RC2":
            case "DK2":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieYellow")
                        .appendContent(data)
                        .build();
                break;
            case "Z2":
            case "RC3":
            case "DK3":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieRed")
                        .appendContent(data)
                        .build();
                break;
            case ">Z2":
            case ">DK3":
            case ">RC3":
            case "gefährlich":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieBlack")
                        .appendContent(data)
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
                        .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("nicht").build().appendTag())
                        .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("gefährlich").build().appendTag())
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

    public static HtmlTable buildInnerTable()
    {
        final String HTML_BASIC_INNER_TABLE_STYLE = new StringBuilder()
                .append("'")
                .append("width:100%")
                .append(";")
                .append("height:100%")
                .append(";")
                .append("border-collapse:collapse")
                .append(";")
                .append("mso-table-layout-alt:fixed")
                .append(";")
                .append("border:1")
                .append(";")
                .append("mso-border-alt:solid windowtext .5pt")
                .append(";")
                .append("mso-padding-alt:0cm 5.4pt 0cm 5.4pt")
                .append("'")
                .toString();

        HtmlTable innerTable = new HtmlTable.Builder()
                .appendAttribute("class", "MsoTableGrid")
                .appendAttribute("width", "100%")
                .appendAttribute("height", "100%")
                .appendAttribute("border", "1")
                .appendAttribute("background", "red")
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendAttribute("style", HTML_BASIC_INNER_TABLE_STYLE)
                .build();


        return innerTable;
    }
}
