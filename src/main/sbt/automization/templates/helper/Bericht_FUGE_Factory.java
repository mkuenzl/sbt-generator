package sbt.automization.templates.helper;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class Bericht_FUGE_Factory {
    private static final String aufschluss = "FUGE";
    private static final String headerCellClass = "NormalHeader";
    private static final String normalCellClass = "NormalBold";

    public static String createIDRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle :
                erkundungsstellen)
        {
            HtmlCell htmlCell_ERK_ID = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalBold")
                    .appendAttribute("width", "60")
                    .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                    .build();

            row.appendContent(htmlCell_ERK_ID.appendTag());
        }

        return row.appendTag();
    }

    public static String createAufschlussRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle :
                erkundungsstellen)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "50")
                    .appendContent(erkundungsstelle.getInformation("ERK_AUFSCHLUSS_UG_OH_BA"))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }

    public static String createChemieIDRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_ID = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_ID"))
                    .build();

            rowCHEMIE_ID.appendContent(htmlCell_CHEMIE_ID.appendTag());
        }

        return rowCHEMIE_ID.appendTag();

    }

    public static String createChemieMufvRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_MUFV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_MUFV"))
                    .build();

            rowCHEMIE_MUFV.appendContent(htmlCell_CHEMIE_MUFV.appendTag());
        }

        return rowCHEMIE_MUFV.appendTag();
    }

    public static String createAVVRow(List<Erkundungsstelle> erkundungsstellen){
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

        for (Erkundungsstelle erkundungsstelle :
                erkundungsstellen)
        {
            //TODO AVV
            HtmlCell htmlCell_AVV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "50")
                    .appendContent("AVV")
                    .build();

            rowERK_AVV_PECH.appendContent(htmlCell_AVV.appendTag());
        }

        return rowERK_AVV_PECH.appendTag();
    }

}
