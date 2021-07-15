package sbt.automization.gui;

import sbt.automization.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FileDropDownActionListener implements ActionListener
{
	JComboBox comboBox;

	public FileDropDownActionListener(JComboBox comboBox){
		super();
		this.comboBox = comboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object selectedItem = comboBox.getSelectedItem();
		if (selectedItem instanceof String)
		{
			String item = (String) selectedItem;

			if ("".equals(item)) return;

			//String itemPath = "/report-templates/".concat(item);

			try
			{
				Util.exportFile("/report-templates/", item);
			} catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
		}
	}
}
