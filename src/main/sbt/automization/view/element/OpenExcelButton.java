package sbt.automization.view.element;

import sbt.automization.view.ViewConstant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OpenExcelButton extends CustomButton
{
	public OpenExcelButton(String text)
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
				//TODO: open excel, add error case, add file path
				try
				{
					Desktop.getDesktop().open(new File(ViewConstant.pathComponent.getText()));
				} catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		});
	}
	
	public OpenExcelButton(String text, Rectangle position)
	{
		super(text, position);
		addListener();
	}
}