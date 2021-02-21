package sbt.automization.format;

import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlText;

public final class HtmlCellFormatUtil {

    public static HtmlCell formatPech(final String data)
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
                htmlCell.appendContent("");
                break;
        }

        return htmlCell;
    }

    public static HtmlCell formatChemie(final String data)
    {
        HtmlCell htmlCell;

        switch (data){
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
            case "gefährlich":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieBlack")
                        .appendContent(data)
                        .build();
                break;
            case "nicht gefährlich":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieWhite")
                        .appendContent("nicht")
                        .appendContent(new HtmlText.Builder().appendAttribute("class","Normal").appendContent("gefährlich").build().appendTag())
                        .build();
                break;
            default:
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(data)
                        .build();
                break;
        }

        return htmlCell;
    }
}
