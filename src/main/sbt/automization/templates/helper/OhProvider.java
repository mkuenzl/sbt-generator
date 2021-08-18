package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.references.RefProbe;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlRow;

import java.util.List;

public final class OhProvider extends RowProvider
{
    public OhProvider()
    {
        super("OH");
    }

    @Override
    public String createLegendRow(List<DataTable> dataTables)
    {
        int size = Integer.valueOf(headerCellWidth) + dataTables.size()* Integer.valueOf(normalCellWidth);

        return null;
    }

    public String createGroundExposureRow(List<DataTable> dataTables)
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

        for (DataTable dataTable :
		        dataTables)
        {
            HtmlCell cell = new HtmlCell.Builder()
                    .appendAttribute("class", normalCellClass)
                    .appendAttribute("width", normalCellWidth)
                    .appendContent(dataTable.get(RefProbe.OUTCROP_UG_OH_BA))
                    .build();

            row.appendContent(cell.appendTag());
        }

        return row.appendTag();
    }
}
