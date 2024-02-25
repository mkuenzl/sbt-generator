package sbt.automization.core.data;


import sbt.automization.core.format.printer.UtilityPrinter;

/**
 * Repräsentiert die statischen Textbausteine aus der datenbank-template, die Parametern zugeordnet werden können.
 * Diese müssen teilweise, aufgrund des nicht funktionierenden Wordbreaks, manuell formatiert werden.
 * Dieses enum mapped den originalen Textbausteinen gegen sein formatiertes Equivalent.
 */
public enum ParameterValueToFormat
{
    EINGEHALTEN("eingehalten", "ein" + UtilityPrinter.printLineBreakWithHyphen() + "gehalten"),
    NICHT_EINGEHALTEN("nicht eingehalten", "nicht ein" + UtilityPrinter.printLineBreakWithHyphen() + "gehalten"),
    AUFFAELLIGKEIT("Auffälligkeit", "Auf" + UtilityPrinter.printLineBreakWithHyphen() + "fälligkeit"),
    KEINE_AUFFAELLIGKEIT("keine Auffälligkeit", "keine Auf" + UtilityPrinter.printLineBreakWithHyphen() + "fälligkeit");

    /**
     * Wert, wie er auch in der <b>Auswahlliste</b> der datenbank-template zu finden wäre.
     */
    private final String valueInExcel;
    /**
     * Formatierter Wert zur ordentlichen Darstellung in den Reports.
     */
    private final String formattedValueForReport;

    ParameterValueToFormat(String valueInExcel, String formattedValueForReport)
    {
        this.valueInExcel = valueInExcel;
        this.formattedValueForReport = formattedValueForReport;
    }

    public static String getFormattedValue(String valueInExcel)
    {
        for (ParameterValueToFormat parameterValueToFormat : values())
        {
            if (parameterValueToFormat.getValueInExcel()
                                      .equals(valueInExcel))
            {
                return parameterValueToFormat.getFormattedValueForReport();
            }
        }
        return valueInExcel;
    }

    public String getValueInExcel()
    {
        return valueInExcel;
    }

    public String getFormattedValueForReport()
    {
        return formattedValueForReport;
    }
}
