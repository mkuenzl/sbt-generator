package sbt.automization.core.format.text;

import sbt.automization.core.html.HtmlText;

/**
 * {@link TextFormatter}-Implementation for highlighting chemical values as {@link HtmlText}s.
 */
public final class ChemistryMarkupTextFormatter
        extends AbstractTextFormatter
{
    @Override
    public String format(final String classification)
    {

        StringBuilder stringBuilder = new StringBuilder();

        switch (classification)
        {
            // WHITE BACKGROUND COLORING
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
            case "DK 0":
                stringBuilder.append(new HtmlText.Builder()
                                             .appendAttribute("class", "Normal")
                                             .appendContent(
                                                     "<span style=\"" +
                                                             "background-color: white;" +
                                                             "font-weight: bold;" +
                                                             "\n\n" +
                                                             "  color: black\">")
                                             .appendContent(classification)
                                             .appendContent("</span>")
                                             .build()
                                             .appendTag());
                break;
            // CYAN BACKGROUND COLORING
            case "BM-0*":
            case "BG-0*":
            case "Z0*":
                stringBuilder.append(new HtmlText.Builder()
                                             .appendAttribute("class", "Normal")
                                             .appendContent(
                                                     "<span style=\"background-color: " +
                                                             "#00FFFF;font-weight: bold;" +
                                                             "\n" +
                                                             "  color: black\">")
                                             .appendContent(classification)
                                             .appendContent("</span>")
                                             .build()
                                             .appendTag());
                break;
            // GREEN BACKGROUND COLORING
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
                stringBuilder.append(new HtmlText.Builder()
                                             .appendAttribute("class", "Normal")
                                             .appendContent(
                                                     "<span style=\"background-color: #00FF00;font-weight:" +
                                                             " bold;" +
                                                             "\n" +
                                                             "  color: black\">")
                                             .appendContent(classification)
                                             .appendContent("</span>")
                                             .build()
                                             .appendTag());
                break;
            // YELLOW BACKGROUND COLORING
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
                stringBuilder.append(new HtmlText.Builder()
                                             .appendAttribute("class", "Normal")
                                             .appendContent(
                                                     "<span style=\"background-color: yellow;font-weight: bold;" +
                                                             "\n" +
                                                             "  color: black\">")
                                             .appendContent(classification)
                                             .appendContent("</span>")
                                             .build()
                                             .appendTag());
                break;
            // ORANGE BACKGROUND COLORING
            case "BG-F2":
            case "BM-F2":
                stringBuilder.append(new HtmlText.Builder()
                                             .appendAttribute("class", "Normal")
                                             .appendContent(
                                                     "<span style=\"background-color: #FFC000;" +
                                                             "font-weight: bold;\n" +
                                                             "  color: black\">")
                                             .appendContent(classification)
                                             .appendContent("</span>")
                                             .build()
                                             .appendTag());
                break;
            // RED BACKGROUND COLORING
            case "BM-F3":
            case "BG-F3":
            case "RC-3":
            case "GS-3":
            case "Z2":
            case "RC3":
            case "DK III":
                stringBuilder.append(new HtmlText.Builder()
                                             .appendAttribute(
                                                     "class",
                                                     "Normal")
                                             .appendContent(
                                                     "<span style=\"background-color: red;font-weight: bold;\n" +
                                                             "  color: white\">")
                                             .appendContent(
                                                     classification)
                                             .appendContent(
                                                     "</span>")
                                             .build()
                                             .appendTag());
                break;
            // BLACK BACKGROUND COLORING
            case ">BM-F3":
            case ">BG-F3":
            case ">Z2":
            case ">DK III":
            case ">RC3":
            case "gefährlich":
                stringBuilder.append(new HtmlText.Builder()
                                             .appendAttribute("class", "Normal")
                                             .appendContent(
                                                     "<span style=\"background-color: black;font-weight: bold;\n" +
                                                             "  color: white\">")
                                             .appendContent(classification)
                                             .appendContent("</span>")
                                             .build()
                                             .appendTag());
                break;
            case "nicht gefährlich": stringBuilder.append(new HtmlText.Builder()
                                                                    .appendAttribute("class", "Normal")
                                                                    .appendContent(
                                                                            "<span style=\"background-color: white;" +
                                                                                    "font-weight: bold;\n" +
                                                                                    "  color: black\">")
                                                                    .appendContent("nicht")
                                                                    .appendContent("</span>")
                                                                    .build()
                                                                    .appendTag())
                                                    .append(new HtmlText.Builder()
                                                                    .appendAttribute("class", "Normal")
                                                                    .appendContent("<span style=\"font-weight: " +
                                                                                           "bold\";>")
                                                                    .appendContent("gefährlich")
                                                                    .appendContent("</span>")
                                                                    .build()
                                                                    .appendTag());
            break;
            case "nicht eingehalten": stringBuilder.append(new HtmlText.Builder()
                                                                     .appendAttribute("class", "Normal")
                                                                     .appendContent(
                                                                             "<span style=\"font-weight: bold\";>")
                                                                     .appendContent("nicht")
                                                                     .appendContent("</span>")
                                                                     .build()
                                                                     .appendTag())
                                                     .append(new HtmlText.Builder()
                                                                     .appendAttribute("class", "Normal")
                                                                     .appendContent(
                                                                             "<span style=\"font-weight: bold\";>")
                                                                     .appendContent("eingehalten")
                                                                     .appendContent("</span>")
                                                                     .build()
                                                                     .appendTag());
            break;
            case "eingehalten": case "Auffälligkeit": stringBuilder.append(new HtmlText.Builder()
                                                                                .appendAttribute("class", "Normal")
                                                                                .appendContent(
                                                                                        "<span style=\"font-weight: " +
                                                                                                "bold\";>")
                                                                                .appendContent(classification)
                                                                                .appendContent("</span>")
                                                                                .build()
                                                                                .appendTag());
            break;
            case "keine Auffälligkeit": stringBuilder.append(new HtmlText.Builder()
                                                                       .appendAttribute("class", "Normal")
                                                                       .appendContent(
                                                                               "<span style=\"font-weight: bold\";>")
                                                                       .appendContent("keine")
                                                                       .appendContent("</span>")
                                                                       .build()
                                                                       .appendTag())
                                                       .append(new HtmlText.Builder()
                                                                       .appendAttribute("class", "Normal")
                                                                       .appendContent(
                                                                               "<span style=\"font-weight: bold\";>")
                                                                       .appendContent("Auffälligkeit")
                                                                       .appendContent("</span>")
                                                                       .build()
                                                                       .appendTag());
            break;
            default: stringBuilder.append(new HtmlText.Builder()
                                                    .appendAttribute("class", "Normal")
                                                    .appendContent(replaceIfEmpty(classification))
                                                    .build()
                                                    .appendTag());
            break;
        }
        return stringBuilder.toString();
    }

    @Override
    public String format(String firstText, String secondText)
    {
        return null;
    }
}
