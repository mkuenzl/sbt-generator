package sbt.automization.templates;


import sbt.automization.data.ExplorationSite;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.*;

import java.util.List;

public final class AppendixLP extends AHtmlTable
{
    private static AppendixLP instance;

    private AppendixLP() {}

    public static AppendixLP getInstance()
    {
        if (instance == null)
        {
            synchronized (AppendixLP.class)
            {
                if (instance == null)
                {
                    instance = new AppendixLP();
                }
            }
        }
        return instance;
    }

    @Override
    public void constructTable(final List<ExplorationSite> explorationSites)
    {
        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(constructAndGetTableHeader())
                .build();


        for (ExplorationSite explorationSite : explorationSites)
        {
            String erk_lp = explorationSite.getInformation("ERK_LP");

            if (! "".equals(erk_lp) && ! "-".equals(erk_lp))
            {

                HtmlCell cellLPIdentifier = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendContent("LP".concat(erk_lp))
                        .build();

                HtmlCell cellERKID = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendContent(explorationSite.getInformation("ERK_ID"))
                        .build();

                HtmlCell cellERKORT = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(explorationSite.getInformation("ERK_ORT"))
                        .build();

                HtmlCell cellERKLP1 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendAttribute("width", "40")
                        .appendContent(explorationSite.getInformation("ERK_LP1"))
                        .build();

                HtmlCell cellERKLP2 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendAttribute("width", "40")
                        .appendContent(explorationSite.getInformation("ERK_LP2"))
                        .build();

                HtmlCell cellERKLP3 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendAttribute("width", "40")
                        .appendContent(explorationSite.getInformation("ERK_LP3"))
                        .build();

                HtmlCell cellERKLPMEAN = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendAttribute("width", "40")
                        .appendContent(explorationSite.getInformation("ERK_LP_MEAN"))
                        .build();

                HtmlCell cellERKLPEV = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendContent(explorationSite.getInformation("ERK_LP_EV"))
                        .build();

                HtmlCell cellERKLPEV15 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendContent(explorationSite.getInformation("ERK_LP_EV15"))
                        .build();

                HtmlCell cellERKLPEV2 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("align", "center")
                        .appendContent(TextFormatUtil.formatLP(explorationSite.getInformation("ERK_LP_EV2"),
                                explorationSite.getInformation("ERK_LP_EV15")))
                        .build();

                HtmlRow htmlRow = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cellLPIdentifier.appendTag())
                        .appendContent(cellERKID.appendTag())
                        .appendContent(cellERKORT.appendTag())
                        .appendContent(cellERKLP1.appendTag())
                        .appendContent(cellERKLP2.appendTag())
                        .appendContent(cellERKLP3.appendTag())
                        .appendContent(cellERKLPMEAN.appendTag())
                        .appendContent(cellERKLPEV.appendTag())
                        .appendContent(cellERKLPEV15.appendTag())
                        .appendContent(cellERKLPEV2.appendTag())
                        .build();

                table.appendContent(htmlRow.appendTag());
            }
        }
        setTable(table.appendTag());
    }

    @Override
    String constructAndGetTableHeader()
    {
        HtmlTableHeader cell1 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "55")
                .appendAttribute("rowspan", "3")        //Zelle geht über 3 Reihen
                .appendContent("Versuch")
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("Nr.").build().appendTag())
                .build();

        HtmlTableHeader cell2 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "65")
                .appendAttribute("rowspan", "3")        //Zelle geht über 3 Reihen
                .appendContent("Erk. St.")
                .build();

        HtmlTableHeader cell3 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "185")
                .appendAttribute("rowspan", "3")        //Zelle geht über 3 Reihen
                .appendContent("Lage der Messstelle")
                .build();

        HtmlTableHeader cell4 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "160")
                .appendAttribute("colspan", "4")        //Zelle geht über 4 Spalten
                .appendContent("Setzung")
                .build();

        HtmlTableHeader cell5 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendAttribute("rowspan", "2")
                .appendContent("E<sub>Vdyn</sub>")
                .build();

        HtmlTableHeader cell6 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendAttribute("rowspan", "2")
                .appendContent("E<sub>Vdyn</sub>")
                .appendContent(new HtmlText.Builder().appendAttribute("class", "Normal").appendContent("<sub>(-15%)</sub>").build().appendTag())
                .build();

        HtmlTableHeader cell7 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "45")
                .appendAttribute("rowspan", "2")
                .appendContent("E<sub>V2</sub>")
                .appendContent("<div>[42]</div>")
                .build();

        //First Header Row
        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("height", "2")
                .appendContent(cell1.appendTag())
                .appendContent(cell2.appendTag())
                .appendContent(cell3.appendTag())
                .appendContent(cell4.appendTag())
                .appendContent(cell5.appendTag())
                .appendContent(cell6.appendTag())
                .appendContent(cell7.appendTag())
                .build();

        //Second Header
        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "40")
                .appendContent("S<sub>1</sub>")
                .build();

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "40")
                .appendContent("S<sub>2</sub>")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "40")
                .appendContent("S<sub>3</sub>")
                .build();

        HtmlTableHeader cell24 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "40")
                .appendContent("x̅")
                .build();

        //Second Header Row
        HtmlRow row2 = new HtmlRow.Builder()
                .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .appendContent(cell24.appendTag())
                .build();

        //Third Header
        HtmlTableHeader cell31 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "40")
                .appendContent("mm")
                .build();

        HtmlTableHeader cell32 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "40")
                .appendContent("mm")
                .build();

        HtmlTableHeader cell33 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "40")
                .appendContent("mm")
                .build();

        HtmlTableHeader cell34 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "40")
                .appendContent("mm")
                .build();

        HtmlTableHeader cell35 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "45")
                .appendContent("MN/m²")
                .build();

        HtmlTableHeader cell36 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "45")
                .appendContent("MN/m²")
                .build();

        HtmlTableHeader cell37 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeaderUnits")
                .appendAttribute("width", "45")
                .appendContent("MN/m²")
                .build();

        //Third Header Row
        HtmlRow row3 = new HtmlRow.Builder()
                .appendContent(cell31.appendTag())
                .appendContent(cell32.appendTag())
                .appendContent(cell33.appendTag())
                .appendContent(cell34.appendTag())
                .appendContent(cell35.appendTag())
                .appendContent(cell36.appendTag())
                .appendContent(cell37.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag())
                .append(row3.appendTag());

        return stringBuilder.toString();
    }

    @Override
    public void constructTable(final ExplorationSite site)
    {

    }

    @Override
    HtmlTable constructAndGetTableObject()
    {
        return new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", HTML_BASIC_TABLE_STYLE)
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(constructAndGetTableHeader())
                .build();
    }

    @Override
    public String getExportFileName()
    {
        return "Anlage-LP";
    }
}
