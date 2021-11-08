package sbt.automization.core.styles;

import java.util.ArrayList;
import java.util.List;

public class StyleObject
{
	private final StyleClass cssClass;
	private List<String> parameters;
	
	public StyleObject(StyleClass cssClass)
	{
		this.cssClass = cssClass;
		this.parameters = new ArrayList<>();
	}
	
	public void addParameter(String parameter, String value)
	{
		parameters.add(parameter.concat(":").concat(value));
	}
	
	public void addParameter(List<String> parameters)
	{
		this.parameters.addAll(parameters);
	}
	
	@Override
	public String toString()
	{
		String className = cssClass.toString();
		
		StringBuilder parameterString = new StringBuilder().append("'");
		for (String next : parameters)
		{
			parameterString.append(next)
					.append(";");
		}
		parameterString.append("'");
		
		return "class=".concat(className).concat(" ").concat("style=").concat(parameterString.toString());
	}
}
