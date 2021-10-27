package sbt.automization.view.element;

import sbt.automization.core.data.Examination;
import sbt.automization.core.export.HtmlTemplateExport;
import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.core.parser.CsvParser;
import sbt.automization.core.parser.ExcelParser;
import sbt.automization.core.parser.TableParser;
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
				TableParser parser;
				List<Map<String, String>> parsedSiteInformation;
				
				String path = ViewConstant.pathComponent.getText();
				File inputFile = new File(path);
				File fromExcel = null;
				try
				{
					if (path.endsWith(".csv"))
					{
						parser = new CsvParser();
					} else if (path.endsWith(".xlsx"))
					{
						ExcelParser excelParser = new ExcelParser();
						excelParser.setSheetName(ViewConstant.dataSet);
						parser = excelParser;
					} else
					{
						return;
					}
					
					parsedSiteInformation = parser.parse(inputFile);
					
					Examination examination = new Examination(parsedSiteInformation, inputFile.getParent());
					for (HtmlTemplate strategy : ViewConstant.strategyList)
					{
						try
						{
							examination.export(new HtmlTemplateExport(strategy));
						} catch (Exception exception)
						{
//							StringBuilder stringBuilder = new StringBuilder();
//							StackTraceElement[] stackTrace = exception.getStackTrace();
//							for (StackTraceElement stackTraceElement : stackTrace)
//							{
//								stringBuilder.append(stackTraceElement.toString())
//										.append("\n");
//							}
							ErrorPopup.showMessage("Es gab einen Fehler bei der Erstellung der " + strategy.getExportFileName() + "\n"
									+ exception.getMessage());
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
