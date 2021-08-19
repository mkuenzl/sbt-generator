package sbt.automization.gui.listener;

import sbt.automization.gui.StrategyStorage;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBoxItemListener implements ItemListener
{
	@Override
	public void itemStateChanged(final ItemEvent e)
	{
		if (e.getStateChange() == ItemEvent.SELECTED)
		{
			//System.out.println(((JCheckBox) e.getItem()).getText() + "- selected");

			StrategyStorage.getInstance().addStrategy(((JCheckBox) e.getItem()).getText());
		} else
		{
			StrategyStorage.getInstance().removeStrategy(((JCheckBox) e.getItem()).getText());

			//System.out.println(((JCheckBox) e.getItem()).getText() + "- unselected");
		}
	}
}
