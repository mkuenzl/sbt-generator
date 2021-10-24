package sbt.automization.view.element;

import sbt.automization.view.ViewConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSetCheckBox extends CustomCheckBox
{
	private String dataSet = "";
	private final List<JCheckBox> relatedCheckBoxes = new ArrayList<>();
	
	public DataSetCheckBox(String text, Rectangle position)
	{
		super(text, position);
		addListener();
	}
	
	private void addListener()
	{
		this.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(final ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					for (JCheckBox relatedCheckBox : relatedCheckBoxes)
					{
						relatedCheckBox.setSelected(false);
					}
					ViewConstant.dataSet = dataSet;
				} else
				{
					ViewConstant.dataSet = "";
				}
			}
		});
	}
	
	public void setDataSet(String name)
	{
		this.dataSet = name;
	}
	
	public void setRelatedCheckBoxes(JCheckBox... checkBoxes)
	{
		this.relatedCheckBoxes.addAll(Arrays.asList(checkBoxes));
	}
}
