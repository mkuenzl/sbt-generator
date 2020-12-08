package sbt;

import sbt.automization.projekt.Projekt;
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
        projekt.export(new HtmlTemplateExportStrategy(commandLineInterface.getStrategy()));
        //projekt.export(new HtmlTemplateExportStrategy(PNTemplateStrategy.getInstance()));
    }
}
