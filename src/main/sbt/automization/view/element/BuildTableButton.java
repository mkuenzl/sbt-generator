package sbt.automization.view.element;

import sbt.automization.core.ProjectEngine;
import sbt.automization.core.export.HtmlExport;
import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.view.ViewConstant;
import sbt.automization.view.popup.ErrorPopup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BuildTableButton extends CustomButton
{
	public BuildTableButton(String text)
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
				ProjectEngine projectEngine = new ProjectEngine();
				File inputFile = new File(ViewConstant.pathComponent.getText());
				projectEngine.retrieveDataFrom(inputFile, ViewConstant.dataSet);
				
				for (HtmlTemplate strategy : ViewConstant.strategyList)
				{
					try
					{
						projectEngine.export(new HtmlExport(strategy), inputFile.getParent());
					}
					catch (Exception exception)
					{
						ErrorPopup.showMessage("Es gab einen Fehler bei der Erstellung der " + strategy.getExportFileName() + "\n"
								+ exception.getMessage());
					}
				}
				
			}
		});
	}
	
	public BuildTableButton(String text, Rectangle position)
	{
		super(text, position);
		addListener();
	}
}
