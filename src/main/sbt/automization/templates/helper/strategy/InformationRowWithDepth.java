package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.Key;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.data.Outcrop;
import sbt.automization.styles.ReportStyle;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;

import java.util.List;

public class InformationRowWithDepth extends RowStrategy
{
	public InformationRowWithDepth(List<DataTable> dataTables, Outcrop outcrop, Key key)
	{
		super(dataTables, outcrop, key);
	}

	@Override
	void initializeRow()
	{
		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), ReportStyle.HEADER.getStyle(),
						new String[]{"\"Abfallschlüssel,", formatUnit("Unit")})
		});
	}

	@Override
	String createCell(DataTable table)
	{
		HtmlCell cell = HtmlFactory.createCell(ReportStyle.CELL.getStyleClass(), ReportStyle.CELL.getStyle(),
				new String[]{new SamplePrinter().printAttributeOfSamples(table, outcrop.toString(), key)});

		return cell.appendTag();
	}
}
