package sbt;

import sbt.automization.projekt.Projekt;
import sbt.automization.templates.LPTemplateStrategy;
import sbt.automization.util.CLI;
import sbt.automization.export.HtmlTemplateExportStrategy;
import java.io.File;
import java.io.IOException;

import sbt.automization.util.Parser;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("C:\\Users\\Kuenzl\\Desktop\\Datenbank.CSV");
        //File file = new File("/Users/moritzkunzl/Desktop/HTML/ProjektDaten.csv");

        CLI commandLineInterface = new CLI(args);

        //Parser parser = new Parser(new File(commandLineInterface.getCsvFilePath()));
        Parser parser = new Parser(file);

        Projekt projekt = new Projekt(parser.parse());

        //projekt.export(new HtmlTemplateExportStrategy(commandLineInterface.getStrategy()));
        projekt.export(new HtmlTemplateExportStrategy(LPTemplateStrategy.getInstance()));
    }
}
