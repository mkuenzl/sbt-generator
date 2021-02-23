package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.util.html.*;

import java.util.List;

class Anlage_ERK_TOB_TemplateStrategy extends AHtmlTemplateStrategy
{
    private String aufschluss = "";

    @Override
    public void buildHtmlTable(final List<Erkundungsstelle> data)
    {

    }

    @Override
    public void buildHtmlTable(final Erkundungsstelle data)
    {
        aufschluss = data.getInformation("ERK_AUFSCHLUSS_TOB");

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
            if ("TOB".equals(schicht.getInformation("SCHICHT_AUFSCHLUSS")))
            {
                //Art der Schicht
                HtmlCell cell1 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_ART"))
                        .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal")
                                .appendContent(schicht.getInformation("SCHICHT_KOERNUNG"))
                                .appendContent(" ")
                                .appendContent(schicht.getInformation("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT"))
                                .build()
                                .appendTag())
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

                //LAGA_BO
                String chemie_laga_bo = schicht.getInformation("CHEMIE_LAGA_BO");
                HtmlCell cell5 = HtmlCellFormatUtil.formatChemie(chemie_laga_bo);

                //LAGA_RC
                String chemie_laga_rc = schicht.getInformation("CHEMIE_LAGA_RC");
                HtmlCell cell6 = HtmlCellFormatUtil.formatChemie(chemie_laga_rc);

                //TL_GESTEIN
                String chemie_tlgestein = schicht.getInformation("CHEMIE_TLGESTEIN");
                HtmlCell cell7 = HtmlCellFormatUtil.formatChemie(chemie_tlgestein);

                //LP_DYN
                HtmlCell cell8 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(data.getInformation("ERK_LP_EV2"))
                        .appendContent("")
                        .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal")
                                .appendContent(data.getInformation("ERK_LP_EV15"))
                                .build()
                                .appendTag())
                        .build();

                HtmlCell cell9 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_KORNGROESSENVERTEILUNG"))
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
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal")
                        .appendContent("Tragschicht ohne")
                        .build()
                        .appendTag())
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal")
                        .appendContent("Bindemittel")
                        .build()
                        .appendTag())
                .build();


        HtmlTableHeader cell12 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("colspan", "8")        //Zelle geht über 3 Reihen
                .appendAttribute("align", "left")
                .appendContent("Aufschlussverfahren:".concat(" ").concat(aufschluss))
                .build();


        //Second Row
        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "125")
                .appendAttribute("align", "left")
                .appendAttribute("rowspan", "2")
                .appendContent("Art der Schicht")
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
                .appendAttribute("rowspan", "2")
                .appendContent("TL Ge.")
                .build();

        HtmlTableHeader cell28 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("E<sub>V2</sub>").build().appendTag())
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("E<sub>Vdyn</sub>").build().appendTag())
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("<sub>(-15%)</sub>").build().appendTag())
                .build();


        HtmlTableHeader cell29 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("KGV")
                .build();


        //Third Row
//        HtmlTableHeader cell31 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("width", "166")
//                .appendAttribute("align", "left")
//                .appendContent("-")
//                .build();

        HtmlTableHeader cell32 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("cm")
                .build();

        HtmlTableHeader cell33 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("cm")
                .build();

//        HtmlTableHeader cell34 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("width", "60")
//                .appendContent("-")
//                .build();

//        HtmlTableHeader cell35 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("width", "60")
//                .appendContent("-")
//                .build();

//        HtmlTableHeader cell36 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("width", "60")
//                .appendContent("-")
//                .build();

        HtmlTableHeader cell37 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("MN/m²")
                .build();

        HtmlTableHeader cell38 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("M.-%")
                .build();

//        HtmlTableHeader cell38 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("width", "60")
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
                //     .appendContent(cell31.appendTag())
                .appendContent(cell32.appendTag())
                .appendContent(cell33.appendTag())
                //    .appendContent(cell34.appendTag())
                //     .appendContent(cell35.appendTag())
                //    .appendContent(cell36.appendTag())
                .appendContent(cell37.appendTag())
                .appendContent(cell38.appendTag())
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
