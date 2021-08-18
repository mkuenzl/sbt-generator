package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.references.Reference;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.styles.ReportStyle;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;

import java.util.List;

public class InformationRowWithDepth extends RowStrategy
{
	public InformationRowWithDepth(List<DataTable> dataTables, Outcrop outcrop, Reference reference)
	{
		super(dataTables, outcrop, reference);
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
				new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop.toString(), reference)});

		return cell.appendTag();
	}
}
