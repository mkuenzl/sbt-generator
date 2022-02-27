package sbt.automization.view.element;

import sbt.automization.core.util.FileUtils;
import sbt.automization.view.ViewConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CreateExcelButton extends CustomButton
{
	public CreateExcelButton(String text)
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
				try
				{
					JFileChooser explorer = new JFileChooser();
					explorer.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					explorer.setCurrentDirectory(new File(System.getProperty("user.dir")));
					
					int response = explorer.showOpenDialog(null); //select file to open
					
					if (response == JFileChooser.APPROVE_OPTION)
					{
						File file = new File(explorer.getSelectedFile().getAbsolutePath());
						
						String path = file.toString();
						//ViewConstant.pathComponent.setText(path);
						
						String fileName = "/datenbank-template.xlsm";
						FileUtils.copyFileTo(fileName, path);
						
						ViewConstant.pathComponent.setText(path.concat(fileName));
					}
					
					
					
//					String fileName = "/datenbank-template.xlsm";
//					FileUtils.copyFileToUserDirectory(fileName);
//
//					ViewConstant.pathComponent.setText(System.getProperty("user.dir").concat(fileName));
				} catch (IOException ioException)
				{
					ioException.printStackTrace();
				}
			}
		});
	}
	
	public CreateExcelButton(String text, Rectangle position)
	{
		super(text, position);
		addListener();
	}
}
