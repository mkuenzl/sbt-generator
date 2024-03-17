package sbt.automization.core.templates.report;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.retrieval.*;
import sbt.automization.core.templates.construction.RowFactory;
import sbt.automization.core.templates.construction.strategies.CellPerProbe;
import sbt.automization.core.templates.report.tableparts.CrossSectionWithPitchAfter08Report;
import sbt.automization.core.templates.report.tableparts.CrossSectionWithoutPitchAfter08Report;

import java.util.Collection;
import java.util.List;

/**
 * Repräsentiert den <b>GOB-Report</b> nach August 2023, der die <b>Abgrenzung nach Gefährlichkeit</b> nach der
 * <b>MKUEM</b>-Verordnung listet.
 */
public final class BoundSuperstructure08Report
        extends AbstractReport
{
    private static BoundSuperstructure08Report instance;
    private final RowFactory provider;

    private BoundSuperstructure08Report()
    {
        super(Outcrop.GOB);
        provider = new RowFactory(Outcrop.GOB);
    }

    public static BoundSuperstructure08Report getInstance()
    {
        if (instance == null)
        {
            synchronized (BoundSuperstructure08Report.class)
            {
                if (instance == null)
                {
                    instance = new BoundSuperstructure08Report();
                }
            }
        }
        return instance;
    }

    @Override
    public String getExportFileName()
    {
        return "GOB-08-Report";
    }

    @Override
    public void constructTemplate(List<DataTable> dataTables)
    {
        Collection<List<DataTable>> tablesSplitIntoPortions = splitIntoPortionPerPage(dataTables);
        for (List<DataTable> portion : tablesSplitIntoPortions)
        {
            buildTable(portion);

            addTable();
            addPageBreak();
        }
    }

    private void buildTable(List<DataTable> dataTables)
    {
        createTable();
        provider.setDataTables(dataTables);
        provider.setCellStrategy(new CellPerProbe());

        addToTable(provider.getRow(header.createCell(new String[]{"Erkundungsstelle"}), new IdRetrieval()));
        addToTable(provider.getRow(header.createCell(new String[]{"Aufbruch"}), new SuperstructureExposureRetrieval()));

        constructTechnicalFeatures(dataTables);
        constructEnvironmentTechnicalFeatures(dataTables);
        // Pechfreier Querschnitt
        CrossSectionWithoutPitchAfter08Report crossSectionWithoutPitchAfter08Report = new CrossSectionWithoutPitchAfter08Report();
        crossSectionWithoutPitchAfter08Report.constructTemplate(dataTables);
        addToTable(crossSectionWithoutPitchAfter08Report.getTemplate());
        // Pechhaltiger Querschnitt
        CrossSectionWithPitchAfter08Report crossSectionWithPitchAfter08Report = new CrossSectionWithPitchAfter08Report();
        crossSectionWithPitchAfter08Report.constructTemplate(dataTables);
        addToTable(crossSectionWithPitchAfter08Report.getTemplate());
    }

    @Override
    protected void constructTechnicalFeatures(List<DataTable> dataTables)
    {
        addTechnicalHeader(dataTables);

        addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Gesamtdicke geb. Oberbau,"}, "cm"),
                                                new SizeTotalGOBRetrieval()));
        addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Belastungsklasse,"},
                                                                  "RStO<sup>[5]</sup>"), new LoadClassRetrieval()));
        addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Erweichungspunkt RuK<sup>[31]</sup>,"},
                                                                  "°C"), new RuKCombinedRetrieval()));
        addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Soll Einzelwert,"}, "RuK"),
                                                new RuKSingleValueRetrieval()));
    }

    @Override
    protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
    {
        addEnvironmentTechnicalHeader(dataTables);

        addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis qualitativ"}),
                                                new PitchQualitativeRetrieval()));
        addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis halbquantitativ"}),
                                                new PitchHalfQuantitativeRetrieval()));
        addToTable(provider.getRowWithDataCheck(header.createCell(new String[]{"Pechnachweis quantitativ"}),
                                                new PitchQuantitativeRetrieval()));
    }

    @Override
    protected void addLegendRow(List<DataTable> dataTables)
    {

    }

    @Override
    public void constructTemplate(DataTable dataTable)
    {

    }
}
