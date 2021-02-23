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

class Anlage_ERK_OB_TemplateStrategy extends AHtmlTemplateStrategy
{
    private String aufschluss = "";

    @Override
    public void buildHtmlTable(final List<Erkundungsstelle> data)
    {
    }

    @Override
    public void buildHtmlTable(final Erkundungsstelle data)
    {
        aufschluss = data.getInformation("ERK_AUFSCHLUSS_OB");

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
            if ("GOB".equals(schicht.getInformation("SCHICHT_AUFSCHLUSS")))
            {
                //Art der Schicht
                HtmlCell cellSchichtArt = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_ART").concat("  ").concat(schicht.getInformation("SCHICHT_KOERNUNG")))
                        .build();

                //Dicke
                HtmlCell cellDicke = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_DICKE"))
                        .build();

                //Tiefe
                HtmlCell cellTiefe = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
                        .build();


                //MUFV
                String chemie_mufv = schicht.getInformation("CHEMIE_MUFV");
                HtmlCell cellMUFV = HtmlCellFormatUtil.formatChemie(chemie_mufv);

                //Pech
                String schicht_pech = schicht.getInformation("SCHICHT_PECH");
                HtmlCell cellPech = HtmlCellFormatUtil.formatPech(schicht_pech);

                //RuK
                HtmlCell cellRUK = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_RUK"))
                        .build();

                //LP?
                HtmlCell cellEmpty1 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent("")
                        .build();

                //LP?
                HtmlCell cellEmpty2 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent("")
                        .build();

                //Notiz
                HtmlCell cellBemerkungen = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_NOTIZ"))
                        .build();

                HtmlRow row = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cellSchichtArt.appendTag())
                        .appendContent(cellDicke.appendTag())
                        .appendContent(cellTiefe.appendTag())
                        .appendContent(cellMUFV.appendTag())
                        .appendContent(cellPech.appendTag())
                        .appendContent(cellRUK.appendTag())
                        .appendContent(cellEmpty1.appendTag())
                        .appendContent(cellEmpty2.appendTag())
                        .appendContent(cellBemerkungen.appendTag())
                        .build();

                table.appendContent(row.appendTag());
            }
        }

        //Belastungklasse Tiefe von-bis
        HtmlCell cellEmpty1 = new HtmlCell.Builder()
                .appendAttribute("class", "NormalErkundungsstelle")
                .appendAttribute("colspan", "5")        //Zelle geht über 3 Spalten
                .appendContent("")
                .build();

        //Belastungsklasse
        HtmlCell cellBelastungsklasse = new HtmlCell.Builder()
                .appendAttribute("class", "NormalErkundungsstelle")
                .appendAttribute("colspan", "2")
                .appendContent(TextFormatUtil.formatBelastungsklasse(data))
                .build();

        //Belastungsklassen Tafel
        HtmlCell cellBelastungklasseTafel = new HtmlCell.Builder()
                .appendAttribute("class", "NormalErkundungsstelle")
                .appendAttribute("colspan", "2")
                .appendContent(data.getInformation("ERK_BELASTUNGSKLASSE_TAFEL"))
                .build();

        HtmlRow rowBelastungklasse = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(cellEmpty1.appendTag())
                .appendContent(cellBelastungsklasse.appendTag())
                .appendContent(cellBelastungklasseTafel.appendTag())
                .build();

        table.appendContent(rowBelastungklasse.appendTag());

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
                .appendContent("Gebundener Oberbau")
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
                .appendContent("PECH")
                .build();

        HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("RuK")
                .build();

        HtmlTableHeader cell27 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("")
                .build();

        HtmlTableHeader cell28 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("")
                .build();

        HtmlTableHeader cell29 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("Notiz")
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
//
//        HtmlTableHeader cell35 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendContent("-")
//                .build();

        HtmlTableHeader cell36 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("°C")
                .build();

//        HtmlTableHeader cell37 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .build();

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
                //  .appendContent(cell31.appendTag())
                .appendContent(cell32.appendTag())
                .appendContent(cell33.appendTag())
                //  .appendContent(cell34.appendTag())
                //   .appendContent(cell35.appendTag())
                .appendContent(cell36.appendTag())
                //   .appendContent(cell37.appendTag())
                //   .appendContent(cell38.appendTag())
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
