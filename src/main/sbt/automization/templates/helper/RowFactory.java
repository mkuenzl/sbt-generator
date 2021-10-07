package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.key.Key;
import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.styles.StyleParameter;
import sbt.automization.styles.StyleParameterBuilder;
import sbt.automization.templates.helper.information.InformationRetrievalStrategy;
import sbt.automization.templates.helper.strategies.CellRow;
import sbt.automization.templates.helper.strategies.CellRowStrategy;
import sbt.automization.util.CheckDataAvailability;

import java.util.List;

public final class RowFactory
{
	private final Outcrop outcrop;
	private CellRowStrategy cellRowStrategy;
	private StyleParameter styleParameter;
	private List<DataTable> dataTables;

	public RowFactory(Outcrop outcrop)
	{
		this.outcrop = outcrop;
		createStandardStyle();
	}

	private void createStandardStyle()
	{
		this.styleParameter = new StyleParameterBuilder()
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("3")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("1.8")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}

	public RowFactory(Outcrop outcrop, StyleParameter styleParameter)
	{
		this.outcrop = outcrop;
		this.styleParameter = styleParameter;
	}

	public String getRowWithDataCheck(HtmlCell header, InformationRetrievalStrategy informationRetrievalStrategy)
	{
		if (checkDataAvailability(informationRetrievalStrategy))
		{
			return getRow(header, informationRetrievalStrategy);
		}

		return "";
	}

	public boolean checkDataAvailability(InformationRetrievalStrategy informationRetrievalStrategy)
	{
		Key dataKey = informationRetrievalStrategy.getInformationKey();

		return CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop.toString(), dataKey);
	}

	public String getRow(HtmlCell header, InformationRetrievalStrategy informationRetrievalStrategy)
	{
		cellRowStrategy.setRetrievalStrategy(informationRetrievalStrategy);
		cellRowStrategy.setStyle(styleParameter);
		informationRetrievalStrategy.setOutcrop(outcrop);

		return build(header);
	}

	private String build(HtmlCell header)
	{
		List<HtmlCell> cells = cellRowStrategy.build(dataTables);

		HtmlRow row = HtmlFactory.createRow(styleParameter.getRowClass());

		row.appendContent(header.appendTag());

		for (HtmlCell cell : cells)
		{
			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public void setDataTables(List<DataTable> tables)
	{
		this.dataTables = tables;
	}

	public void setCellStrategy(CellRow cellRow)
	{
		this.cellRowStrategy = cellRow;
	}

	public void setStyleParameter(StyleParameter styleParameter) { this.styleParameter = styleParameter; }
}
