package sbt.automization.gui.listener;

import sbt.automization.util.FileExport;

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
				FileExport.copyFileTo(item, "/report-templates/");
			} catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
		}
	}
}