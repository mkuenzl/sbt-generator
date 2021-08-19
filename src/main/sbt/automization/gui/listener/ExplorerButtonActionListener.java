package sbt.automization.gui.listener;

import sbt.automization.gui.TableToolVisualInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExplorerButtonActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser explorer = new JFileChooser();
		explorer.setCurrentDirectory(new File(System.getProperty("user.dir")));

		int response = explorer.showOpenDialog(null); //select file to open

		if (response == JFileChooser.APPROVE_OPTION)
		{
			File file = new File(explorer.getSelectedFile().getAbsolutePath());

			String path = file.toString();
			TableToolVisualInterface.textField.setText(path);
		}
	}
}
