package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class FugeFactory
{
    private static final String aufschluss = "FUGE";
    private static final String headerCellClass = "NormalHeader";
    private static final String normalCellClass = "NormalBold";

    public static String createIDRow(List<ExplorationSite> explorationSites)
    {
        //Erkundungsstellen ID
        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "110")
                        .appendContent("Erkundungsstelle")
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite :
                explorationSites)
        {
            HtmlCell htmlCell_ERK_ID = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalBold")
                    .appendAttribute("width", "60")
                    .appendContent(explorationSite.getInformation("ERK_ID"))
                    .build();

            row.appendContent(htmlCell_ERK_ID.appendTag());
        }

        return row.appendTag();
    }

    public static String createAufschlussRow(List<ExplorationSite> explorationSites)
    {
        //Erkundungsstellen Aufschlussart
        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Aufschlussart")
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite :
                explorationSites)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "50")
                    .appendContent(explorationSite.getInformation("ERK_AUFSCHLUSS_OB"))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }

    public static String createChemieIDRow(List<ExplorationSite> explorationSites)
    {
        HtmlRow rowCHEMIE_ID = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Laborprobe")
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : explorationSites)
        {
            HtmlCell htmlCell_CHEMIE_ID = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, aufschluss, "CHEMIE_ID"))
                    .build();

            rowCHEMIE_ID.appendContent(htmlCell_CHEMIE_ID.appendTag());
        }

        return rowCHEMIE_ID.appendTag();

    }

    public static String createChemieMufvRow(List<ExplorationSite> explorationSites)
    {
        HtmlRow rowCHEMIE_MUFV = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Abgrenzung")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal")
                                .appendContent("Gefährlichkeit,")
                                .build()
                                .appendTag())
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("Schreiben des MUFV<sup>[26]</sup>")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : explorationSites)
        {
            HtmlCell htmlCell_CHEMIE_MUFV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, aufschluss, "CHEMIE_MUFV"))
                    .build();

            rowCHEMIE_MUFV.appendContent(htmlCell_CHEMIE_MUFV.appendTag());
        }

        return rowCHEMIE_MUFV.appendTag();
    }

    public static String createAVVRow(List<ExplorationSite> erkundungsstellen){
        //AVV
        HtmlRow rowERK_AVV = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Abfallschlüssel,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("AVV<sup>[6]</sup>")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite :
                erkundungsstellen)
        {
            //TODO AVV
            HtmlCell htmlCell_AVV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "50")
                    .appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, aufschluss, "CHEMIE_ABFALLSCHLUESSEL"))
                    .build();

            rowERK_AVV.appendContent(htmlCell_AVV.appendTag());
        }

        return rowERK_AVV.appendTag();
    }

}
