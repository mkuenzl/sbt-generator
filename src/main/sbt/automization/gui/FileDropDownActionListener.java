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

			String itemPath = "/".concat(item).concat(".docx");

			try
			{
				Util.exportFile(itemPath);
			} catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
		}
	}
}
