package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.ReferenceParameterChemistry;
import sbt.automization.data.refactoring.references.ReferenceProbe;
import sbt.automization.data.refactoring.references.ReferenceSample;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.Outcrop;
import sbt.automization.util.html.*;

import java.util.List;

final class AppendixSiteOH extends AppendixTemplate
{
    private String outcrop = "";

    private void setOutcrop(DataTable dataTable)
    {
        outcrop = dataTable.get(ReferenceProbe.OUTCROP_UG_OH_BA);
    }

    @Override
    public void constructTemplate(DataTable dataTable)
    {
        setOutcrop(dataTable);
        HtmlTable table = constructAndGetTableObject();

        if (dataTable instanceof Probe)
        {
            Probe probe = (Probe) dataTable;
            List<Sample> samplesOfOutcrop = probe.getSamplesBy(ReferenceSample.OUTCROP, Outcrop.OH.toString());

            for (Sample sample : samplesOfOutcrop)
            {
                String row = createRow(sample);
                table.appendContent(row);
            }
        }

        addToTemplate(table.appendTag());
    }

    @Override
    public void constructTable(final List<ExplorationSite> sites)
    {

    }

    @Override
    public void constructTable(final ExplorationSite site)
    {

    }

    private String createRow(Sample sample)
    {
        String row = HtmlFactory.createRow("Normal", new String[]{
                HtmlFactory.createCell("Normal", 125, 1, 1, 1,
                        new String[]{TextFormatUtil.formatSoilGroup(sample.get(ReferenceSample.TYPE))}),
                HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
                        new String[]{sample.get(ReferenceSample.THICKNESS)}),
                HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
                        new String[]{sample.get(ReferenceSample.DEPTH_END)}),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.MUFV)),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.LAGA_BO)),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.LAGA_RC)),
                HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
                        new String[]{sample.get(ReferenceSample.WATER_CONTENT)}),
                HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
                        new String[]{sample.get(ReferenceSample.WATER_PROCTOR)}),
                HtmlFactory.createCell("NormalCenter", 60, 1, 1, 1,
                        new String[]{"-"})
        });

        return row;
    }

    @Override
    String constructAndGetTableHeader()
    {
        String firstRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
                HtmlFactory.createHeader("NormalTableHeader", 125, 1, 1, 1, "left",
                        new String[]{"Oberboden"}),
                HtmlFactory.createHeader("NormalTableHeader", 480, 1, 1, 8, "left",
                        new String[]{"Aufschlussverfahren:",outcrop}),
        });

        String secondRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
                HtmlFactory.createHeader("NormalTableHeader", 125, 1, 2, 1, "left",
                        new String[]{"Bodengruppe"}),
                HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
                        new String[]{"Dicke"}),
                HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
                        new String[]{"Tiefe"}),
                HtmlFactory.createHeader("NormalTableHeader", 60, 1, 2, 1,
                        new String[]{"MUFV", "<div>[18]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", 60, 1, 2, 1,
                        new String[]{"LAGA BO", "<div>[11]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", 60, 1, 2, 1,
                        new String[]{"LAGA RC", "<div>[28]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
                        new String[]{"WG", "<div>[19]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
                        new String[]{"W<sub>Pr</sub>"}),
                HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1, 1,
                        new String[]{"Proctor", "<div>[20]</div>"})
        });

        String thirdRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
                HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
                        new String[]{"cm"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
                        new String[]{"cm"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
                        new String[]{"M.-%"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
                        new String[]{"M.-%"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
                        new String[]{"Mg/m³"})
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
