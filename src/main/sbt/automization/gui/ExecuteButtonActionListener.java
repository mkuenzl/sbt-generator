package sbt.automization.gui;

import sbt.automization.data.TableInformation;
import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.templates.IHtmlTemplate;
import sbt.automization.util.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ExecuteButtonActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		File csv = new File(TableToolVisualInterface.textField.getText());
		Parser parser = new Parser(csv);

		try
		{

			List<Map<String, String>> parsedSiteInformation = parser.parse();
			TableInformation tableInformation = new TableInformation(parsedSiteInformation, csv.getParent());
			for (IHtmlTemplate strategy : StrategyStorage.getInstance().getStrategies())
			{
				try{
					tableInformation.export(new HtmlTemplateExportStrategy(strategy));
				} catch (Exception exception)
				{
					ErrorPopup.showErrorMessage("Es gab einen Fehler bei der Erstellung der " + strategy.getExportFileName() + "\n"
					                           + exception.toString());
				}
			}
		} catch (Exception exception)
		{
			ErrorPopup.showErrorMessage("Es gab einen Fehler bei dem Lesen der CSV. " + "\n"
					+ exception.toString());
		}
	}
}
