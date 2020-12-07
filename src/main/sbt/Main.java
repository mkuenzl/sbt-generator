package sbt;

import sbt.automization.projekt.Projekt;
import sbt.automization.templates.*;
import sbt.automization.util.CLI;
import sbt.automization.export.HtmlTemplateExportStrategy;
import java.io.File;
import java.io.IOException;

import sbt.automization.util.Parser;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String csvFilePath = System.getProperty("user.dir").concat(File.separator).concat("datenbank.csv");
        File file = new File(csvFilePath);
        //File file = new File("/Users/moritzkunzl/Desktop/HTML/ProjektDaten.csv");


        CLI commandLineInterface = new CLI(args);
        commandLineInterface.parse();

        //FALL KEIN FILE VORHANDEN
        //Parser parser = new Parser(new File(commandLineInterface.getCsvFilePath()));
        Parser parser = new Parser(file);

        Projekt projekt = new Projekt(parser.parse());

        //FALL KEINE STRATEGY VORHANDEN
        //projekt.export(new HtmlTemplateExportStrategy(commandLineInterface.getStrategy()));
        projekt.export(new HtmlTemplateExportStrategy(PNTemplateStrategy.getInstance()));
    }
}
