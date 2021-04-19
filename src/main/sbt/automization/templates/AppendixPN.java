package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.NameFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.*;

import java.util.ArrayList;
import java.util.List;

public final class AppendixPN extends AHtmlTemplate
{
    private static AppendixPN instance;

    private AppendixPN() {}

    public static AppendixPN getInstance()
    {
        if (instance == null)
        {
            synchronized (AppendixPN.class)
            {
                if (instance == null)
                {
                    instance = new AppendixPN();
                }
            }
        }
        return instance;
    }

    @Override
    public void buildHtmlTable(final List<ExplorationSite> sites)
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

        for (ExplorationSite explorationSite : sites)
        {
            List<Layer> layerList = formatSchichtList(explorationSite.getSchichtList());

            for (Layer layer : layerList)
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
                        .appendContent(TextFormatUtil.formatLayerProbe(layer))
                        .build();

                HtmlCell cellBehaeltnis = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(layer.getInformation("SCHICHT_BEHAELTNIS"))
                        .build();

                HtmlCell cellHaufwerk = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent("-")
                        .build();

                //TODO already done in excel sheet
                HtmlCell cellSchichtAbfallArt = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "110")
                        .appendContent(NameFormatUtil.formatArt(layer.getInformation("SCHICHT_ABFALLART")))
                        .build();

                HtmlCell cellSchichtKoernung = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendAttribute("width", "50")
                        .appendContent(layer.getInformation("SCHICHT_KOERNUNG"))
                        .build();

                HtmlCell cellSchichtFarbeGeruchKonsistenz = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal")
                                .appendContent(layer.getInformation("SCHICHT_FARBE"))
                                .build()
                                .appendTag())
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal")
                                .appendContent(layer.getInformation("SCHICHT_GERUCH"))
                                .build()
                                .appendTag())
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", "Normal")
                                .appendContent(layer.getInformation("SCHICHT_BODENART"))
                                .build()
                                .appendTag())
                        .build();

                HtmlCell cellErkundungsstellenIdentifier = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendAttribute("width", "30")
                        .appendContent(explorationSite.getInformation("ERK_ID"))
                        .build();

                HtmlCell cellSchichtTiefe = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent(TextFormatUtil.formatLayerDepth(layer))
                        .build();

                HtmlCell cellErkundungsstellenOberkante = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent(explorationSite.getInformation("ERK_OBERKANTE"))
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
    public void buildHtmlTable(final ExplorationSite site)
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
                .appendContent(TextFormatUtil.printLineBreak())
                .appendContent("Vol.")
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
                .appendContent(TextFormatUtil.printLineBreak())
                .appendContent("Vol.")
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
                .appendContent(TextFormatUtil.printLineBreak())
                .appendContent("Geruch")
                .appendContent(TextFormatUtil.printLineBreak())
                .appendContent("Bodenart")
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

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("l")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("l")
                .build();

        HtmlTableHeader cell25 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendContent("cm")
                .build();

        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell1.appendTag())
                .appendContent(cell2.appendTag())
                .appendContent(cell3.appendTag())
                .appendContent(cell5.appendTag())
                .appendContent(cell6.appendTag())
                .appendContent(cell7.appendTag())
                .appendContent(cell8.appendTag())
                .appendContent(cell9.appendTag())
                .appendContent(cell10.appendTag())
                .build();

        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .appendContent(cell25.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag());

        return stringBuilder.toString();
    }

    public List<Layer> formatSchichtList(List<Layer> layerListe)
    {

        List<Layer> layerList = new ArrayList<>();
        for (Layer layer : layerListe)
        {
            try
            {
                layerList.add((Layer) layer.clone());
            } catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
        }

        int size = layerList.size();

        if(size > 2)
        {
            for (int i = 0 ; i < layerList.size() ; i++)
            {
                if ("GOB".equals(layerList.get(i).getInformation("SCHICHT_AUFSCHLUSS")))
                {
                    if (layerList.get(i).getInformation("SCHICHT_ABFALLART").equals(layerList.get(i + 1).getInformation("SCHICHT_ABFALLART")))
                    {
                        layerList.get(i + 1).setInformation("SCHICHT_TIEFE_START", layerList.get(i).getInformation("SCHICHT_TIEFE_START"));
                        layerList.get(i + 1).setInformation("SCHICHT_KOERNUNG", "");
                        layerList.remove(layerList.get(i));
                        i--;
                    }
                }
            }
        }
        return layerList;
    }

}
