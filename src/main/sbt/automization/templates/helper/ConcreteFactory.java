package sbt.automization.templates.helper;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.refactoring.references.RefProbe;
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

    public String createAufschlussRow(List<DataTable> dataTables)
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

        for (DataTable dataTable :
                dataTables)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", normalCellWidth)
                    .appendContent(dataTable.get(RefProbe.OUTCROP_GOB))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }

    public String createMaterialRow(List<DataTable> dataTables)
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

        for (DataTable dataTable : dataTables)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", normalCellWidth)
                    .appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, ReferenceKey.LAYER_TYPE))
                    .build();

            row.appendContent(cell.appendTag());
        }
        return row.appendTag();
    }

    @Override
    public String createLegendRow(List<DataTable> dataTables)
    {
        int size = Integer.valueOf(headerCellWidth) + dataTables.size()* Integer.valueOf(normalCellWidth);

        //Umwelttechnische Merkmale Trennzeile
        HtmlRow rowLegende = new HtmlRow.Builder()
                .appendAttribute("class", rowClass)
                .appendContent(new HtmlCell.Builder()
                        .appendAttribute("class", headerCellClass)
                        .appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
                        .appendAttribute("width", String.valueOf(size))
                        .appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
                        .build()
                        .appendTag())
                .build();

        return rowLegende.appendTag();
    }
}
