package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.data.Layer;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.List;

final class AppendixSiteOH extends AHtmlTable
{
    private String outcrop = "";

    @Override
    public void constructTable(final List<ExplorationSite> sites)
    {

    }

    @Override
    public void constructTable(final ExplorationSite site)
    {
        outcrop = site.getInformation(InformationTag.SITE_OUTCROP_UG_OH_BA);

        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(constructAndGetTableHeader())
                .build();

        for (Layer layer : site.getLayers())
        {
            if ("OH".equals(layer.getInformation(InformationTag.LAYER_OUTCROP)))
            {
                //Art der Schicht
                HtmlCell cell1 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(TextFormatUtil.formatSoilGroup(layer.getInformation(InformationTag.LAYER_TYPE)))
                        .build();

                //Dicke
                HtmlCell cell2 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(layer.getInformation(InformationTag.LAYER_THICKNESS))
                        .build();

                //Tiefe
                HtmlCell cell3 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(layer.getInformation(InformationTag.LAYER_DEPTH_END))
                        .build();

                //MUFV
                String chemie_mufv = layer.getInformation(InformationTag.CHEMISTRY_MUFV);
                HtmlCell cell4 = HtmlCellFormatUtil.formatChemistry(chemie_mufv);

                //LAGA BO
                String chemie_laga_bo = layer.getInformation(InformationTag.CHEMISTRY_LAGA_BO);
                HtmlCell cell5 = HtmlCellFormatUtil.formatChemistry(chemie_laga_bo);

                //Notiz
                String chemie_laga_rc = layer.getInformation(InformationTag.CHEMISTRY_LAGA_RC);
                HtmlCell cell6 = HtmlCellFormatUtil.formatChemistry(chemie_laga_rc);

                //Wassergehalt
                HtmlCell cell7 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(layer.getInformation(InformationTag.LAYER_WATER_CONTENT))
                        .build();

                //WasserProctor
                HtmlCell cell8 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(TextFormatUtil.formatLayerProctor(layer))
                        .build();

                //Proctor
                HtmlCell cell9 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent("-")
                        .build();

                HtmlRow row = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cell1.appendTag())
                        .appendContent(cell2.appendTag())
                        .appendContent(cell3.appendTag())
                        .appendContent(cell4.appendTag())
                        .appendContent(cell5.appendTag())
                        .appendContent(cell6.appendTag())
                        .appendContent(cell7.appendTag())
                        .appendContent(cell8.appendTag())
                        .appendContent(cell9.appendTag())
                        .build();

                table.appendContent(row.appendTag());
            }
        }

        setTable(table.appendTag());
    }

    @Override
    String constructAndGetTableHeader()
    {
        //First Row
        HtmlTableHeader cell11 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "125")
                .appendAttribute("align", "left")
                .appendContent("Oberboden")
                .build();

        HtmlTableHeader cell12 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("align", "left")
                .appendAttribute("colspan", "8")        //Zelle geht über 3 Reihen
                .appendContent("Aufschlussverfahren:".concat(" ").concat(outcrop))
                .build();


        //Second Row
        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("align", "left")
                .appendAttribute("width", "125")
                .appendAttribute("rowspan", "2")
                .appendContent("Bodengruppe")
                .build();

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Dicke")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Tiefe")
                .build();

        HtmlTableHeader cell24 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("MUFV")
                .appendContent("<div>[18]</div>")
                .build();

        HtmlTableHeader cell25 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("LAGA BO")
                .appendContent("<div>[11]</div>")
                .build();

        HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("LAGA RC")
                .appendContent("<div>[28]</div>")
                .build();

        HtmlTableHeader cell27 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("WG")
                .appendContent("<div>[19]</div>")
                .build();

        HtmlTableHeader cell28 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("W<sub>Pr</sub>")
                .build();

        HtmlTableHeader cell29 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Proctor")
                .appendContent("<div>[20]</div>")
                .build();

        HtmlTableHeader cell32 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("cm")
                .build();

        HtmlTableHeader cell33 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("cm")
                .build();

        HtmlTableHeader cell35 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("M.-%")
                .build();

        HtmlTableHeader cell36 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("M.-%")
                .build();

        HtmlTableHeader cell37 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("Mg/m³")
                .build();

        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell11.appendTag())
                .appendContent(cell12.appendTag())
                .build();

        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .appendContent(cell24.appendTag())
                .appendContent(cell25.appendTag())
                .appendContent(cell26.appendTag())
                .appendContent(cell27.appendTag())
                .appendContent(cell28.appendTag())
                .appendContent(cell29.appendTag())
                .build();

        HtmlRow row3 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                //   .appendContent(cell31.appendTag())
                .appendContent(cell32.appendTag())
                .appendContent(cell33.appendTag())
                //   .appendContent(cell34.appendTag())
                .appendContent(cell35.appendTag())
                .appendContent(cell36.appendTag())
                .appendContent(cell37.appendTag())
                //  .appendContent(cell38.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag())
                .append(row3.appendTag());

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
}
