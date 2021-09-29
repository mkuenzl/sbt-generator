package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.styles.StyleParameter;
import sbt.automization.styles.StyleParameterBuilder;
import sbt.automization.templates.helper.strategy.RowConstruction;
import sbt.automization.templates.helper.strategy.RowStrategy;

import java.util.List;

public class RowProvider
{
	private final Outcrop outcrop;
	private StyleParameter styleParameter;
	private List<DataTable> dataTables;

	public RowProvider(Outcrop outcrop)
	{
		this.outcrop = outcrop;
		createStandardStyle();
	}

	public RowProvider(Outcrop outcrop, StyleParameter styleParameter)
	{
		this.outcrop = outcrop;
		this.styleParameter = styleParameter;
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

	private RowStrategy setUp(RowConstruction rowConstruction)
	{
		rowConstruction.setStyle(styleParameter);
		rowConstruction.setOutcrop(outcrop.toString());
		rowConstruction.setTables(dataTables);

		return rowConstruction;
	}

	public String getRowWithProbes(RowConstruction rowConstruction)
	{
		RowStrategy rowStrategy = setUp(rowConstruction);

		return rowStrategy.buildWithProbes();
	}

	public String getRowWithSamples(RowConstruction rowConstruction)
	{
		RowStrategy rowStrategy = setUp(rowConstruction);

		return rowStrategy.buildWithSamples();
	}

	public String getRowWithSamplesCombined(RowConstruction rowConstruction)
	{
		RowStrategy rowStrategy = setUp(rowConstruction);

		return rowStrategy.buildWithSamplesCombined();
	}

	public void setDataTables(List<DataTable> tables)
	{
		this.dataTables = tables;
	}
}
