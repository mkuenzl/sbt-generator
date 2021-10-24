package sbt.automization.view.element;

import sbt.automization.core.data.Examination;
import sbt.automization.core.export.HtmlTemplateExport;
import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.core.util.csv.CsvCreator;
import sbt.automization.core.util.csv.CsvParser;
import sbt.automization.view.ViewConstant;
import sbt.automization.view.popup.ErrorPopup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

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
				CsvParser csvParser = new CsvParser();
				List<Map<String, String>> parsedSiteInformation;
				
				String path = ViewConstant.pathComponent.getText();
				File inputFile = new File(path);
				File fromExcel = null;
				try
				{
					if (path.endsWith(".csv"))
					{
						parsedSiteInformation = csvParser.parse(inputFile);
					} else if (path.endsWith(".xlsx"))
					{
						CsvCreator csvCreator = new CsvCreator();
						fromExcel = csvCreator.createFromExcel(inputFile, ViewConstant.dataSet);
						parsedSiteInformation = csvParser.parse(fromExcel, csvCreator.getDelimiter());
					} else
					{
						return;
					}
					
					Examination examination = new Examination(parsedSiteInformation, inputFile.getParent());
					for (HtmlTemplate strategy : ViewConstant.strategyList)
					{
						try
						{
							examination.export(new HtmlTemplateExport(strategy));
						} catch (Exception exception)
						{
							ErrorPopup.showMessage("Es gab einen Fehler bei der Erstellung der " + strategy.getExportFileName() + "\n"
									+ exception);
						}
					}
				} catch (Exception exception)
				{
					System.out.println(exception.getMessage());
				} finally
				{
					if (fromExcel != null) fromExcel.delete();
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
