package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.references.Reference;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.styles.ReportStyle;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;

import java.util.List;

public abstract class RowStrategy
{
	protected Reference reference;
	private final List<DataTable> dataTables;
	private HtmlRow row;
	protected Outcrop outcrop;

	public RowStrategy(List<DataTable> dataTables, Outcrop outcrop, Reference reference)
	{
		this.dataTables = dataTables;
		this.outcrop = outcrop;
		this.reference = reference;
	}

	public RowStrategy(List<DataTable> dataTables, Reference reference)
	{
		this.dataTables = dataTables;
		this.outcrop = null;
		this.reference = reference;
	}

	abstract void initializeRow();

	protected String formatUnit(String text)
	{
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", ReportStyle.UNITCELL.getStyleClass())
				.appendContent(text)
				.build()
				.appendTag();

		return formattedUnitText;
	}

	private void createCellForEachTable()
	{
		for (DataTable table : dataTables)
		{
			String cell = createCell(table);
			row.appendContent(cell);
		}
	}

	abstract String createCell(DataTable table);
}
