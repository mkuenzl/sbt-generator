package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class OhFactory
{

    private static final String aufschluss = "OH";
    private static final String headerCellClass = "NormalHeader";
    private static final String normalCellClass = "NormalBold";

    public static String createIDRow(List<ExplorationSite> erkundungsstellen)
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
                erkundungsstellen)
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

    public static String createAufschlussRow(List<ExplorationSite> erkundungsstellen)
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
                erkundungsstellen)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "50")
                    .appendContent(explorationSite.getInformation("ERK_AUFSCHLUSS_UG_OH_BA"))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }

    public static String createDIN18196Row(List<ExplorationSite> erkundungsstellen)
    {
        //DIN18196
        HtmlRow rowERK_DIN18196 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Bodengruppe,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("DIN 18196")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getSchichtAufschluss(aufschluss);

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "SCHICHT_ART"))
                    .build();

            rowERK_DIN18196.appendContent(cell.appendTag());
        }
        return rowERK_DIN18196.appendTag();
    }

    public static String createDIN18915Row(List<ExplorationSite> erkundungsstellen)
    {
        //DIN18915
        HtmlRow rowERK_DIN19682 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Bodengruppe,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("DIN 18915")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getSchichtAufschluss(aufschluss);

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "SCHICHT_BODENKLASSE"))
                    .build();

            rowERK_DIN19682.appendContent(cell.appendTag());
        }
        return rowERK_DIN19682.appendTag();
    }

    public static String createDIN18320Row(List<ExplorationSite> erkundungsstellen)
    {
        //DIN18320:2019-09
        HtmlRow rowERK_DIN18320 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Homogenbereich,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("DIN 18320:2019-09")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getSchichtAufschluss(aufschluss);

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "SCHICHT_HOMOGENBEREICH"))
                    .build();

            rowERK_DIN18320.appendContent(cell.appendTag());
        }
        return rowERK_DIN18320.appendTag();
    }

    public static String createChemieIDRow(List<ExplorationSite> erkundungsstellen)
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

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_ID = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_ID"))
                    .build();

            rowCHEMIE_ID.appendContent(htmlCell_CHEMIE_ID.appendTag());
        }

        return rowCHEMIE_ID.appendTag();

    }

    public static String createChemieLagaBoRow(List<ExplorationSite> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_LAGA_BO = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Zuordnung,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("LAGA Boden")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_LAGA_BO = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_LAGA_BO"))
                    .build();

            rowCHEMIE_LAGA_BO.appendContent(htmlCell_CHEMIE_LAGA_BO.appendTag());
        }

        return rowCHEMIE_LAGA_BO.appendTag();
    }

    public static String createChemieDepvRow(List<ExplorationSite> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_DEPV = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Deponieverordnungs-")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal")
                                .appendContent("klasse,")
                                .build()
                                .appendTag())
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("DepV")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_DEPV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_DEPV"))
                    .build();


            rowCHEMIE_DEPV.appendContent(htmlCell_CHEMIE_DEPV.appendTag());
        }

        return rowCHEMIE_DEPV.appendTag();
    }

    public static String createChemieEntscheidungshilfeRow(List<ExplorationSite> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_ENTSCHEIDUNGSHILFE = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Entscheidungshilfe,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("DepV")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_ENTSCHEIDUNGSILFE = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_ENTSCHEIDUNGSHILFE"))
                    .build();

            rowCHEMIE_ENTSCHEIDUNGSHILFE.appendContent(htmlCell_CHEMIE_ENTSCHEIDUNGSILFE.appendTag());
        }

        return rowCHEMIE_ENTSCHEIDUNGSHILFE.appendTag();
    }

    public static String createChemieAbfallSchluesselRow(List<ExplorationSite> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_AVV = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Abfallschlüssel,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("AVV")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_ENTSCHEIDUNGSILFE = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_ABFALLSCHLUESSEL"))
                    .build();

            rowCHEMIE_AVV.appendContent(htmlCell_CHEMIE_ENTSCHEIDUNGSILFE.appendTag());
        }

        return rowCHEMIE_AVV.appendTag();
    }
}
