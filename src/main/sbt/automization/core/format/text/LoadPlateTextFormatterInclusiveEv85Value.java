package sbt.automization.core.format.text;

import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.html.HtmlText;

public class LoadPlateTextFormatterInclusiveEv85Value
        extends AbstractLoadPlateTextFormatter
{
    @Override
    public String format(String text)
    {
        return null;
    }

    @Override
    public String format(String ev2, String ev85Percent)
    {
        StringBuilder stringBuilder = new StringBuilder();

        if ("< 80".equals(ev2) && !"-".equals(ev85Percent))
        {
            String ev85PercentInformation = ev85Percent.replace(",", ".");
            String replace = ev85PercentInformation.replace("~ ", "");

            double ev85PercentValue = Double.parseDouble(replace);

            String range = formatRange(ev85PercentValue);

            stringBuilder.append(new HtmlText.Builder()
                                         .appendAttribute("class", "Normal")
                                         .appendContent(ev2)
                                         .appendContent(UtilityPrinter.printLineBreak())
                                         .appendContent("("+ev85PercentInformation+")")
                                         .appendContent(new HtmlText.Builder()
                                                                .appendAttribute("class", "Normal6")
                                                                .appendContent(range)
                                                                .build().appendTag())
                                         .build()
                                         .appendTag());
        } else
        {
            stringBuilder.append(ev2);
        }

        return stringBuilder.toString();
    }
}
