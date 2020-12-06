package main.java;

import main.java.export.HtmlTemplateExportStrategy;
import main.java.projekt.Projekt;
import main.java.util.CLI;
import main.java.util.Parser;

import java.io.File;
import java.io.IOException;


public class Main
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("C:\\Users\\Kuenzl\\Desktop\\Datenbank.CSV");
        //File file = new File("/Users/moritzkunzl/Desktop/HTML/ProjektDaten.csv");

        CLI commandLineInterface = new CLI(args);

        Parser parser = new Parser(new File(commandLineInterface.getCsvFilePath()));
        //Parser parser = new Parser(file);

        Projekt projekt = new Projekt(parser.parse());

        projekt.export(new HtmlTemplateExportStrategy(commandLineInterface.getStrategy()));
        //projekt.export(new HtmlTemplateExportStrategy(LPTemplateStrategy.getInstance()));
    }
}
