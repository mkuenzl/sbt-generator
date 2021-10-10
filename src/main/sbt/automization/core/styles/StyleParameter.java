package sbt.automization.core.styles;

import sbt.automization.core.format.text.TextFormatter;

public final class StyleParameter
{
	private String rowClass;

	private String legendCellClass;

	private String headerCellClass;
	private String headerCellWidth;

	private String normalCellClass;
	private String normalCellWidth;

	private String unitCellClass;

	private TextFormatter textFormatter;

	public StyleParameter()
	{

	}

	public String getRowClass()
	{
		return rowClass;
	}

	StyleParameter setRowClass(String rowClass)
	{
		this.rowClass = rowClass;
		return this;
	}

	public String getLegendCellClass()
	{
		return legendCellClass;
	}

	StyleParameter setLegendCellClass(String legendCellClass)
	{
		this.legendCellClass = legendCellClass;
		return this;
	}

	public String getHeaderCellClass()
	{
		return headerCellClass;
	}

	StyleParameter setHeaderCellClass(String headerCellClass)
	{
		this.headerCellClass = headerCellClass;
		return this;
	}

	public String getNormalCellClass()
	{
		return normalCellClass;
	}

	StyleParameter setNormalCellClass(String normalCellClass)
	{
		this.normalCellClass = normalCellClass;
		return this;
	}

	public String getUnitCellClass()
	{
		return unitCellClass;
	}

	StyleParameter setUnitCellClass(String unitCellClass)
	{
		this.unitCellClass = unitCellClass;
		return this;
	}

	public String getNormalCellWidth()
	{
		return "width:".concat(normalCellWidth).concat("cm");
	}

	public double getNormalCellWidthAsDouble()
	{
		return Double.parseDouble(normalCellWidth);
	}

	StyleParameter setNormalCellWidth(String normalCellWidth)
	{
		this.normalCellWidth = normalCellWidth;
		return this;
	}

	public String getHeaderCellWidth()
	{
		return "width:".concat(headerCellWidth).concat("cm");
	}

	public double getHeaderCellWidthAsDouble()
	{
		return Double.parseDouble(headerCellWidth);
	}

	StyleParameter setHeaderCellWidth(String headerCellWidth)
	{
		this.headerCellWidth = headerCellWidth;
		return this;
	}

	public TextFormatter getTextFormatter()
	{
		return textFormatter;
	}

	StyleParameter setTextFormatter(TextFormatter textFormatter)
	{
		this.textFormatter = textFormatter;
		return this;
	}
}
