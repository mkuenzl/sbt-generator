package sbt.automization.templates.helper;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class Bericht_OH_Factory {

    public static String aufschluss = "OH";

    public static String createDIN18196Row(List<Erkundungsstelle> erkundungsstellen)
    {
        //DIN18196
        HtmlRow rowERK_DIN18196 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalErkundungsstelle")
                    .appendAttribute("width", "60")
                    .appendContent("TODO")
                    .build();

            rowERK_DIN18196.appendContent(cell.appendTag());
        }
        return rowERK_DIN18196.appendTag();
    }

    public static String createDIN19682Row(List<Erkundungsstelle> erkundungsstellen)
    {
        //DIN19682
        HtmlRow rowERK_DIN19682 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "100")
                        .appendContent("Bodengruppe,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("DIN 19682")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalErkundungsstelle")
                    .appendAttribute("width", "60")
                    .appendContent("TODO")
                    .build();

            rowERK_DIN19682.appendContent(cell.appendTag());
        }
        return rowERK_DIN19682.appendTag();
    }

    public static String createDIN18320Row(List<Erkundungsstelle> erkundungsstellen)
    {
        //DIN18320:2019-09
        HtmlRow rowERK_DIN18320 = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalErkundungsstelle")
                    .appendAttribute("width", "60")
                    .appendContent("TODO")
                    .build();

            rowERK_DIN18320.appendContent(cell.appendTag());
        }
        return rowERK_DIN18320.appendTag();
    }

    public static String createChemieIDRow(List<Erkundungsstelle> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_ID = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "100")
                        .appendContent("Laborprobe")
                        .build()
                        .appendTag())
                .build();

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_ID = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalErkundungsstelle")
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_ID"))
                    .build();

            rowCHEMIE_ID.appendContent(htmlCell_CHEMIE_ID.appendTag());
        }

        return rowCHEMIE_ID.appendTag();

    }

    public static String createChemieLagaBoRow(List<Erkundungsstelle> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_LAGA_BO = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "100")
                        .appendContent("LAGA Boden,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal6")
                                .appendContent("Zuordnung")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_LAGA_BO = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalErkundungsstelle")
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_LAGA_BO"))
                    .build();

            rowCHEMIE_LAGA_BO.appendContent(htmlCell_CHEMIE_LAGA_BO.appendTag());
        }

        return rowCHEMIE_LAGA_BO.appendTag();
    }

    public static String createChemieDepvRow(List<Erkundungsstelle> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_DEPV = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_DEPV = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalErkundungsstelle")
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_DEPV"))
                    .build();


            rowCHEMIE_DEPV.appendContent(htmlCell_CHEMIE_DEPV.appendTag());
        }

        return rowCHEMIE_DEPV.appendTag();
    }

    public static String createChemieEntscheidungshilfeRow(List<Erkundungsstelle> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_ENTSCHEIDUNGSHILFE = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_ENTSCHEIDUNGSILFE = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalErkundungsstelle")
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_ENTSCHEIDUNGSHILFE"))
                    .build();

            rowCHEMIE_ENTSCHEIDUNGSHILFE.appendContent(htmlCell_CHEMIE_ENTSCHEIDUNGSILFE.appendTag());
        }

        return rowCHEMIE_ENTSCHEIDUNGSHILFE.appendTag();
    }

    public static String createChemieAbfallSchluesselRow(List<Erkundungsstelle> erkundungsstellen)
    {
        HtmlRow rowCHEMIE_AVV = new HtmlRow.Builder()
                .appendAttribute("class", "Normal")
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
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

        for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
        {
            List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

            HtmlCell htmlCell_CHEMIE_ENTSCHEIDUNGSILFE = new HtmlCell.Builder()
                    .appendAttribute("class", "NormalErkundungsstelle")
                    .appendAttribute("width", "60")
                    .appendContent(TextFormatUtil.printSchichtInformation(erkundungsstelle, "OH", "CHEMIE_ABFALLSCHLUESSEL"))
                    .build();

            rowCHEMIE_AVV.appendContent(htmlCell_CHEMIE_ENTSCHEIDUNGSILFE.appendTag());
        }

        return rowCHEMIE_AVV.appendTag();
    }
}
