package sbt.automization.view.element;

import sbt.automization.view.StrategyStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TemplateCheckBox extends CustomCheckBox
{
    public TemplateCheckBox(String text, Rectangle position) {
        super(text, position);
        addListener();
    }

    public TemplateCheckBox(String s, Rectangle position, Color backgroundColor, Color hoverColor) {
        super(s, position, backgroundColor, hoverColor);
        addListener();
    }

    private void addListener()
    {
        this.addItemListener(new ItemListener() {
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
        });
    }

}
