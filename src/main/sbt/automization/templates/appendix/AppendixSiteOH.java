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
                HtmlFactory.createCell("Normal",
                        new String[]{TextFormatUtil.formatSoilGroup(sample.get(ReferenceSample.TYPE))}),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{sample.get(ReferenceSample.THICKNESS)}),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{sample.get(ReferenceSample.DEPTH_END)}),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.MUFV)),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.LAGA_BO)),
                HtmlFactory.createChemistryCell(sample.getParameterValueBy(ReferenceSample.CHEMISTRY_ID, ReferenceParameterChemistry.LAGA_RC)),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{sample.get(ReferenceSample.WATER_CONTENT)}),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{sample.get(ReferenceSample.WATER_PROCTOR)}),
                HtmlFactory.createCell("NormalCenter",
                        new String[]{"-"})
        });

        return row;
    }

    @Override
    String constructAndGetTableHeader()
    {
        String firstRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
                HtmlFactory.createHeader("NormalTableHeader", "width:125px;text-align:left",
                        new String[]{"Oberboden"}),
                HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 1, 8,
                        new String[]{"Aufschlussverfahren:",outcrop}),
        });

        String secondRow = HtmlFactory.createRow("NormalTableHeader", new String[]{
                HtmlFactory.createHeader("NormalTableHeader", "text-align:left", 2, 1,
                        new String[]{"Bodengruppe"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"Dicke"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"Tiefe"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
                        new String[]{"MUFV", "<div>[18]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
                        new String[]{"LAGA BO", "<div>[11]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
                        new String[]{"LAGA RC", "<div>[28]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"WG", "<div>[19]</div>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"W<sub>Pr</sub>"}),
                HtmlFactory.createHeader("NormalTableHeader", "width:60px",
                        new String[]{"Proctor", "<div>[20]</div>"})
        });

        String thirdRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
                HtmlFactory.createHeader("NormalTableHeaderUnits",
                        new String[]{"cm"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits",
                        new String[]{"cm"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits",
                        new String[]{"M.-%"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits",
                        new String[]{"M.-%"}),
                HtmlFactory.createHeader("NormalTableHeaderUnits",
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
