package sbt.automization.view.element;

import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.view.ViewConstant;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TemplateCheckBox extends CustomCheckBox
{
	private HtmlTemplate strategy;
	
	public TemplateCheckBox(String text, Rectangle position, HtmlTemplate strategy)
	{
		super(text, position);
		addListener();
		this.strategy = strategy;
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
					ViewConstant.strategyList.add(strategy);
				} else
				{
					ViewConstant.strategyList.remove(strategy);
				}
			}
		});
	}
	
	public TemplateCheckBox(String s, Rectangle position, Color backgroundColor, Color hoverColor)
	{
		super(s, position, backgroundColor, hoverColor);
		addListener();
	}
	
}
