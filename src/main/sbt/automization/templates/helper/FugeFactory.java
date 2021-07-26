package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;

import java.util.List;

public final class FugeFactory extends ARowFactory
{
    public FugeFactory()
    {
        super("FUGE");
    }

    @Override
    public String createLegendRow(List<ExplorationSite> explorationSites)
    {
        int size = Integer.valueOf(headerCellWidth) + explorationSites.size()* Integer.valueOf(normalCellWidth);

        return null;
    }

    public String createAufschlussRow(List<ExplorationSite> explorationSites)
    {
        //Erkundungsstellen Aufschlussart
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
                    .appendAttribute("width", normalCellClass)
                    .appendContent(explorationSite.getInformation(ReferenceKey.SITE_OUTCROP_OB))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }
}
