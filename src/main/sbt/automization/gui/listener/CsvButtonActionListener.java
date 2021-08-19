package sbt.automization.gui.listener;

import sbt.automization.util.FileExport;

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
			FileExport.copyFileToUserDirectory("/sbt-excel-template.xlsx");
		} catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
}
