package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlRow;

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
                    .appendContent(dataTable.get(ProbeKey.OUTCROP_GOB))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }
}
