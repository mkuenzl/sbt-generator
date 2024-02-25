package sbt.automization.core.format.text;

public abstract class AbstractLoadPlateTextFormatter extends AbstractTextFormatter
{
    protected String formatRange(double ev85PercentValue)
    {
        String range = "";
        if (ev85PercentValue >= 10 && ev85PercentValue < 20) range = "[30 - 40]";
        if (ev85PercentValue >= 20 && ev85PercentValue < 30) range = "[40 - 50]";
        if (ev85PercentValue >= 30 && ev85PercentValue < 40) range = "[50 - 60]";
        if (ev85PercentValue >= 40 && ev85PercentValue < 45) range = "[60 - 80]";
        return range;
    }
}
