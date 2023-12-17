package sbt.automization.core.format.text;

import java.text.DecimalFormat;

public final class NumberTextFormatter
        extends AbstractTextFormatter
{
    @Override
    public String format(String text)
    {
        return replaceIfEmpty(formatStringToTwoDecimals(text));
    }

    @Override
    public String format(String firstText, String secondText)
    {
        return replaceIfEmpty(formatStringToTwoDecimals(firstText)).concat(" ")
                                                                   .concat(replaceIfEmpty(formatStringToTwoDecimals(
                                                                           secondText)));
    }

    public static String formatStringToTwoDecimals(String input)
    {
        if (input.isEmpty())
        {
            return "";
        }
        else
        {
            input = replaceCommaByDot(input);
            double value = Double.parseDouble(input);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(value);
        }
    }

    public static String formatStringToOneDecimal(String input)
    {
        if (input.isEmpty())
        {
            return "";
        }
        else
        {
            input = replaceCommaByDot(input);
            double value = Double.parseDouble(input);
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            return decimalFormat.format(value);
        }
    }

    private static String replaceCommaByDot(String input)
    {
        if (input.contains(","))
        {
            return  input.replace(",", ".");
        }
        return input;
    }
}
