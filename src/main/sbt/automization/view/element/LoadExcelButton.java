package sbt.automization.view.element;

import sbt.automization.view.ViewConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadExcelButton extends CustomButton
{
	public LoadExcelButton(String text)
	{
		super(text);
		addListener();
	}
	
	private void addListener()
	{
		this.addActionListener(new ActionListener()
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
					ViewConstant.pathComponent.setText(path);
				}
			}
		});
	}
	
	public LoadExcelButton(String text, Rectangle position)
	{
		super(text, position);
		addListener();
	}
}