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

final class AppendixSiteGOB extends AHtmlTable
{
    private String outcrop = "";

    @Override
    public void constructTable(final List<ExplorationSite> sites)
    {
    }

    @Override
    public void constructTable(final ExplorationSite site)
    {
        outcrop = site.getInformation(InformationTag.SITE_OUTCROP_OB);

        HtmlTable tableErkOb = new HtmlTable.Builder()
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
            String schicht_aufschluss = layer.getInformation("SCHICHT_AUFSCHLUSS");
            if ("GOB".equals(schicht_aufschluss) || "TMHB".equals(schicht_aufschluss) || "BETON".equals(schicht_aufschluss) ||"BESCHICHTUNG".equals(schicht_aufschluss) ||"ABDICHTUNG".equals(schicht_aufschluss))
            {
                HtmlCell cellSchichtArt = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(TextFormatUtil.formatKindAndGranulation(layer.getInformation("SCHICHT_ART"),
                                layer.getInformation("SCHICHT_KOERNUNG")))
                        .build();

                //Dicke
                HtmlCell cellDicke = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(layer.getInformation("SCHICHT_DICKE"))
                        .build();

                //Tiefe
                HtmlCell cellTiefe = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(layer.getInformation("SCHICHT_TIEFE_ENDE"))
                        .build();


                //MUFV
                String chemie_mufv = layer.getInformation("CHEMIE_MUFV");
                HtmlCell cellMUFV = HtmlCellFormatUtil.formatChemistry(chemie_mufv);

                //Pech
                String schicht_pech = layer.getInformation("SCHICHT_PECH");
                HtmlCell cellPech = HtmlCellFormatUtil.formatPitch(schicht_pech);

                //LAGA RC
                String chemie_laga_rc = layer.getInformation("CHEMIE_LAGA_RC");
                HtmlCell cellLAGARC = HtmlCellFormatUtil.formatChemistry(chemie_laga_rc);

                //TL GESTEIN
                String chemie_tlgestein = layer.getInformation("CHEMIE_TLGESTEIN");
                HtmlCell cellTLGESTEIN = HtmlCellFormatUtil.formatChemistry(chemie_tlgestein);

                //PAK
                HtmlCell cellPAK = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(layer.getInformation("SCHICHT_PAK"))
                        .build();

                //RuK
                HtmlCell cellRUK = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(layer.getInformation("SCHICHT_RUK"))
                        .build();

                HtmlRow layerRow = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cellSchichtArt.appendTag())
                        .appendContent(cellDicke.appendTag())
                        .appendContent(cellTiefe.appendTag())
                        .appendContent(cellMUFV.appendTag())
                        .appendContent(cellPech.appendTag())
                        .appendContent(cellLAGARC.appendTag())
                        .appendContent(cellTLGESTEIN.appendTag())
                        .appendContent(cellPAK.appendTag())
                        .appendContent(cellRUK.appendTag())
                        .build();

                tableErkOb.appendContent(layerRow.appendTag());
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
                .appendContent(TextFormatUtil.formatLoadClass(site))
                .build();

        //Belastungsklassen Tafel
        HtmlCell cellBelastungklasseTafel = new HtmlCell.Builder()
                .appendAttribute("class", "NormalErkundungsstelle")
                .appendAttribute("colspan", "2")
                .appendContent("RStO<sup>[21]</sup>")
                .appendContent(TextFormatUtil.printLineBreak())
                .appendContent(site.getInformation("ERK_BELASTUNGSKLASSE_TAFEL"))
                .build();

        HtmlRow rowBelastungklasse = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(cellEmpty1.appendTag())
                .appendContent(cellBelastungsklasse.appendTag())
                .appendContent(cellBelastungklasseTafel.appendTag())
                .build();

        tableErkOb.appendContent(rowBelastungklasse.appendTag());

        setTable(tableErkOb.appendTag());
    }

    @Override
    String constructAndGetTableHeader()
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
                .appendContent("Aufschlussverfahren:".concat(" ").concat(outcrop))
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
                .appendContent("<div>[23]</div>")
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
                .appendContent("<div>[46]</div>")
                .build();

        HtmlTableHeader cellPech = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("PECH")
                .appendContent("<div>[26]</div>")
                .build();

        HtmlTableHeader cellLAGARC = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("LAGA RC")
                .appendContent("<div>[16]</div>")
                .build();

        HtmlTableHeader cellTLGe = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendAttribute("rowspan", "2")
                .appendContent("TL Ge.")
                .appendContent("<div>[15]</div>")
                .build();

        HtmlTableHeader cellPAK = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("PAK")
                .build();

        HtmlTableHeader cellRUK = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("RuK")
                .appendContent("<div>[34]</div>")
                .build();

        //Third Row
//        HtmlTableHeader cell31 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("align", "left")
//                .appendContent("-")
//                .build();

        HtmlTableHeader cellDickeCm = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("cm")
                .build();

        HtmlTableHeader cellTiefeCm = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("cm")
                .build();

        HtmlTableHeader cellPAKmgkg = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("mg/kg")
                .build();

        HtmlTableHeader cellRukCGrad = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
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
                .appendContent(cellPAK.appendTag())
                .appendContent(cellRUK.appendTag())
                .build();

        HtmlRow thirdHeaderRow = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                .appendContent(cellDickeCm.appendTag())
                .appendContent(cellTiefeCm.appendTag())
                .appendContent(cellPAKmgkg.appendTag())
                .appendContent(cellRukCGrad.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstHeaderRow.appendTag())
                .append(secondHeaderRow.appendTag())
                .append(thirdHeaderRow.appendTag());

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
