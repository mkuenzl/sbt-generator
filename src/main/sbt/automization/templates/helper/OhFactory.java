package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.data.Layer;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class OhFactory extends AReportRowFactory
{
    public OhFactory()
    {
        super("OH");
    }

    @Override
    public String createLegendRow(List<ExplorationSite> explorationSites)
    {
        int size = Integer.valueOf(headerCellWidth) + explorationSites.size()* Integer.valueOf(normalCellWidth);

        return null;
    }

    public String createAufschlussRow(List<ExplorationSite> explorationSites)
    {
        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("class", rowClass)
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", headerCellWidth)
                        .appendContent("Aufschlussart")
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite :
                explorationSites)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", normalCellWidth)
                    .appendContent(explorationSite.getInformation(InformationTag.SITE_OUTCROP_UG_OH_BA))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }
}
