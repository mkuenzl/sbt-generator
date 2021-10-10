package sbt.automization.view.element;

import sbt.automization.core.data.Examination;
import sbt.automization.core.export.HtmlTemplateExport;
import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.core.util.CsvParser;
import sbt.automization.view.ViewParameter;
import sbt.automization.view.popup.ErrorPopup;
import sbt.automization.view.StrategyStorage;
import sbt.automization.view.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

public class BuildTableButton extends CustomButton
{
    public BuildTableButton(String text) {
        super(text);
        addListener();
    }

    public BuildTableButton(String text, Rectangle position) {
        super(text, position);
        addListener();
    }

    private void addListener()
    {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                File csv = new File(ViewParameter.pathComponent.getText());
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
                    System.out.println(exception.getMessage());
                }
            }
        });
    }
}
