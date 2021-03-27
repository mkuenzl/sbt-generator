package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.List;

class Anlage_ERK_OH_Template extends AHtmlTemplate
{
    private String aufschluss = "";

    @Override
    public void buildHtmlTable(final List<Erkundungsstelle> data)
    {

    }

    @Override
    public void buildHtmlTable(final Erkundungsstelle data)
    {
        aufschluss = data.getInformation("ERK_AUFSCHLUSS_UG_OH_BA");

        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();

        for (Schicht schicht : data.getSchichtList())
        {
            if ("OH".equals(schicht.getInformation("SCHICHT_AUFSCHLUSS")))
            {
                //Art der Schicht
                HtmlCell cell1 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(TextFormatUtil.formatSchichtBodenGruppe(schicht.getInformation("SCHICHT_ART")))
                        .build();

                //Dicke
                HtmlCell cell2 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_DICKE"))
                        .build();

                //Tiefe
                HtmlCell cell3 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
                        .build();

                //MUFV
                String chemie_mufv = schicht.getInformation("CHEMIE_MUFV");
                HtmlCell cell4 = HtmlCellFormatUtil.formatChemie(chemie_mufv);

                //LAGA BO
                String chemie_laga_bo = schicht.getInformation("CHEMIE_LAGA_BO");
                HtmlCell cell5 = HtmlCellFormatUtil.formatChemie(chemie_laga_bo);

                //Notiz
                String chemie_laga_rc = schicht.getInformation("CHEMIE_LAGA_RC");
                HtmlCell cell6 = HtmlCellFormatUtil.formatChemie(chemie_laga_rc);

                //Wassergehalt
                HtmlCell cell7 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_WASSERGEHALT"))
                        .build();

                //WasserProctor
                HtmlCell cell8 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(TextFormatUtil.formatSchichtProctor(schicht))
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

        setHtmlTable(table.appendTag());
    }

    @Override
    String setHtmlTableHeader()
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
                .appendContent("Aufschlussverfahren:".concat(" ").concat(aufschluss))
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
                .build();

        HtmlTableHeader cell25 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("LAGA BO")
                .build();

        HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("LAGA RC")
                .build();

        HtmlTableHeader cell27 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("WG")
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
                .build();


        //Third Row
//        HtmlTableHeader cell31 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("align", "left")
//                .appendContent("-")
//                .build();

        HtmlTableHeader cell32 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();

        HtmlTableHeader cell33 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();

//        HtmlTableHeader cell34 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendContent("-")
//                .build();

        HtmlTableHeader cell35 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("M.-%")
                .build();

        HtmlTableHeader cell36 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("M.-%")
                .build();

        HtmlTableHeader cell37 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("Mg/m³")
                .build();

//        HtmlTableHeader cell38 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendContent("-")
//                .build();

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
    public String getExportFileName()
    {
        return null;
    }
}
