package sbt.automization.templates.helper;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;

import java.util.List;

public final class FugeProvider extends RowProvider
{
    public FugeProvider()
    {
        super("FUGE");
    }

    @Override
    public String createLegendRow(List<DataTable> dataTables)
    {
        int size = Integer.valueOf(headerCellWidth) + dataTables.size()* Integer.valueOf(normalCellWidth);

        return null;
    }

    public String createGroundExposureRow(List<DataTable> dataTables)
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
                    .appendAttribute("width", normalCellClass)
                    .appendContent(dataTable.get(RefProbe.OUTCROP_GOB))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }
}
