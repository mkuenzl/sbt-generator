package sbt.automization.core.data;

import sbt.automization.core.data.key.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Sample extends AbstractDataTable
{
	private Probe probe;
	private final List<Parameter> parameters = new ArrayList<>();
	
	public Sample(Map<String, String> informationMap)
	{
		super(informationMap);
	}
	
	public Sample()
	{
		super();
	}
	
	public Sample(Sample copyFrom)
	{
		this.probe = copyFrom.probe;
		this.informationMap = new HashMap<>(copyFrom.informationMap);
		this.parameters.addAll(copyFrom.parameters);
	}
	
	public void addParameter(Parameter parameter)
	{
		this.parameters.add(parameter);
	}
	
	@Override
	public int compareTo(DataTable o)
	{
		return 0;
	}
	
	/**
	 * Used to create multiple objects of the same table. Necessary for PN Template.
	 *
	 * @return a new object with the same information
	 * @throws CloneNotSupportedException only if there was a problem creating another map
	 */
	@Override
	public DataTable clone() throws CloneNotSupportedException
	{
		Sample cloned = (Sample) super.clone();
		
		Map<String, String> clonedMap = new HashMap<>(this.informationMap);
		
		cloned.setTable(clonedMap);
		cloned.addListOfParameter(this.getParameters());
		cloned.setProbe(this.probe);
		
		return cloned;
	}
	
	public List<Parameter> getParameters()
	{
		return parameters;
	}
	
	public void addListOfParameter(List<Parameter> parameter)
	{
		this.parameters.addAll(parameter);
	}
	
	@Override
	public Parameter getParameterBy(Key keyID)
	{
		for (Parameter par : parameters)
		{
			String value = this.get(keyID);
			if (par.contains(value)) return par;
		}
		
		return null;
	}
	
	@Override
	public String getParameterValueBy(Key parameterID, Key valueID)
	{
		for (Parameter parameter : parameters)
		{
			String value = this.get(parameterID);
			if (parameter.contains(value))
			{
				return parameter.get(valueID);
			}
		}
		
		return "";
	}
	
	public Probe getProbe()
	{
		return this.probe;
	}
	
	public void setProbe(Probe probe)
	{
		this.probe = probe;
	}
}
