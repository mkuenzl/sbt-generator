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

class Anlage_ERK_OB_Template extends AHtmlTemplate
{
    private String aufschluss = "";

    @Override
    public void buildHtmlTable(final List<Erkundungsstelle> erkundungsstellen)
    {
    }

    @Override
    public void buildHtmlTable(final Erkundungsstelle erkundungsstelle)
    {
        aufschluss = erkundungsstelle.getInformation("ERK_AUFSCHLUSS_OB");

        HtmlTable tableErkOb = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();

        for (Schicht schicht : erkundungsstelle.getSchichtList())
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

                //LAGA RC
                String chemie_laga_rc = schicht.getInformation("CHEMIE_LAGA_RC");
                HtmlCell cellLAGARC = HtmlCellFormatUtil.formatChemie(chemie_laga_rc);

                //TL GESTEIN
                String chemie_tlgestein = schicht.getInformation("CHEMIE_TLGESTEIN");
                HtmlCell cellTLGESTEIN = HtmlCellFormatUtil.formatChemie(chemie_tlgestein);

                //RuK
                HtmlCell cellRUK = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_RUK"))
                        .build();

                //Notiz
                HtmlCell cellBemerkungen = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_NOTIZ"))
                        .build();

                HtmlRow schichtRow = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cellSchichtArt.appendTag())
                        .appendContent(cellDicke.appendTag())
                        .appendContent(cellTiefe.appendTag())
                        .appendContent(cellMUFV.appendTag())
                        .appendContent(cellPech.appendTag())
                        .appendContent(cellLAGARC.appendTag())
                        .appendContent(cellTLGESTEIN.appendTag())
                        .appendContent(cellRUK.appendTag())
                        .appendContent(cellBemerkungen.appendTag())
                        .build();

                tableErkOb.appendContent(schichtRow.appendTag());
            }
        }

        //Belastungklasse empty call
        HtmlCell cellEmpty1 = new HtmlCell.Builder()
                .appendAttribute("class", "NormalErkundungsstelle")
                .appendAttribute("colspan", "5")        //Zelle geht über 5 Spalten
                .appendContent("")
                .build();

        //Belastungsklasse
        HtmlCell cellBelastungsklasse = new HtmlCell.Builder()
                .appendAttribute("class", "NormalErkundungsstelle")
                .appendAttribute("colspan", "2")
                .appendContent(TextFormatUtil.formatBelastungsklasse(erkundungsstelle))
                .build();

        //Belastungsklassen Tafel
        HtmlCell cellBelastungklasseTafel = new HtmlCell.Builder()
                .appendAttribute("class", "NormalErkundungsstelle")
                .appendAttribute("colspan", "2")
                .appendContent(erkundungsstelle.getInformation("ERK_BELASTUNGSKLASSE_TAFEL"))
                .build();

        HtmlRow rowBelastungklasse = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(cellEmpty1.appendTag())
                .appendContent(cellBelastungsklasse.appendTag())
                .appendContent(cellBelastungklasseTafel.appendTag())
                .build();

        tableErkOb.appendContent(rowBelastungklasse.appendTag());

        setHtmlTable(tableErkOb.appendTag());
    }

    @Override
    String setHtmlTableHeader()
    {
        //First Row
        HtmlTableHeader cellGebundenerOberbauHeader = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "125")
                .appendAttribute("align", "left")
                .appendContent("Gebundener Oberbau")
                .build();

        HtmlTableHeader cellAufschlussverfahren = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("align", "left")
                .appendAttribute("colspan", "8")        //Zelle geht über 3 Reihen
                .appendContent("Aufschlussverfahren:".concat(" ").concat(aufschluss))
                .build();


        //Second Row
        HtmlTableHeader cellSchichtArt = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("align", "left")
                .appendAttribute("width", "125")
                .appendAttribute("rowspan", "2")
                .appendContent("Art der Schicht")
                .build();

        HtmlTableHeader cellDicke = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Dicke")
                .build();

        HtmlTableHeader cellTiefe = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Tiefe")
                .build();

        HtmlTableHeader cellMufv = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("MUFV")
                .build();

        HtmlTableHeader cellPech = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("PECH")
                .build();

        HtmlTableHeader cellLAGARC = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("LAGA RC")
                .build();

        HtmlTableHeader cellTLGe = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("TL Ge.")
                .build();

        HtmlTableHeader cellRUK = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("RuK")
                .build();

        HtmlTableHeader cellNotiz = new HtmlTableHeader.Builder()
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

        HtmlTableHeader cellDickeCm = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();

        HtmlTableHeader cellTiefeCm = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();



        HtmlTableHeader cellRukCGrad = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("°C")
                .build();

        HtmlRow firstHeaderRow = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cellGebundenerOberbauHeader.appendTag())
                .appendContent(cellAufschlussverfahren.appendTag())
                .build();

        HtmlRow secondHeaderRow = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cellSchichtArt.appendTag())
                .appendContent(cellDicke.appendTag())
                .appendContent(cellTiefe.appendTag())
                .appendContent(cellMufv.appendTag())
                .appendContent(cellPech.appendTag())
                .appendContent(cellLAGARC.appendTag())
                .appendContent(cellTLGe.appendTag())
                .appendContent(cellRUK.appendTag())
                .appendContent(cellNotiz.appendTag())
                .build();

        HtmlRow thirdHeaderRow = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                .appendContent(cellDickeCm.appendTag())
                .appendContent(cellTiefeCm.appendTag())
                .appendContent(cellRukCGrad.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstHeaderRow.appendTag())
                .append(secondHeaderRow.appendTag())
                .append(thirdHeaderRow.appendTag());

        return stringBuilder.toString();
    }

    @Override
    public String getExportFileName()
    {
        return null;
    }
}
