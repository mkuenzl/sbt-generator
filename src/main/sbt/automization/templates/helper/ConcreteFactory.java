package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;


public class ConcreteFactory
{
    private static final String aufschluss = "BETON";
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
                    .appendContent(explorationSite.getInformation("ERK_AUFSCHLUSS_TOB"))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }

    public static String createDruckfestigkeitRow(List<ExplorationSite> erkundungsstellen)
    {
        //Druckfestigkeit
        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Druckfestigkeit,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("N/mm²")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "SCHICHT_DRUCKFESTIGKEIT"))
                    .build();

            row.appendContent(cell.appendTag());
        }
        return row.appendTag();

    }

    public static String createDickenRow(List<ExplorationSite> erkundungsstellen)
    {
        //Zonen Material 1 - Anzahl Schichten
        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Dicke,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("cm")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
//            StringBuilder formatedSchichtenMaterial = new StringBuilder();
//
//            List<Schicht> beton = erkundungsstelle.getSchichtAufschluss("BETON");
//
//
//            int size = beton.size();
//            for (int i = 0 ; i < size ; i++)
//            {
//                Schicht schicht = beton.get(i);
//
//                formatedSchichtenMaterial.append(NameFormatUtil.formatArt(schicht.getInformation("SCHICHT_ART")));
//
//                formatedSchichtenMaterial.append(TextFormatUtil.printEmptyRow());
//
//                HtmlText text2 = new HtmlText.Builder()
//                        .appendAttribute("class", "Normal")
//                        .appendContent(schicht.getInformation("SCHICHT_DICKE"))
//                        .build();
//
//                formatedSchichtenMaterial.append(text2.appendTag());
//                formatedSchichtenMaterial.append(TextFormatUtil.printFormatedSchichtTiefe(schicht));
//
//                if (i + 1 < size)
//                {
//                    formatedSchichtenMaterial.append(TextFormatUtil.printCellTextDivider());
//                }
//            }
//
//            HtmlCell cell = new HtmlCell.Builder()
//                    .appendAttribute("class", normalCellClass)
//                    .appendAttribute("width", "50")
//                    .appendContent(formatedSchichtenMaterial.toString())
//                    .build();

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "SCHICHT_ART"))
                    .build();

            row.appendContent(cell.appendTag());
        }
        return row.appendTag();
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
            List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(aufschluss);

            HtmlCell htmlCell_CHEMIE_ID = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_ID"))
                    .build();

            rowCHEMIE_ID.appendContent(htmlCell_CHEMIE_ID.appendTag());
        }

        return rowCHEMIE_ID.appendTag();

    }

    public static String createChemieMufvRow(List<ExplorationSite> erkundungsstellen)
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
                                .appendContent("Schreiben des MUFV")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(aufschluss);

            HtmlCell htmlCell_CHEMIE_MUFV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_MUFV"))
                    .build();

            rowCHEMIE_MUFV.appendContent(htmlCell_CHEMIE_MUFV.appendTag());
        }

        return rowCHEMIE_MUFV.appendTag();
    }

    public static String createChemieLagaRcRow(List<ExplorationSite> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_LAGA_RC = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Zuordnung,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("LAGA Bauschutt")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(aufschluss);

            HtmlCell htmlCell_CHEMIE_LAGA_RC = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_LAGA_RC"))
                    .build();

            rowCHEMIE_LAGA_RC.appendContent(htmlCell_CHEMIE_LAGA_RC.appendTag());
        }

        return rowCHEMIE_LAGA_RC.appendTag();
    }

    public static String createChemieLagaRcOrientierungRow(List<ExplorationSite> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Orientierungswert,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("LAGA Bauschutt")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(aufschluss);

            HtmlCell htmlCell_CHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_LAGARC_ORIENTIERUNGSWERT"))
                    .build();

            rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendContent(htmlCell_CHEMIE_LAGA_RC_ORIENTIERUNG.appendTag());
        }

        return rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendTag();

    }

    public static String createChemieTlGesteinRow(List<ExplorationSite> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_TL_GESTEIN = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Verwertungsklasse,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("TL Gestein")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : erkundungsstellen)
        {
            List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(aufschluss);

            HtmlCell htmlCell_CHEMIE_TL_GESTEIN = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_TLGESTEIN"))
                    .build();


            rowCHEMIE_TL_GESTEIN.appendContent(htmlCell_CHEMIE_TL_GESTEIN.appendTag());
        }

        return rowCHEMIE_TL_GESTEIN.appendTag();
    }

    public static String createChemieDepvRow(List<ExplorationSite> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_DEPV = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Deponieklasse,")
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
            List<Layer> layerAufschlusses = explorationSite.getLayersWithOutcrop(aufschluss);

            HtmlCell htmlCell_CHEMIE_DEPV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_DEPV"))
                    .build();


            rowCHEMIE_DEPV.appendContent(htmlCell_CHEMIE_DEPV.appendTag());
        }

        return rowCHEMIE_DEPV.appendTag();
    }

    public static String createAVVRow(List<ExplorationSite> erkundungsstellen)
    {
        //AVV
        HtmlRow rowERK_AVV_PECH = new HtmlRow.Builder()
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

        for (ExplorationSite explorationSite :
                erkundungsstellen)
        {
            //TODO AVV
            HtmlCell htmlCell_AVV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "50")
                    .appendContent(TextFormatUtil.printLayerInformation(explorationSite, aufschluss, "CHEMIE_ABFALLSCHLUESSEL"))
                    .build();

            rowERK_AVV_PECH.appendContent(htmlCell_AVV.appendTag());
        }

        return rowERK_AVV_PECH.appendTag();
    }

    public static String createLegendeRow(List<ExplorationSite> erkundungsstellen)
    {
        //Umwelttechnische Merkmale Trennzeile
        HtmlRow rowLegende = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "NormalHeader")
                        .appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
                        .appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
                        .build()
                        .appendTag())
                .build();

        return rowLegende.appendTag();
    }
}
