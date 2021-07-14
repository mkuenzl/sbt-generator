package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;


public final class ConcreteFactory extends ARowFactory
{
    public ConcreteFactory()
    {
        super("BETON");
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
                    .appendAttribute("width", normalCellWidth)
                    .appendContent(explorationSite.getInformation(InformationTag.SITE_OUTCROP_OB))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }

    public String createMaterialRow(List<ExplorationSite> explorationSites)
    {
        //Zonen Material 1 - Anzahl Schichten
        HtmlRow row = new HtmlRow.Builder()
                .appendAttribute("class", rowClass)
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("width", headerCellWidth)
                        .appendContent("Dicke,")
                        .appendContent(new HtmlText.Builder()
                                .appendAttribute("class", unitCellClass)
                                .appendContent("cm")
                                .build()
                                .appendTag())
                        .build()
                        .appendTag())
                .build();

        for (ExplorationSite explorationSite : explorationSites)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", normalCellWidth)
                    .appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, InformationTag.LAYER_TYPE))
                    .build();

            row.appendContent(cell.appendTag());
        }
        return row.appendTag();
    }

    @Override
    public String createLegendRow(List<ExplorationSite> explorationSites)
    {
        int size = Integer.valueOf(headerCellWidth) + explorationSites.size()* Integer.valueOf(normalCellWidth);

        //Umwelttechnische Merkmale Trennzeile
        HtmlRow rowLegende = new HtmlRow.Builder()
                .appendAttribute("class", rowClass)
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("colspan", String.valueOf(1 + explorationSites.size()))
                        .appendAttribute("width", String.valueOf(size))
                        .appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
                        .build()
                        .appendTag())
                .build();

        return rowLegende.appendTag();
    }
}
