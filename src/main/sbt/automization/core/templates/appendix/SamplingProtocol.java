package sbt.automization.core.templates.appendix;

import sbt.automization.core.data.*;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.UtilityPrinter;
import sbt.automization.core.format.text.DepthTextFormatter;
import sbt.automization.core.format.text.LineBreakTextFormatter;
import sbt.automization.core.format.text.SampleTypeTextFormatter;
import sbt.automization.core.html.HtmlFactory;

import java.util.ArrayList;
import java.util.List;

public final class SamplingProtocol
        extends Appendix
{
    private static SamplingProtocol instance;

    private SamplingProtocol()
    {
        super();
    }

    public static SamplingProtocol getInstance()
    {
        if (instance == null)
        {
            synchronized (SamplingProtocol.class)
            {
                if (instance == null)
                {
                    instance = new SamplingProtocol();
                }
            }
        }
        return instance;
    }

    @Override
    public String getExportFileName()
    {
        return "PN-Anlage";
    }

    @Override
    public void constructTemplate(List<DataTable> dataTables)
    {
        createTableWithHeader();

        for (DataTable dataTable : dataTables)
        {
            if (dataTable instanceof Probe)
            {
                Probe probe = (Probe) dataTable;

                // DIE FORMAT SAMPLES METHODE
                // FASST PROBEN ZUSAMMEN, SODASS HIER WENIGER PROBEN ANKOMMEN.
                // PROBLEM:
                // ES PASSIERT, DASS HIER GOB PROBEN ZUSAMMENGEFÜHRT WURDEN
                // ABER ALS EP ERSCHEINEN ???
                List<Sample> samples = formatSamples(probe.getSamples());

                for (Sample sample : samples)
                {
                    addAndResetTableOnPageBreak();

                    final String sampleNumber = "P".concat(String.valueOf(++lines));

                    String row = HtmlFactory.createRowAsString("NormalThin8", new String[]{
                            HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
                                                           new String[]{sampleNumber}),
                            // PROBLEM:
                            // ALLES, BEI DEM KEIN "BEHAELTNIS" ANGEGEBEN IST, GILT ALS EP?
                            // IST DAS SO???
                            HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
                                                           new String[]{new SampleTypeTextFormatter().format(sample.get(
                                                                   SampleKey.CONTAINER))}),
                            HtmlFactory.createCellAsString(textFormatter, "Normal",
                                                           new String[]{sample.get(SampleKey.CONTAINER)}),
                            HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
                                                           new String[]{"-"}),
                            HtmlFactory.createCellAsString(textFormatter, "Normal", "width:110px",
                                                           new String[]{new LineBreakTextFormatter().format(
                                                                   getFormattedWasteType(sample))}),
                            HtmlFactory.createCellAsString(textFormatter, "NormalCenter", "width:50px",
                                                           new String[]{sample.get(SampleKey.GRANULATION)}),
                            HtmlFactory.createCellAsString(textFormatter, "Normal", "left",
                                                           new String[]{sample.get(SampleKey.COLOR),
                                                                   UtilityPrinter.printLineBreak(),
                                                                   sample.get(SampleKey.SMELL),
                                                                   UtilityPrinter.printLineBreak(),
                                                                   sample.get(SampleKey.SOIL_TYPE)}),
                            HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
                                                           new String[]{probe.get(ProbeKey.ID)}),
                            HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
                                                           new String[]{new DepthTextFormatter().format(sample.get(
                                                                                                                SampleKey.DEPTH_START),
                                                                                                        sample.get(
                                                                                                                SampleKey.DEPTH_END))}),
                            HtmlFactory.createCellAsString(textFormatter, "NormalCenter",
                                                           new String[]{probe.get(ProbeKey.TOP_EDGE)})
                    });

                    linesPerPage++;

                    addToTable(row);
                }
            }
        }
        addToTemplate(this.table.appendTag());
    }

    /**
     * Currently its necessary to enforce a word breaks for special occasions.
     *
     * @param sample the sample which might contain "bit. Befestigung"
     * @return the regular samples WasteType value, or formatted, see code.
     */
    private static String getFormattedWasteType(Sample sample)
    {
        String formattedValue = "";
        final String actualValue = sample.get(SampleKey.WASTE_TYPE);
        if ("bit. Befestigung".equals(actualValue))
        {
            formattedValue = "bit." + UtilityPrinter.printLineBreak() + "Befestigung";
        }
        else if ("Pechfreie Schicht".equals(actualValue))
        {
            formattedValue = "Pechfreie" + UtilityPrinter.printLineBreak() + "Schicht";
        }
        else if ("Testschicht kein Pech".equals(actualValue))
        {
            formattedValue = "Testschicht" + UtilityPrinter.printLineBreak() + "kein Pech";
        }
        else if ("TestSchicht Beton".equals(actualValue))
        {
            formattedValue = "Testschicht" + UtilityPrinter.printLineBreak() + "Beton";
        }
        else
        {
            formattedValue = actualValue;
        }
        return formattedValue;
    }

    @Override
    public void constructTemplate(DataTable dataTable)
    {

    }

    /**
     * This method creates a new list of layer objects for the pn appendix. Because some layers are combined in the
     * output table.
     * TODO TEST
     *
     * @param samples expects a not empty list of layer objects
     * @return a smaller formatted list of layers
     */
    public List<Sample> formatSamples(List<Sample> samples)
    {
        List<Sample> formattedSamples = new ArrayList<>();
        for (Sample sample : samples)
        {
            formattedSamples.add(DataTableFactory.createSampleFrom(sample.getTable()));
        }

        int size = formattedSamples.size();

        if (size > 2)
        {
            for (int i = 0; i < formattedSamples.size(); i++)
            {
                // HIER WIRD VERSUCHT GOB PROBEN ZUSAMMENZUFASSEN
                // ES WIRD DAVON AUSGEGANGEN DASS DIESE DIREKT UNTER EINANDER STEHEN
                // ES WIRD DABEI DIE START-TIEFER DER NÄCHSTEN DURCH DIE AKTUELLE ERSETZT ? WARUM?

                // PROBLEM!!!
                // DER ERSTEN GOB PROBE IST ES EGAL, WELCHEN AUFSCHLUSS DIE NÄCHSTE HAT.
                // HAUPTSACHE ABFALLART PASST. MIT CHRISTIAN KLÄREN.
                Sample currentSample = formattedSamples.get(i);
                final String currentSampleOutCrop = currentSample.get(SampleKey.OUTCROP);
                final boolean isGOBSample = Outcrop.GOB.toString()
                                                  .equals(currentSampleOutCrop);
                // WENN PROBE GOB ALS AUFSCHLUSS HAT
                if (isGOBSample)
                {
                    final boolean isLastIndex = formattedSamples.size() <= i + 1;
                    if (isLastIndex)
                    {
                        break;
                    }
                    final String ownWasteType = currentSample.get(SampleKey.WASTE_TYPE);
                    final Sample nextSample = formattedSamples.get(i + 1);
                    final String nextSampleWasteType = nextSample.get(SampleKey.WASTE_TYPE);
                    // NÄCHSTE PROBE DARF VON EGAL WELCHER AUFSCHLUSSART SEIN, HAUPTSACHE GLEICHE ABFALLART?
                    if (ownWasteType.equals(nextSampleWasteType))
                    {
                        // dann überschreibe des Nächsten Starttiefe mit der eigenen Starttiefe?
                        final String currentSampleDepthStart = currentSample.get(SampleKey.DEPTH_START);
                        nextSample.put(SampleKey.DEPTH_START, currentSampleDepthStart);
                        // Und überschreibe den Granulations-Wert mit NIX???
                        nextSample.put(SampleKey.GRANULATION, "");
                        // dann GOB-Probe fott.
                        formattedSamples.remove(currentSample);
                        // Mit dem gleichen Sample weiter machen, denn dieses ist grade aufgerutscht.
                        // index erst -- und danach direkt wieder ++.
                        i--;
                    }
                }
            }
        }
        return formattedSamples;
    }

    @Override
    public String constructAndGetTableHeader()
    {
        String firstRow = HtmlFactory.createRowAsString("NormalHeader", new String[]{
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:40px", 2, 1,
                                                 new String[]{"Probe", UtilityPrinter.printLineBreak(), "Nr."}),
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:40px", 2, 1,
                                                 new String[]{"Art"}),
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:105px",
                                                 new String[]{"Behältnis", UtilityPrinter.printLineBreak(), "Vol."}),
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px",
                                                 new String[]{"Haufwerk", UtilityPrinter.printLineBreak(), "Vol."}),
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:140px", 2, 2,
                                                 new String[]{"Abfallart"}),
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:76px", 2, 1,
                                                 new String[]{"Farbe", UtilityPrinter.printLineBreak(), "Geruch",
                                                         UtilityPrinter.printLineBreak(), "Bodenart"}),
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:35px", 2, 1,
                                                 new String[]{"Erk. St."}),
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:70px",
                                                 new String[]{"Tiefe"}),
                HtmlFactory.createHeaderAsString("NormalTableHeader", "width:60px", 2, 1,
                                                 new String[]{"Notiz"})
        });

        String secondRow = HtmlFactory.createRowAsString("NormalHeaderUnits", new String[]{
                HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
                                                 new String[]{"l"}),
                HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
                                                 new String[]{"l"}),
                HtmlFactory.createHeaderAsString("NormalTableHeaderUnits",
                                                 new String[]{"cm"})
        });

        StringBuilder stringBuilder = new StringBuilder()
                .append(firstRow)
                .append(secondRow);

        return stringBuilder.toString();
    }

}
