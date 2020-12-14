package sbt;

import sbt.automization.projekt.Projekt;
import sbt.automization.templates.*;
import sbt.automization.util.CLI;
import sbt.automization.export.HtmlTemplateExportStrategy;

import java.io.IOException;

import sbt.automization.util.Parser;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        CLI commandLineInterface = new CLI(args);
        commandLineInterface.parse();

        //FALL KEIN FILE VORHANDEN
        Parser parser = new Parser(commandLineInterface.getCsvFile());
        //Parser parser = new Parser(file);

        Projekt projekt = new Projekt(parser.parse());

        //FALL KEINE STRATEGY VORHANDEN
        //projekt.export(new HtmlTemplateExportStrategy(commandLineInterface.getStrategy()));
        projekt.export(new HtmlTemplateExportStrategy(Anlage_RUK_TemplateStrategy.getInstance()));
        projekt.export(new HtmlTemplateExportStrategy(Anlage_LP_TemplateStrategy.getInstance()));
        projekt.export(new HtmlTemplateExportStrategy(Anlage_ERK_TemplateStrategy.getInstance()));
        projekt.export(new HtmlTemplateExportStrategy(Anlage_PN_TemplateStrategy.getInstance()));

    }
}
