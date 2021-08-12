package sbt.automization.gui;

import sbt.automization.data.TableInformation;
import sbt.automization.data.refactoring.Examination;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.templates.IHtmlTable;
import sbt.automization.util.CsvParser;

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
		CsvParser csvParser = new CsvParser(csv);

		try
		{

			List<Map<String, String>> parsedSiteInformation = csvParser.parse();
			Examination examination = new Examination(parsedSiteInformation, csv.getParent());
			for (IHtmlTable strategy : StrategyStorage.getInstance().getStrategies())
			{
				try{
					examination.export(new HtmlTemplateExport(strategy));
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
