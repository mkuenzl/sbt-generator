package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.ReferenceParameterChemistry;
import sbt.automization.data.refactoring.references.ReferenceParameterRuK;
import sbt.automization.data.refactoring.references.ReferenceProbe;
import sbt.automization.data.refactoring.references.ReferenceSample;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.Outcrop;
import sbt.automization.util.html.*;

import java.util.List;

final class AppendixSiteGOB extends AppendixTemplate
{
    private String outcrop = "";
    private Probe probe;

    @Override
    public void constructTable(final List<ExplorationSite> sites)
    {
    }

    private void setOutcrop(DataTable dataTable)
    {
        outcrop = dataTable.get(ReferenceProbe.OUTCROP_GOB);
    }

    @Override
    public void constructTemplate(DataTable dataTable)
    {
        setOutcrop(dataTable);
        HtmlTable table = constructAndGetTableObject();

        if (dataTable instanceof Probe)
        {
            this.probe = (Probe) dataTable;
            List<Sample> samplesOfOutcrop = probe.getSamplesBy(ReferenceSample.OUTCROP,
                    new String[]{Outcrop.GOB.toString(),
                            Outcrop.TMHB.toString(),
                            Outcrop.CONCRETE.toString(),
                            Outcrop.SEAL.toString(),
                            Outcrop.COATING.toString()
                    });

            for (Sample sample : samplesOfOutcrop)
            {
                String row = createRow(sample);
                table.appendContent(row);
            }

            String loadClassRow = createLoadClassRow();
            table.appendContent(loadClassRow);
        }

        addToTemplate(table.appendTag());
    }

    private String createRow(Sample sample)
    {
        String row = HtmlFactory.createRow("Normal", new String[]{
                HtmlFactory.createCell("Normal",
                        new String[]{TextFormatUtil.formatKindAndGranulation(sample.get(ReferenceSample.TYPE),
                                sample.get(ReferenceSample.GRANULATION))}),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{sample.get(ReferenceSample.THICKNESS)}),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{sample.get(ReferenceSample.DEPTH_END)}),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.MUFV)),
                HtmlFactory.createPitchCell(sample.get(ReferenceSample.PITCH)),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.LAGA_RC)),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.TL_ROCK_STRATUM)),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{sample.get(ReferenceSample.PAK)}),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{sample.getParameterValueBy(ReferenceSample.RUK_ID, ReferenceParameterRuK.VALUE)})
        });

        return row;
    }

    private String createLoadClassRow()
    {
        String row = HtmlFactory.createRow("Normal", new String[]{
                HtmlFactory.createCell("NormalCenter", 1, 5,
                        new String[]{""}),
                HtmlFactory.createCell("NormalCenter",  1, 2,
                        new String[]{TextFormatUtil.formatLoadClass(probe.get(ReferenceProbe.LOAD_CLASS))}),
                HtmlFactory.createCell("NormalCenter",  1, 2,
                        new String[]{"RStO<sup>[5]</sup>",
                                TextFormatUtil.printLineBreak(),
                                probe.get(ReferenceProbe.LOAD_CLASS_BOARD)})
        });

        return row;
    }

    @Override
    public void constructTable(final ExplorationSite site)
    {
    }

    @Override
    String constructAndGetTableHeader()
    {
        String firstRow = HtmlFactory.createRow("NormalHeader", new String[]{
                HtmlFactory.createHeader("NormalTableHeader", "width:125;text-align:left", 1, 1,
                        new String[]{"Gebundener Oberbau"}),
                HtmlFactory.createHeader("NormalTableHeader", "text-align:left",  1, 8,
                        new String[]{"Aufschlussverfahren:",outcrop}),
        });

        String secondRow = HtmlFactory.createRow("NormalHeader", new String[]{
                HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 2, 1,
                        new String[]{"Art der Schicht"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"Dicke"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"Tiefe"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
                        new String[]{"MUFV", "<div>[18]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",  2, 1,
                        new String[]{"PECH", "<div>[10]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
                        new String[]{"LAGA RC", "<div>[28]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",  2, 1,
                        new String[]{"TL Ge.", "<div>[27]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"PAK"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"RuK", "<div>[31]</div>"})
        });

        String thirdRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
                HtmlFactory.createHeader("NormalTableHeaderUnits",
                        new String[]{"cm"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits",
                        new String[]{"cm"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits",
                        new String[]{"mg/kg"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits",
                        new String[]{"°C"})
        });

        StringBuilder stringBuilder = new StringBuilder()
                .append(firstRow)
                .append(secondRow)
                .append(thirdRow);

        return stringBuilder.toString();
    }

    @Override
    HtmlTable constructAndGetTableObject()
    {
        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(constructAndGetTableHeader())
                .build();

        return table;
    }

    @Override
    public String getExportFileName()
    {
        return null;
    }

    @Override
    public void constructTemplate(List<DataTable> dataTables)
    {

    }
}
