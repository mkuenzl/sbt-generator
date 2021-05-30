package sbt.automization.gui;

import sbt.automization.TableEngine;
import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.templates.IHtmlTemplate;
import sbt.automization.util.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExecuteButtonActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		File csv = new File(TableToolVisualInterface.textField.getText());
		Parser parser = new Parser(csv);

		TableEngine database = null;

		try
		{
			database = new TableEngine(parser.parse(), csv.getParent());
			for (IHtmlTemplate strategy : StrategyStorage.getInstance().getStrategies())
			{
				database.export(new HtmlTemplateExportStrategy(strategy));
			}
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
