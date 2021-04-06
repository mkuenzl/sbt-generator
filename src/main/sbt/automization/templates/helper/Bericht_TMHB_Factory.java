package sbt.automization.templates.helper;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class Bericht_TMHB_Factory {

    private static final String aufschluss = "TMHB";
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
                    .appendContent(erkundungsstelle.getInformation("ERK_AUFSCHLUSS_TOB"))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }

    public static String createGesamtDickeRow(List<Erkundungsstelle> erkundungsstellen)
    {
        //Gesamtdicke Oberbau
        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Gesamtdicke Oberbau,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("cm")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            String gob_dicke = TextFormatUtil.formatErkAufschlussDicke(erkundungsstelle, "GOB");
            String tob_dicke = TextFormatUtil.formatErkAufschlussDicke(erkundungsstelle, aufschluss);

            String doubleValue = String.valueOf(Math.round(Double.parseDouble(gob_dicke) + Double.parseDouble(tob_dicke)));
            String gesamtDicke = doubleValue.replace(".",",");

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(gesamtDicke)
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();

    }

    public static String createDruckfestigkeitRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_DRUCKFESTIGKEIT"))
                    .build();

            row.appendContent(cell.appendTag());
        }
        return row.appendTag();

    }

    public static String createDickeRow(List<Erkundungsstelle> erkundungsstellen)
    {
        //Gesamtdicke Oberbau
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            String tob_dicke = TextFormatUtil.formatErkAufschlussDicke(erkundungsstelle, aufschluss);

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(tob_dicke)
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();

    }

    public static String createBelastungklasseRow(List<Erkundungsstelle> erkundungsstellen)
    {
        //Belastungklasse
        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", "100")
                        .appendContent("Belastungklasse,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("RStO")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(erkundungsstelle.getInformation("ERK_BELASTUNGSKLASSE"))
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

    public static String createChemieLagaRcRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_LAGA_RC = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_LAGA_RC"))
                    .build();

            rowCHEMIE_LAGA_RC.appendContent(htmlCell_CHEMIE_LAGA_RC.appendTag());
        }

        return rowCHEMIE_LAGA_RC.appendTag();
    }

    public static String createChemieLagaRcOrientierungRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_LAGARC_ORIENTIERUNGSWERT"))
                    .build();

            rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendContent(htmlCell_CHEMIE_LAGA_RC_ORIENTIERUNG.appendTag());
        }

        return rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendTag();

    }

    public static String createChemieTlGesteinRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_TL_GESTEIN = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_TLGESTEIN"))
                    .build();


            rowCHEMIE_TL_GESTEIN.appendContent(htmlCell_CHEMIE_TL_GESTEIN.appendTag());
        }

        return rowCHEMIE_TL_GESTEIN.appendTag();
    }

    public static String createChemieDepvRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_DEPV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_DEPV"))
                    .build();


            rowCHEMIE_DEPV.appendContent(htmlCell_CHEMIE_DEPV.appendTag());
        }

        return rowCHEMIE_DEPV.appendTag();
    }

    public static String createAVVRow(List<Erkundungsstelle> erkundungsstellen)
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

        for (Erkundungsstelle erkundungsstelle :
                erkundungsstellen)
        {
            //TODO CHEMIE_ABFALLSCHLUESSEL
            HtmlCell htmlCell_AVV = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", "50")
                    .appendContent("AVV")
                    .build();

            rowERK_AVV_PECH.appendContent(htmlCell_AVV.appendTag());
        }

        return rowERK_AVV_PECH.appendTag();
    }

    public static String createLegendeRow(List<Erkundungsstelle> erkundungsstellen)
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
