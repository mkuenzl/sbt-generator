package sbt.automization.core.styles;

import sbt.automization.core.format.text.TextFormatter;

public class StyleParameterBuilder
{
	private final StyleParameter styleParameter;
	
	public StyleParameterBuilder()
	{
		styleParameter = new StyleParameter();
	}
	
	public StyleParameterBuilder setRowClass(String rowClass)
	{
		styleParameter.setRowClass(rowClass);
		return this;
	}
	
	public StyleParameterBuilder setLegendCellClass(String legendCellClass)
	{
		styleParameter.setLegendCellClass(legendCellClass);
		return this;
	}
	
	public StyleParameterBuilder setHeaderCellClass(String headerCellClass)
	{
		styleParameter.setHeaderCellClass(headerCellClass);
		return this;
	}
	
	
	public StyleParameterBuilder setNormalCellClass(String normalCellClass)
	{
		styleParameter.setNormalCellClass(normalCellClass);
		return this;
	}
	
	public StyleParameterBuilder setUnitCellClass(String unitCellClass)
	{
		styleParameter.setUnitCellClass(unitCellClass);
		return this;
	}
	
	public StyleParameterBuilder setNormalCellWidth(String normalCellWidth)
	{
		styleParameter.setNormalCellWidth(normalCellWidth);
		return this;
	}
	
	
	public StyleParameterBuilder setHeaderCellWidth(String headerCellWidth)
	{
		styleParameter.setHeaderCellWidth(headerCellWidth);
		return this;
	}
	
	public StyleParameterBuilder setTextFormatter(TextFormatter textFormatter)
	{
		styleParameter.setTextFormatter(textFormatter);
		return this;
	}
	
	public StyleParameter build()
	{
		return this.styleParameter;
	}
}
