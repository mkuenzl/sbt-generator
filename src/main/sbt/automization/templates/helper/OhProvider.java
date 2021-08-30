package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.ProbeKey;
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
}