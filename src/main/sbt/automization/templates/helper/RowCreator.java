package sbt.automization.templates.helper;

import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.styles.StyleParameter;
import sbt.automization.styles.StyleParameterBuilder;
import sbt.automization.templates.helper.strategy.RowConstructionStrategy;

public class RowCreator
{
	private final String outcrop;
	private StyleParameter styleParameter;

	public RowCreator(String outcrop)
	{
		this.outcrop = outcrop;
		createStandardStyle();
	}

	public RowCreator(String outcrop, StyleParameter styleParameter)
	{
		this.outcrop = outcrop;
		this.styleParameter = styleParameter;
	}

	private void createStandardStyle()
	{
		this.styleParameter = new StyleParameterBuilder()
				.setRowClass("Normal")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("width:110")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("width:60")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}

	public String getRowWithProbes(RowConstructionStrategy rowConstructionStrategy)
	{
		rowConstructionStrategy.setStyle(styleParameter);
		rowConstructionStrategy.setOutcrop(outcrop);

		return rowConstructionStrategy.buildWithProbes();
	}

	public String getRowWithSamples(RowConstructionStrategy rowConstructionStrategy)
	{
		rowConstructionStrategy.setStyle(styleParameter);
		rowConstructionStrategy.setOutcrop(outcrop);

		return rowConstructionStrategy.buildWithSamples();
	}
}
