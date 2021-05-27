package sbt.automization.gui;

import sbt.automization.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CsvButtonActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Util.exportFile("/sbt-excel-template.xlsx");
		} catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
}
