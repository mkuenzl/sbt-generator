package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.NameFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.*;

import java.util.ArrayList;
import java.util.List;

public final class Anlage_PN_Template extends AHtmlTemplate
{
    private static Anlage_PN_Template instance;

    private Anlage_PN_Template() {}

    public static Anlage_PN_Template getInstance()
    {
        if (instance == null)
        {
            synchronized (Anlage_PN_Template.class)
            {
                if (instance == null)
                {
                    instance = new Anlage_PN_Template();
                }
            }
        }
        return instance;
    }

    @Override
    public void buildHtmlTable(final List<Erkundungsstelle> data)
    {
        int counter = 1;

        HtmlTable tablePN = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();

        int rowCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (Erkundungsstelle erkundungsstelle : data)
        {
            List<Schicht> schichtList = formatSchichtList(erkundungsstelle.getSchichtList());

            for (Schicht schicht : schichtList)
            {

                if (rowCounter >= 20)
                {
                    stringBuilder.append(tablePN.appendTag())
                            .append("<br>")
                            .append("<br>");

                    tablePN = new HtmlTable.Builder()
                            .appendAttribute("class", "MsoNormalTable")
                            .appendAttribute("width", "605")
                            .appendAttribute("border", "1")
                            .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                            .appendAttribute("cellspacing", "0")
                            .appendAttribute("cellpadding", "0")
                            .appendContent(setHtmlTableHeader())
                            .build();

                    rowCounter = 0;
                }


                HtmlCell cellPNCounter = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent("P".concat(String.valueOf(counter++)))
                        .build();

                HtmlCell cellSchichtProbenArt = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent(TextFormatUtil.formatSchichtProbePN(schicht))
                        .build();

                HtmlCell cellBehaeltnis = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_BEHAELTNIS"))
                        .build();

                HtmlCell cellHaufwerk = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent("-")
                        .build();

                //TODO already done in excel sheet
                HtmlCell cellSchichtAbfallArt = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "110")
                        .appendContent(NameFormatUtil.formatArt(schicht.getInformation("SCHICHT_ABFALLART")))
                        .build();

                HtmlCell cellSchichtKoernung = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendAttribute("width", "50")
                        .appendContent(schicht.getInformation("SCHICHT_KOERNUNG"))
                        .build();

                HtmlCell cellSchichtFarbeGeruchKonsistenz = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal")
                                .appendContent(schicht.getInformation("SCHICHT_FARBE"))
                                .build()
                                .appendTag())
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal")
                                .appendContent(schicht.getInformation("SCHICHT_GERUCH"))
                                .build()
                                .appendTag())
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal")
                                .appendContent(schicht.getInformation("SCHICHT_BODENART"))
                                .build()
                                .appendTag())
                        .build();

                HtmlCell cellErkundungsstellenIdentifier = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendAttribute("width", "30")
                        .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                        .build();

                HtmlCell cellSchichtTiefe = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent(TextFormatUtil.formatSchichtTiefe(schicht))
                        .build();

                HtmlCell cellErkundungsstellenOberkante = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent(erkundungsstelle.getInformation("ERK_OBERKANTE"))
                        .build();

                HtmlRow row = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cellPNCounter.appendTag())
                        .appendContent(cellSchichtProbenArt.appendTag())
                        .appendContent(cellBehaeltnis.appendTag())
                        .appendContent(cellHaufwerk.appendTag())
                        .appendContent(cellSchichtAbfallArt.appendTag())
                        .appendContent(cellSchichtKoernung.appendTag())
                        .appendContent(cellSchichtFarbeGeruchKonsistenz.appendTag())
                        .appendContent(cellErkundungsstellenIdentifier.appendTag())
                        .appendContent(cellSchichtTiefe.appendTag())
                        .appendContent(cellErkundungsstellenOberkante.appendTag())
                        .build();

                rowCounter++;

                tablePN.appendContent(row.appendTag());
            }
        }

        stringBuilder.append(tablePN.appendTag());

        setHtmlTable(stringBuilder.toString());
    }

    @Override
    public void buildHtmlTable(final Erkundungsstelle data)
    {

    }

    @Override
    public String getExportFileName()
    {
        return "PN_Tabelle.html";
    }

    @Override
    String setHtmlTableHeader()
    {
        HtmlTableHeader cell1 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "40")
                .appendAttribute("rowspan", "2")
                .appendContent("Probe")
                .build();

        HtmlTableHeader cell2 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "40")
                .appendAttribute("rowspan", "2")
                .appendContent("Art")
                .build();

        HtmlTableHeader cell3 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "105")
                .appendContent("Behältnis")
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("Vol.").build().appendTag())
                .build();

//        HtmlTableHeader cell4 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("width", "40")
//                .appendContent("Vol.")
//                .build();

        HtmlTableHeader cell5 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Haufwerk")
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("Vol.").build().appendTag())
                .build();

        HtmlTableHeader cell6 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "140")
                .appendAttribute("colspan", "2")
                .appendAttribute("rowspan", "2")
                .appendContent("Abfallart")
                .build();

        HtmlTableHeader cell7 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "76")
                .appendAttribute("rowspan", "2")
                .appendContent("Farbe")
                .appendContent(new HtmlText.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Geruch")
                        .build()
                        .appendTag())
                .appendContent(new HtmlText.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent("Bodenart")
                        .build()
                        .appendTag())
                .build();

        HtmlTableHeader cell8 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "30")
                .appendAttribute("rowspan", "2")
                .appendContent("Erk. St.")
                .build();

        HtmlTableHeader cell9 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "70")
                .appendContent("Tiefe")
                .build();

        HtmlTableHeader cell10 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "65")
                .appendAttribute("rowspan", "2")
                .appendContent("Notiz")
                .build();

//        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("colspan", "2")
//                .appendContent("")
//                .build();

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("L")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("L")
                .build();

//        HtmlTableHeader cell24 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("colspan", "4")
//                .appendContent("")
//                .build();

        HtmlTableHeader cell25 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();

//        HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendContent("")
//                .build();


        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell1.appendTag())
                .appendContent(cell2.appendTag())
                .appendContent(cell3.appendTag())
                //         .appendContent(cell4.appendTag())
                .appendContent(cell5.appendTag())
                .appendContent(cell6.appendTag())
                .appendContent(cell7.appendTag())
                .appendContent(cell8.appendTag())
                .appendContent(cell9.appendTag())
                .appendContent(cell10.appendTag())
                .build();

        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                //   .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                //  .appendContent(cell24.appendTag())
                .appendContent(cell25.appendTag())
                //    .appendContent(cell26.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag());

        return stringBuilder.toString();
    }

    public List<Schicht> formatSchichtList(List<Schicht> schichtListe)
    {

        List<Schicht> schichtList = new ArrayList<>();
        for (Schicht schicht : schichtListe)
        {
            try
            {
                schichtList.add((Schicht) schicht.clone());
            } catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
        }
        //TODO NULL POINTER
        for (int i = 0 ; i < schichtList.size() ; i++)
        {
            if ("GOB".equals(schichtList.get(i).getInformation("SCHICHT_AUFSCHLUSS")))
            {
                if (schichtList.get(i).getInformation("SCHICHT_ABFALLART").equals(schichtList.get(i + 1).getInformation("SCHICHT_ABFALLART")))
                {
                    schichtList.get(i + 1).setInformation("SCHICHT_TIEFE_START", schichtList.get(i).getInformation("SCHICHT_TIEFE_START"));
                    schichtList.get(i + 1).setInformation("SCHICHT_KOERNUNG", "");
                    schichtList.remove(schichtList.get(i));
                    i--;
                }
            }
        }
        return schichtList;
    }
}
