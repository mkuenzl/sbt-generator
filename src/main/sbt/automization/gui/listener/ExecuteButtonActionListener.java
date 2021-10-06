package sbt.automization.gui.listener;

import sbt.automization.data.Examination;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.gui.ErrorPopup;
import sbt.automization.gui.StrategyStorage;
import sbt.automization.gui.TableToolVisualInterface;
import sbt.automization.templates.HtmlTemplate;
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
			for (HtmlTemplate strategy : StrategyStorage.getInstance().getStrategies())
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
			ErrorPopup.showMessage("Es gab einen Fehler bei dem Lesen der CSV. " + "\n"
					+ exception);
		}
	}
}
