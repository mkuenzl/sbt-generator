package sbt;

import sbt.automization.gui.GUI;

public class Main
{
    public static void main(String[] args)
    {
        new GUI();

/*
        CLI commandLineInterface = new CLI(args);
        commandLineInterface.parse();

        FALL KEIN FILE VORHANDEN
        Parser parser = new Parser(commandLineInterface.getCsvFile());
        String x = "C:/Users/Kuenzl/Desktop/datenbank.csv";
        Parser parser = new Parser(new File("C:/Users/Kuenzl/Desktop/datenbank.csv"));

        Parser parser = new Parser(new File("datenbank.csv"));

       TableEngine projekt = new TableEngine(parser.parse());

        //FALL KEINE STRATEGY VORHANDEN
        //projekt.export(new HtmlTemplateExportStrategy(commandLineInterface.getStrategy()));
        projekt.export(new HtmlTemplateExportStrategy(Anlage_RUK_TemplateStrategy.getInstance()));
        projekt.export(new HtmlTemplateExportStrategy(Anlage_LP_TemplateStrategy.getInstance()));
        projekt.export(new HtmlTemplateExportStrategy(Anlage_ERK_TemplateStrategy.getInstance()));
        projekt.export(new HtmlTemplateExportStrategy(Anlage_PN_TemplateStrategy.getInstance()));
*/

    }
}
