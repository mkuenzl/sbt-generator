package sbt.automization.view.element;

import sbt.automization.view.StrategyStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DataSetCheckBox extends CustomCheckBox
{
    public DataSetCheckBox(String text, Rectangle position) {
        super(text, position);
        addListener();
    }

    public DataSetCheckBox(String s, Rectangle position, Color backgroundColor, Color hoverColor) {
        super(s, position, backgroundColor, hoverColor);
        addListener();
    }

    private void addListener() {
        this.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //TODO: set specific data variable
                } else {
                    //TODO: set specific data variable
                }
            }
        });
    }
}
